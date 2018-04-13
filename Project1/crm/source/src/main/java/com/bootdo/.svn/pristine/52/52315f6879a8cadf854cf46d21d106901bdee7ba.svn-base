package com.bootdo.contract.dao;

import com.bootdo.contract.domain.ReceivableDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 收款计划表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-12-18 10:17:38
 */
@Mapper
public interface ReceivableDao {

	ReceivableDO get(String receivableId);
	
	List<ReceivableDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ReceivableDO receivable);
	
	int update(ReceivableDO receivable);
	
	int remove(String Receivable_ID);
	
	int batchRemove(String[] receivableIds);
}
