package com.nortal.assignment.project;

import java.util.List;

/**
 * @author Priit Liivak
 */
public interface ProjectService {

  List<Project> getListData();

  void save(Project project);

  void delete(Long id);

  Project getProjectWithMostRecords();

  Project getWorstExecutedProject();

  Project getBestExecutedProject();
}
