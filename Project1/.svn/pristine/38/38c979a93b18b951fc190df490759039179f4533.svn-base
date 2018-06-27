package com.bootdo.common.service;

import com.bootdo.common.domain.FileDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */

public interface FileService {
	
	FileDO get(String id);
	
	List<FileDO> list(Map<String, Object> map);
	
	List<FileDO> listId(Map<String,Object> map);
	
	int countId(Map<String,Object> map);
	
	int count(Map<String, Object> map);
	
	List<FileDO> listRecordAttachment(Map<String,Object> map);
	
	int countRecordAttachment(Map<String,Object> map);
	
	int save(FileDO sysFile);
	
	int update(FileDO sysFile);
	
	int remove(String id);
	
	int batchRemove(Long[] ids);
	
	List<FileDO> listIdCustomer(String[] id);
	
}
