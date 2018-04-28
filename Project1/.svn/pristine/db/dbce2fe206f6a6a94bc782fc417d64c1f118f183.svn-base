package com.bootdo.sales.service;

import com.bootdo.common.domain.Tree;
import com.bootdo.sales.domain.CompanyCustomerDO;
import com.bootdo.sales.domain.CustomerDeptDO;
import com.bootdo.system.domain.DeptDO;

import java.util.List;
import java.util.Map;

/**
 * 客户组织机构_部门
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-12-20 10:21:11
 */
public interface CustomerDeptService {
	
	CustomerDeptDO get(String customerDeptId);
	
	List<CustomerDeptDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CustomerDeptDO customerDept);
	
	int update(CustomerDeptDO customerDept);
	
	int remove(String customerDeptId);
	
	int batchRemove(String[] customerDeptIds);
	
	
	Long[] listParentDept();
	Tree<DeptDO> getTree();

	List<CustomerDeptDO> listTree(Map<String, Object> params);
	
	List<CustomerDeptDO> getTreeList(Map<String, Object> params);

}
