package com.dx.service.file.fallback;

import com.dx.client.model.contract.ContractHtmlBean;
import com.dx.service.file.service.api.IContractFileService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.wxcl.amy.utils.common.ResultMsg;

import java.util.List;

/**
 * @Auther: DX01
 * @Date: 2018/9/4 10:14
 * @Description:
 */
@Component("contractFileServiceFallbackFactory")
public class ContractFileServiceFallbackFactory implements FallbackFactory<IContractFileService> {
    @Override
    public IContractFileService create(Throwable throwable) {
        return new IContractFileService() {
            @Override
            public ResultMsg save(ContractHtmlBean contractHtmlBean) {
                return null;
            }

            @Override
            public ResultMsg getHtml(String contractId) {
                return null;
            }

            @Override
            public ResultMsg getEnclosures(String contractId) {
                return null;
            }
        };
    }
}
