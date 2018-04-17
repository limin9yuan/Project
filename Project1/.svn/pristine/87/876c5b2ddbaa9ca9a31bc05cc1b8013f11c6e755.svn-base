package com.bootdo.contract.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.activiti.config.ActivitiConstant;
import com.bootdo.activiti.service.impl.ActTaskServiceImpl;
import com.bootdo.common.domain.DictDO;
import com.bootdo.contract.dao.TravelDao;
import com.bootdo.contract.domain.TravelDO;
import com.bootdo.contract.service.TravelService;
import com.bootdo.inner.domain.InnerOrgEmployeeDO;
import com.bootdo.inner.service.InnerOrgEmployeeService;



@Service
public class TravelServiceImpl implements TravelService {
	@Autowired
	private TravelDao travelDao;
	@Autowired
	private ActTaskServiceImpl actTaskService;
	@Autowired
	private InnerOrgEmployeeService innerOrgEmployeeService;
	
	@Override
	public TravelDO get(String travelId){
		return travelDao.get(travelId);
	}
	
	@Override
	public List<TravelDO> list(Map<String, Object> map){
		return travelDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return travelDao.count(map);
	}
	
	@Override
	public int save(TravelDO travel){
		if(travel.getTravelDepartureDate()!=null&&travel.getTravelDepartureDate().equals("")){
			travel.setTravelDepartureDate(null);
		}
		if(travel.getTravelReturnDate()!=null&&travel.getTravelReturnDate().equals("")){
			travel.setTravelReturnDate(null);
		}
		int ret=travelDao.save(travel);
		/*
		此处获取自增长主键ID，此ID作为流程的businessId
		需注意在SQL语句XML配置文件save中需增加：useGeneratedKeys="true" keyProperty="travelId" 用来获取数据库自增长ID
		<insert id="save" parameterType="com.bootdo.contract.domain.TravelDO" useGeneratedKeys="true" keyProperty="travelId">
		*/
		
		String travelId=travel.getTravelId();
		//流程标题，每个业务根据自己特点，体现主要信息,例：出差申请,出差人姓名+出发地+目的地
		String title="";
		InnerOrgEmployeeDO emp=innerOrgEmployeeService.get(travel.getTravelName());
		if(emp!=null){
			title=emp.getEmployeeName();
		}
		title=title+" "+travel.getTravelPlaceIssue()+"-"+travel.getTravelPlaceEnded();
		//添加保存时发起审批流程
		String PROCESS_INSTANCE_ID=actTaskService.startProcess(ActivitiConstant.ACTIVITI_CONTRACT_TRAVEL[0],ActivitiConstant.ACTIVITI_CONTRACT_TRAVEL[1],travelId,title,new HashMap<String,Object>());
		//更新流程实例ID到业务表
		travel.setProcessInstanceId(PROCESS_INSTANCE_ID);
		travelDao.update(travel);
		
		
		return ret;
	}
	
	@Override
	public int update(TravelDO travel){
		if(travel.getTravelDepartureDate()!=null&&travel.getTravelDepartureDate().equals("")){
			travel.setTravelDepartureDate(null);
		}
		if(travel.getTravelReturnDate()!=null&&travel.getTravelReturnDate().equals("")){
			travel.setTravelReturnDate(null);
		}
		
		return travelDao.update(travel);
	}
	
	@Override
	public int remove(String travelId){
		return travelDao.remove(travelId);
	}
	
	@Override
	public int batchRemove(String[] travelIds){
		return travelDao.batchRemove(travelIds);
	}
	
	@Override	
	public List<DictDO> listDic(){
		return travelDao.listDic();
	}
	/**
	 * ******************* 审批流程相关 *************************
	 */
	//审批处理保存
	@Override
	public int formUpdate(TravelDO travel){
		if(travel.getTravelDepartureDate()!=null&&travel.getTravelDepartureDate().equals("")){
			travel.setTravelDepartureDate(null);
		}
		if(travel.getTravelReturnDate()!=null&&travel.getTravelReturnDate().equals("")){
			travel.setTravelReturnDate(null);
		}
		//流程审批处理
		Map<String,Object> vars = new HashMap<>(1);
		vars.put("taskAction",  travel.getTaskAction() );
		
		//actTaskService.complete(travel.getTaskId(),vars);
		//添加审批说明
		actTaskService.complete(travel.getTaskId(),travel.getProcessInstanceId(),travel.getTaskComment(),"",vars);
		
		return travelDao.update(travel);
	}
}
