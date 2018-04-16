package com.bootdo.budget.service;

import com.bootdo.budget.domain.BudgetPurchaseDO;

import java.util.List;
import java.util.Map;

/**
 * 项目采购预算表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:43:33
 */
public interface BudgetPurchaseService {
	
	BudgetPurchaseDO get(String purchaseId);
	
	List<BudgetPurchaseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BudgetPurchaseDO purchase);
	
	int update(BudgetPurchaseDO purchase);
	
	int remove(String purchaseId);
	
	int batchRemove(String[] purchaseIds);
}
