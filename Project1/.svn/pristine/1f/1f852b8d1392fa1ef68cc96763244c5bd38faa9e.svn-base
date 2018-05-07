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
	public WorkDayDO getOfficeDay(Integer id){
		return workDayDao.getOfficeDay(id);
	}

	@Override
	public List<WorkDayDO> list(Map<String, Object> map){
		return workDayDao.list(map);
	}

	@Override
	public List<WorkDayDO> listOfficeDay(Map<String, Object> map){
		return workDayDao.listOfficeDay(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return workDayDao.count(map);
	}

	@Override
	public int countOfficeDay(Map<String, Object> map){
		return workDayDao.countOfficeDay(map);
	}

	@Override
	public int saveOfficeDay(WorkDayDO day){
		return workDayDao.saveOfficeDay(day);
	}

	@Override
	public int saveWorkDay(WorkDayDO day){
		return workDayDao.saveWorkDay(day);
	}

	@Override
	public int saveHoliday(WorkDayDO day){
		return workDayDao.saveHoliday(day);
	}
	
	@Override
	public int update(WorkDayDO day){
		return workDayDao.update(day);
	}

	@Override
	public int updateOfficeDay(WorkDayDO day){
		return workDayDao.updateOfficeDay(day);
	}
	
	@Override
	public int remove(Integer id){
		return workDayDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return workDayDao.batchRemove(ids);
	}

	@Override
	public int removeOfficeDay(Integer id){
		return workDayDao.removeOfficeDay(id);
	}

	@Override
	public int removeWorkDay(String id){
		return workDayDao.removeWorkDay(id);
	}

	@Override
	public int batchRemoveOfficeDay(Integer[] ids){
		return workDayDao.batchRemoveOfficeDay(ids);
	}
	
}
