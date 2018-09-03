package com.bootdo.material.controller;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.dx.client.model.purchase.PurchasePlanItemBean;
import com.dx.client.model.purchase.PurchasePlanBean;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import java.util.*;

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

    @GetMapping("/nextStep/{materilaCode}")
    @RequiresPermissions("material:purchasePlan:add")
    String nextStep(@PathVariable("materilaCode") String materilaCode, Model model){
        model.addAttribute("materilaCode",materilaCode);

        return "material/purchasePlan/nextStep";
    }

    @ResponseBody
    @GetMapping("/getMaterialDetailByCode/{code}")
    @RequiresPermissions("material:purchasePlan:add")
    Map<String, Object> getMaterialDetailByCode(@PathVariable("code") String code){
        List<Map<String, Object>> getPurchasePlanDetailList = new ArrayList<>();//调用接口
        String codeArray[] = code.split(",");
        //做测试数据 调用接口前使用 begin
        for (int i = 0; i < codeArray.length; i++) {
            Map<String, Object> requireMap = new HashMap<>();
            requireMap.put("requirePlanid", codeArray[i]);
            requireMap.put("materialName", "物资A" + i);
            requireMap.put("materilaCode", "物资编码" + i);
            requireMap.put("specification", "规格" + i);
            requireMap.put("materialUnitName", "单位" + i);
            requireMap.put("materialSubArray", "包装物料" + i);
            requireMap.put("requireQty","25345");
            requireMap.put("purchaseQty","456");
            requireMap.put("stockQty", "47");
            requireMap.put("reserveQty", "57657");
            requireMap.put("onwayQty", "878");
            requireMap.put("budgetQty", "8768");
            requireMap.put("referencePrice", "789");
            requireMap.put("budgetPrice", "8908");
            requireMap.put("referenceAmount", "34");
            requireMap.put("requireDate","2018/8/27");
            requireMap.put("arriveDate", "2018/8/27");
            requireMap.put("purchaserName", "张三");
            requireMap.put("description", "sb");
            getPurchasePlanDetailList.add(requireMap);
        }
        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("getPurchasePlanDetailList", getPurchasePlanDetailList);

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
            purchaseMap.put("requirePlanid", codeArray[i]);
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
            purchaseMap.put("requireDate","2018/8/27");
            purchaseMap.put("arriveDate", "2018/8/27");
            purchaseMap.put("purchaserName", "张三");
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
        for(int i=1;i<11;i++){
            //做测试数据 调用接口前使用 begin
            Map<String, Object> purchaseMap = new HashMap<>();
            purchaseMap.put("materialName","物资A"+i);
            purchaseMap.put("materilaCode","物资编码"+i);
            purchaseMap.put("brand","型号"+i);
            purchaseMap.put("materialUnitName","单位"+i);
            purchaseMap.put("requireQty","1000");
            purchaseMap.put("requireDept","需求部门"+i);
            purchaseMap.put("requireDate","2018/8/27");
            purchaseMap.put("createDate","2018/8/20");
            purchaseMap.put("remark","sb");
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
        List<Map<String, Object>> purchasePlanList = new ArrayList<>();//调用接口
        for(int i=1;i<11;i++){
            //做测试数据 调用接口前使用 begin
            Map<String, Object> purchasePlanMap = new HashMap<>();
            purchasePlanMap.put("status","状态"+i);
            purchasePlanMap.put("planNo","编号"+i);
            purchasePlanMap.put("name","名称"+i);
            purchasePlanMap.put("authorDept","编制部门"+i);
            purchasePlanMap.put("purchaseDept","采购部门"+i);
            purchasePlanMap.put("budgetMoney","1000");
            purchasePlanMap.put("totalMoney","2000");
            purchasePlanMap.put("authorUser","编制人"+i);
            purchasePlanMap.put("createDate","2018/8/27");
            purchasePlanList.add(purchasePlanMap);
        }
        int total = 20;//调用接口
        PageUtils pageUtils = new PageUtils(purchasePlanList, total);
        return pageUtils;
    }


    @GetMapping("/edit/{planNo}")
    @RequiresPermissions("material:purchasePlan:edit")
    String edit(@PathVariable("planNo") String planNo,Model model){
        //PurchaseManagementDO purchase = purchaseService.get(purchaseId);
        model.addAttribute("planNo", planNo);
        return "material/purchasePlan/edit";
    }

    /**
     * 删除
     */
    @PostMapping( "/batchRemove")
    @ResponseBody
    @RequiresPermissions("material:purchasePlan:batchRemove")
    public R remove(@RequestParam("ids[]") String[] planNos){
        // PurchasePlanModel.batchRemove(planNos);
        return R.ok();
    }
    /*
    @RequestMapping("/edit_ajax/{purchaseId}")
    @ResponseBody
    Map<String, Object> edit_ajax(@PathVariable("purchaseId") String purchaseId) {
        PurchaseManagementDO purchase = purchaseService.get(purchaseId);
        Map<String, Object> returnData = new HashMap<String, Object>();
        //returnData.put("purchase", purchase);
        return returnData;
    }

    @GetMapping("/view/{purchaseId}")
    @RequiresPermissions("payment:purchaseManagement:edit")
    String view(@PathVariable("purchaseId") String purchaseId,Model model){
        //PurchaseManagementDO purchase = purchaseService.get(purchaseId);
        model.addAttribute("purchaseId", purchaseId);
        return "payment/purchaseManagement/view";
    }

    /**
     * 保存
     */
    /*@ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("payment:purchaseManagement:add")
    public R save( PurchaseManagementDO purchase){
        purchase.setPurchaseOperator(getUserId());
        int purchaseIds = purchaseService.save(purchase);
        if (purchaseIds > 0) {
            MainCopyPersonDO mcp = new MainCopyPersonDO();
            String mainPersonId = purchase.getMainPersonId();
            if (!"".equals(mainPersonId)) {
                String mainPersonIdArray[] = mainPersonId.split(",");
                for (int i = 0; i < mainPersonIdArray.length; i++) {
                    mcp.setTId(purchase.getPurchaseId());
                    mcp.setMainPerson(1);
                    mcp.setEmployeeId(mainPersonIdArray[i]);
                    mcp.setOperator(getUserId());
                    mcp.setTableName("purchase");
                    mainCopyPersonService.save(mcp);

                }
            }

            String copyPersonId = purchase.getCopyPersonId();
            if (!"".equals(copyPersonId)) {
                String copyPersonIdArray[] = copyPersonId.split(",");
                for (int i = 0; i < copyPersonIdArray.length; i++) {
                    mcp.setTId(purchase.getPurchaseId());
                    mcp.setMainPerson(0);
                    mcp.setEmployeeId(copyPersonIdArray[i]);
                    mcp.setOperator(getUserId());
                    mcp.setTableName("purchase");
                    mainCopyPersonService.save(mcp);
                }


            }
            R r = R.ok();
            r.put("purchaseId", purchaseIds);
            return r;
        }
        return R.error();
    }*/
    /**
     * 修改
     */
   /* @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("payment:purchaseManagement:edit")
    public R update( PurchaseManagementDO purchase){
        purchase.setPurchaseOperator(getUserId());
        String purchaseIds = purchase.getPurchaseId();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset",1);
        params.put("limit",2);
        params.put("tId",purchaseIds);
        params.put("tableName","purchase");
        purchaseService.update(purchase);
        mainCopyPersonService.remove(params);
        if (!purchaseIds.equals("")) {
            MainCopyPersonDO mcp = new MainCopyPersonDO();
            String mainPersonId = purchase.getMainPersonId();
            if (!"".equals(mainPersonId)) {
                String mainPersonIdArray[] = mainPersonId.split(",");

                for (int i = 0; i < mainPersonIdArray.length; i++) {
                    mcp.setTId(purchaseIds);
                    mcp.setMainPerson(1);
                    mcp.setEmployeeId(mainPersonIdArray[i]);
                    mcp.setOperator(getUserId());
                    mcp.setTableName("purchase");
                    mainCopyPersonService.save(mcp);

                }
            }

            String copyPersonId = purchase.getCopyPersonId();
            if (!"".equals(copyPersonId)) {
                String copyPersonIdArray[] = copyPersonId.split(",");
                for (int i = 0; i < copyPersonIdArray.length; i++) {
                    mcp.setTId(purchaseIds);
                    mcp.setMainPerson(0);
                    mcp.setEmployeeId(copyPersonIdArray[i]);
                    mcp.setOperator(getUserId());
                    mcp.setTableName("purchase");
                    mainCopyPersonService.save(mcp);

                }
            }

        }
        return R.ok();
    }
*/
    /**
     * 删除
     */
   /* @PostMapping( "/remove")
    @ResponseBody
    @RequiresPermissions("payment:purchaseManagement:remove")
    public R remove( String purchaseId){
        if(purchaseService.remove(purchaseId)>0){
            return R.ok();
        }
        return R.error();
    }*/

}

