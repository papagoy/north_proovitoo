package com.nortal.assignment.employee;

import java.util.List;

/**
 * @author Priit Liivak
 */
public interface EmployeeService {

  List<Employee> getListData();

  void save(Employee employee);

  void delete(Long id);
}
