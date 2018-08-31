package com.dx.server.dcservice.service.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.dx.server.dcservice.config.MultipartSupportConfig;
import com.dx.server.dcservice.fallback.MaterialServiceFallbackFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dx.client.model.datacenter.MaterialBean;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description:
 */
@FeignClient(name="DCSERVICE.DX.COM",
        configuration = MultipartSupportConfig.class,
        fallbackFactory = MaterialServiceFallbackFactory.class
)
public interface IMaterialService {
    @RequestMapping("/materialService/save")
    @ResponseBody
    public Object save(@RequestBody MaterialBean materialBean);
}
