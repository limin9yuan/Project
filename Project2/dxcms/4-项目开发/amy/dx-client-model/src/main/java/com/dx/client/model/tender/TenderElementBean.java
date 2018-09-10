package com.dx.client.model.tender;

import java.util.Date;

/**
 * @Auther: DX01
 * @Date: 2018/9/5 12:47
 * @Description: 招标相关元素
 */
public class TenderElementBean {
    //主键
    private String id;
    //元素键
    private String key;
    //元素值
    private String value;
    //是否在用
    private boolean isActived;
    //创建日期
    private Date createDate;
    //备注
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isActived() {
        return isActived;
    }

    public void setActived(boolean actived) {
        isActived = actived;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
