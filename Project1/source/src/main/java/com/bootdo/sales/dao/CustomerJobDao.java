package com.bootdo.sales.dao;

import com.bootdo.common.domain.DictDO;
import com.bootdo.sales.domain.CustomerJobDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 客户组织机构_岗位
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-12-12 14:07:19
 */
@Mapper
public interface CustomerJobDao {

	CustomerJobDO get(String customerJobId);
	
	List<CustomerJobDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CustomerJobDO customerJob);
	
	List<DictDO> listDic();
	
	int update(CustomerJobDO customerJob);
	
	int remove(String Customer_Job_ID);
	
	int batchRemove(String[] customerJobIds);
}
