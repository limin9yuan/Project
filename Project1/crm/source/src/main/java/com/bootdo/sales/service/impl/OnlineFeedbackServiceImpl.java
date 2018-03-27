package com.bootdo.sales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.sales.dao.OnlineFeedbackDao;
import com.bootdo.sales.domain.OnlineFeedbackDO;
import com.bootdo.sales.service.OnlineFeedbackService;



@Service
public class OnlineFeedbackServiceImpl implements OnlineFeedbackService {
	@Autowired
	private OnlineFeedbackDao onlineFeedbackDao;
	
	@Override
	public OnlineFeedbackDO get(String feedbackId){
		return onlineFeedbackDao.get(feedbackId);
	}
	
	@Override
	public List<OnlineFeedbackDO> list(Map<String, Object> map){
		return onlineFeedbackDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return onlineFeedbackDao.count(map);
	}
	
	@Override
	public int save(OnlineFeedbackDO onlineFeedback){
		return onlineFeedbackDao.save(onlineFeedback);
	}
	
	@Override
	public int update(OnlineFeedbackDO onlineFeedback){
		return onlineFeedbackDao.update(onlineFeedback);
	}
	
	@Override
	public int remove(String feedbackId){
		return onlineFeedbackDao.remove(feedbackId);
	}
	
	@Override
	public int batchRemove(String[] feedbackIds){
		return onlineFeedbackDao.batchRemove(feedbackIds);
	}
	
}
