package com.bootdo.payment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.payment.dao.PaidDao;
import com.bootdo.payment.domain.PaidDO;
import com.bootdo.payment.service.PaidService;



@Service
public class PaidServiceImpl implements PaidService {
	@Autowired
	private PaidDao paidDao;
	
	@Override
	public PaidDO get(String paidId){
		return paidDao.get(paidId);
	}
	
	@Override
	public List<PaidDO> list(Map<String, Object> map){
		return paidDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return paidDao.count(map);
	}
	
	@Override
	public int save(PaidDO paid){
		return paidDao.save(paid);
	}
	
	@Override
	public int update(PaidDO paid){
		return paidDao.update(paid);
	}
	
	@Override
	public int remove(String paidId){
		return paidDao.remove(paidId);
	}
	
	@Override
	public int batchRemove(String[] paidIds){
		return paidDao.batchRemove(paidIds);
	}
	
}
