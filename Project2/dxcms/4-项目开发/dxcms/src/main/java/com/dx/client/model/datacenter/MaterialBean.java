package com.dx.client.model.datacenter;

import java.util.Date;

/*
 * @author cxd
 * @create 2018/8/21
 * @since 1.0.0
 * 物资
 */
public class MaterialBean {
    //主键
    private String id;
    //物资类型Id
    private String materialClassId;
    //物资类型编码
    private String materialClassCode;
    //物资类型名称
    private String materialClassName;
    //父物资id
    private String materialParentId;
    //物资单位id
    private String materialUnitId;
    //物资单位名称
    private String materialUnitName;
    //物资编码
    private String code;
    //物资简码
    private String shortCode;
    //物资名称
    private String name;
    //物资别名
    private String aliasName;
    //品牌
    private String brand;
    //规格
    private String specification;
    //型号
    private String modelNumber;
    //材质
    private String texture;
    //采购周期
    private Integer purchaseCycle;
    //内包装条码
    private String innerBarCode;
    //外包装条码
    private String outsideBarCode;
    //中间包装条码
    private String middleBarCode;
    //是否在用
    private boolean isActived;
    //创建人
    private String createUser;
    //创建日期
    private Date createDate;
    //备注
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterialClassId() {
        return materialClassId;
    }

    public void setMaterialClassId(String materialClassId) {
        this.materialClassId = materialClassId;
    }

    public String getMaterialClassCode() {
        return materialClassCode;
    }

    public void setMaterialClassCode(String materialClassCode) {
        this.materialClassCode = materialClassCode;
    }

    public String getMaterialClassName() {
        return materialClassName;
    }

    public void setMaterialClassName(String materialClassName) {
        this.materialClassName = materialClassName;
    }

    public String getMaterialParentId() {
        return materialParentId;
    }

    public void setMaterialParentId(String materialParentId) {
        this.materialParentId = materialParentId;
    }

    public String getMaterialUnitId() {
        return materialUnitId;
    }

    public void setMaterialUnitId(String materialUnitId) {
        this.materialUnitId = materialUnitId;
    }

    public String getMaterialUnitName() {
        return materialUnitName;
    }

    public void setMaterialUnitName(String materialUnitName) {
        this.materialUnitName = materialUnitName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public Integer getPurchaseCycle() {
        return purchaseCycle;
    }

    public void setPurchaseCycle(Integer purchaseCycle) {
        this.purchaseCycle = purchaseCycle;
    }

    public String getInnerBarCode() {
        return innerBarCode;
    }

    public void setInnerBarCode(String innerBarCode) {
        this.innerBarCode = innerBarCode;
    }

    public String getOutsideBarCode() {
        return outsideBarCode;
    }

    public void setOutsideBarCode(String outsideBarCode) {
        this.outsideBarCode = outsideBarCode;
    }

    public String getMiddleBarCode() {
        return middleBarCode;
    }

    public void setMiddleBarCode(String middleBarCode) {
        this.middleBarCode = middleBarCode;
    }

    public boolean getActived() {
        return isActived;
    }

    public void setActived(boolean actived) {
        isActived = actived;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
