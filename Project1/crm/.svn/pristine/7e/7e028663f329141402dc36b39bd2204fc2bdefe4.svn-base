package com.bootdo.payment.service;

import com.bootdo.payment.domain.PaidDO;

import java.util.List;
import java.util.Map;

/**
 * 付款信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-05 10:14:55
 */
public interface PaidService {
	
	PaidDO get(String paidId);
	
	List<PaidDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PaidDO paid);
	
	int update(PaidDO paid);
	
	int remove(String paidId);
	
	int batchRemove(String[] paidIds);
}
