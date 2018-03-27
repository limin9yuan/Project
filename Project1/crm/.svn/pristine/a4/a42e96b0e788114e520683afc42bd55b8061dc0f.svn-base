package com.bootdo.payment.service;

import com.bootdo.payment.domain.PurchaseManagementDO;

import java.util.List;
import java.util.Map;

/**
 * 采购信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-05 15:59:36
 */
public interface PurchaseManagementService {
	
	PurchaseManagementDO get(String purchaseId);
	
	List<PurchaseManagementDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PurchaseManagementDO purchase);
	
	int update(PurchaseManagementDO purchase);
	
	int remove(String purchaseId);
	
	int batchRemove(String[] purchaseIds);
}
