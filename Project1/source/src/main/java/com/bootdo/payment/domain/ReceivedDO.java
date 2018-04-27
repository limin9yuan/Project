package com.bootdo.payment.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 回款信息表 
 * 开发者：小平
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-2-24
 */

public class ReceivedDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//回款信息编号
	private String receivedId;
	//合同编号
	private String contractId;
	//业务编号
	private String businessId;
	//回款时间
	private String receivedTime;
	//回款金额
	private BigDecimal receivedPrice;
	//回款账号
	private String receivedCardNumber;
	//款项类型
	private String receivedType;
	//合同状态
	private String receivedContractStatus;
	//备注
	private String receivedRemarks;
	//操作人
	private Long receivedOperator;
	//操作时间
	private Date receivedOperateTime;
	//操作人姓名
	private String receivedOperatorName;
	//实际回款
	private String sumReceivedPrice;
	//回款率
	private String reimbursementRate;
	//企业回款数量
	private String reimbursementNumber;
	//已回款数量
	private String number;
	//已回款金额
	private String money;
	//企业名称
	private String customerName;
	//企业客户编号
	private String customerId;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 数量
	 * @return
	 */
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * 金额
	 * @return
	 */
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	/**
	 * 企业回款数量
	 */
	public String getReimbursementNumber() {
		return reimbursementNumber;
	}
	public void setReimbursementNumber(String reimbursementNumber) {
		this.reimbursementNumber = reimbursementNumber;
	}
	/**
	 * 回款率
	 */
	public String getReimbursementRate() {
		return reimbursementRate;
	}
	public void setReimbursementRate(String reimbursementRate) {
		this.reimbursementRate = reimbursementRate;
	}
	/**
	 * 实际回款
	 */
	public String getSumReceivedPrice() {
		return sumReceivedPrice;
	}
	public void setSumReceivedPrice(String sumReceivedPrice) {
		this.sumReceivedPrice = sumReceivedPrice;
	}
	/**
	 * 设置：回款信息编号
	 */
	public void setReceivedId(String receivedId) {
		this.receivedId = receivedId;
	}
	/**
	 * 获取：回款信息编号
	 */
	public String getReceivedId() {
		return receivedId;
	}
	/**
	 * 设置：合同编号
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * 获取：合同编号
	 */
	public String getContractId() {
		return contractId;
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
	 * 设置：回款时间
	 */
	public void setReceivedTime(String receivedTime) {
		this.receivedTime = receivedTime;
	}
	/**
	 * 获取：回款时间
	 */
	public String getReceivedTime() {
		return receivedTime;
	}
	/**
	 * 设置：回款金额
	 */
	public void setReceivedPrice(BigDecimal receivedPrice) {
		this.receivedPrice = receivedPrice;
	}
	/**
	 * 获取：回款金额
	 */
	public BigDecimal getReceivedPrice() {
		return receivedPrice;
	}
	/**
	 * 设置：回款账号
	 */
	public void setReceivedCardNumber(String receivedCardNumber) {
		this.receivedCardNumber = receivedCardNumber;
	}
	/**
	 * 获取：回款账号
	 */
	public String getReceivedCardNumber() {
		return receivedCardNumber;
	}
	/**
	 * 设置：款项类型
	 */
	public void setReceivedType(String receivedType) {
		this.receivedType = receivedType;
	}
	/**
	 * 获取：款项类型
	 */
	public String getReceivedType() {
		return receivedType;
	}
	/**
	 * 设置：合同状态
	 */
	public void setReceivedContractStatus(String receivedContractStatus) {
		this.receivedContractStatus = receivedContractStatus;
	}
	/**
	 * 获取：合同状态
	 */
	public String getReceivedContractStatus() {
		return receivedContractStatus;
	}
	/**
	 * 设置：备注
	 */
	public void setReceivedRemarks(String receivedRemarks) {
		this.receivedRemarks = receivedRemarks;
	}
	/**
	 * 获取：备注
	 */
	public String getReceivedRemarks() {
		return receivedRemarks;
	}
	/**
	 * 设置：操作人
	 */
	public void setReceivedOperator(Long receivedOperator) {
		this.receivedOperator = receivedOperator;
	}
	/**
	 * 获取：操作人
	 */
	public Long getReceivedOperator() {
		return receivedOperator;
	}
	/**
	 * 设置：操作时间
	 */
	public void setReceivedOperateTime(Date receivedOperateTime) {
		this.receivedOperateTime = receivedOperateTime;
	}
	/**
	 * 获取：操作时间
	 */
	public Date getReceivedOperateTime() {
		return receivedOperateTime;
	}
	public String getReceivedOperatorName() {
		return receivedOperatorName;
	}
	public void setReceivedOperatorName(String receivedOperatorName) {
		this.receivedOperatorName = receivedOperatorName;
	}
	
}
