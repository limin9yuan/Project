package com.bootdo.common.dao;

import com.bootdo.common.domain.LogDO;
import com.bootdo.common.domain.MainCopyPersonDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Mingyuan Li on 2018/4/13.
 * Package name: com.bootdo.common.dao.
 * Project name: bootdo.
 */
@Mapper
public interface MainCopyPersonDao {

    List<MainCopyPersonDO> list(Map<String,Object> map);

    int save(MainCopyPersonDO mainCopyPerson);

    int count(Map<String,Object> map);
}