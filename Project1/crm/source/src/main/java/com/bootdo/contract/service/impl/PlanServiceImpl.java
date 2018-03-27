package com.bootdo.contract.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.contract.dao.PlanDao;
import com.bootdo.contract.domain.PlanDO;
import com.bootdo.contract.service.PlanService;



@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
	private PlanDao planDao;
	
	@Override
	public PlanDO get(String planId){
		return planDao.get(planId);
	}
	
	@Override
	public List<PlanDO> list(Map<String, Object> map){
		return planDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return planDao.count(map);
	}
	
	@Override
	public int save(PlanDO plan){
		return planDao.save(plan);
	}
	
	@Override
	public int update(PlanDO plan){
		return planDao.update(plan);
	}
	
	@Override
	public int remove(String planId){
		return planDao.remove(planId);
	}
	
	@Override
	public int batchRemove(String[] planIds){
		return planDao.batchRemove(planIds);
	}
	
}
