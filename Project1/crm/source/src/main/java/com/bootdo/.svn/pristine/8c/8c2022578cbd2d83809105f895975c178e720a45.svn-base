package com.bootdo.contract.service;

import com.bootdo.contract.domain.PayableDO;

import java.util.List;
import java.util.Map;

/**
 * 付款计划表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-12-15 16:02:12
 */
public interface PayableService {
	
	PayableDO get(String payableId);
	
	List<PayableDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PayableDO payable);
	
	int update(PayableDO payable);
	
	int remove(String payableId);
	
	int batchRemove(String[] payableIds);
}
