package com.bootdo.approval.service;

import com.bootdo.approval.domain.AssignmentDO;
import com.bootdo.contract.domain.TravelDO;
import com.bootdo.timesheet.domain.TimesheetDO;

import java.util.List;
import java.util.Map;

/**
 * 任务委托表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-01 13:15:13
 */
public interface AssignmentService {
	
	AssignmentDO get(String assignmentId);
	
	List<AssignmentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AssignmentDO assignment);
	
	int update(AssignmentDO assignment);
	
	int remove(String assignmentId);
	
	int batchRemove(String[] assignmentIds);
	int formUpdate(AssignmentDO travel);

	String saveAssignmentInTimesheet(TimesheetDO timesheet);
}
