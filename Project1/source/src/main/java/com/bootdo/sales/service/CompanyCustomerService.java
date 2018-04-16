package com.bootdo.sales.service;

import com.bootdo.common.domain.DictDO;
import com.bootdo.inner.domain.InnerOrgEmployeeDO;
import com.bootdo.sales.domain.CompanyCustomerDO;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

/**
 * 企业客户信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-16 11:25:16
 */
public interface CompanyCustomerService {
	
	CompanyCustomerDO get(String customerId);
	
	List<CompanyCustomerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CompanyCustomerDO companyCustomer);
	
	int update(CompanyCustomerDO companyCustomer);
	
	int remove(String customerId);
	
	int batchRemove(String[] customerIds);
	
	List<DictDO> listDic();
	
	List<DictDO> listAllDicByArea(String areaId);
	
	Map<String, Object> Import(File file,long userid) ;
    List<CompanyCustomerDO> getQuery(Map<String, Object> params);
	
	public void export(String[] titles, ServletOutputStream out, List<CompanyCustomerDO> list);
}