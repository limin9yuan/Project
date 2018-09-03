package com.dx.client.model.purchase;

import java.math.BigDecimal;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description: 货源计划明细
 */
public class AllotPlanItemBean {
    //主键
    private String id;
    //货源计划id
    private String allotPlanId;
    //采购计划明细id
    private String purchasePlanItemId;
    //供应商id
    private String companyId;
    //供应商名称
    private String companyName;
    //发货单位id
    private String deliverCompanyId;
    //发货单位名称
    private String deliverCompanyName;
    //合同id
    private String contractId;
    //分配比例
    private Double allotRatio;
    //分配数量
    private Double allotQty;
    //合同单价
    private BigDecimal unitPrice;
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

    public String getAllotPlanId() {
        return allotPlanId;
    }

    public void setAllotPlanId(String allotPlanId) {
        this.allotPlanId = allotPlanId;
    }

    public String getPurchasePlanItemId() {
        return purchasePlanItemId;
    }

    public void setPurchasePlanItemId(String purchasePlanItemId) {
        this.purchasePlanItemId = purchasePlanItemId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDeliverCompanyId() {
        return deliverCompanyId;
    }

    public void setDeliverCompanyId(String deliverCompanyId) {
        this.deliverCompanyId = deliverCompanyId;
    }

    public String getDeliverCompanyName() {
        return deliverCompanyName;
    }

    public void setDeliverCompanyName(String deliverCompanyName) {
        this.deliverCompanyName = deliverCompanyName;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Double getAllotRatio() {
        return allotRatio;
    }

    public void setAllotRatio(Double allotRatio) {
        this.allotRatio = allotRatio;
    }

    public Double getAllotQty() {
        return allotQty;
    }

    public void setAllotQty(Double allotQty) {
        this.allotQty = allotQty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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
