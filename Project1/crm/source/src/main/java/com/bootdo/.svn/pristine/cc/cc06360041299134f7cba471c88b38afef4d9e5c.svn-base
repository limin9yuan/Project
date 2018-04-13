package com.bootdo.project.service;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.inner.domain.InnerOrgEmployeeDO;
import com.bootdo.project.domain.ProjectDO;
import com.bootdo.sales.domain.SalesProjectDO;
import com.bootdo.system.domain.DeptDO;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

/**
 * 项目信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-28 09:52:05
 */
public interface ProjectService {
	
	ProjectDO get(String projectId);
	
	List<ProjectDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProjectDO project);
	
	int update(ProjectDO project);
	
	int remove(String projectId);
	
	int batchRemove(String[] projectIds);
	
	Tree<ProjectDO> getTree();
 
	List<DictDO> listDic();
	
	Map<String, Object> uploadExcel(File file,long userid) ;
	
    List<ProjectDO> getQuery(Map<String, Object> params);
	
	public void export(String[] titles, ServletOutputStream out, List<ProjectDO> list);
	
}
