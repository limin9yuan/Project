package com.bootdo.timesheet.service;

import com.bootdo.timesheet.domain.TimesheetDO;

import java.util.List;
import java.util.Map;

/**
 * 工时信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-14 17:52:48
 */
public interface TimesheetService {
	
	TimesheetDO get(String timesheetId);
	
	List<TimesheetDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TimesheetDO timesheet);
	
	int update(TimesheetDO timesheet);
	
	int remove(String timesheetId);
	
	int batchRemove(String[] timesheetIds);
}
