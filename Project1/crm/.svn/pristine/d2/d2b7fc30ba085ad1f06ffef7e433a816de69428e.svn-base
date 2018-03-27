package com.bootdo.budget.service;

import com.bootdo.budget.domain.BudgetDO;
import com.bootdo.common.domain.DictDO;
import com.bootdo.sales.domain.SalesProjectDO;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

/**
 * 项目预算表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:44:13
 */
public interface BudgetService {
	
	BudgetDO get(String budgetId);
	
	List<BudgetDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BudgetDO budget);
	
	int update(BudgetDO budget);
	
	int remove(String budgetId);
	
	int batchRemove(String[] budgetIds);
	
    List<BudgetDO> getQuery(Map<String, Object> params);
	
	public void export(String[] titles, ServletOutputStream out, List<BudgetDO> list);
	
	List<DictDO> listDic(); 
}
