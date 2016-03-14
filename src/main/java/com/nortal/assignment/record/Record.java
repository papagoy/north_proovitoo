package com.nortal.assignment.record;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nortal.assignment.common.CustomDateSerializer;
import com.nortal.assignment.employee.Employee;
import com.nortal.assignment.project.Project;

/**
 * 
 * @author Priit Liivak
 * 
 */
public class Record {

  private Long id;
  private List<Employee> employees;
  private List<Project> projects;
  @JsonSerialize(using = CustomDateSerializer.class)
  private Date date;
  private RecordType type;
  private String content;

  /**
   * @return Single String of comma separated employee names ordered by salary
   */
  public String getEmployeeNames() {
    // TODO: Create a string with all employee names
    return "";
  }
  
  /**
   * @return Single String of comma separated project names ordered alphabetically
   */
  public String getProjectNames() {
    // TODO: Create a string with all project names
    return "";
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  public List<Project> getProjects() {
    return projects;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public RecordType getType() {
    return type;
  }

  public void setType(RecordType type) {
    this.type = type;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getTypeString() {
    return type == null ? null : type.name();
  }

  public void setTypeString(String typeString) {
    if (StringUtils.isBlank(typeString))
      type = null;
    else
      type = RecordType.valueOf(typeString);
  }

}
