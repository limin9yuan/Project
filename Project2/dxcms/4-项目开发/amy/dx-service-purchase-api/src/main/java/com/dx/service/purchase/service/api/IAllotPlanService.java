package com.dx.service.purchase.service.api;

import com.dx.client.model.purchase.AllotPlanBean;
import com.dx.client.model.purchase.AllotPlanItemBean;
import com.dx.client.model.purchase.PurchasePlanItemBean;
import com.dx.service.purchase.config.MultipartSupportConfig;
import com.dx.service.purchase.fallback.AllotPlanServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wxcl.amy.utils.common.ResultMsg;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/3 14:32
 * @Description:货源计划API
 */
@FeignClient(name="PURCHASESERVICE.DX.COM",
        configuration = MultipartSupportConfig.class,
        fallbackFactory = AllotPlanServiceFallbackFactory.class
)
public interface IAllotPlanService {
    //保存
    @RequestMapping("/purchase/allotPlanService/save")
    @ResponseBody
    public ResultMsg save(@RequestBody AllotPlanBean allotPlanBean,
                          @RequestBody List<AllotPlanItemBean> allotPlanItemBeans,
                          @RequestParam boolean isSubmit);

    //注销
    @RequestMapping("/purchase/allotPlanService/cancel")
    @ResponseBody
    public ResultMsg cancel(@RequestParam("allotPlanId") String allotPlanId);

    //删除
    @RequestMapping("/purchase/allotPlanService/remove")
    @ResponseBody
    public ResultMsg remove(@RequestParam("allotPlanId") String allotPlanId);

    //提交审批
    @RequestMapping("/purchase/allotPlanService/submit")
    @ResponseBody
    public ResultMsg submit(@RequestBody AllotPlanBean allotPlanBean);

    //主要
    //返回数据类型AllotPlanBean
    @RequestMapping("/purchase/allotPlanService/primary")
    @ResponseBody
    public ResultMsg primary(@RequestParam("allotPlanId") String allotPlanId);

    //详细
    //返回数据类型List<AllotPlanItemBean>
    @RequestMapping("/purchase/allotPlanService/detail")
    @ResponseBody
    public ResultMsg detail(@RequestParam("allotPlanId") String allotPlanId);

    //依据需求计划明细生成货源计划明细
    //返回数据：List<AllotPlanItemBean>
    @RequestMapping("/purchase/allotPlanService/createItems")
    @ResponseBody
    public ResultMsg createItems(@RequestBody List<PurchasePlanItemBean> purchasePlanItemBeans);

    //获取待受理明细-采购计划明细
    //返回数据类型Map
    @RequestMapping("/purchase/allotPlanService/getWaitAcceptedItems")
    @ResponseBody
    public ResultMsg getWaitAcceptedItems(@RequestParam("acceptUserId") String acceptUserId);

    //查询
    //orderBy参数如：blog_ID desc，示例代码：PageHelper.startPage(pageNum , pageSize); PageHelper.orderBy("blog_ID desc");
    @RequestMapping("/purchase/allotPlanService/search")
    @ResponseBody
    public ResultMsg search(@RequestParam("pageNum") String pageNum,
                            @RequestParam("pageSize") String pageSize,
                            @RequestParam("orderBy") String orderBy,
                            @RequestParam("params") Map<String, Object> params);
}
