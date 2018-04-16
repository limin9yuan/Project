package com.bootdo.sales.dao;

import com.bootdo.sales.domain.RecordDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 行动记录信息表
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-24 17:21:10
 */
@Mapper
public interface RecordDao {

	RecordDO get(String recordId);
	
	List<RecordDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(RecordDO record);
	
	int update(RecordDO record);
	
	int remove(String Record_ID);
	
	int batchRemove(String[] recordIds);
}