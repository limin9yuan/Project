package org.wxcl.amy.model;

import java.util.UUID;

/**
 * @Auther: cxd
 * @Date: 2018/8/27
 * @Description: 合同元素明细
 */
public class ContractElementsModel {
    //主键
    private UUID id;
    //合同id
    private UUID contractId;
    //元素键
    private String key;
    //元素值
    private String value;
    //是否在用
    private Boolean isActived;
    //备注
    private String remark;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getContractId() {
        return contractId;
    }

    public void setContractId(UUID contractId) {
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
