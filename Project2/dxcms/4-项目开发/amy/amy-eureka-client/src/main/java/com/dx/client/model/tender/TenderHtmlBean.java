package com.dx.client.model.tender;

import java.util.Date;

/**
 * @Auther: DX01
 * @Date: 2018/9/5 12:45
 * @Description: 招标相关html
 */
public class TenderHtmlBean {
    //主键
    private String id;
    //合同html
    private StringBuffer htmlText;
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

    public StringBuffer getHtmlText() {
        return htmlText;
    }

    public void setHtmlText(StringBuffer htmlText) {
        this.htmlText = htmlText;
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
