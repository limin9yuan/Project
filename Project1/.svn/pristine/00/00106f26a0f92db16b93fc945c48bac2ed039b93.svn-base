package com.bootdo.budget.dao;


import com.bootdo.budget.domain.BudgetDO;
import com.bootdo.budget.domain.LaborDO;
import com.bootdo.inner.domain.InnerOrgEmployeeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 项目人力安排表
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:43:07
 */
@Mapper
public interface LaborDao {

	List<LaborDO> calculateLaborHour(Map<String, Object> map);

	LaborDO get(String laborId);
	
	List<LaborDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(LaborDO labor);
	
	int update(LaborDO labor);
	
	int remove(String Labor_ID);
	
	int batchRemove(String[] laborIds);

	InnerOrgEmployeeDO getEmployeeLevelSalary(String employeeId);

	int updateLaborSoftware(String budgetId);

	int updateOldProject(String budgetId);

}
