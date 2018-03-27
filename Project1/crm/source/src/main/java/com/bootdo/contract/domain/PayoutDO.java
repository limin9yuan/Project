package com.bootdo.contract.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 请款申请表
 * 
 * @author sw
 * @email 1992lcg@163.com
 * @date 2017-11-30 16:36:08
 */
public class PayoutDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//请款申请编号
	private String payoutId;
	//企业客户编号
	private String customerId;
	//业务编号
	private String businessId;
	//项目编号
	private String projectId;
	//申请人姓名
	private String payoutPerson;
	//请款用途
	private String payoutUseage;
	//请款金额
	private BigDecimal payoutPrice;
	//结算方式
	private String payoutMeans;
	//收款人银行
	private String payoutBeneficiaryBank;
	//银行卡号
	private String payoutCardNumber;
	//关联出差申请编号
	private String payoutRelatedContractId;
	//实际请款结果
	private String payoutResult;
	//备注
	private String payoutRemarks;
	//审批状态
	private Integer payoutApprovalStatus;
	//操作人
	private Long payoutOperator;
	//修改时间
	private Date payoutOperateTime;
	//创建人
	private Long payoutCreator;
	//创建时间
	private Date payoutCreateTime;

	/**
	 * 设置：请款申请编号
	 */
	public void setPayoutId(String payoutId) {
		this.payoutId = payoutId;
	}
	/**
	 * 获取：请款申请编号
	 */
	public String getPayoutId() {
		return payoutId;
	}
	/**
	 * 设置：企业客户编号
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取：企业客户编号
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * 设置：业务编号
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	/**
	 * 获取：业务编号
	 */
	public String getBusinessId() {
		return businessId;
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
	 * 设置：申请人姓名
	 */
	public void setPayoutPerson(String payoutPerson) {
		this.payoutPerson = payoutPerson;
	}
	/**
	 * 获取：申请人姓名
	 */
	public String getPayoutPerson() {
		return payoutPerson;
	}
	/**
	 * 设置：请款用途
	 */
	public void setPayoutUseage(String payoutUseage) {
		this.payoutUseage = payoutUseage;
	}
	/**
	 * 获取：请款用途
	 */
	public String getPayoutUseage() {
		return payoutUseage;
	}
	/**
	 * 设置：请款金额
	 */
	public void setPayoutPrice(BigDecimal payoutPrice) {
		this.payoutPrice = payoutPrice;
	}
	/**
	 * 获取：请款金额
	 */
	public BigDecimal getPayoutPrice() {
		return payoutPrice;
	}
	/**
	 * 设置：结算方式
	 */
	public void setPayoutMeans(String payoutMeans) {
		this.payoutMeans = payoutMeans;
	}
	/**
	 * 获取：结算方式
	 */
	public String getPayoutMeans() {
		return payoutMeans;
	}
	/**
	 * 设置：收款人银行
	 */
	public void setPayoutBeneficiaryBank(String payoutBeneficiaryBank) {
		this.payoutBeneficiaryBank = payoutBeneficiaryBank;
	}
	/**
	 * 获取：收款人银行
	 */
	public String getPayoutBeneficiaryBank() {
		return payoutBeneficiaryBank;
	}
	/**
	 * 设置：银行卡号
	 */
	public void setPayoutCardNumber(String payoutCardNumber) {
		this.payoutCardNumber = payoutCardNumber;
	}
	/**
	 * 获取：银行卡号
	 */
	public String getPayoutCardNumber() {
		return payoutCardNumber;
	}
	/**
	 * 设置：关联出差申请编号
	 */
	public void setPayoutRelatedContractId(String payoutRelatedContractId) {
		this.payoutRelatedContractId = payoutRelatedContractId;
	}
	/**
	 * 获取：关联出差申请编号
	 */
	public String getPayoutRelatedContractId() {
		return payoutRelatedContractId;
	}
	/**
	 * 设置：实际请款结果
	 */
	public void setPayoutResult(String payoutResult) {
		this.payoutResult = payoutResult;
	}
	/**
	 * 获取：实际请款结果
	 */
	public String getPayoutResult() {
		return payoutResult;
	}
	/**
	 * 设置：备注
	 */
	public void setPayoutRemarks(String payoutRemarks) {
		this.payoutRemarks = payoutRemarks;
	}
	/**
	 * 获取：备注
	 */
	public String getPayoutRemarks() {
		return payoutRemarks;
	}
	/**
	 * 设置：审批状态
	 */
	public void setPayoutApprovalStatus(Integer payoutApprovalStatus) {
		this.payoutApprovalStatus = payoutApprovalStatus;
	}
	/**
	 * 获取：审批状态
	 */
	public Integer getPayoutApprovalStatus() {
		return payoutApprovalStatus;
	}
	/**
	 * 设置：操作人
	 */
	public void setPayoutOperator(Long payoutOperator) {
		this.payoutOperator = payoutOperator;
	}
	/**
	 * 获取：操作人
	 */
	public Long getPayoutOperator() {
		return payoutOperator;
	}
	/**
	 * 设置：修改时间
	 */
	public void setPayoutOperateTime(Date payoutOperateTime) {
		this.payoutOperateTime = payoutOperateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getPayoutOperateTime() {
		return payoutOperateTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setPayoutCreator(Long payoutCreator) {
		this.payoutCreator = payoutCreator;
	}
	/**
	 * 获取：创建人
	 */
	public Long getPayoutCreator() {
		return payoutCreator;
	}
	/**
	 * 设置：创建时间
	 */
	public void setPayoutCreateTime(Date payoutCreateTime) {
		this.payoutCreateTime = payoutCreateTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getPayoutCreateTime() {
		return payoutCreateTime;
	}
}
