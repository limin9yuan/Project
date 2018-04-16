package com.bootdo.project.dao;


import com.bootdo.common.domain.DictDO;
import com.bootdo.project.domain.ProductCategoryDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 产品分类信息表
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-29 11:15:42
 */
@Mapper
public interface ProductCategoryDao {

	ProductCategoryDO get(String productId);
	
	List<ProductCategoryDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProductCategoryDO productCategory);
	
	int update(ProductCategoryDO productCategory);
	
	int remove(String Product_ID);
	
	int batchRemove(String[] productIds);
	
	List<DictDO> listDic(); 
}
