package com.bootdo.sales.service;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.MainCopyPersonDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.inner.domain.InnerOrgEmployeeDO;
import com.bootdo.sales.domain.CustomerContactDO;
import com.bootdo.sales.domain.CustomerDeptDO;
import com.bootdo.sales.domain.CustomerJobDO;
import com.bootdo.system.domain.DeptDO;

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

	List<CustomerContactDO> getCustomerDept(Map<String, Object> map);

	List<CustomerContactDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(CustomerContactDO customerContact);
	int saveDownloadTemplate(CustomerContactDO customerContact);

	int update(CustomerContactDO customerContact);

	int remove(String contactId);

	int batchRemove(String[] contactIds);

	List<DictDO> listDic(Map<String, Object> params);
	List<DictDO> listDicContact();
	List<DictDO> listDicjob(Map<String, Object> params);
	Map<String, Object> Import(File file, long userid);

	List<CustomerContactDO> getQuery(Map<String, Object> params);

	public void export(String[] titles, ServletOutputStream out, List<CustomerContactDO> list);

	void export1(String[] titles, ServletOutputStream out, List<CustomerContactDO> list);

	List<CustomerContactDO> getQuery1(Map<String, Object> params);
	List<CustomerJobDO>listJob(Map<String,Object> map);
	int saveMain(MainCopyPersonDO mainCopyPerson);
	Tree<DeptDO> getTree();
	// 主送人抄送人
	List<MainCopyPersonDO> getMainAndCopyPerson(Map<String, Object> map);
}
