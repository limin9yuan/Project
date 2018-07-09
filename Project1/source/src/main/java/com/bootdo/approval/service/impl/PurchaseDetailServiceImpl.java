package com.bootdo.approval.service.impl;

import com.bootdo.approval.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.approval.dao.PurchaseDetailDao;
import com.bootdo.approval.domain.PurchaseDetailDO;




@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService {
	@Autowired
	private PurchaseDetailDao purchaseDetailDao;
	
	@Override
	public PurchaseDetailDO get(Integer purchaseId){
		return purchaseDetailDao.get(purchaseId);
	}
	
	@Override
	public List<PurchaseDetailDO> list(Map<String, Object> map){
		return purchaseDetailDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return purchaseDetailDao.count(map);
	}
	
	@Override
	public int save(PurchaseDetailDO purchaseDetail){
		return purchaseDetailDao.save(purchaseDetail);
	}
	
	@Override
	public int update(PurchaseDetailDO purchaseDetail){
		return purchaseDetailDao.update(purchaseDetail);
	}
	
	@Override
	public int remove(Integer purchaseId){
		return purchaseDetailDao.remove(purchaseId);
	}
	@Override
	public int removeapprovalid(Integer approvalpurchaseId){
		return purchaseDetailDao.removeapprovalid(approvalpurchaseId);
	}
	
	@Override
	public int batchRemove(Integer[] purchaseIds){
		return purchaseDetailDao.batchRemove(purchaseIds);
	}
	
}
