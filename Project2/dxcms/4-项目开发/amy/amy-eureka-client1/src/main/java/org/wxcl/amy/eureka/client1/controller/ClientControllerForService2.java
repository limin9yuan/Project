package org.wxcl.amy.eureka.client1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wxcl.amy.service2.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author wangxin
 * @create 2018/7/26
 * @since 1.0.0
 */
@RestController
@RequestMapping("/client2")
public class ClientControllerForService2 {

    @Autowired
    private org.wxcl.amy.service2.service.api.ITestService testServiceB;

    @RequestMapping("/test1")
    public Object test1()throws Exception{
        Object obj1=this.testServiceB.test1();
        System.out.println(obj1);
        return "ok!";
    }

    @RequestMapping("/save")
    @ResponseBody
    public User add(@RequestBody User user){
        User rv= this.testServiceB.add(user);
        return rv;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Object upload(@RequestParam(value = "file", required = true) MultipartFile file,
                         @RequestParam(value = "filename") String filename)throws Exception{

        try {
            this.testServiceB.fileUpload2(filename, file);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        Map<String,String> rv=new HashMap<>();
        rv.put("state","11");
        return rv;

    }
}
