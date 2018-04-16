package com.bootdo.budget.dao;

import com.bootdo.budget.domain.ExpensesDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 项目报销预算表
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:42:42
 */
@Mapper
public interface ExpensesDao {

	ExpensesDO get(String expensesId);
	
	List<ExpensesDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ExpensesDO expenses);
	
	int update(ExpensesDO expenses);
	
	int remove(String Expenses_ID);
	
	int batchRemove(String[] expensesIds);
}
