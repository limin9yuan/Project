package com.bootdo.material.service;

import com.bootdo.material.domain.RequirementPlanDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-21 16:46:41
 */
public interface RequirementPlanService {
	
	RequirementPlanDO get(Long id);
	
	List<RequirementPlanDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RequirementPlanDO requirementPlan);
	
	int update(RequirementPlanDO requirementPlan);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
