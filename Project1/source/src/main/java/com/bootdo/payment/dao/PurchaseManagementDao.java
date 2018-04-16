package com.bootdo.payment.dao;

import com.bootdo.payment.domain.PurchaseManagementDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 采购信息表
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-05 15:59:36
 */
@Mapper
public interface PurchaseManagementDao {

	PurchaseManagementDO get(String purchaseId);
	
	List<PurchaseManagementDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PurchaseManagementDO purchase);
	
	int update(PurchaseManagementDO purchase);
	
	int remove(String Purchase_ID);
	
	int batchRemove(String[] purchaseIds);
}
