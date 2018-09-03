package com.dx.client.model.contract;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description: 合同元素
 */
public class ContractElementBean {
    //主键
    private String id;
    //合同id
    private String contractId;
    //元素键
    private String key;
    //元素值
    private String value;
    //是否在用
    private Boolean isActived;
    //备注
    private String remark;

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

    public Boolean getActived() {
        return isActived;
    }

    public void setActived(Boolean actived) {
        isActived = actived;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
