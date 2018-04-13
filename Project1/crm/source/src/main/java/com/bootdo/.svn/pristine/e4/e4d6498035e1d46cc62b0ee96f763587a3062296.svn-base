package com.bootdo.sales.dao;

import com.bootdo.sales.domain.CustomerDeptDO;

import java.util.List;
import java.util.Map;

import com.bootdo.system.domain.DeptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户组织机构_部门
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-12-20 10:21:11
 */
@Mapper
public interface CustomerDeptDao {

	CustomerDeptDO get(String customerDeptId);
	
	List<CustomerDeptDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CustomerDeptDO customerDept);
	
	int update(CustomerDeptDO customerDept);
	
	int remove(String Customer_Dept_ID);
	
	int batchRemove(String[] customerDeptIds);

	List<CustomerDeptDO> customerList(Map<String,Object> map);

}
