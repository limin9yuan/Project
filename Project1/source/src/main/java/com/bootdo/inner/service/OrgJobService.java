package com.bootdo.inner.service;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.inner.domain.InnerOrgEmployeeDO;
import com.bootdo.inner.domain.OrgJobDO;
import com.bootdo.project.domain.ProjectDO;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

/**
 * 内部组织机构_岗位
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-24 13:49:26
 */
public interface OrgJobService {
	
	OrgJobDO get(String jobId);
	
	List<OrgJobDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrgJobDO orgJob);
	
	int update(OrgJobDO orgJob);
	
	int remove(String jobId);
	
	int batchRemove(String[] jobIds);
	
	List<DictDO> listDic();
	
	Map<String, Object> dataImport(File file,long userid);

	List<OrgJobDO> getQuery(Map<String, Object> params);
	
	public void export(String[] titles, ServletOutputStream out, List<OrgJobDO> list);
}
