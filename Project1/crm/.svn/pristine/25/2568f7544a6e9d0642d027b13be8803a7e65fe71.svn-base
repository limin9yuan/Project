package com.bootdo.sales.service;

import com.bootdo.sales.domain.CompetitorDO;

import java.util.List;
import java.util.Map;

/**
 * 竞争对手信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:31:23
 */
public interface CompetitorService {
	
	CompetitorDO get(String complaintId);
	
	List<CompetitorDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CompetitorDO competitor);
	
	int update(CompetitorDO competitor);
	
	int remove(String complaintId);
	
	int batchRemove(String[] complaintIds);
}
