package com.bootdo.approval.service;

import com.bootdo.approval.domain.ExpensesNormalDO;
import com.bootdo.contract.domain.TravelDO;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 普通报销申请信息表
 * 
 * @author sw
 * @email 1992lcg@163.com
 * @date 2017-11-28 17:41:01
 */
public interface ExpensesNormalService {

	ExpensesNormalDO view(String expensesNormal);

	ExpensesNormalDO get(String expensesNormal);
	
	List<ExpensesNormalDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExpensesNormalDO expensesNormal);
	
	int update(ExpensesNormalDO expensesNormal);
	
	int remove(String expensesNormal);
	
	int batchRemove(String[] expensesNormals);

	int formUpdate(ExpensesNormalDO expensesNormalDO);
	Map<String, Object> Import(File file,long userid) ;
}
