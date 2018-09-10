package com.dx.service.purchase.service.api;

import com.dx.client.model.purchase.RequireApplyItemBean;
import com.dx.client.model.purchase.RequirePlanBean;
import com.dx.client.model.purchase.RequirePlanItemBean;
import com.dx.service.purchase.config.MultipartSupportConfig;
import com.dx.service.purchase.fallback.RequirePlanServiceFallbackFactory;
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
 * @Date: 2018/9/3 14:31
 * @Description:需求计划API
 */
@FeignClient(name="PURCHASESERVICE.DX.COM",
        configuration = MultipartSupportConfig.class,
        fallbackFactory = RequirePlanServiceFallbackFactory.class
)
public interface IRequirePlanService {
    //保存
    @RequestMapping("/purchase/requirePlanService/save")
    @ResponseBody
    public ResultMsg save(@RequestBody RequirePlanBean requirePlanBean,
                          @RequestBody List<RequirePlanItemBean> requirePlanItemBeans,
                          @RequestParam boolean isSubmit);

    //注销
    @RequestMapping("/purchase/requirePlanService/cancel")
    @ResponseBody
    public ResultMsg cancel(@RequestParam("requirePlanId") String requirePlanId);

    //删除
    @RequestMapping("/purchase/requirePlanService/remove")
    @ResponseBody
    public ResultMsg remove(@RequestParam("requirePlanId") String requirePlanId);

    //提交审批
    @RequestMapping("/purchase/requirePlanService/submit")
    @ResponseBody
    public ResultMsg submit(@RequestBody RequirePlanBean requirePlanBean);

    //主要
    //返回数据类型RequirePlanBean
    @RequestMapping("/purchase/requirePlanService/primary")
    @ResponseBody
    public ResultMsg primary(@RequestParam("requirePlanId") String requirePlanId);

    //详细
    //返回数据类型List<RequirePlanItemBean>
    @RequestMapping("/purchase/requirePlanService/detail")
    @ResponseBody
    public ResultMsg detail(@RequestParam("requirePlanId") String requirePlanId);

    //依据采购申请生成需求计划明细
    // 返回数据类型List<RequirePlanItemBean>
    @RequestMapping("/purchase/requirePlanService/createItems")
    @ResponseBody
    public ResultMsg createItems(@RequestBody List<RequireApplyItemBean> requireApplyItemBeans);

    //获取待受理明细-采购申请明细
    //返回数据类型Map
    @RequestMapping("/purchase/requirePlanService/getWaitAcceptedItems")
    @ResponseBody
    public ResultMsg getWaitAcceptedItems(@RequestParam("acceptUserId") String acceptUserId);

    //查询
    //orderBy参数如：blog_ID desc，示例代码：PageHelper.startPage(pageNum , pageSize); PageHelper.orderBy("blog_ID desc");
    //返回Map类型
    @RequestMapping("/purchase/requirePlanService/search")
    @ResponseBody
    public ResultMsg search(@RequestParam("pageNum") String pageNum,
                            @RequestParam("pageSize") String pageSize,
                            @RequestParam("orderBy") String orderBy,
                            @RequestParam("params") Map<String, Object> params);
}
