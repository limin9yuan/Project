package com.bootdo.common.dao;

import com.bootdo.common.domain.FieldDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-28 14:33:34
 */
@Mapper
public interface FieldDao {

	FieldDO get(Integer id);
	
	List<FieldDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(FieldDO field);
	
	int update(FieldDO field);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
