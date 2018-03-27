package com.bootdo.payment.dao;

import com.bootdo.payment.domain.PaidDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 付款信息表
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-05 10:14:55
 */
@Mapper
public interface PaidDao {

	PaidDO get(String paidId);
	
	List<PaidDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PaidDO paid);
	
	int update(PaidDO paid);
	
	int remove(String Paid_ID);
	
	int batchRemove(String[] paidIds);
}
