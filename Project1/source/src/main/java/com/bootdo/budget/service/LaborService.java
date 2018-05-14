package com.bootdo.budget.service;

import com.bootdo.budget.domain.BudgetDO;
import com.bootdo.budget.domain.LaborDO;
import com.bootdo.common.domain.DictDO;
import com.bootdo.inner.domain.InnerOrgEmployeeDO;

import java.util.List;
import java.util.Map;

/**
 * 项目人力安排表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:43:07
 */
public interface LaborService {
	
	LaborDO get(String laborId);
	
	List<LaborDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LaborDO labor);
	
	int update(LaborDO labor);
	
	int remove(String laborId);
	
	int batchRemove(String[] laborIds);

	
	InnerOrgEmployeeDO getEmployeeLevelSalary(String employeeId);

	int updateLaborSoftware(String budgetId); 
	
	int updateOldProject(String budgetId);


}
