package com.bootdo.ireport.service;

import com.bootdo.ireport.domain.IreportDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-07-26 10:04:49
 */
public interface IreportService {
	
	IreportDO get(Integer ireportid);
	
	List<IreportDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(IreportDO ireport);
	
	int update(IreportDO ireport);
	
	int remove(Integer ireportid);
	
	int batchRemove(Integer[] ireportids);
}
