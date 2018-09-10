package com.dx.service.contract.service.api;

import com.dx.client.model.contract.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wxcl.amy.utils.common.ResultMsg;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/4 07:58
 * @Description: 合同API
 */
public interface IContractService {
    //保存
    @RequestMapping("/contract/contractService/save")
    @ResponseBody
    public ResultMsg save(@RequestBody ContractBean contractBean,                           //合同信息
                          @RequestBody List<ContractMaterialBean> contractMaterialBeans,    //合同物资明细
                          @RequestBody List<ContractSuitBean> contractSuitBeans,            //适用机构
                          @RequestBody List<ContractDeliverBean> contractDeliverBeans,      //发货单位
                          @RequestBody List<ContractEnclosureBean> contractEnclosureBeans,  //合同附件
                          @RequestBody List<ContractElementBean> contractElementBeans,      //合同元素
                          @RequestBody ContractHtmlBean contractHtmlBean,                   //合同文本HTML
                          @RequestParam boolean isSubmit);                                  //是否提交审批

    //注销
    @RequestMapping("/contract/contractService/cancel")
    @ResponseBody
    public ResultMsg cancel(@RequestParam("contractId") String contractId);

    //删除
    @RequestMapping("/contract/contractService/remove")
    @ResponseBody
    public ResultMsg remove(@RequestParam("contractId") String contractId);

    //主要
    //返回数据类型ContractBean
    @RequestMapping("/contract/contractService/primary")
    @ResponseBody
    public ResultMsg primary(@RequestParam("contractId") String contractId);

    //获取合同物资(通过合同id）
    //返回数据类型List<ContractMaterialBean>
    @RequestMapping("/contract/contractService/getMaterialsByContractId")
    @ResponseBody
    public ResultMsg getMaterialsByContractId(@RequestParam("contractId") String contractId);

    //获取合同物资(通过物资编码列表）
    //返回数据类型List<ContractMaterialBean>
    @RequestMapping("/contract/contractService/getMaterialsByCodes")
    @ResponseBody
    public ResultMsg getMaterialsByCodes(@RequestBody List<String> materialCodes);

    //获取生效机构
    //返回数据类型List<ContractSuitBean>
    @RequestMapping("/contract/contractService/getSuits")
    @ResponseBody
    public ResultMsg getSuits(@RequestParam("contractId") String contractId);

    //获取发货单位
    //返回数据类型List<ContractDeliverBean>
    @RequestMapping("/contract/contractService/getDelivers")
    @ResponseBody
    public ResultMsg getDelivers(@RequestParam("contractId") String contractId);

    //获取元素
    //返回数据类型List<ContractElementBean>
    @RequestMapping("/contract/contractService/getElements")
    @ResponseBody
    public ResultMsg getElements(@RequestParam("contractId") String contractId);

    //获取合同html：见IContractFileService
    //获取合同附件：见IContractFileService

    //合同盖章
    @RequestMapping("/contract/contractService/onSealed")
    @ResponseBody
    public ResultMsg onSealed(@RequestParam("contractId") String contractId);

    //合同终止
    @RequestMapping("/contract/contractService/onStoped")
    @ResponseBody
    public ResultMsg onStoped(@RequestParam("contractId") String contractId);

    //合同归档
    @RequestMapping("/contract/contractService/onFiled")
    @ResponseBody
    public ResultMsg onFiled(@RequestParam("contractId") String contractId);

    //变更合同
    //before****：变更前，after****：变更后
    //返回数据：List<>：变更记录
    @RequestMapping("/contract/contractService/changeContract")
    @ResponseBody
    public ResultMsg changeContract(@RequestBody ContractBean beforeContractBean,                           //合同信息
                                 @RequestBody List<ContractMaterialBean> beforeContractMaterialBeans,       //合同物资明细
                                 @RequestBody List<ContractSuitBean> beforeContractSuitBeans,               //适用机构
                                 @RequestBody List<ContractDeliverBean> beforeContractDeliverBeans,         //发货单位
                                 @RequestBody List<ContractEnclosureBean> beforeContractEnclosureBean,      //合同附件
                                 @RequestBody ContractBean afterContractBean,                               //合同信息
                                 @RequestBody List<ContractMaterialBean> afterContractMaterialBeans,        //合同物资明细
                                 @RequestBody List<ContractSuitBean> afterContractSuitBeans,                //适用机构
                                 @RequestBody List<ContractDeliverBean> afterContractDeliverBeans,          //发货单位
                                 @RequestBody List<ContractEnclosureBean> afterContractEnclosureBean        //合同附件
                                 );

    //查询
    //orderBy参数如：blog_ID desc，示例代码：PageHelper.startPage(pageNum , pageSize); PageHelper.orderBy("blog_ID desc");
    //返回Map类型
    @RequestMapping("/contract/contractService/search")
    @ResponseBody
    public ResultMsg search(@RequestParam("pageNum") String pageNum,
                            @RequestParam("pageSize") String pageSize,
                            @RequestParam("orderBy") String orderBy,
                            @RequestParam("params") Map<String, Object> params);
}
