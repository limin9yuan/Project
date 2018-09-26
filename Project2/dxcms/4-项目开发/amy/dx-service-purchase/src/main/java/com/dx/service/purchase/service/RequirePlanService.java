package com.dx.service.purchase.service;

import com.dx.client.model.purchase.RequireApplyItemBean;
import com.dx.client.model.purchase.RequirePlanBean;
import com.dx.service.purchase.service.api.IRequirePlanService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxcl.amy.utils.common.ResultMsg;
import org.wxcl.amy.utils.common.ResultUtil;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/21 13:05
 * @Description:
 */
@RefreshScope
@RestController
@RequestMapping("/purchase")
public class RequirePlanService implements IRequirePlanService {
    @Override
    @RequestMapping("/requirePlanService/save")
    public ResultMsg save(RequirePlanBean requirePlanBean) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/requirePlanService/cancel")
    public ResultMsg cancel(String requirePlanId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/requirePlanService/remove")
    public ResultMsg remove(String requirePlanId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/requirePlanService/submit")
    public ResultMsg submit(RequirePlanBean requirePlanBean) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/requirePlanService/primary")
    public ResultMsg primary(String requirePlanId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/requirePlanService/detail")
    public ResultMsg detail(String requirePlanId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/requirePlanService/createItems")
    public ResultMsg createItems(List<RequireApplyItemBean> requireApplyItemBeans) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/requirePlanService/getWaitAcceptedItems")
    public ResultMsg getWaitAcceptedItems(String acceptUserId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/requirePlanService/search")
    public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
        return ResultUtil.todo("待实现");
    }
}
