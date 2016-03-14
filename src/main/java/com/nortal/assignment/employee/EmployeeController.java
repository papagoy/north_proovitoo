package com.nortal.assignment.employee;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller that manages Employee entries.
 */
@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

  @Resource
  private EmployeeService employeeService;

  @RequestMapping(method = RequestMethod.GET)
  public String getConfigView() {
    return "employee";
  }

  @RequestMapping(value = "data", produces = { "application/json" }, method = RequestMethod.GET)
  @ResponseBody
  public List<Employee> getTeamsData(Model model) {
    return employeeService.getListData();
  }

  @RequestMapping(value = "save", method = RequestMethod.POST)
  public void saveEmployee(@RequestBody final Employee employee) {
    employeeService.save(employee);
  }

  @RequestMapping(value = "delete", method = RequestMethod.POST)
  public void deleteEmployee(@RequestBody final Employee employee) {
    employeeService.delete(employee.getId());
  }

}
