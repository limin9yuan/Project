package com.bootdo.material.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Mingyuan Li on 2018/8/22.
 * Package name: com.bootdo.material.controller.
 * Project name: dxcms.
 */

@Controller
@RequestMapping("/addRequirementPlan/requirementPlan")
public class AddRequirementPlanController {


	@GetMapping()
	@RequiresPermissions("addRequirementPlan:requirementPlan")
	String addRequirementPlan(){
		return "material/requirementPlan/add";
	}


}
