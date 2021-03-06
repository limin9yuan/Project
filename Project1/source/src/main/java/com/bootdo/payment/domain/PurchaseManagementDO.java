package com.bootdo.payment.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 采购信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-05 15:59:36
 */
public class PurchaseManagementDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//采购编号
	private String purchaseId;
	//项目编号
	private String projectId;
	//采购申请编号
	private String approvalPurchaseId;
	//采购人
	private String purchasePerson;
	//采购时间
	private String purchaseTime;
	//交货时间
	private String purchaseDeliveryTime;
	//交货地点
	private String purchaseDeliveryPlace;
	//收货人
	private String purchaseConsignee;
	//联系电话
	private String purchasePhoneNumber;
	//物品名称
	private String purchaseName;
	//品牌
	private String purchaseBrand;
	//型号
	private String purchaseMode;
	//规格/配置
	private String purchaseConfig;
	//单位
	private String purchaseUnit;
	//单价
	private BigDecimal purchaseUnitPrice;
	//数量
	private Integer purchaseNumber;
	//总计
	private BigDecimal purchaseTotalPrice;
	//订货情况
	private String purchaseOrderStatus;
	//已付款
	private BigDecimal purchasePaid;
	//未付款
	private BigDecimal purchaseNotPaid;
	//发货情况
	private String purchaseFulfilmentStatus;
	//备注
	private String purchaseRemarks;
	//操作人
	private Long purchaseOperator;
	//操作时间
	private Date purchaseOperateTime;
	
	
	//采购人名称
	private String purchasePersonName;
	//项目名称
	private String projectName;
	
	//申请人员名称
	private String purchaseOperatorName;
	private Integer purchaseOperators;
	//主送人id
	private String mainPersonId;
	//抄送人id
	private String copyPersonId;

	public String getMainPersonId() {
		return mainPersonId;
	}

	public void setMainPersonId(String mainPersonId) {
		this.mainPersonId = mainPersonId;
	}

	public String getCopyPersonId() {
		return copyPersonId;
	}

	public void setCopyPersonId(String copyPersonId) {
		this.copyPersonId = copyPersonId;
	}

	public Integer getPurchaseOperators() {
		return purchaseOperators;
	}
	public void setPurchaseOperators(Integer purchaseOperators) {
		this.purchaseOperators = purchaseOperators;
	}
	/**
	 * 设置：采购编号
	 */
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	/**
	 * 获取：采购编号
	 */
	public String getPurchaseId() {
		return purchaseId;
	}
	/**
	 * 设置：项目编号
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：项目编号
	 */
	public String getProjectId() {
		return projectId;
	}
	/**
	 * 设置：采购申请编号
	 */
	public void setApprovalPurchaseId(String approvalPurchaseId) {
		this.approvalPurchaseId = approvalPurchaseId;
	}
	/**
	 * 获取：采购申请编号
	 */
	public String getApprovalPurchaseId() {
		return approvalPurchaseId;
	}
	/**
	 * 设置：采购人
	 */
	public void setPurchasePerson(String purchasePerson) {
		this.purchasePerson = purchasePerson;
	}
	/**
	 * 获取：采购人
	 */
	public String getPurchasePerson() {
		return purchasePerson;
	}
	/**
	 * 设置：采购时间
	 */
	public void setPurchaseTime(String purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	/**
	 * 获取：采购时间
	 */
	public String getPurchaseTime() {
		return purchaseTime;
	}
	/**
	 * 设置：交货时间
	 */
	public void setPurchaseDeliveryTime(String purchaseDeliveryTime) {
		this.purchaseDeliveryTime = purchaseDeliveryTime;
	}
	/**
	 * 获取：交货时间
	 */
	public String getPurchaseDeliveryTime() {
		return purchaseDeliveryTime;
	}
	/**
	 * 设置：交货地点
	 */
	public void setPurchaseDeliveryPlace(String purchaseDeliveryPlace) {
		this.purchaseDeliveryPlace = purchaseDeliveryPlace;
	}
	/**
	 * 获取：交货地点
	 */
	public String getPurchaseDeliveryPlace() {
		return purchaseDeliveryPlace;
	}
	/**
	 * 设置：收货人
	 */
	public void setPurchaseConsignee(String purchaseConsignee) {
		this.purchaseConsignee = purchaseConsignee;
	}
	/**
	 * 获取：收货人
	 */
	public String getPurchaseConsignee() {
		return purchaseConsignee;
	}
	/**
	 * 设置：联系电话
	 */
	public void setPurchasePhoneNumber(String purchasePhoneNumber) {
		this.purchasePhoneNumber = purchasePhoneNumber;
	}
	/**
	 * 获取：联系电话
	 */
	public String getPurchasePhoneNumber() {
		return purchasePhoneNumber;
	}
	/**
	 * 设置：物品名称
	 */
	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}
	/**
	 * 获取：物品名称
	 */
	public String getPurchaseName() {
		return purchaseName;
	}
	/**
	 * 设置：品牌
	 */
	public void setPurchaseBrand(String purchaseBrand) {
		this.purchaseBrand = purchaseBrand;
	}
	/**
	 * 获取：品牌
	 */
	public String getPurchaseBrand() {
		return purchaseBrand;
	}
	/**
	 * 设置：型号
	 */
	public void setPurchaseMode(String purchaseMode) {
		this.purchaseMode = purchaseMode;
	}
	/**
	 * 获取：型号
	 */
	public String getPurchaseMode() {
		return purchaseMode;
	}
	/**
	 * 设置：规格/配置
	 */
	public void setPurchaseConfig(String purchaseConfig) {
		this.purchaseConfig = purchaseConfig;
	}
	/**
	 * 获取：规格/配置
	 */
	public String getPurchaseConfig() {
		return purchaseConfig;
	}
	/**
	 * 设置：单位
	 */
	public void setPurchaseUnit(String purchaseUnit) {
		this.purchaseUnit = purchaseUnit;
	}
	/**
	 * 获取：单位
	 */
	public String getPurchaseUnit() {
		return purchaseUnit;
	}
	/**
	 * 设置：单价
	 */
	public void setPurchaseUnitPrice(BigDecimal purchaseUnitPrice) {
		this.purchaseUnitPrice = purchaseUnitPrice;
	}
	/**
	 * 获取：单价
	 */
	public BigDecimal getPurchaseUnitPrice() {
		return purchaseUnitPrice;
	}
	/**
	 * 设置：数量
	 */
	public void setPurchaseNumber(Integer purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}
	/**
	 * 获取：数量
	 */
	public Integer getPurchaseNumber() {
		return purchaseNumber;
	}
	/**
	 * 设置：总计
	 */
	public void setPurchaseTotalPrice(BigDecimal purchaseTotalPrice) {
		this.purchaseTotalPrice = purchaseTotalPrice;
	}
	/**
	 * 获取：总计
	 */
	public BigDecimal getPurchaseTotalPrice() {
		return purchaseTotalPrice;
	}
	/**
	 * 设置：订货情况
	 */
	public void setPurchaseOrderStatus(String purchaseOrderStatus) {
		this.purchaseOrderStatus = purchaseOrderStatus;
	}
	/**
	 * 获取：订货情况
	 */
	public String getPurchaseOrderStatus() {
		return purchaseOrderStatus;
	}
	/**
	 * 设置：已付款
	 */
	public void setPurchasePaid(BigDecimal purchasePaid) {
		this.purchasePaid = purchasePaid;
	}
	/**
	 * 获取：已付款
	 */
	public BigDecimal getPurchasePaid() {
		return purchasePaid;
	}
	/**
	 * 设置：未付款
	 */
	public void setPurchaseNotPaid(BigDecimal purchaseNotPaid) {
		this.purchaseNotPaid = purchaseNotPaid;
	}
	/**
	 * 获取：未付款
	 */
	public BigDecimal getPurchaseNotPaid() {
		return purchaseNotPaid;
	}
	/**
	 * 设置：发货情况
	 */
	public void setPurchaseFulfilmentStatus(String purchaseFulfilmentStatus) {
		this.purchaseFulfilmentStatus = purchaseFulfilmentStatus;
	}
	/**
	 * 获取：发货情况
	 */
	public String getPurchaseFulfilmentStatus() {
		return purchaseFulfilmentStatus;
	}
	/**
	 * 设置：备注
	 */
	public void setPurchaseRemarks(String purchaseRemarks) {
		this.purchaseRemarks = purchaseRemarks;
	}
	/**
	 * 获取：备注
	 */
	public String getPurchaseRemarks() {
		return purchaseRemarks;
	}
	/**
	 * 设置：操作人
	 */
	public void setPurchaseOperator(Long purchaseOperator) {
		this.purchaseOperator = purchaseOperator;
	}
	/**
	 * 获取：操作人
	 */
	public Long getPurchaseOperator() {
		return purchaseOperator;
	}
	/**
	 * 设置：操作时间
	 */
	public void setPurchaseOperateTime(Date purchaseOperateTime) {
		this.purchaseOperateTime = purchaseOperateTime;
	}
	/**
	 * 获取：操作时间
	 */
	public Date getPurchaseOperateTime() {
		return purchaseOperateTime;
	}
	/**
	 * 获取：采购人名称
	 */
	public String getPurchasePersonName() {
		return purchasePersonName;
	}
	/**
	 * 设置：采购人名称
	 */
	public void setPurchasePersonName(String purchasePersonName) {
		this.purchasePersonName = purchasePersonName;
	}
	/**
	 * 设置：项目名称
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * 获取：项目名称
	 */
	public String getProjectName() {
		return projectName;
	}
	public String getPurchaseOperatorName() {
		return purchaseOperatorName;
	}
	public void setPurchaseOperatorName(String purchaseOperatorName) {
		this.purchaseOperatorName = purchaseOperatorName;
	}
}
