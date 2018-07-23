package com.bootdo.project.service;

import com.bootdo.common.domain.DictDO;
import com.bootdo.project.domain.ProductCategoryDO;

import java.util.List;
import java.util.Map;

/**
 * 产品分类信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-29 11:15:42
 */
public interface ProductCategoryService {
	
	ProductCategoryDO get(String productId);
	
	List<ProductCategoryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProductCategoryDO productCategory);
	
	int updateAttachment(ProductCategoryDO productCategory);
	
	int update(ProductCategoryDO productCategory);
	
	int remove(String productId); 
	
	int batchRemove(String[] productIds);

	List<DictDO> listDic();
}
