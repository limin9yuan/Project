package com.bootdo.sales.dao;

import com.bootdo.sales.domain.BugCategoryDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * BUG分类信息表
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-29 10:13:20
 */
@Mapper
public interface BugCategoryDao {

	BugCategoryDO get(String bugId);
	
	List<BugCategoryDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(BugCategoryDO bugCategory);
	
	int update(BugCategoryDO bugCategory);
	
	int remove(String BUG_ID);
	
	int batchRemove(String[] bugIds);
}
