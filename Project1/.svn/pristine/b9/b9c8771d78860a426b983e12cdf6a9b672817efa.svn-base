package com.bootdo.project.dao;

import com.bootdo.project.domain.ProjectDeptDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2018-01-24 17:08:45
 */
@Mapper
public interface ProjectDeptDao {

	ProjectDeptDO get(String projectId);
	
	List<ProjectDeptDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProjectDeptDO projectDept);
	
	int update(ProjectDeptDO projectDept);
	
	int remove(String Project_ID);
	
	int batchRemove(String[] projectIds);
}
