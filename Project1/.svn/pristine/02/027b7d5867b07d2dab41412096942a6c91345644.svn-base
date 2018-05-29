package com.bootdo.approval.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public PurchaseDO view(String purchaseId){
        return purchaseDao.view(purchaseId);
    }
	
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

	/**
	 * ******************* 审批流程相关 *************************
	 */
	//审批处理保存
	@Override
	public int formUpdate(PurchaseDO purchase){
       //流程审批处理
       Map<String,Object> vars = new HashMap<>(16);
       vars.put("taskAction",  purchase.getTaskAction() );
       actTaskService.complete(purchase.getTaskId(),purchase.getProcessInstanceId(),
				 purchase.getTaskComment(),"",vars);
		//判断流程是否结束
		if(actTaskService.isProcessInstanceFinish(purchase.getProcessInstanceId())){
			purchase.setPurchaseApprovalStatus("1");
			purchase.setPurchaseApprovalTime(new Date());
		}else{
			purchase.setPurchaseApprovalStatus("0");
		}

       return purchaseDao.update(purchase);
	}
	
	@Override
	public int save(PurchaseDO purchase){
       int ret = purchaseDao.save(purchase);

       String purchaseId=purchase.getPurchaseId();
       //流程标题，每个业务根据自己特点，体现主要信息
       String title="";
       title=purchase.getPurchaseId()+"-"+purchase.getPurchasePerson();
       //添加保存时发起审批流程
       String PROCESS_INSTANCE_ID=actTaskService.startProcess(ActivitiConstant.ACTIVITI_APPROVAL_PURCHASE[0],
               ActivitiConstant.ACTIVITI_APPROVAL_PURCHASE[1],purchaseId,title,new HashMap<String,Object>());
       //更新流程实例ID到业务表
       purchase.setProcessInstanceId(PROCESS_INSTANCE_ID);
       purchaseDao.update(purchase);
		return Integer.parseInt(purchaseId);
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

	@Override
	public List<PurchaseDO> purchaseApprovalStatus(Map<String, Object> map) {
		return purchaseDao.purchaseApprovalStatus(map);
	}

	@Override
	public List<PurchaseDO> listPurchaseApprovalStatus(Map<String, Object> map) {
		return purchaseDao.listPurchaseApprovalStatus(map);
	}

	@Override
	public int countPurchaseApprovalStatus(Map<String, Object> map) {
		return purchaseDao.countPurchaseApprovalStatus(map);
	}

}