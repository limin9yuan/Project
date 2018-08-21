package org.wxcl.amy.service1.test.service;

import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wxcl.amy.model.TestModel;
import org.wxcl.amy.model.User;
import org.wxcl.amy.service1.service.api.ITestService;
import org.wxcl.amy.service1.test.biz.ITestBiz;
import org.wxcl.amy.util.ResultUtil;

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

    @Autowired
    private ITestBiz testBiz;

    @Value("${username: 没拿到}")
    private String username;

    @RequestMapping("/test1")
    public Object test1() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime=sdf.format(new Date());

        Map<String,Object> rv=new HashMap<>();
        rv.put("code","1");
        rv.put("datetime",datetime);
        rv.put("msg","hello- 配置中心参数测试："+this.username);

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

    //-------------------增删改查DEMO------------------------
    @RequestMapping("/demo/save")
    public Object save(@RequestBody TestModel testModel){
        try{
            int count=this.testBiz.save(testModel);
            if(count>=1)
                return ResultUtil.success();
            else
                return ResultUtil.fail("保存记录数:"+count);
        }catch(Exception ex){
            log.error("error",ex);
            return ResultUtil.fail(ex);
        }
    }

    @RequestMapping("/demo/detail")
    public Object detail(@RequestBody TestModel testModel){
        try{
            TestModel model=this.testBiz.detail(testModel);
            if(model!=null)
                return ResultUtil.success();
            else
                return ResultUtil.fail("没有找到数据");
        }catch(Exception ex){
            log.error(ex);
            return ResultUtil.fail(ex);
        }
    }

    @RequestMapping("/demo/remove")
    public Object remove(@RequestBody TestModel testModel){
        try{
            int count=this.testBiz.remove(testModel);
            if(count>=1)
                return ResultUtil.success();
            else
                return ResultUtil.fail("删除记录数:"+count);
        }catch(Exception ex){
            log.error(ex);
            return ResultUtil.fail(ex);
        }
    }

    @RequestMapping("/demo/list")
    @ResponseBody
    public Object list(String pageNum,String pageSize,TestModel testModel){
        try{
            PageInfo pageInfo = this.testBiz.selectPage(
                    Integer.parseInt(pageNum),
                    Integer.parseInt(pageSize),
                    testModel);
            return pageInfo;
        }catch(Exception e){
            log.error(e);
            return ResultUtil.fail(e.getMessage());
        }

    }


}
