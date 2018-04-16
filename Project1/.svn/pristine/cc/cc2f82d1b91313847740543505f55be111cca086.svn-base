package com.bootdo.sales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.sales.dao.BugCategoryDao;
import com.bootdo.sales.domain.BugCategoryDO;
import com.bootdo.sales.service.BugCategoryService;



@Service
public class BugCategoryServiceImpl implements BugCategoryService {
	@Autowired
	private BugCategoryDao bugCategoryDao;
	
	@Override
	public BugCategoryDO get(String bugId){
		return bugCategoryDao.get(bugId);
	}
	
	@Override
	public List<BugCategoryDO> list(Map<String, Object> map){
		return bugCategoryDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return bugCategoryDao.count(map);
	}
	
	@Override
	public int save(BugCategoryDO bugCategory){
		return bugCategoryDao.save(bugCategory);
	}
	
	@Override
	public int update(BugCategoryDO bugCategory){
		return bugCategoryDao.update(bugCategory);
	}
	
	@Override
	public int remove(String bugId){
		return bugCategoryDao.remove(bugId);
	}
	
	@Override
	public int batchRemove(String[] bugIds){
		return bugCategoryDao.batchRemove(bugIds);
	}
	
}
