package com.dx.service.contract.fallback;

import com.dx.client.model.contract.*;
import com.dx.service.contract.service.api.IContractService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.wxcl.amy.utils.common.ResultMsg;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/4 07:56
 * @Description:
 */
@Component("contractServiceFallbackFactory")
public class ContractServiceFallbackFactory implements FallbackFactory<IContractService> {
    @Override
    public IContractService create(Throwable throwable) {
        return new IContractService() {
            @Override
            public ResultMsg save(ContractBean contractBean) {
                return null;
            }

            @Override
            public ResultMsg cancel(String contractId) {
                return null;
            }

            @Override
            public ResultMsg remove(String contractId) {
                return null;
            }

            @Override
            public ResultMsg primary(String contractId) {
                return null;
            }

            @Override
            public ResultMsg getMaterialsByContractId(String contractId) {
                return null;
            }

            @Override
            public ResultMsg getMaterialsByCodes(List<String> materialCodes) {
                return null;
            }

            @Override
            public ResultMsg getSuits(String contractId) {
                return null;
            }

            @Override
            public ResultMsg getDelivers(String contractId) {
                return null;
            }

            @Override
            public ResultMsg getElements(String contractId) {
                return null;
            }

            @Override
            public ResultMsg onSealed(String contractId) {
                return null;
            }

            @Override
            public ResultMsg onStoped(String contractId) {
                return null;
            }

            @Override
            public ResultMsg onFiled(String contractId) {
                return null;
            }

            @Override
            public ResultMsg changeContract(ContractBean contractBean) {
                return null;
            }

            @Override
            public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
                return null;
            }
        };
    }
}
