package com.bootdo.contract.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bootdo.contract.dao.ContractHardwareDetailDao;
import com.bootdo.contract.domain.ContractHardwareDetailDO;
import com.bootdo.contract.service.ContractHardwareDetailService;

public class ContractHardwareDetailServiceImp implements ContractHardwareDetailService {
	@Autowired
	private ContractHardwareDetailDao contractHardwareDetailDao; 
	
	@Override
	public ContractHardwareDetailDO get(String hardwareDetailId) {
		// TODO Auto-generated method stub
		return contractHardwareDetailDao.get(hardwareDetailId);
	}

	@Override
	public List<ContractHardwareDetailDO> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return contractHardwareDetailDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return contractHardwareDetailDao.count(map);
	}

	@Override
	public int save(ContractHardwareDetailDO contractHardwareDetailDO) {
		// TODO Auto-generated method stub
		return contractHardwareDetailDao.save(contractHardwareDetailDO);
	}

	@Override
	public int update(ContractHardwareDetailDO contractHardwareDetailDO) {
		// TODO Auto-generated method stub
		return contractHardwareDetailDao.update(contractHardwareDetailDO);
	}

	@Override
	public int remove(String Hardware_Detail_Id) {
		// TODO Auto-generated method stub
		return contractHardwareDetailDao.remove(Hardware_Detail_Id);
	}

	@Override
	public int batchRemove(String[] hardwareDetailId) {
		// TODO Auto-generated method stub
		return contractHardwareDetailDao.batchRemove(hardwareDetailId);
	}

}
