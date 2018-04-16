package com.bootdo.contract.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 付款计划表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-12-15 16:02:12
 */
public class PayableDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//付款计划ID
	private Long payableId;
	//合同编号
	private Long contractId;
	//付款计划时间
	private Date payableDate;
	//付款计划金额
	private BigDecimal payablePrice;
	//备注
	private String payableRemarks;
	//操作人
	private String payableOperator;
	//操作时间
	private Date payableOperateTime;

	/**
	 * 设置：付款计划ID
	 */
	public void setPayableId(Long payableId) {
		this.payableId = payableId;
	}
	/**
	 * 获取：付款计划ID
	 */
	public Long getPayableId() {
		return payableId;
	}
	/**
	 * 设置：合同编号
	 */
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	/**
	 * 获取：合同编号
	 */
	public Long getContractId() {
		return contractId;
	}
	/**
	 * 设置：付款计划时间
	 */
	public void setPayableDate(Date payableDate) {
		this.payableDate = payableDate;
	}
	/**
	 * 获取：付款计划时间
	 */
	public Date getPayableDate() {
		return payableDate;
	}
	/**
	 * 设置：付款计划金额
	 */
	public void setPayablePrice(BigDecimal payablePrice) {
		this.payablePrice = payablePrice;
	}
	/**
	 * 获取：付款计划金额
	 */
	public BigDecimal getPayablePrice() {
		return payablePrice;
	}
	/**
	 * 设置：备注
	 */
	public void setPayableRemarks(String payableRemarks) {
		this.payableRemarks = payableRemarks;
	}
	/**
	 * 获取：备注
	 */
	public String getPayableRemarks() {
		return payableRemarks;
	}
	/**
	 * 设置：操作人
	 */
	public void setPayableOperator(String payableOperator) {
		this.payableOperator = payableOperator;
	}
	/**
	 * 获取：操作人
	 */
	public String getPayableOperator() {
		return payableOperator;
	}
	/**
	 * 设置：操作时间
	 */
	public void setPayableOperateTime(Date payableOperateTime) {
		this.payableOperateTime = payableOperateTime;
	}
	/**
	 * 获取：操作时间
	 */
	public Date getPayableOperateTime() {
		return payableOperateTime;
	}
}
