package com.bootdo.approval.service.impl;

import com.bootdo.activiti.config.ActivitiConstant;
import com.bootdo.activiti.service.impl.ActTaskServiceImpl;
import com.bootdo.contract.domain.TravelDO;
import com.bootdo.project.domain.ProjectDO;
import com.bootdo.timesheet.domain.TimesheetDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.approval.dao.AssignmentDao;
import com.bootdo.approval.domain.AssignmentDO;
import com.bootdo.approval.service.AssignmentService;



@Service
public class AssignmentServiceImpl implements AssignmentService {
	@Autowired
	private AssignmentDao assignmentDao;

	@Autowired
	private ActTaskServiceImpl actTaskService;
	
	@Override
	public AssignmentDO get(String assignmentId){
		return assignmentDao.get(assignmentId);
	}
	
	@Override
	public List<AssignmentDO> list(Map<String, Object> map){
		return assignmentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return assignmentDao.count(map);
	}
	
	@Override
	public int save(AssignmentDO assignment){
		int ret=assignmentDao.save(assignment);
		/*
		此处获取自增长主键ID，此ID作为流程的businessId
		需注意在SQL语句XML配置文件save中需增加：useGeneratedKeys="true" keyProperty="travelId" 用来获取数据库自增长ID
		<insert id="save" parameterType="com.bootdo.contract.domain.TravelDO" useGeneratedKeys="true" keyProperty="travelId">
		*/
		String assignmentId=assignment.getAssignmentId();
		//添加保存时发起审批流程
		actTaskService.startProcess(ActivitiConstant.ACTIVITI_APPROVAL_ASSIGNMENT[0],
				ActivitiConstant.ACTIVITI_APPROVAL_ASSIGNMENT[1],assignmentId,assignment.getAssignmentId(),new HashMap<String,Object>());

		return ret;
	}
	
	@Override
	public int update(AssignmentDO assignment){
		return assignmentDao.update(assignment);
	}
	
	@Override
	public int remove(String assignmentId){
		return assignmentDao.remove(assignmentId);
	}
	
	@Override
	public int batchRemove(String[] assignmentIds){
		return assignmentDao.batchRemove(assignmentIds);
	}

	//审批处理保存
	@Override
	public int formUpdate(AssignmentDO assignmentDO){
		//流程审批处理
		Map<String,Object> vars = new HashMap<>(16);
		vars.put("taskAction",  assignmentDO.getTaskAction() );
		actTaskService.complete(assignmentDO.getTaskId(),vars);

		return assignmentDao.update(assignmentDO);
	}



	@Override
	public String saveAssignmentInTimesheet(TimesheetDO assignment){
		int ret=assignmentDao.save(assignment);
		String assignmentId=assignment.getAssignmentId();

		return assignmentId;
	}

}
