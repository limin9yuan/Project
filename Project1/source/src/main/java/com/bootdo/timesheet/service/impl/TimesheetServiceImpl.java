package com.bootdo.timesheet.service.impl;


import com.bootdo.common.domain.DictDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.bootdo.project.domain.ProjectDO;
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
//	@Override
//	public TimesheetDO gets(String projectId){
//		return timesheetDao.get(projectId);
//	}
	
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
	public int save1(TimesheetDO timesheet){
		return timesheetDao.save1(timesheet);
	}



    @Override
	public int update(TimesheetDO timesheet){
		return timesheetDao.update(timesheet);
	}

	//获取自增长主键的值
	@Override
	public String getnewtimesheetId(TimesheetDO timesheet){
		int ret=timesheetDao.save(timesheet);
		String timesheetId=timesheet.getTimesheetId();

		return timesheetId;
	}
	@Override
	public int remove(String timesheetId){
		return timesheetDao.remove(timesheetId);
	}
	
	@Override
	public int batchRemove(String[] timesheetIds){
		return timesheetDao.batchRemove(timesheetIds);
	}
	public ProjectDO getProjectId(String projectId) {
		return timesheetDao.getProjectId(projectId);
	}
 	public List<DictDO> listDic()
	{return timesheetDao.listDic();}



}
