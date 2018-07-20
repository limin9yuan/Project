package com.bootdo.contract.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.contract.dao.ContractSoftwareDetailDao;
import com.bootdo.contract.domain.ContractSoftwareDetailDo;
import com.bootdo.contract.service.ContractSoftwareDetailService;
@Service
public class ContractSoftwareDetailServiceImp implements ContractSoftwareDetailService {

	@Autowired
	private ContractSoftwareDetailDao contractSoftwareDetailDao;
	@Override
	public ContractSoftwareDetailDo get(String softwaresetailId) {
		// TODO Auto-generated method stub
		return contractSoftwareDetailDao.get(softwaresetailId);
	}

	@Override
	public List<ContractSoftwareDetailDo> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return contractSoftwareDetailDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return contractSoftwareDetailDao.count(map);
	}

	@Override
	public int save(ContractSoftwareDetailDo contractSoftwareDetailDo) {
		// TODO Auto-generated method stub
		return contractSoftwareDetailDao.save(contractSoftwareDetailDo);
	}

	@Override
	public int update(ContractSoftwareDetailDo contractSoftwareDetailDo) {
		// TODO Auto-generated method stub
		return contractSoftwareDetailDao.update(contractSoftwareDetailDo);
	}

	@Override
	public int remove(String softwaresetailId) {
		// TODO Auto-generated method stub
		return contractSoftwareDetailDao.remove(softwaresetailId);
	}

	@Override
	public int batchRemove(String[] softwaresetailId) {
		// TODO Auto-generated method stub
		return contractSoftwareDetailDao.batchRemove(softwaresetailId);
	}

}
