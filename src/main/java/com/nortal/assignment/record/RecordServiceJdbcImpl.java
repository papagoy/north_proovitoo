package com.nortal.assignment.record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.nortal.assignment.common.jdbc.ArgPreparedStatementSetter;
import com.nortal.assignment.employee.Employee;
import com.nortal.assignment.project.Project;

/**
 * @author Priit Liivak
 * 
 */
@Service
public class RecordServiceJdbcImpl implements RecordService {

  @Resource
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Record> getListData() {
    List<Record> records = getRecords();

    if (CollectionUtils.isNotEmpty(records)) {
      Map<Long, List<Employee>> recordEmployees = getRecordEmployees();
      Map<Long, List<Project>> recordProjects = getRecordProjects();

      for (Record record : records) {
        record.setEmployees(recordEmployees.get(record.getId()));
        record.setProjects(recordProjects.get(record.getId()));
      }
    }

    return records;
  }

  private List<Record> getRecords() {
    String sql = "select r.id, r.type_code, r.record_date, r.content from RECORD r";

    List<Record> records = jdbcTemplate.query(sql, new RowMapper<Record>() {

      @Override
      public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
        Record record = new Record();
        record.setId(rs.getLong("id"));
        String typeString = rs.getString("type_code");
        record.setType(RecordType.valueOf(typeString));
        record.setDate(rs.getDate("record_date"));
        record.setContent(rs.getString("content"));

        return record;
      }
    });
    return records;
  }

  private Map<Long, List<Employee>> getRecordEmployees() {
    String sql = "select re.record_id, re.employee_id, e.employee_name, e.salary from record_employee re join employee e on re.employee_id = e.id";

    try {
      return jdbcTemplate.queryForObject(sql, new RowMapper<Map<Long, List<Employee>>>() {

        Map<Long, List<Employee>> result = new HashMap<Long, List<Employee>>();

        @Override
        public Map<Long, List<Employee>> mapRow(ResultSet rs, int rowNum) throws SQLException {
          do {
            Long recordId = rs.getLong("record_id");
            List<Employee> employees = result.get(recordId);
            if (employees == null) {
              employees = new ArrayList<>();
              result.put(recordId, employees);
            }

            Employee employee = new Employee();
            employee.setId(rs.getLong("employee_id"));
            employee.setEmployeeName(rs.getString("employee_name"));
            employee.setSalary(rs.getBigDecimal("salary"));
            employees.add(employee);

          } while (rs.next());

          return result;
        }
      });
    } catch (EmptyResultDataAccessException e) {
      return Collections.emptyMap();
    }

  }

  private Map<Long, List<Project>> getRecordProjects() {
    String sql = "select rp.record_id, rp.project_id, p.project_name from record_project rp join project p on rp.project_id = p.id";

    return jdbcTemplate.queryForObject(sql, new RowMapper<Map<Long, List<Project>>>() {

      Map<Long, List<Project>> result = new HashMap<Long, List<Project>>();

      @Override
      public Map<Long, List<Project>> mapRow(ResultSet rs, int rowNum) throws SQLException {
        do {
          Long recordId = rs.getLong("record_id");
          List<Project> projects = result.get(recordId);
          if (projects == null) {
            projects = new ArrayList<>();
            result.put(recordId, projects);
          }

          Project project = new Project();
          project.setId(rs.getLong("project_id"));
          project.setProjectName(rs.getString("project_name"));
          projects.add(project);

        } while (rs.next());

        return result;
      }
    });

  }

  @Override
  public void save(final Record record) {
    Long recordId;

    if (record.getId() == null) {
      recordId = insertRecord(record);
    } else {
      recordId = record.getId();

      String sql = "update record set type_code=?, record_date=?, content=? where id= ?";
      Object[] args = new Object[] { record.getType().name(), record.getDate(), record.getContent(), recordId };
      jdbcTemplate.update(sql, args);
    }

    // It is easier to delete record dependencies and insert them again
    deleteRecordEmployees(recordId);
    deleteRecordProjects(recordId);

    insertRecordEmployees(recordId, record.getEmployees());
    insertRecordProjects(recordId, record.getProjects());
  }

  private void insertRecordProjects(Long recordId, List<Project> projects) {
    Collection<Long> projectIds = CollectionUtils.collect(projects, new Transformer<Project, Long>() {
      @Override
      public Long transform(Project input) {
        return input.getId();
      }
    });

    insertRecordConnections(recordId, projectIds, "record_project", "project_id");
  }

  private void insertRecordEmployees(Long recordId, List<Employee> employees) {
    Collection<Long> employeeIds = CollectionUtils.collect(employees, new Transformer<Employee, Long>() {
      @Override
      public Long transform(Employee input) {
        return input.getId();
      }
    });
    insertRecordConnections(recordId, employeeIds, "record_employee", "employee_id");
  }

  private void insertRecordConnections(Long recordId, Collection<Long> recordConnection, String connectionTableName, String connectionColumnName) {
    if(CollectionUtils.isEmpty(recordConnection)) {
      return;
    }
    String sql = "insert into " + connectionTableName + " (record_id, " + connectionColumnName + ") values (?,?)";
    List<Object[]> args = new ArrayList<>();
    for (Long connectionId : recordConnection) {
      args.add(new Object[] { recordId, connectionId });
    }
    jdbcTemplate.batchUpdate(sql, args);
  }

  private Long insertRecord(final Record record) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(new PreparedStatementCreator() {
      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

        Object[] args = new Object[] { record.getType().name(), record.getDate(), record.getContent() };
        String sql = "insert into record (type_code, record_date, content) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[] { "ID" });
        ArgPreparedStatementSetter.setValues(preparedStatement, args, 1);
        return preparedStatement;
      }
    }, keyHolder);

    return keyHolder.getKey().longValue();
  }

  @Override
  public void delete(Long id) {
    deleteRecordEmployees(id);
    deleteRecordProjects(id);

    String recordSql = "delete from record where id=?";
    jdbcTemplate.update(recordSql, id);
  }

  private void deleteRecordProjects(Long id) {
    String recordProjectSql = "delete from record_project where record_id=?";
    jdbcTemplate.update(recordProjectSql, id);
  }

  private void deleteRecordEmployees(Long id) {
    String recordEmployeeSql = "delete from record_employee where record_id=?";
    jdbcTemplate.update(recordEmployeeSql, id);
  }

}
