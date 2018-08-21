package org.wxcl.amy.service2.service.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wxcl.amy.service2.config.FeignConfig;
import org.wxcl.amy.service2.fallback.TestServiceFallbackFactory;
import org.wxcl.amy.service2.model.User;

import java.io.IOException;

@FeignClient(name="SERVICE2.WANGXIN.COM",
        configuration = FeignConfig.class,
        //fallback = TestServiceFallback.class,
        fallbackFactory = TestServiceFallbackFactory.class)
public interface ITestService {

    @GetMapping("/test/test1")
    public Object test1();

    @RequestMapping("/test/save")
    @ResponseBody
    public User add(@RequestBody User user);

    @RequestMapping(value = "/test/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object fileUpload2(@RequestParam("filename") String filename,
                              @RequestPart("file") MultipartFile file) throws IOException;


}