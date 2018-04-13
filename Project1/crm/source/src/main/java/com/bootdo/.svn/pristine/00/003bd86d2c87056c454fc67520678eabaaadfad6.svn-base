package com.bootdo.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bootdo.common.controller.BaseController;
@RequestMapping("/demo")
@Controller
public class DemoController extends BaseController {
	/**
	 * demo
	 * 
	 * @author smile
	 * @email 
	 * @date 2017-11-19 18:57:25
	 */
	@GetMapping("/datePicker")
	String datePicker(){
	    return "demo/datePicker/datePicker";
	}
	@GetMapping("/tabPage")
	String tabPage(){
	    return "demo/tabPage/tabPage";
	}
}
