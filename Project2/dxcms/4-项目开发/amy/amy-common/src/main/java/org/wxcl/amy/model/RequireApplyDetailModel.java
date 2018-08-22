package org.wxcl.amy.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/*
 * @author cxd
 * @create 2018/8/21
 * @since 1.0.0
 * 采购申请明细
 */
public class RequireApplyDetailModel {
    //主键
    private UUID id;
    //需求申请Id
    private UUID RequireApplyId;
    //物资Id
    private UUID materialId;
    //物资
    private MaterialModel material;
    //物资子码
    private String materialSubArray;
    //需求数量
    private Double requireQty;
    //需求日期
    private Date requireDate;
    //参考价格
    private BigDecimal referencePrice;
    //预算价格
    private BigDecimal budgetPrice;
    //预算数量
    private Double budgetQty;
    //库存数量
    private Double stockQty;
    //是否在用
    private Boolean isActived;
    //是否确认(生成需求计划-true, 否则-false)
    private Boolean isConfirmed;
    //描述
    private String description;
    //备注
    private String remark;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRequireApplyId() {
        return RequireApplyId;
    }

    public void setRequireApplyId(UUID requireApplyId) {
        RequireApplyId = requireApplyId;
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

    public Double getRequireQty() {
        return requireQty;
    }

    public void setRequireQty(Double requireQty) {
        this.requireQty = requireQty;
    }

    public Date getRequireDate() {
        return requireDate;
    }

    public void setRequireDate(Date requireDate) {
        this.requireDate = requireDate;
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

    public Double getBudgetQty() {
        return budgetQty;
    }

    public void setBudgetQty(Double budgetQty) {
        this.budgetQty = budgetQty;
    }

    public Double getStockQty() {
        return stockQty;
    }

    public void setStockQty(Double stockQty) {
        this.stockQty = stockQty;
    }

    public Boolean getActived() {
        return isActived;
    }

    public void setActived(Boolean actived) {
        isActived = actived;
    }

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
