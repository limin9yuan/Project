package com.bootdo.budget.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.budget.dao.LaborDao;
import com.bootdo.budget.domain.BudgetDO;
import com.bootdo.budget.domain.LaborDO;
import com.bootdo.budget.service.LaborService;
import com.bootdo.common.domain.DictDO;
import com.bootdo.inner.domain.InnerOrgEmployeeDO;



@Service
public class LaborServiceImpl implements LaborService {
	@Autowired
	private LaborDao laborDao;

	@Override
	public LaborDO getWorkTime(String date){
		return laborDao.getWorkTime(date);
	}

	@Override
	public List<LaborDO> calculateLaborHour(Map<String, Object> map){
		return laborDao.calculateLaborHour(map);
	}
	
	@Override
	public LaborDO get(String laborId){
		return laborDao.get(laborId);
	}
	
	@Override
	public List<LaborDO> list(Map<String, Object> map){
		return laborDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return laborDao.count(map);
	}
	
	@Override
	public int save(LaborDO labor){
		return laborDao.save(labor);
	}
	
	@Override
	public int update(LaborDO labor){
		return laborDao.update(labor);
	}
	
	@Override
	public int remove(String laborId){
		return laborDao.remove(laborId);
	}
	
	@Override
	public int batchRemove(String[] laborIds){
		return laborDao.batchRemove(laborIds);
	}
	@Override
	public 	InnerOrgEmployeeDO getEmployeeLevelSalary(String employeeId){
		return laborDao.getEmployeeLevelSalary(employeeId);
	}

	@Override
	public int updateLaborSoftware(String budgetId) {
		return laborDao.updateLaborSoftware(budgetId);
	}

	@Override
	public int updateOldProject(String budgetId) {
		return laborDao.updateOldProject(budgetId);
	}
}
