package com.bootdo.project.dao;

import com.bootdo.project.domain.ProjectProcessDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-06-07 14:30:09
 */
@Mapper
public interface ProjectProcessDao {

	ProjectProcessDO get(Integer id);
	
	List<ProjectProcessDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProjectProcessDO process);
	
	int update(ProjectProcessDO process);
	
	int remove(Integer Id);
	
	int batchRemove(Integer[] ids);
}
