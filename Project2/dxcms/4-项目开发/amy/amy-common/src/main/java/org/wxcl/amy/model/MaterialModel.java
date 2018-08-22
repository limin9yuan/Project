package org.wxcl.amy.model;

import java.util.Date;
import java.util.UUID;

/*
 * @author cxd
 * @create 2018/8/21
 * @since 1.0.0
 * 物资
 */

public class MaterialModel {
    //主键
    private UUID id;
    //物资类型Id
    private UUID materialClassId;
    //物资类型名称
    private String MaterialClassName;
    //物资类型
    private DataDictModel MaterialClass;
    //物资单位
    private UUID materialUnitId;
    //物资单位名称
    private String MaterialUnitName;
    //单位
    private DataDictModel MaterialUnit;
    //审批状态Id
    private UUID statusId;
    //审批状态名称
    private String statusName;
    //审批状态
    private DataDictModel status;
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
    private Boolean isActived;
    //创建人
    private String makeUser;
    //创建日期
    private Date makeDate;
    //备注
    private String remark;
    //扩展字段1
    private String extend1;
    //扩展字段2
    private String extend2;
    //扩展字段3
    private String extend3;
    //扩展字段4
    private String extend4;
    //扩展字段5
    private String extend5;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getMaterialClassId() {
        return materialClassId;
    }

    public void setMaterialClassId(UUID materialClassId) {
        this.materialClassId = materialClassId;
    }

    public String getMaterialClassName() {
        return MaterialClassName;
    }

    public void setMaterialClassName(String materialClassName) {
        MaterialClassName = materialClassName;
    }

    public UUID getMaterialUnitId() {
        return materialUnitId;
    }

    public void setMaterialUnitId(UUID materialUnitId) {
        this.materialUnitId = materialUnitId;
    }

    public String getMaterialUnitName() {
        return MaterialUnitName;
    }

    public void setMaterialUnitName(String materialUnitName) {
        MaterialUnitName = materialUnitName;
    }

    public UUID getStatusId() {
        return statusId;
    }

    public void setStatusId(UUID statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public Boolean getActived() {
        return isActived;
    }

    public void setActived(Boolean actived) {
        isActived = actived;
    }

    public String getMakeUser() {
        return makeUser;
    }

    public void setMakeUser(String makeUser) {
        this.makeUser = makeUser;
    }

    public Date getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(Date makeDate) {
        this.makeDate = makeDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }

    public String getExtend4() {
        return extend4;
    }

    public void setExtend4(String extend4) {
        this.extend4 = extend4;
    }

    public String getExtend5() {
        return extend5;
    }

    public void setExtend5(String extend5) {
        this.extend5 = extend5;
    }
}
