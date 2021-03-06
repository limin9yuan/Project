package com.bootdo.approval.service;

import com.bootdo.approval.domain.PurchaseDO;
import com.bootdo.approval.domain.PurchaseDetailDO;

import java.util.List;
import java.util.Map;

/**
 * 采购申请表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-30 14:40:43
 */
public interface PurchaseService {

	PurchaseDO view(String purchaseId);

	PurchaseDO get(String purchaseId);
	
	List<PurchaseDO> purchaseApprovalStatus(Map<String, Object> map);
	
	List<PurchaseDO> listPurchaseApprovalStatus(Map<String, Object> map);
	
	List<PurchaseDO> list(Map<String, Object> map);
	List<PurchaseDetailDO> listDetail(Map<String, Object> map);
	int saveDetail(PurchaseDetailDO purchaseDetail);

	int updateDetail(PurchaseDetailDO purchaseDetail);
	int countPurchaseApprovalStatus(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PurchaseDO purchase);
	
	int update(PurchaseDO purchase);
	
	int remove(String purchaseId);
	
	int batchRemove(String[] purchaseIds);

	int formUpdate(PurchaseDO purchase);  
}
