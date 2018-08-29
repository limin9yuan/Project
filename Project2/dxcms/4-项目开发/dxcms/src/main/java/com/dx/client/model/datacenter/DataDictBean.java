package com.dx.client.model.datacenter;

import java.util.Date;
import java.util.UUID;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description: 数据字典
 */
public class DataDictBean {
    //编号
    private UUID id;
    //标签名
    private String name;
    //数据值
    private String value;
    //类型
    private String type;
    //描述
    private String description;
    //排序（升序）
    private Double sort;
    //父级编号
    private UUID parentId;
    //创建者id
    private UUID createUserId;
    //创建人
    private String createUserName;
    //创建时间
    private Date createDate;
    //更新者id
    private UUID updateUserId;
    //更新者
    private String updateUserName;
    //更新时间
    private Date updateDate;
    //备注信息
    private String remark;
    //删除标记
    private Boolean delFlag;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSort() {
        return sort;
    }

    public void setSort(Double sort) {
        this.sort = sort;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    public UUID getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(UUID createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public UUID getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(UUID updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}
