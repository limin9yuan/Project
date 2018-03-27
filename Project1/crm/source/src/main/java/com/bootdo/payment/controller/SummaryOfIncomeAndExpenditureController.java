package com.bootdo.payment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bootdo.payment.domain.SummaryOfIncomeAndExpenditureDO;
import com.bootdo.payment.service.SummaryOfIncomeAndExpenditureService;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 收支情况总汇
 
 * @小平
 * @email 1992lcg@163.com
 * @date 2018-2-7
 */

@Controller
@RequestMapping("/payment/summaryOfIncomeAndExpenditure")
public class SummaryOfIncomeAndExpenditureController extends BaseController {
	@Autowired
	private SummaryOfIncomeAndExpenditureService summaryOfIncomeAndExpenditureService;

	@GetMapping()
	@RequiresPermissions("payment:summaryOfIncomeAndExpenditure:summaryOfIncomeAndExpenditure")
	String Contract() {
		return "payment/projectExpenditure/summaryOfIncomeAndExpenditure";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("payment:summaryOfIncomeAndExpenditure:summaryOfIncomeAndExpenditure")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<SummaryOfIncomeAndExpenditureDO> projectExpenditureList = (List<SummaryOfIncomeAndExpenditureDO>) summaryOfIncomeAndExpenditureService.list(query);
		int total = summaryOfIncomeAndExpenditureService.count(query);
		PageUtils pageUtils = new PageUtils(projectExpenditureList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listByType() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = summaryOfIncomeAndExpenditureService.listDic();
		return dictList;
	}
	
	@GetMapping("/edit/{projectId}")
	@RequiresPermissions("payment:summaryOfIncomeAndExpenditure:edit")
	String edit(@PathVariable("projectId") String projectId, Model model) {
		SummaryOfIncomeAndExpenditureDO summaryOfIncomeAndExpenditure = summaryOfIncomeAndExpenditureService.get(projectId);
		model.addAttribute("payment", summaryOfIncomeAndExpenditure);
		return "payment/summaryOfIncomeAndExpenditure/edit";
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("payment:summaryOfIncomeAndExpenditure:edit")
	public R update(SummaryOfIncomeAndExpenditureDO projectExpenditure) {
		projectExpenditure.setProjectId(getUserId());
		summaryOfIncomeAndExpenditureService.update(projectExpenditure);
		return R.ok();
	}

}
