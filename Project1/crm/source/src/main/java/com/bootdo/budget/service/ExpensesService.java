package com.bootdo.budget.service;

import com.bootdo.budget.domain.ExpensesDO;

import java.util.List;
import java.util.Map;

/**
 * 项目报销预算表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:42:42
 */
public interface ExpensesService {
	
	ExpensesDO get(String expensesId);
	
	List<ExpensesDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExpensesDO expenses);
	
	int update(ExpensesDO expenses);
	
	int remove(String expensesId);
	
	int batchRemove(String[] expensesIds);
}
