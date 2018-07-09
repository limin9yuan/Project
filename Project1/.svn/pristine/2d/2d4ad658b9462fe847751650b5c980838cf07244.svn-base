package com.bootdo.approval.service;

import com.bootdo.approval.domain.PurchaseDetailDO;

import java.util.List;
import java.util.Map;

/**
 * 采购申请明细表

 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-06-13 17:15:42
 */
public interface PurchaseDetailService {
	
	PurchaseDetailDO get(Integer purchaseId);
	
	List<PurchaseDetailDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PurchaseDetailDO purchaseDetail);
	
	int update(PurchaseDetailDO purchaseDetail);
	
	int remove(Integer purchaseId);
	int removeapprovalid(Integer approvalpurchaseId);
	int batchRemove(Integer[] purchaseIds);
}
