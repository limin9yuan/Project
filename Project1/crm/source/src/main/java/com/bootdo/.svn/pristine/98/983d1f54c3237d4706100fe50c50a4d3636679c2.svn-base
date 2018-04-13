package com.bootdo.sales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.sales.dao.ProjectConsultationDao;
import com.bootdo.sales.domain.ProjectConsultationDO;
import com.bootdo.sales.service.ProjectConsultationService;



@Service
public class ProjectConsultationServiceImpl implements ProjectConsultationService {
	@Autowired
	private ProjectConsultationDao projectConsultationDao;
	
	@Override
	public ProjectConsultationDO get(String consultationId){
		return projectConsultationDao.get(consultationId);
	}
	
	@Override
	public List<ProjectConsultationDO> list(Map<String, Object> map){
		return projectConsultationDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return projectConsultationDao.count(map);
	}
	
	@Override
	public int save(ProjectConsultationDO projectConsultation){
		return projectConsultationDao.save(projectConsultation);
	}
	
	@Override
	public int update(ProjectConsultationDO projectConsultation){
		return projectConsultationDao.update(projectConsultation);
	}
	
	@Override
	public int remove(String consultationId){
		return projectConsultationDao.remove(consultationId);
	}
	
	@Override
	public int batchRemove(String[] consultationIds){
		return projectConsultationDao.batchRemove(consultationIds);
	}
	
}
