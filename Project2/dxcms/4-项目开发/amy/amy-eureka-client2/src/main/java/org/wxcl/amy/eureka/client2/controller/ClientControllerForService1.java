package org.wxcl.amy.eureka.client2.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wxcl.amy.service1.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author wangxin
 * @create 2018/7/26
 * @since 1.0.0
 */
@RefreshScope
@RestController
@RequestMapping("/client1")
public class ClientControllerForService1 {

    @Value("${username: 没拿到}")
    private String username;

    @Autowired
    private org.wxcl.amy.service1.service.api.ITestService testServiceA;

    @ApiOperation(value="测试1", notes="调用service1的test1方法返回相应结果")
    @RequestMapping("/test1")
    public Object test1()throws Exception{
        Object obj1=this.testServiceA.test1();
        System.out.println(obj1);
        return "ok!"+username;
    }

    @RequestMapping("/save")
    @ResponseBody
    public User add(@RequestBody User user){
        User rv= this.testServiceA.add(user);
        return rv;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Object upload(@RequestParam(value = "file", required = true) MultipartFile file,
                       @RequestParam(value = "filename") String filename)throws Exception{

       try {
           this.testServiceA.fileUpload2(filename, file);
       }catch(Exception e){
           e.printStackTrace();
           throw e;
       }
       Map<String,String> rv=new HashMap<>();
       rv.put("state","11");
       return rv;

    }
}
