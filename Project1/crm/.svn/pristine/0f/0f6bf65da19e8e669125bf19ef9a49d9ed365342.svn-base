package com.bootdo.approval.dao;

import com.bootdo.approval.domain.ExpensesTravelDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 差旅报销申请表
 * @author sw
 * @email 1992lcg@163.com
 * @date 2017-11-28 14:18:31
 */
@Mapper
public interface ExpensesTravelDao {

	ExpensesTravelDO get(String expensesTravelId);
	
	List<ExpensesTravelDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ExpensesTravelDO expensesTravel);
	
	int update(ExpensesTravelDO expensesTravel);
	
	int remove(String Expenses_Travel_ID);
	
	int batchRemove(String[] expensesTravelIds);
}
