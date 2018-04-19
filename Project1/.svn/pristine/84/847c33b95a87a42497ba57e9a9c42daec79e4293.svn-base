package com.bootdo.approval.dao;

import com.bootdo.approval.domain.ExpensesNormalDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 普通报销申请信息表
 * @author sw
 * @email 1992lcg@163.com
 * @date 2017-11-28 17:41:01
 */
@Mapper
public interface ExpensesNormalDao {

	ExpensesNormalDO view(String expensesNormal);

	ExpensesNormalDO get(String expensesNormal);
	
	List<ExpensesNormalDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ExpensesNormalDO expensesNormal);
	
	int update(ExpensesNormalDO expensesNormal);
	
	int remove(String Expenses_Normal);
	
	int batchRemove(String[] expensesNormals);
}
