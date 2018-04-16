package com.bootdo.contract.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.contract.dao.PayableDao;
import com.bootdo.contract.domain.PayableDO;
import com.bootdo.contract.service.PayableService;



@Service
public class PayableServiceImpl implements PayableService {
	@Autowired
	private PayableDao payableDao;
	
	@Override
	public PayableDO get(String payableId){
		return payableDao.get(payableId);
	}
	
	@Override
	public List<PayableDO> list(Map<String, Object> map){
		return payableDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return payableDao.count(map);
	}
	
	@Override
	public int save(PayableDO payable){
		return payableDao.save(payable);
	}
	
	@Override
	public int update(PayableDO payable){
		return payableDao.update(payable);
	}
	
	@Override
	public int remove(String payableId){
		return payableDao.remove(payableId);
	}
	
	@Override
	public int batchRemove(String[] payableIds){
		return payableDao.batchRemove(payableIds);
	}
	
}
