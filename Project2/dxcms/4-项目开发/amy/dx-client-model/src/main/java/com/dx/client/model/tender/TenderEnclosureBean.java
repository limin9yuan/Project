package com.dx.client.model.tender;

import java.util.Date;

/**
 * @Auther: DX01
 * @Date: 2018/9/5 12:36
 * @Description: 招标相关附件
 */
public class TenderEnclosureBean {
    //主键
    private String id;
    //文件类型id
    private String typeId;
    //文件类型名称
    private String typeName;
    //URL地址
    private String url;
    //创建日期
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
