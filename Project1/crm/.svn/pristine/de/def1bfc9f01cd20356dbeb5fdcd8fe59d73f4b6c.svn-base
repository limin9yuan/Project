package com.bootdo.sales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.sales.dao.CustomerDeptDao;
import com.bootdo.sales.domain.CustomerDeptDO;
import com.bootdo.sales.service.CustomerDeptService;



@Service
public class CustomerDeptServiceImpl implements CustomerDeptService {
	@Autowired
	private CustomerDeptDao customerDeptDao;
	
	@Override
	public CustomerDeptDO get(String customerDeptId){
		return customerDeptDao.get(customerDeptId);
	}
	
	@Override
	public List<CustomerDeptDO> list(Map<String, Object> map){
		return customerDeptDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return customerDeptDao.count(map);
	}
	
	@Override
	public int save(CustomerDeptDO customerDept){
		return customerDeptDao.save(customerDept);
	}
	
	@Override
	public int update(CustomerDeptDO customerDept){
		return customerDeptDao.update(customerDept);
	}
	
	@Override
	public int remove(String customerDeptId){
		return customerDeptDao.remove(customerDeptId);
	}
	
	@Override
	public int batchRemove(String[] customerDeptIds){
		return customerDeptDao.batchRemove(customerDeptIds);
	}
	
}
