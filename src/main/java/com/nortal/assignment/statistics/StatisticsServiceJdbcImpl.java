package com.nortal.assignment.statistics;

import com.nortal.assignment.project.Project;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Priit Liivak
 * 
 */
@Service
public class StatisticsServiceJdbcImpl implements StatisticsService {

  @Resource
  private JdbcTemplate jdbcTemplate;

  private final class ProjectMapper implements RowMapper<Project> {
    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
      Project project = new Project();
      project.setId(rs.getLong("id"));
      project.setProjectName(rs.getString("project_name"));
      return project;
    }
  }

  private final class EmployeeCountMapper implements ResultSetExtractor<Statistics> {
    @Override
    public Statistics extractData(ResultSet rs) throws SQLException, DataAccessException {
      Statistics statistics = new Statistics();
      List<String> labels = new ArrayList<>();
      List<Integer> data = new ArrayList<>();
      while(rs.next()){
          labels.add(rs.getString("project_name"));
          data.add(rs.getInt("record_count"));
      }
      statistics.setLabels(labels);
      statistics.setData(data);
      return statistics;
    }
  }

  private final class SatisfactionMapper implements ResultSetExtractor<Statistics> {
    @Override
    public Statistics extractData(ResultSet rs) throws SQLException, DataAccessException {
      List<String> labels = new ArrayList<>();
      List<Integer> data = new ArrayList<>();
      while(rs.next()){
          try {
              labels.add(rs.getString(0));
              data.add(rs.getInt(2));
          } catch (SQLException e) {}
      }
      Statistics statistics = new Statistics();
      statistics.setLabels(labels);
      statistics.setData(data);
      return statistics;
    }
  }

  private final class EmployeeRecordsMapper implements ResultSetExtractor<List<Statistics>> {
    @Override
    public List<Statistics> extractData(ResultSet rs) throws SQLException, DataAccessException {
      Map<Integer, Statistics> statisticsList = new HashMap<>();
      while(rs.next()){
        Statistics statistics = new Statistics();
        statistics.setTitle(rs.getString("employee_name"));
        statistics.addLabel(rs.getString("type_code"));
        statistics.addData(rs.getInt("type_count"));
        statisticsList.put(rs.getInt("employee_id"), statistics);
      }
      return new ArrayList<>(statisticsList.values());
    }
  }

  @Override
  public Project getProjectWithMostRecords() {
    // Some sql here later when hardcoded values are discovered.
    Project p = new Project();
    p.setProjectName("Washing the car");
    return p;
  }
  
  @Override
  public Project getBestExecutedProject() {
    // Some sql here later when hardcoded values are discovered.
    Project p = new Project();
    p.setProjectName("Washing the car");
    return p;
  }

  @Override
  public Project getWorstExecutedProject() {
    // Some sql here later when hardcoded values are discovered.
    Project p = new Project();
    p.setProjectName("Washing the car");
    return p;
  }

  @Override
  public Statistics getEmployeeCountGroupedByProject() {
    //@formatter:off
    String sql = "SELECT rp.project_id, p.project_name, COUNT(re.record_id) record_count "
                + "FROM record_employee re "
                + "LEFT JOIN record_project rp ON rp.record_id = re.record_id "
                + "LEFT JOIN project p ON p.id = rp.project_id "
                + "GROUP BY rp.project_id, p.project_name";
    //@formatter:on
    return jdbcTemplate.query(sql, new EmployeeCountMapper());
  }

  @Override
  public Statistics getSatisfactionRecordsByProject(int projectId) {
    //@formatter:off
    String sql = "SELECT r.type_code, count(1) as type_count "
                + "FROM record_project rp "
                + "JOIN record r on r.id=rp.record_id where rp.project_id = ? "
                + "GROUP BY r.type_code";
    //@formatter:on
    return jdbcTemplate.query(sql, new SatisfactionMapper(), 1);
  }

  public List<Statistics> getEmployeeRecordsByProject(int projectId) {
    //@formatter:off
    String sql = "SELECT count(1) as type_count, r.type_code, re.employee_id, e.employee_name "
                + "FROM record_project rp "
                + "JOIN record_employee re on re.record_id = rp.record_id "
                + "JOIN employee e on e.id = re.employee_id "
                + "JOIN record r on r.id=rp.record_id where rp.project_id = ? "
                + "GROUP BY re.employee_id, e.employee_name, r.type_code";
    //@formatter:on
    return jdbcTemplate.query(sql, new EmployeeRecordsMapper(), projectId);
  }

}
