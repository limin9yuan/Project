package com.bootdo.ireport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.ireport.dao.IreportDao;
import com.bootdo.ireport.domain.IreportDO;
import com.bootdo.ireport.service.IreportService;



@Service
public class IreportServiceImpl implements IreportService {
	@Autowired
	private IreportDao ireportDao;
	
	@Override
	public IreportDO get(Integer ireportid){
		return ireportDao.get(ireportid);
	}
	
	@Override
	public List<IreportDO> list(Map<String, Object> map){
		return ireportDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return ireportDao.count(map);
	}
	
	@Override
	public int save(IreportDO ireport){
		return ireportDao.save(ireport);
	}
	
	@Override
	public int update(IreportDO ireport){
		return ireportDao.update(ireport);
	}
	
	@Override
	public int remove(Integer ireportid){
		return ireportDao.remove(ireportid);
	}
	
	@Override
	public int batchRemove(Integer[] ireportids){
		return ireportDao.batchRemove(ireportids);
	}
	
}
