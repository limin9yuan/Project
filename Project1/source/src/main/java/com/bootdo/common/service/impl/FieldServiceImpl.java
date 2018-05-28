package com.bootdo.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.common.dao.FieldDao;
import com.bootdo.common.domain.FieldDO;
import com.bootdo.common.service.FieldService;



@Service
public class FieldServiceImpl implements FieldService {
	@Autowired
	private FieldDao fieldDao;
	
	@Override
	public FieldDO get(Integer id){
		return fieldDao.get(id);
	}
	
	@Override
	public List<FieldDO> list(Map<String, Object> map){
		return fieldDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return fieldDao.count(map);
	}
	
	@Override
	public int save(FieldDO field){
		return fieldDao.save(field);
	}
	
	@Override
	public int update(FieldDO field){
		return fieldDao.update(field);
	}
	
	@Override
	public int remove(Integer id){
		return fieldDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return fieldDao.batchRemove(ids);
	}
	
}
