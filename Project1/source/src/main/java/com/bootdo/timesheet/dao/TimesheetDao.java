package com.bootdo.timesheet.dao;

import java.util.List;
import java.util.Map;
import com.bootdo.timesheet.domain.TimesheetDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工时信息表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-14 17:52:48
 */
@Mapper
public interface TimesheetDao {

	TimesheetDO get(String timesheetId);
	
	List<TimesheetDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TimesheetDO timesheet);
	
	int update(TimesheetDO timesheet);
	
	int remove(String TimeSheet_ID);
	
	int batchRemove(String[] timesheetIds);
}