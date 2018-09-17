package com.dx.client.model.contract;

import java.util.Date;
import java.util.List;

/**
 * @Auther: DX01
 * @Date: 2018/9/4 09:49
 * @Description: 合同文本html
 */
public class ContractHtmlBean {
    //主键
    private String id;
    //合同id
    private String contractId;
    //合同html
    private StringBuffer htmlText;
    //创建日期
    private Date createDate;
    //备注
    private String remark;
    //合同元素列表
    private List<ContractElementBean> contractElementBeans;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
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

    public List<ContractElementBean> getContractElementBeans() {
        return contractElementBeans;
    }

    public void setContractElementBeans(List<ContractElementBean> contractElementBeans) {
        this.contractElementBeans = contractElementBeans;
    }
}
