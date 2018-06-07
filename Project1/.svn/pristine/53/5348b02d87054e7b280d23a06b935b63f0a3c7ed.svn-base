package com.bootdo.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.project.dao.ProjectProcessDao;
import com.bootdo.project.domain.ProjectProcessDO;
import com.bootdo.project.service.ProjectProcessService;



@Service
public class ProjectProjectProcessServiceImpl implements ProjectProcessService {
	@Autowired
	private ProjectProcessDao projectProcessDao;
	
	@Override
	public ProjectProcessDO get(Integer id){
		return projectProcessDao.get(id);
	}
	
	@Override
	public List<ProjectProcessDO> list(Map<String, Object> map){
		return projectProcessDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return projectProcessDao.count(map);
	}
	
	@Override
	public int save(ProjectProcessDO process){
		return projectProcessDao.save(process);
	}
	
	@Override
	public int update(ProjectProcessDO process){
		return projectProcessDao.update(process);
	}
	
	@Override
	public int remove(Integer id){
		return projectProcessDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return projectProcessDao.batchRemove(ids);
	}
	
}
