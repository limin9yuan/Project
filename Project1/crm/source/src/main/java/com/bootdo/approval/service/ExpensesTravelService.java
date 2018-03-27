package com.bootdo.approval.service;

import com.bootdo.approval.domain.ExpensesTravelDO;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 差旅报销申请表
 * 
 * @author sw
 * @email 1992lcg@163.com
 * @date 2017-11-28 14:18:31
 */
public interface ExpensesTravelService {
	
	ExpensesTravelDO get(String expensesTravelId);
	
	List<ExpensesTravelDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExpensesTravelDO expensesTravel);
	
	int update(ExpensesTravelDO expensesTravel);
	
	int remove(String expensesTravelId);
	
	int batchRemove(String[] expensesTravelIds);
	Map<String, Object> Import(File file,long userid) ;
}
