package org.wxcl.amy.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/*
 * @author cxd
 * @create 2018/8/21
 * @since 1.0.0
 * 需求计划明细
 */
public class RequirePlanDetailModel {
    //主键
    private UUID id;
    //需求计划Id
    private UUID requirePlanid;
    //物资Id
    private UUID materialId;
    //物资
    private MaterialModel material;
    //物资子码
    private String materialSubArray;
    //采购员Id
    private UUID purchaserId;
    //需求数量
    private Double requireQty;
    //采购数量
    private Double purchaseQty;
    //预算数量
    private Double budgetQty;
    //参考单价
    private BigDecimal referencePrice;
    //预算单价
    private BigDecimal budgetPrice;
    //需求日期
    private Date requireDate;
    //到货日期
    private Date arriveDate;
    //库存量
    private Double stockQty;
    //可用量
    private Double usableQty;
    //在途量
    private Double onwayQty;
    //安全储备量
    private Double reserveQty;
    //是否关闭
    private Boolean isClosed;
    //是否已经采购
    private Boolean isPurchased;
    //生成日期
    private Date createDate;
    //是否可用
    private Boolean isActived;
    //描述
    private String description;
    //备注(一般用于系统备注)
    private String Remark;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRequirePlanid() {
        return requirePlanid;
    }

    public void setRequirePlanid(UUID requirePlanid) {
        this.requirePlanid = requirePlanid;
    }

    public UUID getMaterialId() {
        return materialId;
    }

    public void setMaterialId(UUID materialId) {
        this.materialId = materialId;
    }

    public MaterialModel getMaterial() {
        return material;
    }

    public void setMaterial(MaterialModel material) {
        this.material = material;
    }

    public String getMaterialSubArray() {
        return materialSubArray;
    }

    public void setMaterialSubArray(String materialSubArray) {
        this.materialSubArray = materialSubArray;
    }

    public UUID getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(UUID purchaserId) {
        this.purchaserId = purchaserId;
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

    public Double getStockQty() {
        return stockQty;
    }

    public void setStockQty(Double stockQty) {
        this.stockQty = stockQty;
    }

    public Double getUsableQty() {
        return usableQty;
    }

    public void setUsableQty(Double usableQty) {
        this.usableQty = usableQty;
    }

    public Double getOnwayQty() {
        return onwayQty;
    }

    public void setOnwayQty(Double onwayQty) {
        this.onwayQty = onwayQty;
    }

    public Double getReserveQty() {
        return reserveQty;
    }

    public void setReserveQty(Double reserveQty) {
        this.reserveQty = reserveQty;
    }

    public Boolean getClosed() {
        return isClosed;
    }

    public void setClosed(Boolean closed) {
        isClosed = closed;
    }

    public Boolean getPurchased() {
        return isPurchased;
    }

    public void setPurchased(Boolean purchased) {
        isPurchased = purchased;
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
