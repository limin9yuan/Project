package com.bootdo.material.controller;


import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.*;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.dx.client.model.datacenter.MaterialBean;
import com.dx.client.model.purchase.RequireApplyItemBean;
import com.dx.client.model.purchase.RequireApplyBean;
import org.springframework.web.multipart.MultipartFile;
import org.wxcl.amy.utils.common.ResultMsg;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

@RefreshScope
@Controller
@RequestMapping("/material/requireApply")
public class RequireApplyController extends BaseController {
    @Autowired
    private com.dx.service.purchase.service.api.IRequireApplyService requireApplyService;
    @Autowired
    private com.dx.service.datacenter.service.api.IMaterialService  materialService;
    @Autowired
    private BootdoConfig bootdoConfig;


    private String prefix="material/requireApply"  ;

    /**
     * 采购申请管理页
     */
    @RequiresPermissions("material:requireApply:requireApply")
    @GetMapping("")
    String requireApply(Model model) {
        return prefix + "/requireApply";
    }

    /**
     * 采购申请编制页
     */
    @GetMapping("/add")
    @RequiresPermissions("material:requireApply:add")
    String addRequireApply(Model model){
        Long createUserId =getUser().getUserId();
        String createUserName =getUser().getUsername();
        String deptName =getUser().getDeptName();
        Long deptId =getUser().getDeptId();
        String code = "";
        String businessDate = DateUtils.format(new Date(),DateUtils.DATE_PATTERN);
        String name = businessDate.substring(0,4)+"年"+businessDate.substring(5,7)+"月采购申请";

        model.addAttribute("name", name);//名称
        model.addAttribute("code", code);//编号
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
     * 物资明细列表页
     */
    @GetMapping("/materialList")
    @RequiresPermissions("material:requireApply:add")
    String materialList(Model model){
        return prefix + "/materialList";
    }

    /**
     * 修改页
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("material:requireApply:edit")
    String edit(@PathVariable("id") String id, Model model) {
        //调用接口
        ResultMsg rm = requireApplyService.primary(id);
        //做测试数据 begin
        RequireApplyBean requireApplyModel = new RequireApplyBean();
        requireApplyModel.setId(id);
        requireApplyModel.setName("2018年8月采购申请");
        requireApplyModel.setCode(id);
        requireApplyModel.setAuthorCorpId("8");
        requireApplyModel.setAuthorCorpName("研发二部");
        String businessDate = DateUtils.format(new Date(),DateUtils.DATE_PATTERN);
        //requireApplyModel.setBusinessDate(new Date("YYYY-MM-DD"));
        //requireApplyModel.setAuthorCorpId("编制部门Id");
        //requireApplyModel.setCreateUserId("编制人Id");
        requireApplyModel.setCreateUserName("编制人姓名");
        requireApplyModel.setRemark("备注");
        rm = new ResultMsg();
        rm.setData(requireApplyModel);
        //做测试数据 end
        model.addAttribute("requireApplyModel", rm.getData());//编制日期
        return prefix + "/edit";
    }
    /**
     * 查看页
     */
    @GetMapping("/view/{id}")
    @RequiresPermissions("material:requireApply:requireApply")
    String view(@PathVariable("id") String id, Model model) {
        //调用接口
        ResultMsg rm = requireApplyService.primary(id);
        //做测试数据 begin
        RequireApplyBean requireApplyModel = new RequireApplyBean();
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
        rm = new ResultMsg();
        rm.setData(requireApplyModel);
        //做测试数据 end
        model.addAttribute("requireApplyModel", rm.getData());
        return prefix + "/view";
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("material:requireApply:edit")
    public R update(@RequestParam Map<String, Object> params) {

        RequireApplyBean requireApplyModel = new RequireApplyBean();
        requireApplyModel.setName((String)params.get("name"));
        requireApplyModel.setCode((String)params.get("code"));
        requireApplyModel.setAuthorCorpId((String)params.get("authorCorpId"));
        requireApplyModel.setCreateUserId((String)params.get("createUserId"));
        requireApplyModel.setRemark((String)params.get("remark"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date createDate = sdf.parse((String)params.get("createDate"));
            requireApplyModel.setCreateDate(createDate);
            Date businessDate = sdf.parse((String)params.get("businessDate"));
            requireApplyModel.setBusinessDate(businessDate);
        }catch (Exception e){
            return R.error();
        }
        List itemList = new ArrayList<RequireApplyItemBean>();

        JSONArray array = JSONArray.fromObject(params.get("applyEntryJson"));
        for(int i=0;i<array.size();i++){
            System.out.println(array.get(i));
            RequireApplyItemBean requireApplyItemBean = (RequireApplyItemBean) JSONObject.toBean((JSONObject)array.get(i), RequireApplyItemBean.class);
            itemList.add(requireApplyItemBean);
        }
        requireApplyModel.setRequireApplyItemBeans(itemList);
        ResultMsg rms = requireApplyService.save(requireApplyModel);
        if ("1".equals(rms.getCode())) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("material:requireApply:remove")
    public R remove(String id) {

        //调用接口
        ResultMsg rms = requireApplyService.remove(id);
        if ("1".equals(rms.getCode())) {
            return R.ok();
        }
        return R.error(rms.getCode(), rms.getMsg());
    }

    /**
     * 取得选择物资列表
     */
    @GetMapping("/getMaterialList")
    @RequiresPermissions("material:requireApply:add")
    @ResponseBody
    PageInfo getMaterialList(@RequestParam Map<String, Object> params){
        // 查询列表数据
        ResultMsg rsm = materialService.search(params.get("pageNumber").toString(),
                params.get("pageSize").toString(),"",
                params);
        //做测试数据 begin
        List<Map<String, Object>> materialList = new ArrayList();
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
        int total = 20;
        PageInfo pageInfo = new PageInfo(materialList,
                Integer.parseInt(params.get("pageNumber").toString()));
        pageInfo.setTotal(total);
        //做测试数据 end
        //PageInfo pageInfo = (PageInfo)rsm.getData();//调用接口
        return pageInfo;
    }

    /**
     * 采购申请列表
     */
    @GetMapping("/list")
    @ResponseBody
    PageInfo list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        ResultMsg rsm = requireApplyService.search(params.get("pageNumber").toString(),
                params.get("pageSize").toString(),"",
                params);
        //做测试数据 begin
        List<Map<String, Object>> requireApplyList = new ArrayList();
        for(int i=1;i<11;i++){
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
        int total = 20;
        PageInfo pageInfo = new PageInfo(requireApplyList,
                Integer.parseInt(params.get("pageNumber").toString()));
        pageInfo.setTotal(total);
        //做测试数据 end
        //PageInfo pageInfo = (PageInfo)rsm.getData();//调用接口

        return pageInfo;
    }

    /**
     * 采购申请明细
     */
    @GetMapping("/requireMaterialDetailList")
    @ResponseBody
    PageInfo requireMaterialDetailList(@RequestParam Map<String, Object> params) {

        ResultMsg rsm = materialService.search(params.get("pageNumber").toString(),
                params.get("pageSize").toString(),
                "",params);
        // 查询列表数据
        List<RequireApplyItemBean> requireMaterialDetailList = new ArrayList();//调用接口
        //做测试数据 调用接口前使用 begin
        /*RequireApplyDetailModel requireApplyDetailModel = new  RequireApplyDetailModel();
        MaterialModel ma = new MaterialModel();
        ma.setName("测试物资");
        requireApplyDetailModel.setMaterial(ma);
        requireMaterialDetailList.add(requireApplyDetailModel);*/
        //做测试数据 end
        int total = 1;//调用接口
        PageInfo pageInfo = new PageInfo(requireMaterialDetailList, total);
        return pageInfo;
    }

    /**
     * 取得一条采购物资记录
     */
    @ResponseBody
    @GetMapping("/getMaterialDetailByCode/{code}")
    @RequiresPermissions("material:requireApply:add")
    Map<String, Object> getMaterialDetailByCode(@PathVariable("code") String code){
        //调用接口
        List ml = new ArrayList();
        ml.add(code);
        ResultMsg rsm = requireApplyService.createItems(ml);
        //做测试数据 begin
        RequireApplyItemBean materialItemBean = new RequireApplyItemBean();
        materialItemBean.setMaterialName("物资A"+code);
        materialItemBean.setMaterialSubArray("物资M包装"+code);
        materialItemBean.setMaterialCode("物资编码"+code);
        materialItemBean.setMaterialUnitName("单位"+code);
        materialItemBean.setSpecification("规格型号"+code);
        materialItemBean.setTexture("材质"+code);
        //materialItemBean.setOutsideBarCode("包装物资"+code);
        materialItemBean.setBudgetQty(1000.23);//预算数量
        materialItemBean.setBudgetPrice(new BigDecimal("900.24"));//预算单价
        materialItemBean.setReferencePrice(new BigDecimal("900.24"));//参考单价
        materialItemBean.setStockQty(200.34);//库存数量
        materialItemBean.setAcceptUserName("张三");
        materialItemBean.setAcceptUserId("zhangsan");
        Map<String, Object> returnData = new HashMap<String, Object>();
        rsm = new ResultMsg();
        rsm.setData(materialItemBean);
        //做测试数据 end
        returnData.put("materialDetail", rsm.getData());
        return returnData;

    }
    /**
     * 取得采购申请物资明细列表
     */
    @GetMapping("/getRequireApplyDetailByCode")
    @RequiresPermissions("material:requireApply:edit")
    @ResponseBody
    Map<String, Object> getRequireApplyDetailByCode(@RequestParam("id") String id){
        //调用接口
        ResultMsg rsm = requireApplyService.detail(id);

        //做测试数据 调用接口前使用 begin
        List<RequireApplyItemBean> requireApplyDetailList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            //做测试数据 begin
            RequireApplyItemBean materialItemBean = new RequireApplyItemBean();
            materialItemBean.setMaterialName("物资A"+id);
            //materialItemBean.setMaterialClassName("物资类别"+code);
            materialItemBean.setMaterialCode("物资编码"+id);
            materialItemBean.setMaterialUnitName("单位"+id);
            materialItemBean.setSpecification("规格型号"+id);
            materialItemBean.setTexture("材质"+id);
            //materialItemBean.setOutsideBarCode("包装物资"+code);
            materialItemBean.setBudgetQty(1000.23);//预算数量
            materialItemBean.setRequireQty(500.90);//需求数量
            materialItemBean.setBudgetPrice(new BigDecimal("900.24"));//预算单价
            materialItemBean.setReferencePrice(new BigDecimal("900.24"));//参考单价
            materialItemBean.setStockQty(200.34);//库存数量
            materialItemBean.setAcceptUserName("张三");
            materialItemBean.setAcceptUserId("zhangsan");
            requireApplyDetailList.add(materialItemBean);
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

    /**
     * 提交审批
     */
    @ResponseBody
    @PostMapping("/commitApply")
    @RequiresPermissions("material:requireApply:approve")
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
    @RequiresPermissions("material:requireApply:cancelApprove")
    public R cancelApply(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        //int contactIds = service.save(customerContact);

        return R.ok();
    }

    /**
     * exls表格导入
     */
    @ResponseBody
    @PostMapping("/uploadExcel")
    @RequiresPermissions("material:requireApply:add")
    R uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        File datafile = null;
        try {
            FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
            datafile = new File(bootdoConfig.getUploadPath() + fileName);

            Map<String, Object> errorMsgs = ImportMaterial(datafile);
            if ("success".equals(errorMsgs.get("result"))) {
                R r = R.ok();
                r.put("list", errorMsgs.get("list"));
                return r;
            } else {
                return R.error();
            }
        } catch (Exception e) {
            return R.error();
        }

    }

    public Map<String, Object> ImportMaterial(File file) {
        Workbook wookbook = null;
        List<String> errorMsgs = null;
        Map<String, Object> result = null;
        List<RequireApplyItemBean> list = new ArrayList<RequireApplyItemBean>();

        int rtn = 0;
        try {
            result = new HashMap<String, Object>();
            errorMsgs = new ArrayList<String>();

            FileInputStream is = new FileInputStream(file); // 文件流
            wookbook = WorkbookFactory.create(is); // 这种方式 Excel 2003/2007/2010 都是可以处理的

            // 在Excel文档中，第一张工作表的缺省索引是0
            // 其语句为：HSSFSheet sheet = wookbook.getSheetAt(0);
            Sheet sheet = wookbook.getSheetAt(0);// wookbook.getSheet("Sheet1");
            // 获取到Excel文件中的所有行数
            int rows = sheet.getPhysicalNumberOfRows();
            // Excel文件中的第一行（标题行）
            int cellCount = 0;
            String titleName = "";
            // 遍历行
            my: for (int i = 0; i < rows; i++) {
                // 读取左上端单元格(跳过第一行标题行)
                Row row = sheet.getRow(i);
                RequireApplyItemBean requireApplyItemBean = new RequireApplyItemBean(); //

                // 行不为空
                if (row != null) {
                    if (i == 0) {
                        // 获取到Excel文件中的第一行（标题行）
                        Row rowCount = sheet.getRow(i);

                        // 获取到Excel文件中的所有的列
                        cellCount = rowCount.getPhysicalNumberOfCells();
                        continue;
                    }
                    // 获取到Excel文件中的所有的列
                    // int cells = row.getPhysicalNumberOfCells();
                    String cellvalue = "";
                    String contact = "";
                    // String agentCode = null;
                    String companyName = null;
                    // 遍历列
                    for (int j = 0; j < cellCount; j++) {
                        cellvalue = ""; // 清空之前之前取到的列的值
                        // 获取到列的值
                        Cell cell = row.getCell(j);
                        // String value = "";
                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case XSSFCell.CELL_TYPE_FORMULA:
                                    break;
                                case XSSFCell.CELL_TYPE_NUMERIC: {
                                    short format = cell.getCellStyle().getDataFormat();
                                    if (format == 14 || format == 31 || format == 57 || format == 58) { // excel中的时间格式
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                        double value = cell.getNumericCellValue();
                                        Date date = DateUtil.getJavaDate(value);
                                        cellvalue = sdf.format(date);
                                    }
                                    // 判断当前的cell是否为Date
                                    else if (HSSFDateUtil.isCellDateFormatted(cell)) { // 先注释日期类型的转换，在实际测试中发现HSSFDateUtil.isCellDateFormatted(cell)只识别2014/02/02这种格式。
                                        // 如果是Date类型则，取得该Cell的Date值 // 对2014-02-02格式识别不出是日期格式
                                        Date date = cell.getDateCellValue();
                                        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                                        cellvalue = formater.format(date);
                                    } else { // 如果是纯数字
                                        // 取得当前Cell的数值
                                        cellvalue = NumberToTextConverter.toText(cell.getNumericCellValue());
                                    }
                                    break;
                                }
                                case XSSFCell.CELL_TYPE_STRING:
                                    cellvalue = cell.getStringCellValue();
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (i == 0) {
                            titleName=cellvalue==null?"":cellvalue;
                        }else{
                            if ("物资编码".equals(titleName.trim())) {
                                requireApplyItemBean.setMaterialCode(cellvalue);
                            } else if ("物资名称".equals(titleName.trim())) {
                                requireApplyItemBean.setMaterialName(cellvalue);
                            } else if ("需求数量".equals(titleName.trim())) {
                                requireApplyItemBean.setRequireQty(Double.parseDouble(cellvalue));
                            } else if ("参考单价".equals(titleName.trim())) {
                                requireApplyItemBean.setReferencePrice(new BigDecimal(cellvalue));
                            } else if ("要求到货日期".equals(titleName.trim())) {
                                if (cellvalue == null || cellvalue == "") {
                                    continue;
                                } else {
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    requireApplyItemBean.setRequireDate(formatter.parse(cellvalue));
                                }
                            } else if ("说明信息".equals(titleName.trim())) {
                                requireApplyItemBean.setRemark(cellvalue);
                            }
                        }


                    } // --->遍历列

                }
                list.add(requireApplyItemBean);
                rtn = list.size();
            }
        } catch (Exception e) {
            errorMsgs.add(e.getMessage());
            e.printStackTrace();
        } finally {
            wookbook.cloneSheet(0); // 关闭sheet页
            try {
                wookbook.close(); // 关闭Excel文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (CollectionUtils.isEmpty(errorMsgs)) { // errorMsgs.size() == 0

            if (rtn > 0) {
                result.put("result", "success");
                result.put("list", list);
            } else {
                result.put("result", "false");
            }
        } else {
            result.put("result", "error");
            result.put("msg", errorMsgs);
        }

        return result;
    }
}
