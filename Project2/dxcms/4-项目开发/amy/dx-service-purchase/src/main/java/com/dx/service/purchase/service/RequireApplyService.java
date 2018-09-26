package com.dx.service.purchase.service;

import com.dx.client.model.purchase.RequireApplyBean;
import com.dx.service.purchase.service.api.IRequireApplyService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxcl.amy.utils.common.ResultMsg;
import org.wxcl.amy.utils.common.ResultUtil;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/3 14:21
 * @Description:
 */
@RefreshScope
@RestController
@RequestMapping("/purchase")
public class RequireApplyService implements IRequireApplyService {
    @Override
    @RequestMapping("/requireApplyService/save")
    public ResultMsg save(RequireApplyBean requireApplyBean) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/requireApplyService/cancel")
    public ResultMsg cancel(String requireApplyId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/requireApplyService/remove")
    public ResultMsg remove(String requireApplyId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/requireApplyService/submit")
    public ResultMsg submit(RequireApplyBean requireApplyBean) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/requireApplyService/primary")
    public ResultMsg primary(String requireApplyId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/requireApplyService/detail")
    public ResultMsg detail(String requireApplyId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/requireApplyService/createItems")
    public ResultMsg createItems(List<String> materialCodes) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/requireApplyService/search")
    public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
        return ResultUtil.todo("待实现");
    }
}
