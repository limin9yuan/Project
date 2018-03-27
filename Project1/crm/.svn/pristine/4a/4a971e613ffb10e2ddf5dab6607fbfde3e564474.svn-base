package com.bootdo.approval.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.approval.dao.AssignmentDao;
import com.bootdo.approval.domain.AssignmentDO;
import com.bootdo.approval.service.AssignmentService;



@Service
public class AssignmentServiceImpl implements AssignmentService {
	@Autowired
	private AssignmentDao assignmentDao;
	
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
		return assignmentDao.save(assignment);
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
	
}
