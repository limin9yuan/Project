package com.bootdo.sales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.DictDO;
import com.bootdo.sales.dao.RequirementCategoryDao;
import com.bootdo.sales.domain.RequirementCategoryDO;
import com.bootdo.sales.service.RequirementCategoryService;



@Service
public class RequirementCategoryServiceImpl implements RequirementCategoryService {
	@Autowired
	private RequirementCategoryDao requirementCategoryDao;
	
	@Override
	public RequirementCategoryDO get(String requirementId){
		return requirementCategoryDao.get(requirementId);
	}
	
	@Override
	public List<RequirementCategoryDO> list(Map<String, Object> map){
		return requirementCategoryDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return requirementCategoryDao.count(map);
	}
	
	@Override
	public int save(RequirementCategoryDO requirementCategory){
		return requirementCategoryDao.save(requirementCategory);
	}
	
	@Override
	public int update(RequirementCategoryDO requirementCategory){
		return requirementCategoryDao.update(requirementCategory);
	}
	
	@Override
	public int remove(String requirementId){
		return requirementCategoryDao.remove(requirementId);
	}
	
	@Override
	public int batchRemove(String[] requirementIds){
		return requirementCategoryDao.batchRemove(requirementIds);
	}
	@Override
	public List<DictDO> listDic() {
		return requirementCategoryDao.listDic();
	}
	
}
