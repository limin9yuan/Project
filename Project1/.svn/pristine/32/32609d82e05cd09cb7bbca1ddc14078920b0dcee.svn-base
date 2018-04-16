package com.bootdo.budget.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.budget.dao.ExpensesDao;
import com.bootdo.budget.domain.ExpensesDO;
import com.bootdo.budget.service.ExpensesService;



@Service
public class ExpensesServiceImpl implements ExpensesService {
	@Autowired
	private ExpensesDao expensesDao;
	
	@Override
	public ExpensesDO get(String expensesId){
		return expensesDao.get(expensesId);
	}
	
	@Override
	public List<ExpensesDO> list(Map<String, Object> map){
		return expensesDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return expensesDao.count(map);
	}
	
	@Override
	public int save(ExpensesDO expenses){
		return expensesDao.save(expenses);
	}
	
	@Override
	public int update(ExpensesDO expenses){
		return expensesDao.update(expenses);
	}
	
	@Override
	public int remove(String expensesId){
		return expensesDao.remove(expensesId);
	}
	
	@Override
	public int batchRemove(String[] expensesIds){
		return expensesDao.batchRemove(expensesIds);
	}
	
}
