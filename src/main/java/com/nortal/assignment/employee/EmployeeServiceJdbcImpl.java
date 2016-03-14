package com.nortal.assignment.employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

/**
 * @author Priit Liivak
 * 
 */
@Service
public class EmployeeServiceJdbcImpl implements EmployeeService {

  @Resource
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Employee> getListData() {
    String sql = "select id, employee_name, kind, salary from employee";

    return jdbcTemplate.query(sql, new EmployeeRowMapper());
  }

  @Override
  public void save(Employee employee) {
    String sql;
    List<Object> args = new ArrayList<>();
    args.add(employee.getEmployeeName());
    args.add(employee.getKind());
    args.add(employee.getSalary());

    if (employee.getId() == null) {
      sql = "insert into employee (employee_name, kind, salary) values (?,?,?)";
    } else {
      sql = "update employee set employee_name=?, kind=?, salary=? where id= ?";
      args.add(employee.getId());
    }

    jdbcTemplate.update(sql, args.toArray());
  }

  @Override
  public void delete(Long employeeId) {
    String sql = "delete from employee where id=?";
    jdbcTemplate.update(sql, employeeId);
  }

  public final class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
      Employee employee = new Employee();
      employee.setId(rs.getLong("id"));
      employee.setEmployeeName(rs.getString("employee_name"));
      employee.setKind(rs.getString("kind"));
      employee.setSalary(rs.getBigDecimal("salary"));

      return employee;
    }
  }

}
