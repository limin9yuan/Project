package com.bootdo.sales.service;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.inner.domain.InnerOrgEmployeeDO;
import com.bootdo.sales.domain.SalesProjectDO;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * 售前项目信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-16 11:25:36
 */
public interface SalesProjectService {
	
	SalesProjectDO get(String projectId);
	
	List<SalesProjectDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SalesProjectDO salesProject);
	
	int update(SalesProjectDO salesProject);
	
	int remove(String projectId);
	
	int batchRemove(String[] projectIds);
    
    PageUtils selfList(Map<String, Object> map);

	List<DictDO> listAllDic();
	
	List<DictDO> listDic();
	
	Map<String, Object> uploadExcel(File file,long userid) ;
	
	List<SalesProjectDO> getQuery(Map<String, Object> params);
	
	public void export(String[] titles, ServletOutputStream out, List<SalesProjectDO> list);
	
	String getMaxProjectId();
} 
