package com.bootdo.common.service;

import com.bootdo.common.domain.FileDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */

public interface FileService {

	FileDO get(String id);

	List<FileDO> list(Map<String, Object> map);

	// 企业客户
	List<FileDO> listId(Map<String, Object> map);

	// 企业客户
	int countId(Map<String, Object> map);

	int count(Map<String, Object> map);

	// 行动管理
	List<FileDO> listRecordAttachment(Map<String, Object> map);

	// 客服记录
	List<FileDO> listRecordServiceAttachment(Map<String, Object> map);

	int listRecordServiceAttachmentCount(Map<String, Object> map);

	// 客户投诉信息
	List<FileDO> listComplaintAttachment(Map<String, Object> map);

	// 客户投诉信息
	int listComplaintAttachmentCount(Map<String, Object> map);

	int countRecordAttachment(Map<String, Object> map);

	// 合同审批
	List<FileDO> listContractAttachment(Map<String, Object> map);

	// 合同审批
	int listContractAttachmentCount(Map<String, Object> map);

	// 合同增补
	List<FileDO> listAdditionalRecordAttachment(Map<String, Object> map);

	// 合同增补
	int listAdditionalRecordAttachmentCount(Map<String, Object> map);

	// 客户在线反馈
	List<FileDO> listOnlineFeedbackAttachment(Map<String, Object> map);

	// 客户在线反馈
	int listOnlineFeedbackAttachmentCount(Map<String, Object> map);

	// 开票管理
	List<FileDO> listInvoiceAttachment(Map<String, Object> map);

	// 开票管理
	int listInvoiceAttachmentCount(Map<String, Object> map);

	int save(FileDO sysFile);

	int update(FileDO sysFile);

	int remove(String id);

	int batchRemove(Long[] ids);

	List<FileDO> downLoadListId(String[] id);

	int removes(String removeId);
}
