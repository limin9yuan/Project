package com.dx.client.model.datacenter;

import java.util.Date;
import java.util.UUID;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description: 供应商、废旧物资客户
 */
public class CompanyBean {
    //主键
    private UUID id;
    //类型id
    private UUID typeId;
    //类型名称
    private String typeName;
    //行政区域id
    private UUID districtId;
    //行政区域名称
    private String districtName;
    //合作方式id
    private UUID cooperationId;
    //合作方式名称
    private String cooperationName;
    //统一信用代码（税号）
    private String creditCode;
    //编码
    private String code;
    //简码
    private String shortCode;
    //名称
    private String name;
    //别名
    private String aliasName;
    //注册日期
    private Date registerDate;
    //注册地址
    private Date registerAddress;
    //注册资金
    private Double registerCapital;
    //注册资金货币类型id
    private UUID currencyTypeId;
    //注册资金货币类型名称
    private String currencyTypeName;
    //法人
    private String legalPerson;
    //电话
    private String telephone;
    //传真
    private String fax;
    //经营范围
    private String dealScope;
    //经营期限自
    private String dealDateBegin;
    //经营期限至
    private String dealDateEnd;
    //是否在用
    private Boolean isActived;
    //是否加入黑名单
    private Boolean isBlacklist;
    //生成日期
    private Date createDate;
    //生成人
    private String createUser;
    //备注
    private String remark;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTypeId() {
        return typeId;
    }

    public void setTypeId(UUID typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public UUID getDistrictId() {
        return districtId;
    }

    public void setDistrictId(UUID districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public UUID getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(UUID cooperationId) {
        this.cooperationId = cooperationId;
    }

    public String getCooperationName() {
        return cooperationName;
    }

    public void setCooperationName(String cooperationName) {
        this.cooperationName = cooperationName;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(Date registerAddress) {
        this.registerAddress = registerAddress;
    }

    public Double getRegisterCapital() {
        return registerCapital;
    }

    public void setRegisterCapital(Double registerCapital) {
        this.registerCapital = registerCapital;
    }

    public UUID getCurrencyTypeId() {
        return currencyTypeId;
    }

    public void setCurrencyTypeId(UUID currencyTypeId) {
        this.currencyTypeId = currencyTypeId;
    }

    public String getCurrencyTypeName() {
        return currencyTypeName;
    }

    public void setCurrencyTypeName(String currencyTypeName) {
        this.currencyTypeName = currencyTypeName;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDealScope() {
        return dealScope;
    }

    public void setDealScope(String dealScope) {
        this.dealScope = dealScope;
    }

    public String getDealDateBegin() {
        return dealDateBegin;
    }

    public void setDealDateBegin(String dealDateBegin) {
        this.dealDateBegin = dealDateBegin;
    }

    public String getDealDateEnd() {
        return dealDateEnd;
    }

    public void setDealDateEnd(String dealDateEnd) {
        this.dealDateEnd = dealDateEnd;
    }

    public Boolean getActived() {
        return isActived;
    }

    public void setActived(Boolean actived) {
        isActived = actived;
    }

    public Boolean getBlacklist() {
        return isBlacklist;
    }

    public void setBlacklist(Boolean blacklist) {
        isBlacklist = blacklist;
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
