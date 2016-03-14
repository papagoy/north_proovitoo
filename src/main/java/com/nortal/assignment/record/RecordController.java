package com.nortal.assignment.record;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller that manages Record entries.
 */
@Controller
@RequestMapping(value = "/record")
public class RecordController {

  @Resource
  private RecordService recordService;

  @RequestMapping(method = RequestMethod.GET)
  public String getView() {
    return "record";
  }

  @RequestMapping(value = "export", method = RequestMethod.GET)
  public HttpEntity<byte[]> getDataExport(Model model) throws IOException {
    // TODO: Generate an CSV file with all records
    byte[] documentBody = "nothing here yet".getBytes();

    HttpHeaders header = new HttpHeaders();
    header.setContentType(new MediaType("text", "csv"));
    header.set("Content-Disposition", "attachment; filename=report.csv");
    header.setContentLength(documentBody.length);

    return new HttpEntity<byte[]>(documentBody, header);
  }

  @RequestMapping(value = "data", produces = { "application/json" }, method = RequestMethod.GET)
  @ResponseBody
  public List<Record> getData(Model model) {
    return recordService.getListData();
  }

  @RequestMapping(value = "save", method = RequestMethod.POST)
  public void save(@RequestBody final Record record) {
    recordService.save(record);
  }

  @RequestMapping(value = "delete", method = RequestMethod.POST)
  public void delete(@RequestBody final Record record) {
    recordService.delete(record.getId());
  }

}
