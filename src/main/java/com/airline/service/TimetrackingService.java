package com.airline.service;


import java.text.ParseException;
import java.util.List;

import com.airline.entity.Timetracking;

public interface TimetrackingService {

	String SaveTimetracking(Timetracking timetracking);
	List<Timetracking>  timetrackingList();
	Timetracking delete(String id);
	Timetracking getTimetrackingById(String id);
	List<Timetracking> findByIsActive();
	List<Timetracking> search(String captainId,String search_from_date,String search_to_date);
	List<Timetracking> searchByCaptainId(String captainId);
	void downloadTimesheetExcel(List<Timetracking> timetrackingList) throws ParseException;
}
