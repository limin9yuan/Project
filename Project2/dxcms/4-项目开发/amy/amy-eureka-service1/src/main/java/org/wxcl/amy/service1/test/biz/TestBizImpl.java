package org.wxcl.amy.service1.test.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wxcl.amy.model.TestModel;
import org.wxcl.amy.service1.test.dao.TestModelMapper;

import java.util.List;

/**
 *
 *
 * @author wangxin
 * @create 2018/8/17
 * @since 1.0.0
 */
@Service("testBiz")
@Transactional(rollbackFor = Exception.class)
public class TestBizImpl implements ITestBiz {

    @Autowired
    private TestModelMapper testModelMapper;

    @Override
    public int save(TestModel testModel) throws Exception {
        if(testModel.getId()==null)
        return this.testModelMapper.insertSelective(testModel);
        else
            return this.testModelMapper.updateByPrimaryKeySelective(testModel);
    }

    @Override
    public TestModel detail(TestModel testModel) throws Exception {
        return this.testModelMapper.selectByPrimaryKey(testModel.getId());
    }

    @Override
    public int remove(TestModel testModel) throws Exception {
        return this.testModelMapper.deleteByPrimaryKey(testModel.getId());
    }

    @Override
    public PageInfo selectPage(Integer page, Integer size, TestModel testModel) throws Exception {
        page=page<=0?1:page;
        size=size<=10?10:size;
        PageHelper.startPage(page, size);
        List<TestModel> list = this.testModelMapper.selectAll(testModel);
        PageInfo pageInfo = new PageInfo(list);
        Page pageObj = (Page) list;
        return pageInfo;
    }
}
