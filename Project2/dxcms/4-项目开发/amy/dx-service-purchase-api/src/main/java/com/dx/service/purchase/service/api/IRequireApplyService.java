package com.dx.service.purchase.service.api;

import com.dx.client.model.datacenter.MaterialBean;
import com.dx.client.model.purchase.RequireApplyBean;
import com.dx.client.model.purchase.RequireApplyItemBean;
import com.dx.client.model.purchase.RequirePlanBean;
import com.dx.service.purchase.config.PurchaseFeignConfig;
import com.dx.service.purchase.fallback.RequireApplyServiceFallbackFactory;
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
 * @Date: 2018/9/3 14:21
 * @Description:采购申请API
 */
@FeignClient(name="PURCHASESERVICE.DX.COM",
        configuration = PurchaseFeignConfig.class,
        fallbackFactory = RequireApplyServiceFallbackFactory.class
)
public interface IRequireApplyService {
    //保存
    @RequestMapping("/purchase/requireApplyService/save")
    @ResponseBody
    public ResultMsg save(@RequestBody RequireApplyBean requireApplyBean);

    //注销
    @RequestMapping("/purchase/requireApplyService/cancel")
    @ResponseBody
    public ResultMsg cancel(@RequestParam("requireApplyId") String requireApplyId);

    //删除
    @RequestMapping("/purchase/requireApplyService/remove")
    @ResponseBody
    public ResultMsg remove(@RequestParam("requireApplyId") String requireApplyId);

    //提交审批
    @RequestMapping("/purchase/requireApplyService/submit")
    @ResponseBody
    public ResultMsg submit(@RequestBody RequireApplyBean requireApplyBean);

    //主要
    //返回数据类型RequireApplyBean
    @RequestMapping("/purchase/requireApplyService/primary")
    @ResponseBody
    public ResultMsg primary(@RequestParam("requireApplyId") String requireApplyId);

    //详细
    //返回数据类型List<RequireApplyItemBean>
    @RequestMapping("/purchase/requireApplyService/detail")
    @ResponseBody
    public ResultMsg detail(@RequestParam("requireApplyId") String requireApplyId);

    //依据物资获取采购申请明细
    //返回数据：List<RequireApplyItemBean>
    @RequestMapping("/purchase/requireApplyService/createItems")
    @ResponseBody
    public ResultMsg createItems(@RequestParam("materialCodes") List<String> materialCodes);

    //查询
    //orderBy参数如：blog_ID desc，示例代码：PageHelper.startPage(pageNum , pageSize); PageHelper.orderBy("blog_ID desc");
    @RequestMapping("/purchase/requireApplyService/search")
    @ResponseBody
    public ResultMsg search(@RequestParam("pageNum") String pageNum,
                            @RequestParam("pageSize") String pageSize,
                            @RequestParam("orderBy") String orderBy,
                            @RequestParam("params") Map<String, Object> params);
}
