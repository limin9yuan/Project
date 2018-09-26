package com.dx.service.purchase.service;

import com.dx.client.model.purchase.PurchasePlanBean;
import com.dx.client.model.purchase.RequirePlanItemBean;
import com.dx.service.purchase.service.api.IPurchasePlanService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxcl.amy.utils.common.ResultMsg;
import org.wxcl.amy.utils.common.ResultUtil;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/21 13:00
 * @Description:
 */
@RefreshScope
@RestController
@RequestMapping("/purchase")
public class PurchasePlanService implements IPurchasePlanService {
    @Override
    @RequestMapping("/purchasePlanService/save")
    public ResultMsg save(PurchasePlanBean purchasePlanBean) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchasePlanService/cancel")
    public ResultMsg cancel(String purchasePlanId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchasePlanService/remove")
    public ResultMsg remove(String purchasePlanId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchasePlanService/submit")
    public ResultMsg submit(PurchasePlanBean purchasePlanBean) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchasePlanService/primary")
    public ResultMsg primary(String purchasePlanId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchasePlanService/detail")
    public ResultMsg detail(String purchasePlanId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchasePlanService/createItems")
    public ResultMsg createItems(List<RequirePlanItemBean> requirePlanItemBeans) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchasePlanService/getWaitAcceptedItems")
    public ResultMsg getWaitAcceptedItems(String acceptUserId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchasePlanService/search")
    public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
        return ResultUtil.todo("待实现");
    }
}
