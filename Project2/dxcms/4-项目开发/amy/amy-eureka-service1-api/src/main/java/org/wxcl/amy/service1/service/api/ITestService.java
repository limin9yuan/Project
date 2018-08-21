package org.wxcl.amy.service1.service.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wxcl.amy.model.TestModel;
import org.wxcl.amy.model.User;
import org.wxcl.amy.service1.config.MultipartSupportConfig;
import org.wxcl.amy.service1.fallback.TestServiceFallbackFactory;

import java.io.IOException;

@FeignClient(name="SERVICE1.WANGXIN.COM",
        configuration = MultipartSupportConfig.class,
        //fallback = TestServiceFallback.class,
        fallbackFactory = TestServiceFallbackFactory.class
        )
public interface ITestService {

    /**
     * 连接测试接口
     * @return
     */
    @GetMapping("/test/test1")
    public Object test1();

    @RequestMapping("/test/save")
    @ResponseBody
    public User add(@RequestBody User user);

    @RequestMapping(value = "/test/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object fileUpload2(@RequestParam("filename") String filename,
                              @RequestPart("file") MultipartFile file) throws IOException ;



    //-------------------增删改查DEMO------------------------
    @RequestMapping("/test/demo/save")
    @ResponseBody
    public Object save(@RequestBody  TestModel testModel);

    @RequestMapping("/test/demo/detail")
    @ResponseBody
    public Object detail(@RequestBody TestModel testModel);

    @RequestMapping("/test/demo/remove")
    @ResponseBody
    public Object remove(@RequestBody TestModel testModel);

    @RequestMapping("/test/demo/list")
    @ResponseBody
    public Object list(@RequestParam("pageNum")  String pageNum,
                       @RequestParam("pageSize")  String pageSize,
                       @RequestBody TestModel testModel);


}

