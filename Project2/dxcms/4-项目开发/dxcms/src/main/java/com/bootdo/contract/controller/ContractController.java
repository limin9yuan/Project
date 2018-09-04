package com.bootdo.contract.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.service.UserService;
import com.dx.client.model.contract.ContractBean;
import com.dx.client.model.contract.ContractDeliverBean;

/**
 * 合同起草
 * 
 * @author Administrator
 * @param <contractDelivers>
 * 
 * 
 */
@Controller
@RequestMapping("/ContractCreation/ContractCreation")
public class ContractController<contractDelivers> extends BaseController {
	@Autowired
	UserService userService;
//	@Autowired 
//	private ContractBean contractBean;
	/**
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ContractCreation:ContractCreation")
	@GetMapping()
	String ContractCreation(Model model) {
		Long userId = getUser().getUserId();
		String userName = getUser().getUsername();
		String deptName = getUser().getDeptName();
		Long deptId = getUser().getDeptId();
		model.addAttribute("authorUserName", userName);
		model.addAttribute("authorUserId", userId);
		model.addAttribute("authorDeptName", deptName);
		model.addAttribute("authorDeptId", deptId);
		// 适用机构 下拉列表查询数据
		List<Map<String, Object>> selectTree = new ArrayList<>();// 调用接口
		for (int i = 0; i < 4; i++) {
			Map<String, Object> contractSuits = new HashMap<>();
			// 测试数据 调用接口前使用
			contractSuits.put("id", "1" + i); // 注意*前台页面接受的必须是id、pid即name,前台引用jquery
			contractSuits.put("pId", "0"); // 树形下拉框插件MultipleTreeSelect,也必须是json数据*
			contractSuits.put("text", "火之国");
			JSONObject js = new JSONObject(contractSuits); // 将map类型转成json数据
			selectTree.add(js); // 将转换完成的json数据add到selectTree
		}
		model.addAttribute("selectTree", selectTree); // 将数据传到前台
		return "contract/ContractCreation/add";
	}

//	@PostMapping("/selectTree")
//	@ResponseBody
//	@RequiresPermissions("ContractCreation:ContractCreation")
//	String selectTree(@RequestParam Map<String, Object> params,Model model) {
//		
//		return "contract/ContractCreation/add";
//	}

	// 富文本编辑
	@PostMapping("/richText")
	@ResponseBody
	@RequiresPermissions("ContractCreation:ContractCreation")
	R richText(@RequestParam("ctrls[]") String[] ctrls) {
		System.out.println("************************");
		for (int i = 0; i < ctrls.length; i++) {// 遍历前台页面传回的数据
			String data = ctrls[i];
			System.out.println(data + "************************");
		}
		System.out.println("************************");
		return R.ok();
	}

	//
	@GetMapping("/tree")
	@ResponseBody
	public Tree<DeptDO> tree() {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		tree = userService.getTree();
		return tree;
	}

	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("ContractCreation:ContractCreation:add")
	R save(ContractBean contractbean, @RequestParam("signupForm") String signupForm) {
		System.out.println(signupForm = "************************");
		return R.error();
	}

	@GetMapping("/project")
	@RequiresPermissions("ContractCreation:ContractCreation:add")
	String project() {
		return "/contract/ContractCreation/projectList";
	}
	@GetMapping("/projectList")
	@RequiresPermissions("ContractCreation:ContractCreation:add")
	@ResponseBody
	public PageUtils projectList(@RequestParam Map<String, Object> params) {
		// 查询列表数据
//			Query query = new Query(params);
		List<Map<String, Object>> projectList = new ArrayList<>();// 调用接口
		for (int i = 1; i <= 20; i++) {
			// 做测试数据 调用接口前使用 begin
			Map<String, Object> map = new HashMap<>();
			map.put("projectId", "100" + i);
			map.put("projectName", "物资编码" + i);
			projectList.add(map);
		}
		int total = 20;// 调用接口
		PageUtils pageUtils = new PageUtils(projectList, total);
		return pageUtils;
	}
	
	@GetMapping("/contractDelivers")
	@RequiresPermissions("ContractCreation:ContractCreation:add")
	String contractDeliversList() {
		return "/contract/ContractCreation/relevantParty";
	}
	@GetMapping("/contractDeliversList")
	@RequiresPermissions("ContractCreation:ContractCreation:add")
	@ResponseBody
	public PageUtils contractDeliversList(@RequestParam Map<String, Object> params) {
		// 查询列表数据
//			Query query = new Query(params);
		List<ContractDeliverBean> contractDelivers=new ArrayList<ContractDeliverBean>();//调用接口
		
//		List<Map<String, Object>> contractDeliversList = new ArrayList<>();// 调用接口
		
		for (int i = 0; i <= 20; i++) {
			ContractDeliverBean aa=new ContractDeliverBean();
			Date nowDate = new Date();
			aa.setDeliverCompanyId("123"+i);
			aa.setDeliverCompanyName("供货公司"+i);
			aa.setId("1"+i);
			aa.setCreateDate(nowDate);
			contractDelivers.add(aa);
		}
		
		int total = 20;// 调用接口
		PageUtils pageUtils = new PageUtils(contractDelivers, total);
		return pageUtils;
	}
}
