package com.dx.service.datacenter.service;

import com.dx.client.model.datacenter.MaterialBean;
import com.dx.service.datacenter.bll.IMaterialBll;
import com.dx.service.datacenter.service.api.IMaterialService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxcl.amy.utils.common.ResultMsg;
import org.wxcl.amy.utils.common.ResultUtil;

import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/8/31 09:10
 * @Description:
 */
@RefreshScope
@RestController
@RequestMapping("/datacenter")
public class MaterialService implements IMaterialService {
    @Override
    @RequestMapping("/materialService/save")
    public ResultMsg save(MaterialBean materialBean) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/materialService/cancel")
    public ResultMsg cancel(String materialId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/materialService/remove")
    public ResultMsg remove(String materialId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/materialService/detail")
    public ResultMsg detail(String materialId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/materialService/search")
    public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
        return ResultUtil.todo("待实现");
    }
}
