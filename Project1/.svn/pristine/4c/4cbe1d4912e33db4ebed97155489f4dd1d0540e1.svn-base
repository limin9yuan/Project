package com.bootdo.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.project.dao.ProjectDeptDao;
import com.bootdo.project.domain.ProjectDeptDO;
import com.bootdo.project.service.ProjectDeptService;



@Service
public class ProjectDeptServiceImpl implements ProjectDeptService {
	@Autowired
	private ProjectDeptDao projectDeptDao;
	
	@Override
	public ProjectDeptDO get(String projectId){
		return projectDeptDao.get(projectId);
	}
	
	@Override
	public List<ProjectDeptDO> list(Map<String, Object> map){
		return projectDeptDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return projectDeptDao.count(map);
	}
	
	@Override
	public int save(ProjectDeptDO projectDept){
		return projectDeptDao.save(projectDept);
	}
	
	@Override
	public int update(ProjectDeptDO projectDept){
		return projectDeptDao.update(projectDept);
	}
	
	@Override
	public int remove(String projectId){
		return projectDeptDao.remove(projectId);
	}
	
	@Override
	public int batchRemove(String[] projectIds){
		return projectDeptDao.batchRemove(projectIds);
	}
	
}
