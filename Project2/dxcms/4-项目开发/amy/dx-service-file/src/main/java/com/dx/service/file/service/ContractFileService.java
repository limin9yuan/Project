package com.dx.service.file.service;

import com.dx.client.model.contract.ContractHtmlBean;
import com.dx.service.file.service.api.IContractFileService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxcl.amy.utils.common.ResultMsg;
import org.wxcl.amy.utils.common.ResultUtil;

/**
 * @Auther: DX01
 * @Date: 2018/9/21 15:01
 * @Description:
 */
@RefreshScope
@RestController
@RequestMapping("/file")
public class ContractFileService implements IContractFileService {
    @Override
    @RequestMapping("/contractFileService/save")
    public ResultMsg save(ContractHtmlBean contractHtmlBean) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/contractFileService/getHtml")
    public ResultMsg getHtml(String contractId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/contractFileService/getEnclosures")
    public ResultMsg getEnclosures(String contractId) {
        return ResultUtil.todo("待实现");
    }
}
