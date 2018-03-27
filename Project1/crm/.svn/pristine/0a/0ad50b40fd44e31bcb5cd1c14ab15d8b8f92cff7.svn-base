package com.bootdo.sales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.sales.dao.CustomerJobDao;
import com.bootdo.sales.domain.CustomerJobDO;
import com.bootdo.sales.service.CustomerJobService;



@Service
public class CustomerJobServiceImpl implements CustomerJobService {
	@Autowired
	private CustomerJobDao customerJobDao;
	
	@Override
	public CustomerJobDO get(String customerJobId){
		return customerJobDao.get(customerJobId);
	}
	
	@Override
	public List<CustomerJobDO> list(Map<String, Object> map){
		return customerJobDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return customerJobDao.count(map);
	}
	
	@Override
	public int save(CustomerJobDO customerJob){
		return customerJobDao.save(customerJob);
	}
	
	@Override
	public int update(CustomerJobDO customerJob){
		return customerJobDao.update(customerJob);
	}
	
	@Override
	public int remove(String customerJobId){
		return customerJobDao.remove(customerJobId);
	}
	
	@Override
	public int batchRemove(String[] customerJobIds){
		return customerJobDao.batchRemove(customerJobIds);
	}
	
}
