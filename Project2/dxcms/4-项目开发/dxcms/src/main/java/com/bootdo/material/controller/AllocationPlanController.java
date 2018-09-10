package com.bootdo.material.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mingyuan Li on 2018/9/4.
 * Package name: com.bootdo.material.controller.
 * Project name: dxcms.
 */
@Controller
@RequestMapping("/allocationPlan/allocationPlan")
public class AllocationPlanController {

    @GetMapping()
    @RequiresPermissions("allocationPlan:allocationPlan")
    String allocationPlan(){
        return "material/allocationPlan/allocationPlan";
    }

    @GetMapping("/add")
    @RequiresPermissions("allocationPlan:add")
    String addAllocationPlan(){
        return "material/allocationPlan/add";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("allocationPlan:allocationPlan")
    public PageUtils list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<Map<String, Object>> allocationPlanList = new ArrayList<>();//调用接口
        for(int i=1;i<11;i++){
            //做测试数据 调用接口前使用 begin
            Map<String, Object> requirePlanMap = new HashMap<>();
            requirePlanMap.put("status","状态"+i);
            requirePlanMap.put("planNo","编号"+i);
            requirePlanMap.put("name","名称"+i);
            requirePlanMap.put("authorDept","编制部门"+i);
            requirePlanMap.put("budgetMoney","1000");
            requirePlanMap.put("totalMoney","2000");
            requirePlanMap.put("authorUser","编制人"+i);
            requirePlanMap.put("createDate","2018/8/27");
            allocationPlanList.add(requirePlanMap);
        }
        int total = 20;//调用接口
        PageUtils pageUtils = new PageUtils(allocationPlanList, total);
        return pageUtils;
    }

    @ResponseBody
    @GetMapping("/allocationPlanAddList")
    @RequiresPermissions("allocationPlan:allocationPlan")
    public PageUtils allocationPlanAddList(@RequestParam Map<String, Object> params){
        //查询列表数据
//		Query query = new Query(params);
        List<Map<String, Object>> allocationPlanAddList = new ArrayList<>();//调用接口
        for(int i=1;i<21;i++){
            //做测试数据 调用接口前使用 begin
            Map<String, Object> requireMap = new HashMap<>();
            requireMap.put("purchasePlanItemId",i);
            requireMap.put("materialName","物资A"+i);
            requireMap.put("materilaCode","物资编码"+i);
            requireMap.put("brand","型号"+i);
            requireMap.put("materialUnitName","单位"+i);
            requireMap.put("requireQty","1000");
            requireMap.put("requireDept","需求部门"+i);
            requireMap.put("requireDate","2018/8/27");
            requireMap.put("createDate","2018/8/20");
            requireMap.put("remark","sb");
            allocationPlanAddList.add(requireMap);
        }
        int total = 20;//调用接口
        PageUtils pageUtils = new PageUtils(allocationPlanAddList, total);
        return pageUtils;
    }

    @GetMapping("/nextStep/{purchasePlanId}")
    @RequiresPermissions("allocationPlan:add")
    String nextStep(@PathVariable("purchasePlanId") String purchasePlanId, Model model){
//        String tabNames = "办公用品类,机物料类,单独采购";
//        model.addAttribute("tabNames",tabNames);
        model.addAttribute("purchasePlanId",purchasePlanId);

        return "material/allocationPlan/nextStep";
    }

    @ResponseBody
    @GetMapping("/getAllocationPlanDetail")
    @RequiresPermissions("allocationPlan:add")
    Map<String, Object> getSourcePlanDetail(@RequestParam("code") String code){
        List<Map<String, Object>> getAllocationPlanDetailList = new ArrayList<>();//调用接口
        List<Map<String, Object>> supplierList = new ArrayList<>();//调用接口
        List<String> categoryList = new ArrayList<>();//调用接口
//        List<List> returnList = new ArrayList<>();
        String codeArray[] = code.split(",");
        //做测试数据 调用接口前使用 begin
        for (int i = 0; i < 10; i++) {
            Map<String, Object> allocationMap = new HashMap<>();
            if (i < 7){
                if (i < 4){
                    allocationMap.put("materialClassName", "办公用品类");

                }
                else {
                    allocationMap.put("materialClassName", "机物料类");

                }
                if (i % 2 == 0){
                    allocationMap.put("companyName", "供应商" + 1);
                    allocationMap.put("authorDeptName", "需求机构" + i);
                    allocationMap.put("materialName", "物料名称" + i);
                    allocationMap.put("materilaCode", "物料编码" + i);
                    allocationMap.put("specification", "物料特性" + i);
                    allocationMap.put("materialUnitName","单位");
                    allocationMap.put("purchaseQty","采购数量");
                    allocationMap.put("unitPrice","4000");
                    allocationMap.put("allotQty","200");
                    allocationMap.put("allotRatio","50");
                }
                if (i % 2 == 1){
                    allocationMap.put("companyName", "供应商" + 2);
                    allocationMap.put("authorDeptName", "需求机构" + i);
                    allocationMap.put("materialName", "物料名称" + i);
                    allocationMap.put("materilaCode", "物料编码" + i);
                    allocationMap.put("specification", "物料特性" + i);
                    allocationMap.put("materialUnitName","单位");
                    allocationMap.put("purchaseQty","采购数量");
                    allocationMap.put("unitPrice","60");
                    allocationMap.put("allotQty","99");
                    allocationMap.put("allotRatio","15");
                }
            }else {
                allocationMap.put("companyName", "供应商" + i);
                allocationMap.put("authorDeptName", "需求机构" + i);
                allocationMap.put("materialClassName", "单独采购");
                allocationMap.put("materialName", "物料名称" + i);
                allocationMap.put("materilaCode", "物料编码" + i);
                allocationMap.put("specification", "物料特性" + i);
                allocationMap.put("materialUnitName","单位");
                allocationMap.put("purchaseQty","采购数量");
                allocationMap.put("unitPrice","124");
                allocationMap.put("allotQty","78");
                allocationMap.put("allotRatio","6");
            }

            getAllocationPlanDetailList.add(allocationMap);
        }

        for(int i = 0; i < getAllocationPlanDetailList.size(); i++){
            Map<String, Object> supplierMap = new HashMap<>();
            supplierMap.put("companyName",getAllocationPlanDetailList.get(i).get("companyName"));
            supplierMap.put("unitPrice",getAllocationPlanDetailList.get(i).get("unitPrice"));
            supplierMap.put("allotQty",getAllocationPlanDetailList.get(i).get("allotQty"));
            supplierMap.put("allotRatio",getAllocationPlanDetailList.get(i).get("allotRatio"));
            supplierMap.put("materialClassName", getAllocationPlanDetailList.get(i).get("materialClassName"));
            supplierList.add(supplierMap);
        }

        List<String> tempList = new ArrayList<>();
        for (int i = 0; i < getAllocationPlanDetailList.size(); i++){
            tempList.add((String) getAllocationPlanDetailList.get(i).get("materialClassName"));

        }
        for(String str : tempList) {
            if(!categoryList.contains(str)) {
                categoryList.add(str);
            }
        }
        List<List> returnList = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++){
            List<Map<String, Object>> temp = new ArrayList<>();
            for (int j = 0; j < getAllocationPlanDetailList.size(); j++){
                if (categoryList.get(i) == getAllocationPlanDetailList.get(j).get("materialClassName")){
                    temp.add(getAllocationPlanDetailList.get(j));
                }
            }
            returnList.add(temp);
        }

        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("returnList", returnList);
        returnData.put("supplierList",supplierList);
        returnData.put("categoryList",categoryList);
        return returnData;

    }




//    @ResponseBody
//    @GetMapping("/addLi")
//    @RequiresPermissions("allocationPlan:add")
//    Map<String, Object> addLi(){
//        List<Map<String, Object>> getList = new ArrayList<>();//调用接口
//        //做测试数据 调用接口前使用 begin
//        for (int i = 0; i < 3; i++) {
//            Map<String, Object> listMap = new HashMap<>();
//            listMap.put("id", "id" +i);
//            listMap.put("name", "办公用品类" + i);
//            getList.add(listMap);
//        }
//        Map<String, Object> returnData = new HashMap<String, Object>();
//        returnData.put("getList", getList);
//
//        return returnData;
//
//    }
//
//    @ResponseBody
//    @GetMapping("/addSingleLi")
//    @RequiresPermissions("allocationPlan:add")
//    Map<String, Object> addSingleLi(){
//        //做测试数据 调用接口前使用 begin
//
//        Map<String, Object> singleMap = new HashMap<>();
//        singleList.put("id", "purchase");
//        singleList.put("name", "单独采购");
//
//        Map<String, Object> returnData = new HashMap<String, Object>();
//        returnData.put("singleList", singleList);
//
//        return returnData;
//
//    }
}
