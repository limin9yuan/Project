package com.bootdo.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.common.dao.FileDao;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;



@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileDao sysFileMapper;
	
	@Override
	public FileDO get(String id){
		return sysFileMapper.get(id);
	}
	
	@Override
	public List<FileDO> list(Map<String, Object> map){
		return sysFileMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return sysFileMapper.count(map);
	}
	
	@Override
	public int save(FileDO sysFile){
		int red=sysFileMapper.save(sysFile);
		String id=sysFile.getId();
		return Integer.parseInt(id);
	}
	
	@Override
	public int update(FileDO sysFile){
		return sysFileMapper.update(sysFile);
	}
	
	@Override
	public int remove(String id){
		return sysFileMapper.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return sysFileMapper.batchRemove(ids);
	}

	@Override
	public List<FileDO> listId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sysFileMapper.listId(map);
	}

	@Override
	public int countId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sysFileMapper.countId(map);
	}

	@Override
	public List<FileDO> listRecordAttachment(Map<String, Object> map) {
		// TODO 附件iD查看列表
		return sysFileMapper.listRecordAttachment(map);
	}

	@Override
	public int countRecordAttachment(Map<String, Object> map) {
		// TODO 附件iD查看列表
		return sysFileMapper.countRecordAttachment(map);
	}


	@Override
	public List<FileDO> downLoadListId(String[] id) {
		// TODO Auto-generated method stub
		return sysFileMapper.downLoadListId(id);
	}

	@Override
	public List<FileDO> listRecordServiceAttachment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sysFileMapper.listRecordServiceAttachment(map);
	}

	@Override
	public int listRecordServiceAttachmentCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sysFileMapper.listRecordServiceAttachmentCount(map);
	}

	@Override
	public List<FileDO> listComplaintAttachment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sysFileMapper.listComplaintAttachment(map);
	}

	@Override
	public int listComplaintAttachmentCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sysFileMapper.listComplaintAttachmentCount(map);
	}

	@Override
	public List<FileDO> listContractAttachment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sysFileMapper.listContractAttachment(map);
	}

	@Override
	public int listContractAttachmentCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sysFileMapper.listContractAttachmentCount(map);
	}

	@Override
	public List<FileDO> listAdditionalRecordAttachment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sysFileMapper.listAdditionalRecordAttachment(map);
	}

	@Override
	public int listAdditionalRecordAttachmentCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sysFileMapper.listAdditionalRecordAttachmentCount(map);
	}

	@Override
	public List<FileDO> listOnlineFeedbackAttachment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sysFileMapper.listOnlineFeedbackAttachment(map);
	}

	@Override
	public int listOnlineFeedbackAttachmentCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sysFileMapper.listOnlineFeedbackAttachmentCount(map);
	}

	@Override
	public int removes(String removeId) {
		// TODO Auto-generated method stub
		return sysFileMapper.removes(removeId);
	}

	@Override
	public List<FileDO> listInvoiceAttachment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sysFileMapper.listInvoiceAttachment(map);
	}

	@Override
	public int listInvoiceAttachmentCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sysFileMapper.listInvoiceAttachmentCount(map);
	}

	
}
