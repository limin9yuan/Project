 package com.bootdo.sales.service;

import com.bootdo.common.domain.DictDO;
import com.bootdo.sales.domain.BusinessDO;
import com.bootdo.sales.domain.RecordComplaintDO;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

/**
 * 客户投诉信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-28 18:06:03
 */
public interface RecordComplaintService {
	
	RecordComplaintDO get(String complaintId);
	
	List<RecordComplaintDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RecordComplaintDO recordComplaint);
	
	int update(RecordComplaintDO recordComplaint);
	
	int remove(String complaintId);
	
	int batchRemove(String[] complaintIds);
	
	List<DictDO> listDic();
	
	List<DictDO> listDicxmbh();
	
	Map<String, Object> dataImport(File file,long userid) ;
	
	List<RecordComplaintDO> getQuery(Map<String, Object> params);
	
	public void export(String[] titles, ServletOutputStream out, List<RecordComplaintDO> list);
}