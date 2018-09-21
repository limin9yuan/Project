
package com.bootdo.material.controller;


import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.*;
import com.dx.client.model.purchase.PurchaseOrderBean;
import com.dx.service.purchase.service.api.IPurchaseOrderService;
import com.github.pagehelper.PageInfo;
import com.dx.client.model.purchase.PurchaseOrderItemBean;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.dx.client.model.datacenter.MaterialBean;
import com.dx.client.model.purchase.RequireApplyItemBean;
import com.dx.client.model.purchase.RequireApplyBean;
import org.wxcl.amy.utils.common.ResultMsg;
//import org.wxcl.amy.utils.common.ResultMsg;

import java.math.BigDecimal;
import java.util.*;

@RequestMapping("/material/purchaseOrder")
@Controller
public class PurchaseOrderController extends BaseController {
    private String prefix="material/purchaseOrder"  ;
    @Autowired
    private IPurchaseOrderService iPurchaseOrderService;
    /**
     * 采购订单管理页
     */
    @RequiresPermissions("material:purchaseOrder:purchaseOrder")
    @GetMapping("")
    String purchaseOrder(Model model) {
        return prefix + "/purchaseOrder";
    }

    /**
     * 采购订单列表
     */
    @GetMapping("/list")
    @ResponseBody
    PageInfo list(@RequestParam Map<String, Object> params) {
       /* iPurchaseOrderService.save( new PurchaseOrderBean(),
                new ArrayList< PurchaseOrderItemBean >(),
        true);*/
        /*ResultMsg rsm = iPurchaseOrderService.search(params.get("pageNumber").toString(),
                                        params.get("pageSize").toString(),"",
                                        params);*/


        List<Map<String, Object>> requireApplyList = new ArrayList();//调用接口
        for(int i=1;i<11;i++){
            //做测试数据 调用接口前使用 begin
            Map<String, Object> materialMap = new HashMap<>();
            materialMap.put("id",i);
            materialMap.put("status","状态"+i);
            materialMap.put("planNo",i);
            materialMap.put("name","名称"+i);
            materialMap.put("authorDeptName","编制部门"+i);
            materialMap.put("purchaseDept","统管部门"+i);
            materialMap.put("budgetMoney","100"+i);
            materialMap.put("totalMoney","1000"+i);
            materialMap.put("authorUserName","编制人"+i);
            materialMap.put("createDate","2018-08-"+String.valueOf(10+i));
            requireApplyList.add(materialMap);
        }
        //做测试数据 end
        int total = 20;//调用接口
        PageInfo pageInfo = new PageInfo(requireApplyList,
                Integer.parseInt(params.get("pageNumber").toString()));
        pageInfo.setTotal(total);
        /*if("0".equals(rsm.getCode())){
            return null;
        }*/
        return pageInfo;
    }

    /**
     * 采购订单编制页
     */
    @GetMapping("/add")
    @RequiresPermissions("material:purchaseOrder:add")
    String addPurchaseOrder(Model model){
        Long createUserId =getUser().getUserId();
        String createUserName =getUser().getUsername();
        String deptName =getUser().getDeptName();
        Long deptId =getUser().getDeptId();
        String planNo = "0001001009";
        String businessDate = DateUtils.format(new Date(),DateUtils.DATE_PATTERN);
        String name = businessDate.substring(0,4)+"年"+businessDate.substring(5,7)+"月采购申请";

        model.addAttribute("name", name);//名称
        model.addAttribute("planNo", planNo);//编号
        model.addAttribute("authorCorpName", deptName); //编制机构名称
        model.addAttribute("businessDate", businessDate); //编制机构名称
        model.addAttribute("authorCorpName", deptName); //编制部门名称
        model.addAttribute("authorCorpId", deptId); //编制部门Id

        model.addAttribute("createUserId", createUserId); //编制人Id
        model.addAttribute("createUserName", createUserName); //编制人姓名
        model.addAttribute("createDate", businessDate);//编制日期

        return prefix + "/add";
    }

    /**
     * 采购订单编制页
     */
    @GetMapping("/multiAdd")
    @RequiresPermissions("material:purchaseOrder:multiAdd")
    String multiAddPurchaseOrder(Model model){
        Long createUserId =getUser().getUserId();
        String createUserName =getUser().getUsername();
        String deptName =getUser().getDeptName();
        Long deptId =getUser().getDeptId();
        String planNo = "0001001009";
        String businessDate = DateUtils.format(new Date(),DateUtils.DATE_PATTERN);
        String name = businessDate.substring(0,4)+"年"+businessDate.substring(5,7)+"月采购申请";

        model.addAttribute("name", name);//名称
        model.addAttribute("planNo", planNo);//编号
        model.addAttribute("authorCorpName", deptName); //编制机构名称
        model.addAttribute("businessDate", businessDate); //编制机构名称
        model.addAttribute("authorCorpName", deptName); //编制部门名称
        model.addAttribute("authorCorpId", deptId); //编制部门Id

        model.addAttribute("createUserId", createUserId); //编制人Id
        model.addAttribute("createUserName", createUserName); //编制人姓名
        model.addAttribute("createDate", businessDate);//编制日期

        return prefix + "/add";
    }

    /**
     * 修改页
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("material:purchaseOrder:edit")
    String edit(@PathVariable("id") String id, Model model) {
        RequireApplyBean requireApplyModel = new RequireApplyBean();//此处为接口取得数据
        requireApplyModel.setId(id);
        requireApplyModel.setName("2018年8月采购申请");
        requireApplyModel.setCode(id);
        requireApplyModel.setAuthorCorpId("8");
        requireApplyModel.setAuthorCorpName("研发二部");
        //requireApplyModel.setBusinessDate(new Date("YYYY-MM-DD"));
        //requireApplyModel.setAuthorCorpId("编制部门Id");
        //requireApplyModel.setCreateUserId("编制人Id");
        requireApplyModel.setCreateUserName("编制人姓名");
        requireApplyModel.setRemark("备注");

        model.addAttribute("requireApplyModel", requireApplyModel);//编制日期
        return prefix + "/edit";
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("material:requireApply:edit")
    public R update(RequireApplyBean requireApplyModel) {
        int re=1;
        //int re = requireApplyService.update(requireApplyModel) > 0
        if (re > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 采购订单变更页
     */
    @GetMapping("/change")
    @RequiresPermissions("material:purchaseOrder:change")
    String change(Model model) {
        PurchaseOrderBean purchaseOrderBean = new PurchaseOrderBean();//此处为接口取得数据
        /*purchaseOrderBean.setId(id);//主键
        purchaseOrderBean.setStatusId("状态");
        purchaseOrderBean.setCode(id);//采购订单 编号
        purchaseOrderBean.setName("2018年8月采购订单");
        purchaseOrderBean.setCompanyName("供应商名称");//供应商名称
        purchaseOrderBean.setRelatedCode("关联单据编号");//关联单据编号
        purchaseOrderBean.setTaxRate(0.5);//税率
        purchaseOrderBean.setPaymentType("付款方式及期限");//付款方式及期限
        purchaseOrderBean.setArriveAddress("交货地点");//交货地点
        // purchaseOrderBean.setArriveAddress("质量标准");//质量标准
        //货源分配计划单编号
        purchaseOrderBean.setTotalMoney(new BigDecimal("3000.89"));//订单总金额
        purchaseOrderBean.setAuthorCorpId("编制部门Id");
        purchaseOrderBean.setAuthorCorpName("编制部门名称");
        purchaseOrderBean.setCreateUserId("编制人Id");
        purchaseOrderBean.setCreateUserName("编制人姓名");
        purchaseOrderBean.setRemark("备注");//备注*/

        List<MaterialBean> materialList = new ArrayList();//调用接口
        for(int i=1;i<11;i++){
            //做测试数据 调用接口前使用 begin
            MaterialBean materialBean = new MaterialBean();
            materialBean.setId(String.valueOf(i));
            materialBean.setName("物资名称"+i);
            materialBean.setCode("物资编码"+i);
            materialList.add(materialBean);
        }

        model.addAttribute("purchaseOrder", purchaseOrderBean);//编制日期
        model.addAttribute("materialList", materialList);//编制日期
        return prefix + "/change";
    }
    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("material:purchaseOrder:remove")
    public R remove(Long id) {
        /*if(requireApplyService.checkCanRemove(id)) {
            if (requireApplyService.remove(id) > 0) {
                return R.ok();
            }
        }else {
            return R.error(1, "该申请已经被审批,不允许删除");
        }
        return R.error();*/
        return R.ok();
    }

    /**
     * 批量下达
     */
    @PostMapping("/multiGoDown")
    @ResponseBody
    @RequiresPermissions("material:purchaseOrder:multiGoDown")
    public R multiGoDown(Long id) {
        /*if(requireApplyService.checkCanRemove(id)) {
            if (requireApplyService.remove(id) > 0) {
                return R.ok();
            }
        }else {
            return R.error(1, "该申请已经被审批,不允许删除");
        }
        return R.error();*/
        return R.ok();
    }

    /**
     * 执行
     */
    @PostMapping("/execute")
    @ResponseBody
    @RequiresPermissions("material:purchaseOrder:execute")
    public R execute(Long id) {
        /*if(requireApplyService.checkCanRemove(id)) {
            if (requireApplyService.remove(id) > 0) {
                return R.ok();
            }
        }else {
            return R.error(1, "该申请已经被审批,不允许删除");
        }
        return R.error();*/
        return R.ok();
    }

    /**
     * 打印
     */
    @PostMapping("/print")
    @ResponseBody
    @RequiresPermissions("material:purchaseOrder:print")
    public R print(Long id) {
        /*if(requireApplyService.checkCanRemove(id)) {
            if (requireApplyService.remove(id) > 0) {
                return R.ok();
            }
        }else {
            return R.error(1, "该申请已经被审批,不允许删除");
        }
        return R.error();*/
        return R.ok();
    }

    /**
     * 提交审批
     */
    @ResponseBody
    @PostMapping("/approve")
    @RequiresPermissions("material:purchaseOrder:approve")
    public R approve(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        //int contactIds = service.save(customerContact);
        R r = new R();
        r.put("id",1);
        return r.ok();
    }

    /**
     * 取消审批
     */
    @ResponseBody
    @PostMapping("/cancelApprove")
    @RequiresPermissions("material:purchaseOrder:cancelApprove")
    public R cancelApply(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        //int contactIds = service.save(customerContact);

        return R.ok();
    }


    /**
     * 查看页
     */
    @GetMapping("/view/{id}")
    @RequiresPermissions("material:requireApply:requireApply")
    String view(@PathVariable("id") String id, Model model) {
        RequireApplyBean requireApplyModel = new RequireApplyBean();//此处为接口取得数据
        requireApplyModel.setId(id);
        requireApplyModel.setName("2018年8月采购申请");
        requireApplyModel.setCode(id);
        requireApplyModel.setAuthorCorpId("8");
        requireApplyModel.setAuthorCorpName("研发二部");
        //requireApplyModel.setBusinessDate(new Date("YYYY-MM-DD"));
        //requireApplyModel.setAuthorCorpId("编制部门Id");
        //requireApplyModel.setCreateUserId("编制人Id");
        requireApplyModel.setCreateUserName("编制人姓名");
        requireApplyModel.setRemark("备注");

        model.addAttribute("requireApplyModel", requireApplyModel);//编制日期
        return prefix + "/view";
    }




    /**
     * 取得选择物资列表
     */
    @GetMapping("/getMaterialList")
    @RequiresPermissions("material:requireApply:add")
    @ResponseBody
    PageUtils getMaterialList(@RequestParam Map<String, Object> params){
        // 查询列表数据
        //Query query = new Query(params);
        List<Map<String, Object>> materialList = new ArrayList();//调用接口
        for(int i=1;i<11;i++){
            //做测试数据 调用接口前使用 begin
            Map<String, Object> materialMap = new HashMap<>();
            materialMap.put("name","物资A"+i);
            materialMap.put("materialClassName","物资类别"+i);
            materialMap.put("code",i);
            materialMap.put("brand","规格型号"+i);
            materialMap.put("texture","材质"+i);
            materialMap.put("materialUnitId","单位"+i);
            materialList.add(materialMap);
        }
        //做测试数据 end
        int total = 20;//调用接口
        PageUtils pageUtil = new PageUtils(materialList, total);
        return pageUtil;
    }



    /**
     * 采购申请明细
     */
    @GetMapping("/requireMaterialDetailList")
    @ResponseBody
    PageUtils requireMaterialDetailList(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<RequireApplyItemBean> requireMaterialDetailList = new ArrayList();//调用接口
        //做测试数据 调用接口前使用 begin
        /*RequireApplyDetailModel requireApplyDetailModel = new  RequireApplyDetailModel();
        MaterialModel ma = new MaterialModel();
        ma.setName("测试物资");
        requireApplyDetailModel.setMaterial(ma);
        requireMaterialDetailList.add(requireApplyDetailModel);*/
        //做测试数据 end
        int total = 1;//调用接口
        PageUtils pageUtil = new PageUtils(requireMaterialDetailList, total);
        return pageUtil;
    }

    /**
     * 取得一条采购物资记录
     */
    @ResponseBody
    @GetMapping("/getMaterialDetailByCode/{code}")
    @RequiresPermissions("material:requireApply:add")
    Map<String, Object> getMaterialDetailByCode(@PathVariable("code") String code){
        //做测试数据 调用接口前使用 begin
        Map<String, Object> materialMap = new HashMap<>();
        materialMap.put("name","物资A"+code);
        materialMap.put("materialClassName","物资类别"+code);
        materialMap.put("code","物资编码"+code);
        materialMap.put("materialUnitId","单位"+code);
        materialMap.put("specification","规格型号"+code);
        materialMap.put("texture","材质"+code);
        materialMap.put("materialSubArray","包装物资"+code);
        materialMap.put("budgetQty","1000");
        materialMap.put("budgetPrice","900.24");
        materialMap.put("referencePrice","890.45");
        materialMap.put("stockQty","200");
        materialMap.put("acceptUserName","张三");
        materialMap.put("acceptUserId","zhangsan");
        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("materialDetail", materialMap);

        return returnData;

    }
    /**
     * 取得采购申请物资明细列表
     */
    @GetMapping("/getRequireApplyDetailByCode")
    @RequiresPermissions("material:requireApply:edit")
    @ResponseBody
    Map<String, Object> getRequireApplyDetailByCode(@RequestParam("id") String id){
        List<Map<String, Object>> requireApplyDetailList = new ArrayList<>();//调用接口
        //做测试数据 调用接口前使用 begin
        for (int i = 0; i < 15; i++) {
            Map<String, Object> requireMap = new HashMap<>();
            requireMap.put("planNo", i);
            requireMap.put("name", "物资A" + i);
            requireMap.put("code", "物资编码" + i);
            requireMap.put("specification", "规格" + i);
            requireMap.put("materialUnitName", "单位" + i);
            requireMap.put("materialUnitId", "单位" + i);
            requireMap.put("materialSubArray", "包装物料" + i);
            requireMap.put("requireQty","100"+i);
            requireMap.put("budgetQty","200"+i);
            requireMap.put("stockQty", "150"+i);
            requireMap.put("referencePrice", "50"+i);
            requireMap.put("budgetPrice", "60"+i);
            requireMap.put("requireDate","2018-08-2"+i);
            requireMap.put("acceptUserId", "001");
            requireMap.put("acceptUserName", "编制人"+i);
            requireMap.put("description", "说明信息"+i);
            requireApplyDetailList.add(requireMap);
        }
        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("requireApplyDetailList", requireApplyDetailList);

        return returnData;

    }
    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("material:requireApply:add")
    public R save(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        //int contactIds = service.save(customerContact);

        return R.ok();
    }


}
