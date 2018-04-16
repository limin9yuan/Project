package com.bootdo.sales.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.common.domain.DictDO;

/**
 * 
 * @author smile
 * @email 1992lcg@163.com
 * @date 2017-11-23 20:15:31
 */
@Mapper
public interface ProvinceDao {

	List<DictDO> listDic();
	
	List<DictDO> listCityDic(String provinceId);
	
	List<DictDO> listAreaDic(String cityId);
}
