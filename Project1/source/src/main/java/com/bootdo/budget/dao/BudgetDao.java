package com.bootdo.budget.dao;

import com.bootdo.budget.domain.BudgetDO;
import com.bootdo.common.domain.DictDO;
import com.bootdo.contract.domain.ContractDO;
import com.bootdo.project.domain.ProjectDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 项目预算表
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:44:13
 */
@Mapper
public interface BudgetDao {
	List<DictDO> listProjectByArea(Map<String, Object> map);

	BudgetDO view(String budgetId);

	BudgetDO get(String budgetId);
	
	List<BudgetDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(BudgetDO budget);
	
	int update(BudgetDO budget);
	
	int remove(String Budget_ID);
	
	int batchRemove(String[] budgetIds);
	
	List<DictDO> listDic();

	ProjectDO getProjectId(String projectId);

	BudgetDO getTotal(String budgetId);

	BudgetDO setOldProject(String budgetId);

	ProjectDO getBudgetServiceRevenue(String projectId);

	BudgetDO setSoftware(String budgetId);
	
	BudgetDO setBlender(String budgetId); 

	int updateSoftware(String budgetId);

	int updateOldProject(String budgetId);

	int updateBudgetLaborCost(String budgetId);

	int updateBudgetPurchaseCost(String budgetId);

	int updateBudgetTravelCost(String budgetId);

	int updateBlender(String budgetId);       
	
}
