package com.bootdo.contract.dao;

import com.bootdo.contract.domain.PlanDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 交付计划表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-12-18 10:17:48
 */
@Mapper
public interface PlanDao {

	PlanDO get(String planId);
	
	List<PlanDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PlanDO plan);
	
	int update(PlanDO plan);
	
	int remove(String Plan_ID);
	
	int batchRemove(String[] planIds);
}
