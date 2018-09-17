package com.bootdo.material.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import com.bootdo.common.controller.BaseController;

@Controller
@RequestMapping("/material/purchasePlan")

public class PurchasePlanController extends BaseController {
    private String prefix="material/purchasePlan"  ;
    @GetMapping()
    @RequiresPermissions("material:purchasePlan:purchasePlan")
    String purchasePlan(){
        return "material/purchasePlan/purchasePlan";
    }
    @GetMapping("/add")
    @RequiresPermissions("material:purchasePlan:add")
    String add(){
        return "material/purchasePlan/add";
    }

    @GetMapping("/addMore")
    @RequiresPermissions("material:purchasePlan:add")
    String addMore(){
        return "material/purchasePlan/addMore";
    }

    @GetMapping("/nextStep/{requirePlanid}")
    @RequiresPermissions("material:purchasePlan:add")
    String nextStep(@PathVariable("requirePlanid") String requirePlanid, Model model){
        model.addAttribute("requirePlanid",requirePlanid);

        return "material/purchasePlan/nextStep";
    }

    @GetMapping("/check/{planNo}")
    @RequiresPermissions("material:purchasePlan:check")
    String check(@PathVariable("planNo") String planNo, Model model){
        model.addAttribute("planNo",planNo);
        model.addAttribute("businessDate","2018/8/27");
        model.addAttribute("authorUser","编制人");
        model.addAttribute("createDate","2018/8/27");
        model.addAttribute("remark","sb");

        return "material/purchasePlan/check";
    }

    @RequestMapping("/check_ajax/{planNo}")
    @ResponseBody
    Map<String, Object> check_ajax(@PathVariable("planNo") String planNo) {
        List<Map<String, Object>> checkList = new ArrayList<>();//调用接口
        //做测试数据 调用接口前使用 begin
        for (int i = 1; i < 6; i++) {
            Map<String, Object> purchaseMap = new HashMap<>();
            //purchaseMap.put("requirePlanid", "物资编码" + i);
            purchaseMap.put("materialType", "物料类别" + i);
            purchaseMap.put("materialName", "物资A" + i);
            purchaseMap.put("materilaCode", "物资编码" + i);
            purchaseMap.put("specification", "规格" + i);
            purchaseMap.put("materialUnitName", "单位" + i);
            purchaseMap.put("materialSubArray", "包装物料" + i);
            purchaseMap.put("requireQty","25345");
            purchaseMap.put("purchaseQty","456");
            purchaseMap.put("stockQty", "47");
            purchaseMap.put("reserveQty", "57657");
            purchaseMap.put("onwayQty", "878");
            purchaseMap.put("budgetQty", "8768");
            purchaseMap.put("referencePrice", "789");
            purchaseMap.put("budgetPrice", "8908");
            purchaseMap.put("referenceAmount", "34");
            //purchaseMap.put("requireDate","2018/8/27");
            purchaseMap.put("arriveDate", "2018/8/27");
            //purchaseMap.put("purchaserName", "张三");
            purchaseMap.put("requireDept", "需求部门" + i);
            purchaseMap.put("requirePlanid", "需求计划编号" + i);
            purchaseMap.put("description", "sb");
            checkList.add(purchaseMap);
        }
        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("checkList", checkList);
        return returnData;
    }

    @GetMapping("/edit/{planNo}")
    @RequiresPermissions("material:purchasePlan:edit")
    String edit(@PathVariable("planNo") String planNo, Model model){
        model.addAttribute("planNo",planNo);
        model.addAttribute("businessDate","2018/8/27");
        model.addAttribute("authorUser","编制人");
        model.addAttribute("createDate","2018/8/27");
        model.addAttribute("remark","sb");

        return "material/purchasePlan/edit";
    }

    @RequestMapping("/edit_ajax/{planNo}")
    @ResponseBody
    Map<String, Object> edit_ajax(@PathVariable("planNo") String planNo) {
        List<Map<String, Object>> editList = new ArrayList<>();//调用接口
        //做测试数据 调用接口前使用 begin
        for (int i = 1; i < 6; i++) {
            Map<String, Object> purchaseMap = new HashMap<>();
            //purchaseMap.put("requirePlanid", "物资编码" + i);
            purchaseMap.put("materialType", "物料类别" + i);
            purchaseMap.put("materialName", "物资A" + i);
            purchaseMap.put("materilaCode", "物资编码" + i);
            purchaseMap.put("specification", "规格" + i);
            purchaseMap.put("materialUnitName", "单位" + i);
            purchaseMap.put("materialSubArray", "包装物料" + i);
            purchaseMap.put("requireQty","25345");
            purchaseMap.put("purchaseQty","456");
            purchaseMap.put("stockQty", "47");
            purchaseMap.put("reserveQty", "57657");
            purchaseMap.put("onwayQty", "878");
            purchaseMap.put("budgetQty", "8768");
            purchaseMap.put("referencePrice", "789");
            purchaseMap.put("budgetPrice", "8908");
            purchaseMap.put("referenceAmount", "34");
            //purchaseMap.put("requireDate","2018/8/27");
            purchaseMap.put("arriveDate", "2018/8/27");
           // purchaseMap.put("purchaserName", "张三");
            purchaseMap.put("requireDept", "需求部门" + i);
            purchaseMap.put("requirePlanid", "需求计划编号" + i);
            purchaseMap.put("description", "sb");
            editList.add(purchaseMap);
        }
        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("editList", editList);
        return returnData;
    }

    @ResponseBody
    @GetMapping("/getMaterialDetailByCode/{code}")
    @RequiresPermissions("material:purchasePlan:add")
    Map<String, Object> getMaterialDetailByCode(@PathVariable("code") String code){
        List<Map<String, Object>> getPurchasePlanDetailList = new ArrayList<>();//调用接口
        String codeArray[] = code.split(",");
        //做测试数据 调用接口前使用 begin
        for (int i = 0; i < codeArray.length; i++) {
            Map<String, Object> purchaseMap = new HashMap<>();
            //purchaseMap.put("requirePlanid", codeArray[i]);
            purchaseMap.put("materialType", "物料类别" + i);
            purchaseMap.put("materialName", "物资A" + i);
            purchaseMap.put("materilaCode", "物资编码" + i);
            purchaseMap.put("specification", "规格" + i);
            purchaseMap.put("materialUnitName", "单位" + i);
            purchaseMap.put("materialSubArray", "包装物料" + i);
            purchaseMap.put("requireQty","25345");
            purchaseMap.put("purchaseQty","5");
            purchaseMap.put("stockQty", "47");
            purchaseMap.put("reserveQty", "57657");
            purchaseMap.put("onwayQty", "878");
            purchaseMap.put("budgetQty", "7");
            purchaseMap.put("referencePrice", "100");
            purchaseMap.put("budgetPrice", "200");
            purchaseMap.put("referenceAmount", "500");
            //purchaseMap.put("requireDate","2018/8/27");
            purchaseMap.put("arriveDate", "2018/8/27");
            //purchaseMap.put("purchaserName", "张三");
            purchaseMap.put("requireDept", "需求部门" + i);
            purchaseMap.put("requirePlanid", codeArray[i]);
            purchaseMap.put("description", "sb");
            getPurchasePlanDetailList.add(purchaseMap);
        }
        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("getPurchasePlanDetailList", getPurchasePlanDetailList);

        return returnData;

    }

    @ResponseBody
    @GetMapping("/purchasePlanGroup")
    @RequiresPermissions("material:purchasePlan:add")
    Map<String, Object> purchasePlanGroup(@RequestParam("code") String code){
        List<Map<String, Object>> purchasePlanGroup = new ArrayList<>();//调用接口
        String codeArray[] = code.split(",");
        //做测试数据 调用接口前使用 begin

        int type1=0;
        int type1cnt=0;
        int budgetAmount=0;
        for (int i = 0; i < codeArray.length; i++) {
            Map<String, Object> purchaseMap = new HashMap<>();
            //purchaseMap.put("requirePlanid", codeArray[i]);
            if(i<=3) {
                type1 = type1+ 5;
                type1cnt = type1cnt  + 500;
                budgetAmount = budgetAmount+1400;
            }
            if(i==3){
                purchaseMap.put("materialType", "物料类别1" );
                purchaseMap.put("materialUnitName", "单位1" );
                purchaseMap.put("purchaseQty",type1);
                purchaseMap.put("referenceAmount",type1cnt );
                purchaseMap.put("budgetAmount", budgetAmount);
                purchasePlanGroup.add(purchaseMap);
                type1 = 0;
                type1cnt = 0;
                budgetAmount = 0;
            }
            if(i>3) {
                type1 = type1+ 5;
                type1cnt = type1cnt  + 500;
                budgetAmount = budgetAmount+1400;
            }
            if(i==codeArray.length-1){
                purchaseMap.put("materialType", "物料类别2" );
                purchaseMap.put("materialUnitName", "单位2");
                purchaseMap.put("purchaseQty",type1);
                purchaseMap.put("referenceAmount",type1cnt );
                purchaseMap.put("budgetAmount", budgetAmount);
                purchasePlanGroup.add(purchaseMap);
            }




        }

        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("purchasePlanGroup", purchasePlanGroup);

        return returnData;

    }

    @ResponseBody
    @GetMapping("/getPurchasePlanDetail")
    @RequiresPermissions("material:purchasePlan:add")
    Map<String, Object> getPurchasePlanDetail(@RequestParam("code") String code){
        List<Map<String, Object>> getPurchasePlanDetailList = new ArrayList<>();//调用接口
        String codeArray[] = code.split(",");
        //做测试数据 调用接口前使用 begin
        for (int i = 0; i < codeArray.length; i++) {
            Map<String, Object> purchaseMap = new HashMap<>();
            //purchaseMap.put("requirePlanid", codeArray[i]);
            if(i<=3){
                purchaseMap.put("materialType", "物料类别1");

                purchaseMap.put("materialUnitName", "单位1");
            }else{
                purchaseMap.put("materialType", "物料类别2");
                purchaseMap.put("materialUnitName", "单位2");
            }

            purchaseMap.put("materialName", "物资A" + i);
            purchaseMap.put("materilaCode", "物资编码" + i);
            purchaseMap.put("specification", "规格" + i);
            purchaseMap.put("materialSubArray", "包装物料" + i);
            purchaseMap.put("requireQty","100");
            purchaseMap.put("purchaseQty","5");
            purchaseMap.put("stockQty", "47");
            purchaseMap.put("reserveQty", "57657");
            purchaseMap.put("onwayQty", "878");
            purchaseMap.put("budgetQty", "7");
            purchaseMap.put("referencePrice", "100");
            purchaseMap.put("budgetPrice", "200");
            purchaseMap.put("referenceAmount", "500");
            //purchaseMap.put("requireDate","2018/8/27");
            purchaseMap.put("arriveDate", "2018/8/27");
            //purchaseMap.put("purchaserName", "张三");
            purchaseMap.put("requireDept", "需求部门" + i);
            purchaseMap.put("requirePlanid", codeArray[i]);
            purchaseMap.put("description", "sb");
            getPurchasePlanDetailList.add(purchaseMap);
        }

        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("getPurchasePlanDetailList", getPurchasePlanDetailList);

        return returnData;

    }

    @ResponseBody
    @GetMapping("/purchasePlanDetailList")
    @RequiresPermissions("material:purchasePlan:purchasePlan")
    public PageUtils purchasePlanDetailList(@RequestParam Map<String, Object> params){
        //查询列表数据
//		Query query = new Query(params);
        List<Map<String, Object>> purchasePlanDetailList = new ArrayList<>();//调用接口
        for(int i=1;i<21;i++){
            //做测试数据 调用接口前使用 begin
            Map<String, Object> purchaseMap = new HashMap<>();
            purchaseMap.put("requirePlanid","需求计划编号"+i);
            purchaseMap.put("materialName","物资A"+i);
            purchaseMap.put("materilaCode","物资编码"+i);
            purchaseMap.put("specification","型号"+i);
            purchaseMap.put("materialUnitName","单位"+i);
            purchaseMap.put("referencePrice","10"+i);
            purchaseMap.put("requireQty","1000");
            purchaseMap.put("requireDept","需求部门"+i);
            purchaseMap.put("arriveDate","2018/8/27");
            purchaseMap.put("createDate","2018/8/20");
            purchaseMap.put("description","sb");
            purchasePlanDetailList.add(purchaseMap);
        }
        int total = 20;//调用接口
        PageUtils pageUtils = new PageUtils(purchasePlanDetailList, total);
        return pageUtils;
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("material:purchasePlan:purchasePlan")
    public PageUtils list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<Map<String, Object>> purchasePlanDetailList = new ArrayList<>();//调用接口
        for(int i=1;i<11;i++){
            //做测试数据 调用接口前使用 begin
            Map<String, Object> purchaseMap = new HashMap<>();
            purchaseMap.put("statusName","状态"+i);
            purchaseMap.put("planNo","编号"+i);
            purchaseMap.put("name","名称"+i);
            purchaseMap.put("authorDeptName","编制部门"+i);
            //purchaseMap.put("purchaseDept","采购部门"+i);
            purchaseMap.put("budgetMoney","1000");
            purchaseMap.put("totalMoney","2000");
            purchaseMap.put("authorUserName","编制人"+i);
            purchaseMap.put("createDate","2018/8/27");
            purchasePlanDetailList.add(purchaseMap);
        }
        int total = 20;//调用接口
        PageUtils pageUtils = new PageUtils(purchasePlanDetailList, total);
        return pageUtils;
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("material:purchasePlan:add")
    public R save(@RequestParam Map<String, Object> params){
        System.out.println(params);
        //int contactIds = service.save(customerContact);

        return R.ok();
    }
    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("material:purchasePlan:add")
    public R update(@RequestParam Map<String, Object> params){
        System.out.println(params);
        //int contactIds = service.save(customerContact);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping( "/remove")
    @ResponseBody
    @RequiresPermissions("material:purchasePlan:remove")
    public R remove( String id){
        System.out.println(id);
        //int contactIds = service.save(customerContact);

        return R.ok();
    }

    /**
     * 提交审批
     */
    @PostMapping( "/submitApproval")
    @ResponseBody
    @RequiresPermissions("material:purchasePlan:submitApproval")
    public R submitApproval( String planNo){
        System.out.println(planNo);
        //int contactIds = service.save(customerContact);

        return R.ok();
    }
    /**
     * 撤回审批
     */
    @PostMapping( "/withdrawApproval")
    @ResponseBody
    @RequiresPermissions("material:purchasePlan:withdrawApproval")
    public R withdrawApproval( String planNo){
        System.out.println(planNo);
        //int contactIds = service.save(customerContact);

        return R.ok();
    }
}
