package com.bootdo.sales.service;

import com.bootdo.sales.domain.ProjectConsultationDO;

import java.util.List;
import java.util.Map;

/**
 * 客户项目咨询信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-27 11:53:50
 */
public interface ProjectConsultationService {
	
	ProjectConsultationDO get(String consultationId);
	
	List<ProjectConsultationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProjectConsultationDO projectConsultation);
	
	int update(ProjectConsultationDO projectConsultation);
	
	int remove(String consultationId);
	
	int batchRemove(String[] consultationIds);
}
