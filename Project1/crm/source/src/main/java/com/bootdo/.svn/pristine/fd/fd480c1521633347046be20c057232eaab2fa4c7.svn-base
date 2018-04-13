package com.bootdo.inner.dao;

import com.bootdo.common.domain.DictDO;
import com.bootdo.inner.domain.OrgJobDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 内部组织机构_岗位
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-24 13:49:26
 */
@Mapper
public interface OrgJobDao {

	OrgJobDO get(String jobId);
	
	List<OrgJobDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OrgJobDO orgJob);
	
	int update(OrgJobDO orgJob);
	
	int remove(String Job_ID);
	
	int batchRemove(String[] jobIds);
	
	List<DictDO> listDic();
}
