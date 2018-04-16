package com.bootdo.sales.service;

import com.bootdo.sales.domain.OnlineFeedbackDO;

import java.util.List;
import java.util.Map;

/**
 * 客户在线反馈信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-27 11:51:50
 */
public interface OnlineFeedbackService {
	
	OnlineFeedbackDO get(String feedbackId);
	
	List<OnlineFeedbackDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OnlineFeedbackDO onlineFeedback);
	
	int update(OnlineFeedbackDO onlineFeedback);
	
	int remove(String feedbackId);
	
	int batchRemove(String[] feedbackIds);
}
