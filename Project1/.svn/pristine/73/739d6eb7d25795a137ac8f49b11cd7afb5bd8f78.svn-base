package com.bootdo.sales.service.impl;

import com.bootdo.system.dao.DeptDao;
import com.bootdo.system.domain.DeptDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.sales.dao.CustomerDeptDao;
import com.bootdo.sales.domain.CompanyCustomerDO;
import com.bootdo.sales.domain.CustomerDeptDO;
import com.bootdo.sales.service.CustomerDeptService;



@Service
public class CustomerDeptServiceImpl implements CustomerDeptService {
	@Autowired
	private CustomerDeptDao customerDeptDao;
	@Autowired
	private CustomerDeptDao customerDeptTree;
	@Autowired
	private DeptDao sysDeptMapper;
	@Override
	public CustomerDeptDO get(String customerDeptId){
		return customerDeptDao.get(customerDeptId);
	}
	
	@Override
	public List<CustomerDeptDO> list(Map<String, Object> map){
		return customerDeptDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return customerDeptDao.count(map);
	}
	
	@Override
	public int save(CustomerDeptDO customerDept){
		return customerDeptDao.save(customerDept);
	}
	
	@Override
	public int update(CustomerDeptDO customerDept){
		return customerDeptDao.update(customerDept);
	}
	
	@Override
	public int remove(String customerDeptId){
		return customerDeptDao.remove(customerDeptId);
	}
	
	@Override
	public int batchRemove(String[] customerDeptIds){
		return customerDeptDao.batchRemove(customerDeptIds);
	}

	@Override
	public List<CustomerDeptDO> listTree(Map<String, Object> params) {

		List<CustomerDeptDO> customerDepts = customerDeptDao.customerList(params);

		return customerDepts;
	}

	@Override
	public Tree<DeptDO> getTree() {
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		List<CustomerDeptDO> customerDepts = customerDeptTree.listTree(new HashMap<String,Object>(16));
		for (CustomerDeptDO customerDept : customerDepts) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(customerDept.getCustomerDeptId().toString());
			tree.setParentId(customerDept.getCustomerDeptParentDept().toString());
			tree.setText(customerDept.getCustomerDeptName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public Long[] listParentDept() {
		// TODO Auto-generated method stub
		return null;
	}

}
