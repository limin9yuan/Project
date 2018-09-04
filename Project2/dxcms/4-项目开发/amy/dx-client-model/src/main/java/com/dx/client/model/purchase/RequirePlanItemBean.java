package com.dx.client.model.purchase;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description: 需求计划明细
 */
public class RequirePlanItemBean {
    //主键
    private String id;
    //需求计划Id
    private String requirePlanid;
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

    public String getRequirePlanid() {
        return requirePlanid;
    }

    public void setRequirePlanid(String requirePlanid) {
        this.requirePlanid = requirePlanid;
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
