package com.dx.service.tender.service.api;

import com.dx.client.model.tender.ImplSchemeBean;
import com.dx.client.model.tender.TenderElementBean;
import com.dx.client.model.tender.TenderHtmlBean;
import com.dx.client.model.tender.TenderSchemeBean;
import com.dx.service.tender.config.MultipartSupportConfig;
import com.dx.service.tender.fallback.ImplSchemeServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wxcl.amy.utils.common.ResultMsg;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/5 10:42
 * @Description: 实施方案API
 */
@FeignClient(name="TENDERSERVICE.DX.COM",
        configuration = MultipartSupportConfig.class,
        fallbackFactory = ImplSchemeServiceFallbackFactory.class
)
public interface IImplSchemeService {
    //保存
    @RequestMapping("/tender/implSchemeService/save")
    @ResponseBody
    public ResultMsg save(@RequestBody ImplSchemeBean implSchemeBean,                   //实施方案信息
                          @RequestBody List<TenderSchemeBean> tenderSchemeBeans,        //附件
                          @RequestBody List<TenderElementBean> tenderElementBeans,      //元素
                          @RequestBody TenderHtmlBean tenderHtmlBean,                   //文本HTML
                          @RequestParam boolean isSubmit);                             //是否提交审批

    //注销
    @RequestMapping("/tender/implSchemeService/cancel")
    @ResponseBody
    public ResultMsg cancel(@RequestParam("implSchemeId") String implSchemeId);

    //删除
    @RequestMapping("/tender/implSchemeService/remove")
    @ResponseBody
    public ResultMsg remove(@RequestParam("implSchemeId") String implSchemeId);

    //主要
    //返回数据类型ImplementSchemeBean
    @RequestMapping("/tender/implSchemeService/primary")
    @ResponseBody
    public ResultMsg primary(@RequestParam("implSchemeId") String implSchemeId);

    //获取元素
    //返回数据类型List<TenderElementBean>
    @RequestMapping("/tender/implSchemeService/getElements")
    @ResponseBody
    public ResultMsg getElements(@RequestParam("implSchemeId") String implSchemeId);

    //获取html：见ITenderFileService
    //获取附件：见ITenderFileService

    //查询
    //orderBy参数如：blog_ID desc，示例代码：PageHelper.startPage(pageNum , pageSize); PageHelper.orderBy("blog_ID desc");
    //返回Map类型
    @RequestMapping("/tender/implSchemeService/search")
    @ResponseBody
    public ResultMsg search(@RequestParam("pageNum") String pageNum,
                            @RequestParam("pageSize") String pageSize,
                            @RequestParam("orderBy") String orderBy,
                            @RequestParam("params") Map<String, Object> params);
}
