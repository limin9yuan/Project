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
	public List<FileDO> listIdCustomer(String[] id) {
		// TODO Auto-generated method stub
		return sysFileMapper.listIdCustomer(id);
	}

	
}
