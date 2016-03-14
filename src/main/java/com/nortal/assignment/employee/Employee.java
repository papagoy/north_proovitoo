package com.nortal.assignment.employee;

import java.math.BigDecimal;

/**
 *
 * @author Priit Liivak
 *
 */
public class Employee {

  private Long id;
  private String employeeName;
  private String kind;
  private BigDecimal salary;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

}
