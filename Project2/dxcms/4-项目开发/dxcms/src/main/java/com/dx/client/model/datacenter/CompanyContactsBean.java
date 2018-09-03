package com.dx.client.model.datacenter;

import java.util.Date;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description: 供应商、废旧物资联系人
 */
public class CompanyContactsBean {
    //主键
    private String id;
    //公司id
    private String companyId;
    //联系人
    private String linkman;
    //移动电话
    private String mobilePhone;
    //电子邮箱
    private String email;
    //是否在用
    private Boolean isActived;
    //生成日期
    private Date createDate;
    //生成人
    private String createUser;
    //备注
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActived() {
        return isActived;
    }

    public void setActived(Boolean actived) {
        isActived = actived;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
