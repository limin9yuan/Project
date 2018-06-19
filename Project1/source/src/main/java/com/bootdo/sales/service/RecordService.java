package com.bootdo.sales.service;

import com.bootdo.sales.domain.RecordDO;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 行动记录信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-24 17:21:10
 */
public interface RecordService {
	
	RecordDO get(String recordId);
	
	List<RecordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RecordDO record);
	
	int update(RecordDO record);
	
	int updateRecordAttachment(RecordDO record);
	
	int remove(String recordId);
	
	int batchRemove(String[] recordIds);
	
	Map<String, Object> uploadExcel(File file,long userid) ;
}
