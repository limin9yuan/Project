package com.bootdo.contract.service;

import com.bootdo.contract.domain.PlanDO;

import java.util.List;
import java.util.Map;

/**
 * 交付计划表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-12-18 10:17:48
 */
public interface PlanService {
	
	PlanDO get(String planId);
	
	List<PlanDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PlanDO plan);
	
	int update(PlanDO plan);
	
	int remove(String planId);
	
	int batchRemove(String[] planIds);
}
