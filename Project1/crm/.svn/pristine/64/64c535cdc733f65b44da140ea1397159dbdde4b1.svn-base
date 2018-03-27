package com.bootdo.project.dao;

import com.bootdo.common.domain.DictDO;
import com.bootdo.project.domain.ProjectDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 项目信息表
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-28 09:52:05
 */
@Mapper
public interface ProjectDao {

	ProjectDO get(String projectId);
	
	List<ProjectDO> list(Map<String,Object> map);

	
	int count(Map<String,Object> map);
	
	int save(ProjectDO project);
	
	int update(ProjectDO project);
	
	int remove(String Project_ID);
	
	int batchRemove(String[] projectIds);
	
	
	List<DictDO> listDic();
	
	//List<ProjectDO> searchProject(Map<String,Object> map);
}
