package org.wxcl.amy.model;

import java.util.UUID;

/*
 * @author cxd
 * @create 2018/8/22
 * @since 1.0.0
 * 组织机构
 */

public class CorporationModel {
    //主键
    private UUID id;
    //编号
    private String code;
    //名称
    private String name;

    //TODO:其他属性


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
