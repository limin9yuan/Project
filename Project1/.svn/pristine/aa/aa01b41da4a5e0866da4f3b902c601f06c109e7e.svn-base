package com.bootdo.sales.service;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.MainCopyPersonDO;
import com.bootdo.inner.domain.InnerOrgEmployeeDO;
import com.bootdo.sales.domain.CustomerContactDO;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

/**
 * 联系人信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-02 18:57:25
 */
public interface CustomerContactService {
	
	CustomerContactDO get(String contactId);
	
	List<CustomerContactDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(CustomerContactDO customerContact);
	
	int update(CustomerContactDO customerContact);
	
	int remove(String contactId);
	
	int batchRemove(String[] contactIds);
	
	List<DictDO> listDic();
	Map<String, Object> Import(File file,long userid) ;
List<CustomerContactDO> getQuery(Map<String, Object> params);
	
	public void export(String[] titles, ServletOutputStream out, List<CustomerContactDO> list);

	void export1(String[] titles, ServletOutputStream out,
			List<CustomerContactDO> list);

	List<CustomerContactDO> getQuery1(Map<String, Object> params);
	
	int saveMain(MainCopyPersonDO mainCopyPerson);
	//主送人抄送人
	List<MainCopyPersonDO> getMainAndCopyPerson(Map<String,Object> map);
}
