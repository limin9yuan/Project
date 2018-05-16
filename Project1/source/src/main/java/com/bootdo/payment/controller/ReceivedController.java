package com.bootdo.payment.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.MainCopyPersonDO;
import com.bootdo.common.service.MainCopyPersonService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.payment.domain.ReceivedDO;
import com.bootdo.payment.service.ReceivedService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.contract.domain.ReceivableDO;

/**
 * 回款信息表 
 * 开发者：小平
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-2-24
 */

@Controller
@RequestMapping("/payment/received")
public class ReceivedController extends BaseController {
	@Autowired
	private ReceivedService receivedService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private MainCopyPersonService mainCopyPersonService;

	@GetMapping()
	@RequiresPermissions("payment:received:received")
	String Received() {
		return "payment/received/received";
	}
	
	
	@GetMapping("/receivableType")
    String receivedType(){
        return "payment/received/receivableType";
    }

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("payment:received:received")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		params.put("userId", getUserId());
		params.put("userName", getUsername());
		params.put("tableName", "payment_received");
		// 查询列表数据
		params.put("receivedOperator", (getUserId()));
		params.put("Identification", (getIdentification()));
		if (params.get("saleManager") != null && params.get("saleManager") != "") {
			params.put("saleManager", "%" + params.get("saleManager") + "%");
		}
		if (params.get("projectId") != null && params.get("projectId") != "") {
			params.put("projectId", "%" + params.get("projectId") + "%");
		}
		Query query = new Query(params);
		List<ReceivedDO> receivedList = receivedService.list(query);
		int total = receivedService.count(query);
		PageUtils pageUtils = new PageUtils(receivedList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("payment:received:add")
	String add() {
		return "payment/received/add";
	}

	@GetMapping("/import")
	@RequiresPermissions("payment:received:dataImport")
	String importFile() {
		return "payment/received/import";
	}

	// edit数据绑定
	@RequestMapping("/edit_ajax/{receivedId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("receivedId") String receivedId) {
		ReceivedDO received = receivedService.get(receivedId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("received", received);
		return returnData;
	}
	
	@GetMapping("/edit/{receivedId}")
	@RequiresPermissions("payment:received:edit")
	String edit(@PathVariable("receivedId") String receivedId, Model model) {
		model.addAttribute("receivedId", receivedId);
		return "payment/received/edit";
	}

	@GetMapping("/view/{receivedId}")
	@RequiresPermissions("payment:received:edit")
	String view(@PathVariable("receivedId") String receivedId, Model model) {
		model.addAttribute("receivedId", receivedId);
		return "payment/received/view";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("payment:received:add")
	public R save(ReceivedDO received) {
		received.setReceivedOperator(getUserId());
		if (received.getReceivableDate().equals("")){
			received.setReceivableDate(null);
		}
		int receivedIds = receivedService.save(received);
		if (receivedIds > 0) {
			MainCopyPersonDO mcp = new MainCopyPersonDO();
			String mainPersonId = received.getMainPersonId();
			if (!"".equals(mainPersonId)) {
				String mainPersonIdArray[] = mainPersonId.split(",");
				for (int i = 0; i < mainPersonIdArray.length; i++) {
					mcp.setTId(received.getReceivedId());
					mcp.setMainPerson(1);
					mcp.setEmployeeId(mainPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("payment_received");
					mainCopyPersonService.save(mcp);

				}
			}

			String copyPersonId = received.getCopyPersonId();
			if (!"".equals(copyPersonId)) {
				String copyPersonIdArray[] = copyPersonId.split(",");
				for (int i = 0; i < copyPersonIdArray.length; i++) {
					mcp.setTId(received.getReceivedId());
					mcp.setMainPerson(0);
					mcp.setEmployeeId(copyPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("payment_received");
					mainCopyPersonService.save(mcp);
				}


			}
			R r = R.ok();
			r.put("receivedId", receivedIds);
			return r;
		}

		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("payment:received:edit")
	public R update(ReceivedDO received) {
		received.setReceivedOperator(getUserId());
		received.setReceivedOperator(getUserId());
		if (received.getReceivableDate().equals("")){
			received.setReceivableDate(null);
		}
		String receivedIds = received.getReceivedId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset",1);
		params.put("limit",2);
		params.put("tId",receivedIds);
		params.put("tableName","payment_received");
		receivedService.update(received);
		mainCopyPersonService.remove(params);
		if (!receivedIds.equals("")) {
			MainCopyPersonDO mcp = new MainCopyPersonDO();
			String mainPersonId = received.getMainPersonId();
			if (!"".equals(mainPersonId)) {
				String mainPersonIdArray[] = mainPersonId.split(",");

				for (int i = 0; i < mainPersonIdArray.length; i++) {
					mcp.setTId(receivedIds);
					mcp.setMainPerson(1);
					mcp.setEmployeeId(mainPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("payment_received");
					mainCopyPersonService.save(mcp);

				}
			}

			String copyPersonId = received.getCopyPersonId();
			if (!"".equals(copyPersonId)) {
				String copyPersonIdArray[] = copyPersonId.split(",");
				for (int i = 0; i < copyPersonIdArray.length; i++) {
					mcp.setTId(receivedIds);
					mcp.setMainPerson(0);
					mcp.setEmployeeId(copyPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("payment_received");
					mainCopyPersonService.save(mcp);

				}
			}

		}
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("payment:received:remove")
	public R remove(String receivedId) {
		if (receivedService.remove(receivedId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("payment:received:batchRemove")
	public R remove(@RequestParam("ids[]") String[] receivedIds) {
		receivedService.batchRemove(receivedIds);
		return R.ok();
	}

	/**
	 * 导入文件
	 */
	@ResponseBody
	@PostMapping("/dataImport")
	R upload2(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		File datafile = null;
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
			datafile = new File(bootdoConfig.getUploadPath() + fileName);
		} catch (Exception e) {
			return R.error();
		}
		long userid = getUserId(); // log数据保存 用户id
		receivedService.dataImport(datafile, userid);
		return null;
	}

}
