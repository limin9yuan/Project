package com.dx.service.datacenter.service.api;

import com.dx.client.model.datacenter.MaterialBean;
import com.dx.service.datacenter.config.MultipartSupportConfig;
import com.dx.service.datacenter.fallback.MaterialServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wxcl.amy.utils.common.ResultMsg;

import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/8/31 09:21
 * @Description:
 */
@FeignClient(name="DATACENTERSERVICE.DX.COM",
        configuration = MultipartSupportConfig.class,
        fallbackFactory = MaterialServiceFallbackFactory.class
)
public interface IMaterialService {
    //保存
    @RequestMapping("/dataCenter/materialService/save")
    @ResponseBody
    public ResultMsg save(@RequestBody MaterialBean materialBean);

    //注销
    @RequestMapping("/dataCenter/materialService/cancel")
    @ResponseBody
    public ResultMsg cancel(@RequestParam("materialId") String materialId);

    //删除
    @RequestMapping("/dataCenter/materialService/remove")
    @ResponseBody
    public ResultMsg remove(@RequestParam("materialId") String materialId);

    //详细
    @RequestMapping("/dataCenter/materialService/detail")
    @ResponseBody
    public ResultMsg detail(@RequestParam("materialId") String materialId);

    //查询
    //orderBy参数如：blog_ID desc，示例代码：PageHelper.startPage(pageNum , pageSize); PageHelper.orderBy("blog_ID desc");
    @RequestMapping("/dataCenter/materialService/search")
    @ResponseBody
    public ResultMsg search(@RequestParam("pageNum") String pageNum,
                            @RequestParam("pageSize") String pageSize,
                            @RequestParam("orderBy") String orderBy,
                            @RequestParam("params") Map<String, Object> params);
}
