package com.bootdo.budget.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 项目采购预算表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:43:33
 */
public class BudgetPurchaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//项目预算编号
	private String budgetId;
	//项目采购预算编号
	private String purchaseId;
	//采购类型
	private String purchaseType;
	//采购金额预估
	private BigDecimal purchasePrice;
	//预估说明
	private String purchaseDescription;
	//客户承担
	private BigDecimal purchaseCustomerRate;
	//项目组承担
	private BigDecimal purchaseProjectRate;
	//备注
	private String purchaseRemarks;
	//操作人
	private Long purchaseOperator;
	//操作时间
	private Date purchaseOperateTime;
	
	//总计
	private BigDecimal purchaseTotalPrice;


	/**
	 * 设置：项目预算编号
	 */
	public void setBudgetId(String budgetId) {
		this.budgetId = budgetId;
	}
	/**
	 * 获取：项目预算编号
	 */
	public String getBudgetId() {
		return budgetId;
	}
	/**
	 * 设置：项目采购预算编号
	 */
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	/**
	 * 获取：项目采购预算编号
	 */
	public String getPurchaseId() {
		return purchaseId;
	}
	/**
	 * 设置：采购类型
	 */
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	/**
	 * 获取：采购类型
	 */
	public String getPurchaseType() {
		return purchaseType;
	}
	/**
	 * 设置：采购金额预估
	 */
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	/**
	 * 获取：采购金额预估
	 */
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}
	/**
	 * 设置：预估说明
	 */
	public void setPurchaseDescription(String purchaseDescription) {
		this.purchaseDescription = purchaseDescription;
	}
	/**
	 * 获取：预估说明
	 */
	public String getPurchaseDescription() {
		return purchaseDescription;
	}
	/**
	 * 设置：客户承担
	 */
	public void setPurchaseCustomerRate(BigDecimal purchaseCustomerRate) {
		this.purchaseCustomerRate = purchaseCustomerRate;
	}
	/**
	 * 获取：客户承担
	 */
	public BigDecimal getPurchaseCustomerRate() {
		return purchaseCustomerRate;
	}
	/**
	 * 设置：项目组承担
	 */
	public void setPurchaseProjectRate(BigDecimal purchaseProjectRate) {
		this.purchaseProjectRate = purchaseProjectRate;
	}
	/**
	 * 获取：项目组承担
	 */
	public BigDecimal getPurchaseProjectRate() {
		return purchaseProjectRate;
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
	public BigDecimal getPurchaseTotalPrice() {
		return purchaseTotalPrice;
	}
	public void setPurchaseTotalPrice(BigDecimal purchaseTotalPrice) {
		this.purchaseTotalPrice = purchaseTotalPrice;
	}
}
