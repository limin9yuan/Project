package com.bootdo.contract.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.contract.dao.ReceivableDao;
import com.bootdo.contract.domain.ReceivableDO;
import com.bootdo.contract.service.ReceivableService;



@Service
public class ReceivableServiceImpl implements ReceivableService {
	@Autowired
	private ReceivableDao receivableDao;
	
	@Override
	public ReceivableDO get(String receivableId){
		return receivableDao.get(receivableId);
	}
	
	@Override
	public List<ReceivableDO> list(Map<String, Object> map){
		return receivableDao.list(map);
	}
	
	@Override
	public List<ReceivableDO> getContractId(Map<String, Object> map){
		return receivableDao.getContractId(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return receivableDao.count(map);
	}
	
	@Override
	public int save(ReceivableDO receivable){
		return receivableDao.save(receivable);
	}
	
	@Override
	public int update(ReceivableDO receivable){
		return receivableDao.update(receivable);
	}
	
	@Override
	public int remove(String receivableId){
		return receivableDao.remove(receivableId);
	}
	
	@Override
	public int batchRemove(String[] receivableIds){
		return receivableDao.batchRemove(receivableIds);
	}

	@Override
	public List<ReceivableDO> sumReceivablePrice(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return receivableDao.sumReceivablePrice(map);
	}

	@Override
	public List<ReceivableDO> planCunstomerNumber(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return receivableDao.planCunstomerNumber(map);
	}

	@Override
	public List<ReceivableDO> examinePlanCunstomerNumber(Map<String, Object> map) {
		// TODO 未回款企业详情
		return receivableDao.examinePlanCunstomerNumber(map);
	}

	@Override
	public int countExaminePlanCunstomerNumber(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return receivableDao.countExaminePlanCunstomerNumber(map);
	}
	
}
