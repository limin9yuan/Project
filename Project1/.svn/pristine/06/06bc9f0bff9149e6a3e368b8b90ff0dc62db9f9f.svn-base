package com.bootdo.sales.dao;

import com.bootdo.common.domain.DictDO;
import com.bootdo.sales.domain.CompanyCustomerDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 企业客户信息表
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-16 11:25:16
 */
@Mapper
public interface CompanyCustomerDao {

	CompanyCustomerDO get(String customerId);
	
	List<CompanyCustomerDO> list(Map<String,Object> map);
	//新客户
	List<CompanyCustomerDO> newCustomer(Map<String, Object> map);
	//旧客户
	List<CompanyCustomerDO> oldCustomer(Map<String, Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CompanyCustomerDO companyCustomer);
	
	int update(CompanyCustomerDO companyCustomer);
	
	int remove(String Customer_ID);
	
	int batchRemove(String[] customerIds);
	
	List<DictDO> listDic();
	
	List<DictDO> listAllDicByArea(String areaId);
}
