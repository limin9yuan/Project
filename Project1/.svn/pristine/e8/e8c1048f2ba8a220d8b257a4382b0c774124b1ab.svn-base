package com.bootdo.approval.dao;

import com.bootdo.approval.domain.PurchaseDetailDO;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 采购申请明细表

 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-06-13 17:15:42
 */
@Mapper
public interface PurchaseDetailDao {

	PurchaseDetailDO get(Integer purchaseId);
	
	List<PurchaseDetailDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PurchaseDetailDO purchaseDetail);

	int update(PurchaseDetailDO purchaseDetail);
	int removeapprovalid(Integer Approval_Purchase_ID);
	int remove(Integer Purchase_ID);

	int batchRemove(Integer[] purchaseIds);
}
