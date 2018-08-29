package com.dx.client.model.purchase;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description: 货源计划明细
 */
public class AllocationPlanItemBean {
    //主键
    private UUID id;
    //货源计划id
    private UUID allocationPlanId;
    //采购计划明细id
    private UUID purchasePlanItemId;
    //供应商id
    private UUID companyId;
    //供应商名称
    private String companyName;
    //发货单位id
    private UUID deliverCompanyId;
    //发货单位名称
    private String deliverCompanyName;
    //合同id
    private UUID contractId;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAllocationPlanId() {
        return allocationPlanId;
    }

    public void setAllocationPlanId(UUID allocationPlanId) {
        this.allocationPlanId = allocationPlanId;
    }

    public UUID getPurchasePlanItemId() {
        return purchasePlanItemId;
    }

    public void setPurchasePlanItemId(UUID purchasePlanItemId) {
        this.purchasePlanItemId = purchasePlanItemId;
    }

    public UUID getCompanyId() {
        return companyId;
    }

    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public UUID getDeliverCompanyId() {
        return deliverCompanyId;
    }

    public void setDeliverCompanyId(UUID deliverCompanyId) {
        this.deliverCompanyId = deliverCompanyId;
    }

    public String getDeliverCompanyName() {
        return deliverCompanyName;
    }

    public void setDeliverCompanyName(String deliverCompanyName) {
        this.deliverCompanyName = deliverCompanyName;
    }

    public UUID getContractId() {
        return contractId;
    }

    public void setContractId(UUID contractId) {
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
