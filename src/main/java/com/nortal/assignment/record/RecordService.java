package com.nortal.assignment.record;

import java.util.List;

/**
 * @author Priit Liivak
 */
public interface RecordService {

  List<Record> getListData();

  void save(Record record);

  void delete(Long id);
}
