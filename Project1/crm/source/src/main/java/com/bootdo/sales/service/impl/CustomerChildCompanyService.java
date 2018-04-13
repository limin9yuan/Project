package com.bootdo.sales.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.sales.dao.CustomerChildCompanyDao;
import com.bootdo.sales.domain.CustomerChildCompanyDo;
@Service
public class CustomerChildCompanyService implements com.bootdo.sales.service.CustomerChildCompanyService {

	
	@Autowired
	private CustomerChildCompanyDao customerChildCompanyDao;
	@Override
	public CustomerChildCompanyDo get(String childCompanyId) {
		return customerChildCompanyDao.get(childCompanyId);
	}

	@Override
	public List<CustomerChildCompanyDo> list(Map<String, Object> map) {
		return customerChildCompanyDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return customerChildCompanyDao.count(map);
	}

	@Override
	public int save(CustomerChildCompanyDo customerChildCompany) {
		return customerChildCompanyDao.save(customerChildCompany);
	}

	@Override
	public int update(CustomerChildCompanyDo customerChildCompany) {
		return customerChildCompanyDao.update(customerChildCompany);
	}

	@Override
	public int remove(String Child_Company_ID) {
		return customerChildCompanyDao.remove(Child_Company_ID);
	}

	@Override
	public int batchRemove(String[] childCompanyIds) {
		return customerChildCompanyDao.batchRemove(childCompanyIds);
	}

	
	//-----------------------------------------------------------------------------------
	//审批处理保存
	@Override
	public int formUpdate(CustomerChildCompanyDo customerChildCompany) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> Import(File file, long userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerChildCompanyDo> listDic() {
		return customerChildCompanyDao.listDic();
	}

	

}
