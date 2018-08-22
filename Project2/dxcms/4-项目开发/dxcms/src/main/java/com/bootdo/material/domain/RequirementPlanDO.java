package com.bootdo.material.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-21 16:46:41
 */
public class RequirementPlanDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long corporationId;
	//
	private String requirementPlanNo;
	//
	private Long statusId;
	//
	private Long ownerId;
	//
	private Long purchaseDeptId;
	//
	private Long authorDeptId;
	//
	private Long guid;
	//
	private String name;
	//
	private String applyReason;
	//
	private Date stockDate;
	//
	private Date periodDate;
	//
	private Date makeDate;
	//
	private String makeUser;
	//
	private BigDecimal totalMoney;
	//
	private BigDecimal budgetMoney;
	//
	private Boolean isSubmit;
	//
	private Boolean isApproveBegin;
	//
	private Boolean isApproveFinish;
	//
	private Boolean isConfirmed;
	//
	private Boolean isActived;
	//
	private String remark;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setCorporationId(Long corporationId) {
		this.corporationId = corporationId;
	}
	/**
	 * 获取：
	 */
	public Long getCorporationId() {
		return corporationId;
	}
	/**
	 * 设置：
	 */
	public void setRequirementPlanNo(String requirementPlanNo) {
		this.requirementPlanNo = requirementPlanNo;
	}
	/**
	 * 获取：
	 */
	public String getRequirementPlanNo() {
		return requirementPlanNo;
	}
	/**
	 * 设置：
	 */
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	/**
	 * 获取：
	 */
	public Long getStatusId() {
		return statusId;
	}
	/**
	 * 设置：
	 */
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	/**
	 * 获取：
	 */
	public Long getOwnerId() {
		return ownerId;
	}
	/**
	 * 设置：
	 */
	public void setPurchaseDeptId(Long purchaseDeptId) {
		this.purchaseDeptId = purchaseDeptId;
	}
	/**
	 * 获取：
	 */
	public Long getPurchaseDeptId() {
		return purchaseDeptId;
	}
	/**
	 * 设置：
	 */
	public void setAuthorDeptId(Long authorDeptId) {
		this.authorDeptId = authorDeptId;
	}
	/**
	 * 获取：
	 */
	public Long getAuthorDeptId() {
		return authorDeptId;
	}
	/**
	 * 设置：
	 */
	public void setGuid(Long guid) {
		this.guid = guid;
	}
	/**
	 * 获取：
	 */
	public Long getGuid() {
		return guid;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	/**
	 * 获取：
	 */
	public String getApplyReason() {
		return applyReason;
	}
	/**
	 * 设置：
	 */
	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}
	/**
	 * 获取：
	 */
	public Date getStockDate() {
		return stockDate;
	}
	/**
	 * 设置：
	 */
	public void setPeriodDate(Date periodDate) {
		this.periodDate = periodDate;
	}
	/**
	 * 获取：
	 */
	public Date getPeriodDate() {
		return periodDate;
	}
	/**
	 * 设置：
	 */
	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}
	/**
	 * 获取：
	 */
	public Date getMakeDate() {
		return makeDate;
	}
	/**
	 * 设置：
	 */
	public void setMakeUser(String makeUser) {
		this.makeUser = makeUser;
	}
	/**
	 * 获取：
	 */
	public String getMakeUser() {
		return makeUser;
	}
	/**
	 * 设置：
	 */
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	/**
	 * 设置：
	 */
	public void setBudgetMoney(BigDecimal budgetMoney) {
		this.budgetMoney = budgetMoney;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getBudgetMoney() {
		return budgetMoney;
	}
	/**
	 * 设置：
	 */
	public void setIsSubmit(Boolean isSubmit) {
		this.isSubmit = isSubmit;
	}
	/**
	 * 获取：
	 */
	public Boolean getIsSubmit() {
		return isSubmit;
	}
	/**
	 * 设置：
	 */
	public void setIsApproveBegin(Boolean isApproveBegin) {
		this.isApproveBegin = isApproveBegin;
	}
	/**
	 * 获取：
	 */
	public Boolean getIsApproveBegin() {
		return isApproveBegin;
	}
	/**
	 * 设置：
	 */
	public void setIsApproveFinish(Boolean isApproveFinish) {
		this.isApproveFinish = isApproveFinish;
	}
	/**
	 * 获取：
	 */
	public Boolean getIsApproveFinish() {
		return isApproveFinish;
	}
	/**
	 * 设置：
	 */
	public void setIsConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}
	/**
	 * 获取：
	 */
	public Boolean getIsConfirmed() {
		return isConfirmed;
	}
	/**
	 * 设置：
	 */
	public void setIsActived(Boolean isActived) {
		this.isActived = isActived;
	}
	/**
	 * 获取：
	 */
	public Boolean getIsActived() {
		return isActived;
	}
	/**
	 * 设置：
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
	}
}
