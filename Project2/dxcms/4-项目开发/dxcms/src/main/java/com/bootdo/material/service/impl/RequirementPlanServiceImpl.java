package com.bootdo.material.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.material.dao.RequirementPlanDao;
import com.bootdo.material.service.RequirementPlanService;
import org.wxcl.amy.model.RequirePlanModel;


@Service
public class RequirementPlanServiceImpl implements RequirementPlanService {
	@Autowired
	private RequirementPlanDao requirementPlanDao;
	
	@Override
	public RequirePlanModel get(Long id){
		return requirementPlanDao.get(id);
	}
	
	@Override
	public List<RequirePlanModel> list(Map<String, Object> map){
		return requirementPlanDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return requirementPlanDao.count(map);
	}
	
	@Override
	public int save(RequirePlanModel requirementPlan){
		return requirementPlanDao.save(requirementPlan);
	}
	
	@Override
	public int update(RequirePlanModel requirementPlan){
		return requirementPlanDao.update(requirementPlan);
	}
	
	@Override
	public int remove(Long id){
		return requirementPlanDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return requirementPlanDao.batchRemove(ids);
	}
	
}
