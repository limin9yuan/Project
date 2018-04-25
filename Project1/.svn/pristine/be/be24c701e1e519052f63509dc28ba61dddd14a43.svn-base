package com.bootdo.approval.service.impl;

import com.bootdo.activiti.config.ActivitiConstant;
import com.bootdo.activiti.service.impl.ActTaskServiceImpl;
import com.bootdo.contract.domain.TravelDO;
import com.bootdo.project.domain.ProjectDO;
import com.bootdo.timesheet.domain.TimesheetDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
   public AssignmentDO view(String assignmentId){
        return assignmentDao.view(assignmentId);
    }

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

	//审批处理保存
	@Override
	public int formUpdate(AssignmentDO assignment){
       //流程审批处理
       Map<String,Object> vars = new HashMap<>(16);
       vars.put("taskAction",  assignment.getTaskAction() );
       actTaskService.complete(assignment.getTaskId(),assignment.getProcessInstanceId(),
               assignment.getTaskComment(),"",vars);
		//判断流程是否结束
		if(actTaskService.isProcessInstanceFinish(assignment.getProcessInstanceId())){
			assignment.setAssignmentApprovalStatus("1");
			assignment.setAssignmentApprovalTime(new Date());
		}else{
			assignment.setAssignmentApprovalStatus("0");
		}

       return assignmentDao.update(assignment);
	}
	
	@Override
	public int save(AssignmentDO assignment){
       int ret = assignmentDao.save(assignment);

       String assignmentId=assignment.getAssignmentId();
       //流程标题，每个业务根据自己特点，体现主要信息
       String title="";
       title=assignment.getAssignmentId()+"-"+assignment.getAssignmentDept();
       //添加保存时发起审批流程
       String PROCESS_INSTANCE_ID=actTaskService.startProcess(ActivitiConstant.ACTIVITI_APPROVAL_ASSIGNMENT[0],
               ActivitiConstant.ACTIVITI_APPROVAL_ASSIGNMENT[1],assignmentId,title,new HashMap<String,Object>());
       //更新流程实例ID到业务表
       assignment.setProcessInstanceId(PROCESS_INSTANCE_ID);
       assignmentDao.update(assignment);
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

	@Override
	public String saveAssignmentInTimesheet(TimesheetDO assignment){
		int ret=assignmentDao.save(assignment);
		String assignmentId=assignment.getAssignmentId();

		return assignmentId;
	}

}
