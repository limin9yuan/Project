package com.bootdo.payment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.payment.dao.PurchaseManagementDao;
import com.bootdo.payment.domain.PurchaseManagementDO;
import com.bootdo.payment.service.PurchaseManagementService;



@Service
public class PurchaseManagementServiceImpl implements PurchaseManagementService {
	@Autowired
	private PurchaseManagementDao purchaseDao;
	
	@Override
	public PurchaseManagementDO get(String purchaseId){
		return purchaseDao.get(purchaseId);
	}
	
	@Override
	public List<PurchaseManagementDO> list(Map<String, Object> map){
		return purchaseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return purchaseDao.count(map);
	}
	
	@Override
	public int save(PurchaseManagementDO purchase){
		return purchaseDao.save(purchase);
	}
	
	@Override
	public int update(PurchaseManagementDO purchase){
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
