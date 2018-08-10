package com.bootdo.sales.service.impl;

import com.bootdo.budget.domain.BudgetDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.DictDO;
import com.bootdo.sales.dao.CustomerContactDao;
import com.bootdo.sales.dao.ProvinceDao;
import com.bootdo.sales.domain.ProvinceDO;
import com.bootdo.sales.service.ProvinceService;



@Service
public class ProvinceServiceImpl implements ProvinceService {
	@Autowired
	private ProvinceDao provinceDao;
	
	@Override	
	public List<DictDO> listDic(){
		return provinceDao.listDic();
	}

	@Override
	public List<DictDO> listCityDic(String provinceId) {
		return provinceDao.listCityDic(provinceId);
	}

	@Override
	public List<DictDO> listAreaDic(String cityId) {
		return provinceDao.listAreaDic(cityId);
	}

//	@Override
//	public ProvinceDO getProvince(String budgetId){
//		return provinceDao.getProvince(budgetId);
//	}
//
//
//	@Override
//	public ProvinceDO getCity(String budgetId){
//		return provinceDao.getCity(budgetId);
//	}
//
//	@Override
//	public ProvinceDO getArea(String budgetId){
//		return provinceDao.getArea(budgetId);
//	}


}
