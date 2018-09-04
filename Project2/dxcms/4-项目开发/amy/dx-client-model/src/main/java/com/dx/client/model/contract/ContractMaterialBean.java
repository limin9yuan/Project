package com.dx.client.model.contract;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description: 合同物资明细
 */
public class ContractMaterialBean {
    //主键
    private String id;
    //合同id
    private String contractId;
    //适用机构id
    private String suitCorpId;
    //适用机构名称
    private String suitCorpName;
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
    //单价
    private BigDecimal price;
    //数量
    private Double qty;
    //区间数量自
    private Double qtyFrom;
    //区间数量至
    private Double qtyTo;
    //有效期自
    private Date dateFrom;
    //有效期至
    private Date dateTo;
    //合同物资父id
    private String parentId;
    //生成日期
    private Date createDate;
    //是否在用
    private boolean isActived;
    //描述
    private String description;
    //备注
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getQtyFrom() {
        return qtyFrom;
    }

    public void setQtyFrom(Double qtyFrom) {
        this.qtyFrom = qtyFrom;
    }

    public Double getQtyTo() {
        return qtyTo;
    }

    public void setQtyTo(Double qtyTo) {
        this.qtyTo = qtyTo;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
