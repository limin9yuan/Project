package com.bootdo.contract.dao;

import com.bootdo.common.domain.DictDO;
import com.bootdo.contract.domain.AdditionalRecordsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 合同增补记录
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2018-01-04 13:10:31
 */
@Mapper
public interface AdditionalRecordsDao {

	AdditionalRecordsDO get(String recordId);
	
	List<AdditionalRecordsDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AdditionalRecordsDO additionalRecords);
	
	int update(AdditionalRecordsDO additionalRecords);
	
	int remove(String Record_ID);
	
	int batchRemove(String[] recordIds);

	List<DictDO> listDic(); 
}
