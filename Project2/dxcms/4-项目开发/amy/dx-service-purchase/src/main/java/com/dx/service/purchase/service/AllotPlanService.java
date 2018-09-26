package com.dx.service.purchase.service;

import com.dx.client.model.purchase.AllotPlanBean;
import com.dx.client.model.purchase.PurchasePlanItemBean;
import com.dx.service.purchase.service.api.IAllotPlanService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxcl.amy.utils.common.ResultMsg;
import org.wxcl.amy.utils.common.ResultUtil;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/21 12:49
 * @Description:
 */
@RefreshScope
@RestController
@RequestMapping("/purchase")
public class AllotPlanService implements IAllotPlanService {
    @Override
    @RequestMapping("/allotPlanService/save")
    public ResultMsg save(AllotPlanBean allotPlanBean) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/allotPlanService/cancel")
    public ResultMsg cancel(String allotPlanId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/allotPlanService/remove")
    public ResultMsg remove(String allotPlanId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/allotPlanService/submit")
    public ResultMsg submit(AllotPlanBean allotPlanBean) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/allotPlanService/primary")
    public ResultMsg primary(String allotPlanId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/allotPlanService/detail")
    public ResultMsg detail(String allotPlanId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/allotPlanService/createItems")
    public ResultMsg createItems(List<PurchasePlanItemBean> purchasePlanItemBeans) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/allotPlanService/getWaitAcceptedItems")
    public ResultMsg getWaitAcceptedItems(String acceptUserId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/allotPlanService/search")
    public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
        return ResultUtil.todo("待实现");
    }
}
