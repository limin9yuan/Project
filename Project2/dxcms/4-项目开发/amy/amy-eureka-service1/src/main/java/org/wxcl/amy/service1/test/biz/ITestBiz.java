package org.wxcl.amy.service1.test.biz;

import com.github.pagehelper.PageInfo;
import org.wxcl.amy.model.TestModel;

/**
 *
 *
 * @author wangxin
 * @create 2018/8/17
 * @since 1.0.0
 */
public interface ITestBiz {

    public int save(TestModel testModel)throws Exception;

    public TestModel detail(TestModel testModel)throws Exception;

    public int remove(TestModel testModel)throws Exception;

    public PageInfo selectPage(Integer page, Integer size, TestModel testModel) throws Exception ;

}
