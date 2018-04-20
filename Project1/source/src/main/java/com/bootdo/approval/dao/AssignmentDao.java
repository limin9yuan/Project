package com.bootdo.approval.dao;

import com.bootdo.approval.domain.AssignmentDO;
import com.bootdo.project.domain.ProjectDO;
import java.util.List;
import java.util.Map;


import org.apache.ibatis.annotations.Mapper;

/**
 * 任务委托表
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-01 13:15:13
 */
@Mapper
public interface AssignmentDao {

	AssignmentDO view(String assignmentId);

	AssignmentDO get(String assignmentId);
	
	List<AssignmentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AssignmentDO assignment);
	
	int update(AssignmentDO assignment);
	
	int remove(String Assignment_ID);
	
	int batchRemove(String[] assignmentIds);


	List<AssignmentDO> getProjectId(Map<String,Object> map);
}
