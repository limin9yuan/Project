package com.dx.client.model.purchase;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description: 采购计划明细
 */
public class PurchasePlanItemBean {
    //主键
    private String id;
    //采购计划id
    private String purchasePlanId;
    //需求计划明细id
    private String requirePlanItemId;
    //物资id
    private String materialId;
    //物资编码
    private String materilaCode;
    //物资名称
    private String materialName;
    //单位名称
    private String materialUnitName;
    //规格
    private String specification;
    //材质
    private String texture;
    //物资子码
    private String materialSubArray;
    //采购员Id
    private String purchaserId;
    //采购员名称
    private String purchaserName;
    //需求数量
    private Double requireQty;
    //采购数量
    private Double purchaseQty;
    //预算数量
    private Double budgetQty;
    //参考价格
    private BigDecimal referencePrice;
    //预算价格
    private BigDecimal budgetPrice;
    //需求日期
    private Date requireDate;
    //到货日期
    private Date arriveDate;
    //是否关闭
    private boolean isClosed;
    //是否已受理
    private boolean isAccepted;
    //生成日期
    private Date createDate;
    //是否可用
    private boolean isActived;
    //描述
    private String description;
    //备注(一般用于系统备注)
    private String Remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPurchasePlanId() {
        return purchasePlanId;
    }

    public void setPurchasePlanId(String purchasePlanId) {
        this.purchasePlanId = purchasePlanId;
    }

    public String getRequirePlanItemId() {
        return requirePlanItemId;
    }

    public void setRequirePlanItemId(String requirePlanItemId) {
        this.requirePlanItemId = requirePlanItemId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterilaCode() {
        return materilaCode;
    }

    public void setMaterilaCode(String materilaCode) {
        this.materilaCode = materilaCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialUnitName() {
        return materialUnitName;
    }

    public void setMaterialUnitName(String materialUnitName) {
        this.materialUnitName = materialUnitName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getMaterialSubArray() {
        return materialSubArray;
    }

    public void setMaterialSubArray(String materialSubArray) {
        this.materialSubArray = materialSubArray;
    }

    public String getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(String purchaserId) {
        this.purchaserId = purchaserId;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public Double getRequireQty() {
        return requireQty;
    }

    public void setRequireQty(Double requireQty) {
        this.requireQty = requireQty;
    }

    public Double getPurchaseQty() {
        return purchaseQty;
    }

    public void setPurchaseQty(Double purchaseQty) {
        this.purchaseQty = purchaseQty;
    }

    public Double getBudgetQty() {
        return budgetQty;
    }

    public void setBudgetQty(Double budgetQty) {
        this.budgetQty = budgetQty;
    }

    public BigDecimal getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(BigDecimal referencePrice) {
        this.referencePrice = referencePrice;
    }

    public BigDecimal getBudgetPrice() {
        return budgetPrice;
    }

    public void setBudgetPrice(BigDecimal budgetPrice) {
        this.budgetPrice = budgetPrice;
    }

    public Date getRequireDate() {
        return requireDate;
    }

    public void setRequireDate(Date requireDate) {
        this.requireDate = requireDate;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    public boolean getClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
