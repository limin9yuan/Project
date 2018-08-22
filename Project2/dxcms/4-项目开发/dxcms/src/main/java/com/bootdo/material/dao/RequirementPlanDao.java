package com.bootdo.material.dao;

import com.bootdo.material.domain.RequirementPlanDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-21 16:46:41
 */
@Mapper
public interface RequirementPlanDao {

	RequirementPlanDO get(Long id);
	
	List<RequirementPlanDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(RequirementPlanDO requirementPlan);
	
	int update(RequirementPlanDO requirementPlan);
	
	int remove(Long ID);
	
	int batchRemove(Long[] ids);
}
