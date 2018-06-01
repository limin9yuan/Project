package com.bootdo.timesheet.service;

import java.util.List;


import com.bootdo.common.domain.DictDO;
import com.bootdo.project.domain.ProjectDO;
import com.bootdo.timesheet.domain.TimesheetDO;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * 工时信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-14 17:52:48
 */
public interface TimesheetService {

	TimesheetDO view(String timesheetId);

	TimesheetDO get(String timesheetId);
	List<TimesheetDO> list(Map<String, Object> map);
	List<TimesheetDO> listcount(Map<String, Object> map);
	List<TimesheetDO> approvelist(Map<String, Object> map);
	int count(Map<String, Object> map);
	int listcountnum(Map<String, Object> map);
	int save(TimesheetDO timesheet);
	int save1(TimesheetDO timesheet);
	public String getnewtimesheetId(TimesheetDO timesheet);
	int update(TimesheetDO timesheet);
	
	int remove(String timesheetId);
	
	int batchRemove(String[] timesheetIds);

	ProjectDO getProjectId(String projectId);
	List<DictDO> listDic();
	int formUpdate(TimesheetDO timesheet);
}
