package com.dx.service.datacenter.service.api;

import com.dx.client.model.datacenter.CompanyBean;
import com.dx.service.datacenter.config.MultipartSupportConfig;
import com.dx.service.datacenter.fallback.CompanyServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wxcl.amy.utils.common.ResultMsg;

import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/8/31 10:15
 * @Description:
 */
@FeignClient(name = "DATACENTERSERVICE.DX.COM",
        configuration = MultipartSupportConfig.class,
        fallbackFactory = CompanyServiceFallbackFactory.class)
public interface ICompanyService {
    //保存
    @RequestMapping("/dataCenter/CompanyService/save")
    @ResponseBody
    public ResultMsg save(@RequestBody CompanyBean companyBean);

    //注销
    @RequestMapping("/dataCenter/CompanyService/cancel")
    @ResponseBody
    public ResultMsg cancel(@RequestParam("companyId") String companyId);

    //删除
    @RequestMapping("/dataCenter/CompanyService/remove")
    @ResponseBody
    public ResultMsg remove(@RequestParam("companyId") String companyId);

    //详细
    @RequestMapping("/dataCenter/CompanyService/detail")
    @ResponseBody
    public ResultMsg detail(@RequestParam("companyId") String companyId);

    //查询
    @RequestMapping("/dataCenter/CompanyService/search")
    @ResponseBody
    public ResultMsg search(@RequestParam("pageNum") String pageNum,
                            @RequestParam("pageSize") String pageSize,
                            @RequestParam("orderBy") String orderBy,
                            @RequestParam("params") Map<String, Object> params);
}
