package com.nortal.assignment.statistics;

import java.util.List;

public class Statistics {

  private String title;
  private List<String> labels;
  private List<Integer> data;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<String> getLabels() {
    return labels;
  }

  public void setLabels(List<String> labels) {
    this.labels = labels;
  }

  public void addLabel(String value) {
    // TODO: Is this ok?
    labels.add(value);
  }

  public void setData(List<Integer> data) {
    this.data = data;
  }

  public void addData(Integer value) {
    // TODO: Is this ok?
    data.add(value);
  }

  public List<Integer> getData() {
    return data;
  }

}
