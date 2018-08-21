/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: TestService
 * Author:   wangxin
 * Date:     2018/7/26 17:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.wxcl.amy.service2.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wxcl.amy.service2.model.User;
import org.wxcl.amy.service2.service.api.ITestService;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@RequestMapping("/test")
public class TestService implements ITestService {

    private static final Log log = LogFactory.getLog(TestService.class);

    @Value("${username: 没拿到}")
    private String username;

    @RequestMapping("/test1")
    public Object test1() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime=sdf.format(new Date());

        Map<String,Object> rv=new HashMap<>();
        rv.put("code","1");
        rv.put("datetime",datetime);
        rv.put("msg","world- 配置中心参数测试："+this.username);

        return rv;
    }

    @RequestMapping("/save")
    @ResponseBody
    public User add(@RequestBody User user){
        log.info("接收请求对象："+user);
        return user;
    }

    @RequestMapping("/upload")
    public Object fileUpload2(@RequestParam("filename") String filename,
                              @RequestParam("file") MultipartFile file) throws IOException {
        long  startTime=System.currentTimeMillis();
        log.info("fileName："+file.getOriginalFilename()+" filename:"+filename);
        //String root="/Users/wangxin/dev/projects/amy-springcloud-project/amy/";
        String root="E:\\ftp_home\\wangxin\\app_test\\upload";
        String path=root+new Date().getTime()+file.getOriginalFilename();

        File newFile=new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        long  endTime=System.currentTimeMillis();
        log.info("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");

        Map<String,String> rv=new HashMap<>();
        rv.put("aaa","bbbb");
        return rv;
    }

}
