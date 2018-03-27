package com.bootdo.timesheet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.timesheet.dao.TimesheetDao;
import com.bootdo.timesheet.domain.TimesheetDO;
import com.bootdo.timesheet.service.TimesheetService;



@Service
public class TimesheetServiceImpl implements TimesheetService {
	@Autowired
	private TimesheetDao timesheetDao;
	
	@Override
	public TimesheetDO get(String timesheetId){
		return timesheetDao.get(timesheetId);
	}
	
	@Override
	public List<TimesheetDO> list(Map<String, Object> map){
		return timesheetDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return timesheetDao.count(map);
	}
	
	@Override
	public int save(TimesheetDO timesheet){
		return timesheetDao.save(timesheet);
	}
	
	@Override
	public int update(TimesheetDO timesheet){
		return timesheetDao.update(timesheet);
	}
	
	@Override
	public int remove(String timesheetId){
		return timesheetDao.remove(timesheetId);
	}
	
	@Override
	public int batchRemove(String[] timesheetIds){
		return timesheetDao.batchRemove(timesheetIds);
	}
	
}
