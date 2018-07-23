package com.bootdo.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.DictDO;
import com.bootdo.project.dao.ProductCategoryDao;
import com.bootdo.project.domain.ProductCategoryDO;
import com.bootdo.project.service.ProductCategoryService;



@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Override
	public ProductCategoryDO get(String productId){
		return productCategoryDao.get(productId);
	}
	
	@Override
	public List<ProductCategoryDO> list(Map<String, Object> map){
		return productCategoryDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productCategoryDao.count(map);
	}
	
	@Override
	public int save(ProductCategoryDO productCategory){
		return productCategoryDao.save(productCategory);
	}
	
	@Override
	public int update(ProductCategoryDO productCategory){
		return productCategoryDao.update(productCategory);
	}
	
	@Override
	public int remove(String productId){
		return productCategoryDao.remove(productId);
	}
	
	@Override
	public int batchRemove(String[] productIds){
		return productCategoryDao.batchRemove(productIds);
	}
	@Override
	public List<DictDO> listDic() {
		return productCategoryDao.listDic();
	}

	@Override
	public int updateAttachment(ProductCategoryDO productCategory) {
		// TODO Auto-generated method stub
		return productCategoryDao.updateAttachment(productCategory);
	}
}

