package com.nortal.assignment.statistics;

import com.nortal.assignment.project.Project;

import java.util.List;

/**
 * @author Priit Liivak
 */
public interface StatisticsService {

  Project getProjectWithMostRecords();

  Project getWorstExecutedProject();

  Project getBestExecutedProject();

  Statistics getSatisfactionRecordsByProject(int projectId);

  Statistics getEmployeeCountGroupedByProject();

  List<Statistics> getEmployeeRecordsByProject(int projectId);

}
