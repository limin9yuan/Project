package com.dx.service.contract.service;

import com.dx.client.model.contract.ContractBean;
import com.dx.service.contract.service.api.IContractService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxcl.amy.utils.common.ResultMsg;
import org.wxcl.amy.utils.common.ResultUtil;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/21 14:50
 * @Description:
 */
@RefreshScope
@RestController
@RequestMapping("/contract")
public class ContractService implements IContractService {
    @Override
    @RequestMapping("/contractService/save")
    public ResultMsg save(ContractBean contractBean) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/contractService/cancel")
    public ResultMsg cancel(String contractId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/contractService/remove")
    public ResultMsg remove(String contractId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/contractService/primary")
    public ResultMsg primary(String contractId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/contractService/getMaterialsByContractId")
    public ResultMsg getMaterialsByContractId(String contractId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/contractService/getMaterialsByCodes")
    public ResultMsg getMaterialsByCodes(List<String> materialCodes) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/contractService/getSuits")
    public ResultMsg getSuits(String contractId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/contractService/getDelivers")
    public ResultMsg getDelivers(String contractId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/contractService/getElements")
    public ResultMsg getElements(String contractId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/contractService/onSealed")
    public ResultMsg onSealed(String contractId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/contractService/onStoped")
    public ResultMsg onStoped(String contractId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/contractService/onFiled")
    public ResultMsg onFiled(String contractId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/contractService/changeContract")
    public ResultMsg changeContract(ContractBean contractBean) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/contractService/search")
    public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
        return ResultUtil.todo("待实现");
    }
}

