package com.dx.client.model.contract;

import java.util.Date;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description: 合同适用机构
 */
public class ContractSuitBean {
    //主键
    private String id;
    //适用机构id
    private String suitCorpId;
    //适用机构名称
    private String suitCorpName;
    //创建时间
    private Date createDate;
    //是否在用
    private boolean isActived;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSuitCorpId() {
        return suitCorpId;
    }

    public void setSuitCorpId(String suitCorpId) {
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

    public boolean getActived() {
        return isActived;
    }

    public void setActived(boolean actived) {
        isActived = actived;
    }
}
