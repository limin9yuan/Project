package com.dx.client.model.purchase;

import java.util.Date;

/**
 * @Auther: DX01
 * @Date: 2018/9/3 19:06
 * @Description: 采购订单变更
 */
public class PurchaseOrderChangeBean {
    //主键
    private String id;
    //采购订单id
    private String purchaseOrderId;
    //变更类型id
    private String changeTypeId;
    //变更类型名称
    private String changeTypeName;
    //变更项目
    private String changeItem;
    //变更前
    private String beforeItem;
    //变更后
    private String afterItem;
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

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public String getChangeTypeId() {
        return changeTypeId;
    }

    public void setChangeTypeId(String changeTypeId) {
        this.changeTypeId = changeTypeId;
    }

    public String getChangeTypeName() {
        return changeTypeName;
    }

    public void setChangeTypeName(String changeTypeName) {
        this.changeTypeName = changeTypeName;
    }

    public String getChangeItem() {
        return changeItem;
    }

    public void setChangeItem(String changeItem) {
        this.changeItem = changeItem;
    }

    public String getBeforeItem() {
        return beforeItem;
    }

    public void setBeforeItem(String beforeItem) {
        this.beforeItem = beforeItem;
    }

    public String getAfterItem() {
        return afterItem;
    }

    public void setAfterItem(String afterItem) {
        this.afterItem = afterItem;
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
