package com.bootdo.sales.service;

import com.bootdo.common.domain.DictDO;
import com.bootdo.sales.domain.RequirementCategoryDO;

import java.util.List;
import java.util.Map;

/**
 * 需求分类信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-29 14:20:38
 */
public interface RequirementCategoryService {
	
	RequirementCategoryDO get(String requirementId);
	
	List<RequirementCategoryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RequirementCategoryDO requirementCategory);
	
	int update(RequirementCategoryDO requirementCategory);
	
	int remove(String requirementId);
	
	int batchRemove(String[] requirementIds);

	List<DictDO> listDic(); 
}
