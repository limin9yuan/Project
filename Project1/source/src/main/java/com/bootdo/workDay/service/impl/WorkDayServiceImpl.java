package com.bootdo.workDay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.workDay.dao.WorkDayDao;
import com.bootdo.workDay.domain.WorkDayDO;
import com.bootdo.workDay.service.WorkDayService;



@Service
public class WorkDayServiceImpl implements WorkDayService {
	@Autowired
	private WorkDayDao workDayDao;
	
	@Override
	public WorkDayDO get(Integer id){
		return workDayDao.get(id);
	}
	
	@Override
	public List<WorkDayDO> list(Map<String, Object> map){
		return workDayDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return workDayDao.count(map);
	}
	
	@Override
	public int save(WorkDayDO day){
		return workDayDao.save(day);
	}

	@Override
	public int holiday(WorkDayDO day){
		return workDayDao.holiday(day);
	}
	
	@Override
	public int update(WorkDayDO day){
		return workDayDao.update(day);
	}
	
	@Override
	public int remove(Integer id){
		return workDayDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return workDayDao.batchRemove(ids);
	}
	
}
