package com.bootdo.budget.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.budget.dao.BudgetPurchaseDao;
import com.bootdo.budget.domain.BudgetPurchaseDO;
import com.bootdo.budget.service.BudgetPurchaseService;



@Service
public class BudgetPurchaseServiceImpl implements BudgetPurchaseService {
	@Autowired
	private BudgetPurchaseDao purchaseDao;
	
	@Override
	public BudgetPurchaseDO get(String purchaseId){
		return purchaseDao.get(purchaseId);
	}
	
	@Override
	public List<BudgetPurchaseDO> list(Map<String, Object> map){
		return purchaseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return purchaseDao.count(map);
	}
	
	@Override
	public int save(BudgetPurchaseDO purchase){
		return purchaseDao.save(purchase);
	}
	
	@Override
	public int update(BudgetPurchaseDO purchase){
		return purchaseDao.update(purchase);
	}
	
	@Override
	public int remove(String purchaseId){
		return purchaseDao.remove(purchaseId);
	}
	
	@Override
	public int batchRemove(String[] purchaseIds){
		return purchaseDao.batchRemove(purchaseIds);
	}
	
}
