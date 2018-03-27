package com.bootdo.budget.dao;

import com.bootdo.budget.domain.LaborDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 项目人力安排表
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:43:07
 */
@Mapper
public interface LaborDao {

	LaborDO get(String laborId);
	
	List<LaborDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(LaborDO labor);
	
	int update(LaborDO labor);
	
	int remove(String Labor_ID);
	
	int batchRemove(String[] laborIds);
}
