package com.bootdo.sales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.sales.dao.CompetitorDao;
import com.bootdo.sales.domain.CompetitorDO;
import com.bootdo.sales.service.CompetitorService;



@Service
public class CompetitorServiceImpl implements CompetitorService {
	@Autowired
	private CompetitorDao competitorDao;
	
	@Override
	public CompetitorDO get(String complaintId){
		return competitorDao.get(complaintId);
	}
	
	@Override
	public List<CompetitorDO> list(Map<String, Object> map){
		return competitorDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return competitorDao.count(map);
	}
	
	@Override
	public int save(CompetitorDO competitor){
		return competitorDao.save(competitor);
	}
	
	@Override
	public int update(CompetitorDO competitor){
		return competitorDao.update(competitor);
	}
	
	@Override
	public int remove(String complaintId){
		return competitorDao.remove(complaintId);
	}
	
	@Override
	public int batchRemove(String[] complaintIds){
		return competitorDao.batchRemove(complaintIds);
	}
	
}
