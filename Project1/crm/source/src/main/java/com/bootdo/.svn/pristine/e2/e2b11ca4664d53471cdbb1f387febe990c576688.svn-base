package com.bootdo.payment.dao;

import com.bootdo.payment.domain.ReceivedDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 回款信息表 
 * 开发者：小平
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-2-24
 */

@Mapper
public interface ReceivedDao {

	ReceivedDO get(String receivedId);
	
	List<ReceivedDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ReceivedDO received);
	
	int update(ReceivedDO received);
	
	int remove(String Received_ID);
	
	int batchRemove(String[] receivedIds);
}
