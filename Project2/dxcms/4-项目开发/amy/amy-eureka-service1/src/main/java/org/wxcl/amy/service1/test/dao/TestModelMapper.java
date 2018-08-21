package org.wxcl.amy.service1.test.dao;


import org.apache.ibatis.annotations.Mapper;
import org.wxcl.amy.model.TestModel;

import java.util.List;

@Mapper
public interface TestModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestModel record);

    int insertSelective(TestModel record);

    TestModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestModel record);

    int updateByPrimaryKey(TestModel record);

    List<TestModel> selectAll(TestModel testModel);
}