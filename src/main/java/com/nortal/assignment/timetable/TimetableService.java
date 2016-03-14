package com.nortal.assignment.timetable;

import com.nortal.assignment.record.Record;

import java.util.List;

/**
 * @author Priit Liivak
 */
public interface TimetableService {

  List<Timetable> getListData();

  void save(Timetable timetable);

  void delete(Long id);

}
