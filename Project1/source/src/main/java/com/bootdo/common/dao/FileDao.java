package com.bootdo.common.dao;

import com.bootdo.common.domain.FileDO;
import com.bootdo.sales.domain.RecordDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 文件上传
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface FileDao {

	FileDO get(String id);
	
	List<FileDO> list(Map<String,Object> map);
	
	List<FileDO> listId(Map<String,Object> map);
	
	int countId(Map<String,Object> map);
	
	List<FileDO> listRecordAttachment(Map<String,Object> map);
	
	int countRecordAttachment(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(FileDO file);
	
	int update(FileDO file);
	
	int remove(String  id);
	
	int batchRemove(Long[] ids);
}
