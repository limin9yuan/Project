package com.bootdo.approval.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.approval.dao.PurchaseDao;
import com.bootdo.approval.domain.PurchaseDO;
import com.bootdo.approval.service.PurchaseService;



@Service
public class PurchaseServiceImpl implements PurchaseService {
	@Autowired
	private PurchaseDao purchaseDao;
	
	@Override
	public PurchaseDO get(String purchaseId){
		return purchaseDao.get(purchaseId);
	}
	
	@Override
	public List<PurchaseDO> list(Map<String, Object> map){
		return purchaseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return purchaseDao.count(map);
	}
	
	@Override
	public int save(PurchaseDO purchase){
		return purchaseDao.save(purchase);
	}
	
	@Override
	public int update(PurchaseDO purchase){
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
