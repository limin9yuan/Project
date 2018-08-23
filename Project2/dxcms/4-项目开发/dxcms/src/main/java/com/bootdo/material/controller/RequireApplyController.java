package com.bootdo.material.controller;


import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wxcl.amy.model.MaterialModel;
import org.wxcl.amy.model.RequireApplyDetailModel;
import org.wxcl.amy.model.RequireApplyModel;

import java.util.*;

@RequestMapping("/material/requireApply")
@Controller
public class RequireApplyController extends BaseController {
    private String prefix="material/requireApply"  ;

    @RequiresPermissions("material:requireApply:requireApply")
    @GetMapping("")
    String requireApply(Model model) {
        return prefix + "/requireApply";
    }

    @GetMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<RequireApplyModel> requireApplyList = new ArrayList();//调用接口
        //做测试数据 调用接口前使用 begin
        RequireApplyModel requireApplyModel = new RequireApplyModel();
        requireApplyModel.setName("测试");
        requireApplyList.add(requireApplyModel);
        //做测试数据 end
        int total = 1;//调用接口
        PageUtils pageUtil = new PageUtils(requireApplyList, total);
        return pageUtil;
    }
    @GetMapping("/requireMaterialDetailList")
    @ResponseBody
    PageUtils requireMaterialDetailList(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<RequireApplyDetailModel> requireMaterialDetailList = new ArrayList();//调用接口
        //做测试数据 调用接口前使用 begin
        RequireApplyDetailModel requireApplyDetailModel = new  RequireApplyDetailModel();
        MaterialModel ma = new MaterialModel();
        ma.setMaterialClassName("测试物资");
        requireApplyDetailModel.setMaterial(ma);
        requireMaterialDetailList.add(requireApplyDetailModel);
        //做测试数据 end
        int total = 1;//调用接口
        PageUtils pageUtil = new PageUtils(requireMaterialDetailList, total);
        return pageUtil;
    }

}
