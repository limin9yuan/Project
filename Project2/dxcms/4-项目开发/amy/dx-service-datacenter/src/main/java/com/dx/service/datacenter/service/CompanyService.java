package com.dx.service.datacenter.service;

import com.dx.client.model.datacenter.CompanyBean;
import com.dx.service.datacenter.service.api.ICompanyService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxcl.amy.utils.common.ResultMsg;
import org.wxcl.amy.utils.common.ResultUtil;

import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/21 12:10
 * @Description:
 */
@RefreshScope
@RestController
@RequestMapping("/datacenter")
public class CompanyService implements ICompanyService {
    @Override
    @RequestMapping("/CompanyService/save")
    public ResultMsg save(CompanyBean companyBean) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/CompanyService/cancel")
    public ResultMsg cancel(String companyId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/CompanyService/remove")
    public ResultMsg remove(String companyId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/CompanyService/detail")
    public ResultMsg detail(String companyId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/CompanyService/search")
    public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
        return ResultUtil.todo("待实现");
    }
}
