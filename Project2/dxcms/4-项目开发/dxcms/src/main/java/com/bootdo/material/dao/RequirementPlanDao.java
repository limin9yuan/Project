package com.bootdo.material.dao;

import com.bootdo.material.domain.RequirementPlanDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.wxcl.amy.model.RequirePlanModel;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-21 16:46:41
 */
@Mapper
public interface RequirementPlanDao {

	RequirePlanModel get(Long id);
	
	List<RequirePlanModel> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(RequirePlanModel requirementPlan);
	
	int update(RequirePlanModel requirementPlan);
	
	int remove(Long ID);
	
	int batchRemove(Long[] ids);
}
