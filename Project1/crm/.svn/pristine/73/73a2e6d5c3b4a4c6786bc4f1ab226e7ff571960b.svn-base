package com.bootdo.approval.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.activiti.config.ActivitiConstant;
import com.bootdo.activiti.service.impl.ActTaskServiceImpl;
import com.bootdo.approval.dao.PurchaseDao;
import com.bootdo.approval.domain.PurchaseDO;
import com.bootdo.approval.service.PurchaseService;



@Service
public class PurchaseServiceImpl implements PurchaseService {
	@Autowired
	private PurchaseDao purchaseDao;
	@Autowired
	private ActTaskServiceImpl actTaskService;
	
	@Override
	public PurchaseDO get(String purchaseId){
		return purchaseDao.get(purchaseId);
	}
	
	@Override
	public List<PurchaseDO> list(Map<String, Object> map){
		return purchaseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return purchaseDao.count(map);
	}
	
	@Override
	public int save(PurchaseDO purchase){
		int ret=purchaseDao.save(purchase);
		String purchaseId=purchase.getPurchaseId();
		//添加保存时发起审批流程
		actTaskService.startProcess(ActivitiConstant.ACTIVITI_CONTRACT_PURCHASE[0],ActivitiConstant.ACTIVITI_CONTRACT_PURCHASE[1],purchaseId,purchase.getPurchasePerson(),new HashMap<String,Object>());
		
		return ret;
	}
	
	@Override
	public int update(PurchaseDO purchase){
		return purchaseDao.update(purchase);
	}
	
	@Override
	public int remove(String purchaseId){
		return purchaseDao.remove(purchaseId);
	}
	
	@Override
	public int batchRemove(String[] purchaseIds){
		return purchaseDao.batchRemove(purchaseIds);
	}
	/**
	 * ******************* 审批流程相关 *************************
	 */
	//审批处理保存
	@Override
	public int formUpdate(PurchaseDO purchase){
		//流程审批处理
		Map<String,Object> vars = new HashMap<>(16);
		vars.put("taskAction",  purchase.getTaskAction() );
		actTaskService.complete(purchase.getTaskId(),vars);
		
		return purchaseDao.update(purchase);
	}
}