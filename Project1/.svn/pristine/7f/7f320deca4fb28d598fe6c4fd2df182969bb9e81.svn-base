package com.bootdo.approval.dao;

import com.bootdo.approval.domain.PurchaseDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 采购申请表
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-30 14:40:43
 */
@Mapper
public interface PurchaseDao {

	PurchaseDO get(String purchaseId);
	
	List<PurchaseDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PurchaseDO purchase);
	
	int update(PurchaseDO purchase);
	
	int remove(String Purchase_ID);
	
	int batchRemove(String[] purchaseIds);
}
