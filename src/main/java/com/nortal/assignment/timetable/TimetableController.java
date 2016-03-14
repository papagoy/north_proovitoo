package com.nortal.assignment.timetable;

import com.nortal.assignment.record.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Controller that manages Timetable.
 */
@Controller
@RequestMapping(value = "/timetable")
public class TimetableController {

  private static final Logger LOG = LoggerFactory.getLogger(TimetableController.class);

  @Resource
  private TimetableService timetableService;

  @RequestMapping(method = RequestMethod.GET)
  public String getView() {
        return "timetable";
    }

  @RequestMapping(value = "data", produces = { "application/json" }, method = RequestMethod.GET)
  @ResponseBody
  public List<Timetable> getData(Model model) {
    return timetableService.getListData();
  }

  @RequestMapping(value = "save", method = RequestMethod.POST)
  public void save(@RequestBody final Timetable timetable) {
    timetableService.save(timetable);
  }

  @RequestMapping(value = "delete", method = RequestMethod.POST)
  public void delete(@RequestBody final Timetable timetable) {
    timetableService.delete(timetable.getId());
  }

}
