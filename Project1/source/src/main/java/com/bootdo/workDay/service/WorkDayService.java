package com.bootdo.workDay.service;

import com.bootdo.workDay.domain.WorkDayDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-02 16:57:14
 */
public interface WorkDayService {
	
	WorkDayDO get(Integer id);

	WorkDayDO getOfficeDay(Integer id);
	
	List<WorkDayDO> list(Map<String, Object> map);

	List<WorkDayDO> listOfficeDay(Map<String, Object> map);
	
	int count(Map<String, Object> map);

	int countOfficeDay(Map<String, Object> map);
	
	int saveHoliday(WorkDayDO day);

	int saveOfficeDay(WorkDayDO day);

	int saveWorkDay(WorkDayDO day);

	int update(WorkDayDO day);

	int updateOfficeDay(WorkDayDO day);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	int removeOfficeDay(Integer id);

	int removeWorkDay(String id);

	int batchRemoveOfficeDay(Integer[] ids);
}
