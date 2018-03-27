package com.bootdo.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.DictDO;
import com.bootdo.project.dao.ModuleCategoryDao;
import com.bootdo.project.domain.ModuleCategoryDO;
import com.bootdo.project.service.ModuleCategoryService;



@Service
public class ModuleCategoryServiceImpl implements ModuleCategoryService {
	@Autowired
	private ModuleCategoryDao moduleCategoryDao;
	
	@Override
	public ModuleCategoryDO get(String moduleId){
		return moduleCategoryDao.get(moduleId);
	}
	
	@Override
	public List<ModuleCategoryDO> list(Map<String, Object> map){
		return moduleCategoryDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return moduleCategoryDao.count(map);
	}
	
	@Override
	public int save(ModuleCategoryDO moduleCategory){
		return moduleCategoryDao.save(moduleCategory);
	}
	
	@Override
	public int update(ModuleCategoryDO moduleCategory){
		return moduleCategoryDao.update(moduleCategory);
	}
	
	@Override
	public int remove(String moduleId){
		return moduleCategoryDao.remove(moduleId);
	}
	
	@Override
	public int batchRemove(String[] moduleIds){
		return moduleCategoryDao.batchRemove(moduleIds);
	}
	
	@Override
	public List<DictDO> listDic() {
		return moduleCategoryDao.listDic();
	}
	
}

