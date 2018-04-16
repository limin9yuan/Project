package com.bootdo.inner.dao;

import com.bootdo.common.domain.DictDO;
import com.bootdo.inner.domain.InnerOrgEmployeeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 内部组织机构_员工表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-22 13:27:13
 */
@Mapper
public interface InnerOrgEmployeeDao {

	InnerOrgEmployeeDO get(String employeeId);
	
	List<InnerOrgEmployeeDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(InnerOrgEmployeeDO innerOrgEmployee);
	
	int update(InnerOrgEmployeeDO innerOrgEmployee);
	
	int remove(String Employee_ID);
	
	int batchRemove(String[] employeeIds);
	
	List<DictDO> listDic();
}
