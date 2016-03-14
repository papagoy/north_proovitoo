package com.nortal.assignment.timetable;

import com.nortal.assignment.employee.Employee;
import com.nortal.assignment.project.Project;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Priit Liivak
 */
@Service
public class TimetableServiceJdbcImpl implements TimetableService {

  @Resource
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Timetable> getListData() {
    // TODO Remove hard coded value and get the actual data from DB

    List<Timetable> list = new ArrayList<>();
    Timetable timetable = new Timetable();
    timetable.setId((long) 0);
    timetable.setStartDate(new Date());
    timetable.setEndDate(new Date());

    Employee employee = new Employee();
    employee.setId((long) 0);
    employee.setEmployeeName("Rigby");
    timetable.setEmployee(employee);

    Project project = new Project();
    project.setId((long) 0);
    project.setProjectName("Wash the car");
    timetable.setProject(project);

    list.add(timetable);

    return list;
  }

  @Override
  public void save(final Timetable data) {
    // TODO Do actual save here
  }

  @Override
  public void delete(Long id) {
    String recordSql = "DELETE FROM timetable WHERE id=?";
    jdbcTemplate.update(recordSql, id);
  }

}
