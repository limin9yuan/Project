package com.dx.client.model.contract;

import java.util.Date;
import java.util.UUID;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description: 合同适用机构
 */
public class ContractSuitBean {
    //主键
    private UUID id;
    //适用机构id
    private UUID suitCorpId;
    //适用机构名称
    private String suitCorpName;
    //创建时间
    private Date createDate;
    //是否在用
    private Boolean isActived;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getSuitCorpId() {
        return suitCorpId;
    }

    public void setSuitCorpId(UUID suitCorpId) {
        this.suitCorpId = suitCorpId;
    }

    public String getSuitCorpName() {
        return suitCorpName;
    }

    public void setSuitCorpName(String suitCorpName) {
        this.suitCorpName = suitCorpName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getActived() {
        return isActived;
    }

    public void setActived(Boolean actived) {
        isActived = actived;
    }
}
