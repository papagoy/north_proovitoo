package com.nortal.assignment.statistics;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.nortal.assignment.project.Project;

/**
 * Controller that manages Statistics.
 */
@Controller
@RequestMapping(value = "/statistics")
public class StatisticsController {

  private static final Logger LOG = LoggerFactory.getLogger(StatisticsController.class);

  @Resource
  private StatisticsService statisticsService;

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView getView() throws InterruptedException {
    final ModelAndView modelAndView = new ModelAndView("statistics");
    modelAndView.addObject("bestExecutedProject", statisticsService.getBestExecutedProject());
    modelAndView.addObject("projectWithMostRecords", statisticsService.getProjectWithMostRecords());
    modelAndView.addObject("worstExecutedProject", statisticsService.getWorstExecutedProject());
    return modelAndView;
  }

  @RequestMapping(value = "employeeInvolvement", produces = { "application/json" }, method = RequestMethod.GET)
  @ResponseBody
  public Statistics getEmployeeInvolvement() {
    return statisticsService.getEmployeeCountGroupedByProject();
  }

  @RequestMapping(value = "satisfactionRecords/{projectId}", produces = { "application/json" }, method = RequestMethod.GET)
  @ResponseBody
  public Statistics getSatisfactionRecordsData(@PathVariable final int projectId) {
    return statisticsService.getSatisfactionRecordsByProject(projectId);
  }

  @RequestMapping(value = "employeeRecords/{projectId}", produces = { "application/json" }, method = RequestMethod.GET)
  @ResponseBody
  public List<Statistics> getEmployeeRecordsData(@PathVariable final int projectId) {
    return statisticsService.getEmployeeRecordsByProject(projectId);
  }

}
