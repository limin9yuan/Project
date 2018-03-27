package com.bootdo.project.dao;


import com.bootdo.common.domain.DictDO;
import com.bootdo.project.domain.ModuleCategoryDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 模块分类信息表
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-29 11:15:05
 */
@Mapper
public interface ModuleCategoryDao {

	ModuleCategoryDO get(String moduleId);
	
	List<ModuleCategoryDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ModuleCategoryDO moduleCategory);
	
	int update(ModuleCategoryDO moduleCategory);
	
	int remove(String Module_ID);
	
	int batchRemove(String[] moduleIds);
	
	List<DictDO> listDic(); 
}

