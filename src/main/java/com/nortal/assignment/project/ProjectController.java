package com.nortal.assignment.project;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller that manages Project entries.
 */
@Controller
@RequestMapping(value = "/project")
public class ProjectController {
  
  private static final Logger LOG = LoggerFactory.getLogger(ProjectController.class);

  @Resource
  private ProjectService projectService;

  @RequestMapping(method = RequestMethod.GET)
  public String getConfigView() {
        return "project";
}

  @RequestMapping(value = "data", produces = { "application/json" }, method = RequestMethod.GET)
  @ResponseBody
  public List<Project> getData(Model model) {
    return projectService.getListData();
  }

  @RequestMapping(value = "save", method = RequestMethod.POST)
  public void save(@RequestBody final Project project) {
    projectService.save(project);
  }

  @RequestMapping(value = "delete", method = RequestMethod.POST)
  public void delete(@RequestBody final Project project) {
    projectService.delete(project.getId());
  }

}
