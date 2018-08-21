package org.wxcl.amy.service1.fallback;

import org.springframework.web.multipart.MultipartFile;
import org.wxcl.amy.model.TestModel;
import org.wxcl.amy.model.User;
import org.wxcl.amy.service1.service.api.ITestService;
import org.wxcl.amy.util.ResultUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author wangxin
 * @create 2018/7/31
 * @since 1.0.0
 */
public class TestServiceFallback implements ITestService {
    @Override
    public Object test1() {
        return "无法调用";
    }

    @Override
    public User add(User user) {
        user = new User();
        user.setUsername("-1");
        user.setAddress("-1");
        user.setPhone("-1");
        return user;
    }

    @Override
    public Object fileUpload2(String filename, MultipartFile file) throws IOException {
        Map<String, String> rv = new HashMap<>();
        rv.put("code", "-1");
        rv.put("msg", "服务停止");
        return rv;
    }

    @Override
    public Object save(TestModel testModel) {
        return ResultUtil.fail("服务停止");
    }

    @Override
    public Object detail(TestModel testModel) {
        return ResultUtil.fail("服务停止");
    }

    @Override
    public Object remove(TestModel testModel) {
        return ResultUtil.fail("服务停止");
    }

    @Override
    public Object list(String pageNum, String pageSize, TestModel testModel) {
        return ResultUtil.fail("服务停止");
    }
}
