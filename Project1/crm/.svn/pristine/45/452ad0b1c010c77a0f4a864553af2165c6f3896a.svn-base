package com.bootdo.sales.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.bootdo.sales.domain.CustomerChildCompanyDo;

//客户组织机构_子公司
public interface CustomerChildCompanyService {
	
	CustomerChildCompanyDo get(String childCompanyId);
	
	List<CustomerChildCompanyDo> list(Map<String, Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CustomerChildCompanyDo customerChildCompany);
	
	int update(CustomerChildCompanyDo customerChildCompany);
	
	int remove(String Child_Company_ID);
	
	int batchRemove(String[] childCompanyIds);
	
	int formUpdate(CustomerChildCompanyDo customerChildCompany);
	Map<String,Object> Import(File file,long userid);
}
