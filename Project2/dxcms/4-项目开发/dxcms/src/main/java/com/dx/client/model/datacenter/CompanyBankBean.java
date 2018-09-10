package com.dx.client.model.datacenter;

import java.util.Date;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description: 供应商、废旧物资客户银行账户
 */
public class CompanyBankBean {
    //主键
    private String id;
    //公司id
    private String companyId;
    //开户行名称
    private String name;
    //账户
    private String account;
    //是否在用
    private boolean isActived;
    //创建日期
    private Date createDate;
    //创建人
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean getActived() {
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
