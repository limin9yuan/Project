package com.bootdo.contract.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 收款计划表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-12-18 10:17:38
 */
public class ReceivableDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//收款计划ID
	private Long receivableId;
	//合同编号
	private Long contractId;
	//收款计划时间
	private Date receivableDate;
	//收款计划金额
	private BigDecimal receivablePrice;
	//收款负责人
	private String receivableOwner;
	//备注
	private String receivableRemarks;
	//操作人
	private String receivableOperator;
	//操作时间
	private Date receivableOperateTime;

	/**
	 * 设置：收款计划ID
	 */
	public void setReceivableId(Long receivableId) {
		this.receivableId = receivableId;
	}
	/**
	 * 获取：收款计划ID
	 */
	public Long getReceivableId() {
		return receivableId;
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
	 * 设置：收款计划时间
	 */
	public void setReceivableDate(Date receivableDate) {
		this.receivableDate = receivableDate;
	}
	/**
	 * 获取：收款计划时间
	 */
	public Date getReceivableDate() {
		return receivableDate;
	}
	/**
	 * 设置：收款计划金额
	 */
	public void setReceivablePrice(BigDecimal receivablePrice) {
		this.receivablePrice = receivablePrice;
	}
	/**
	 * 获取：收款计划金额
	 */
	public BigDecimal getReceivablePrice() {
		return receivablePrice;
	}
	/**
	 * 设置：收款负责人
	 */
	public void setReceivableOwner(String receivableOwner) {
		this.receivableOwner = receivableOwner;
	}
	/**
	 * 获取：收款负责人
	 */
	public String getReceivableOwner() {
		return receivableOwner;
	}
	/**
	 * 设置：备注
	 */
	public void setReceivableRemarks(String receivableRemarks) {
		this.receivableRemarks = receivableRemarks;
	}
	/**
	 * 获取：备注
	 */
	public String getReceivableRemarks() {
		return receivableRemarks;
	}
	/**
	 * 设置：操作人
	 */
	public void setReceivableOperator(String receivableOperator) {
		this.receivableOperator = receivableOperator;
	}
	/**
	 * 获取：操作人
	 */
	public String getReceivableOperator() {
		return receivableOperator;
	}
	/**
	 * 设置：操作时间
	 */
	public void setReceivableOperateTime(Date receivableOperateTime) {
		this.receivableOperateTime = receivableOperateTime;
	}
	/**
	 * 获取：操作时间
	 */
	public Date getReceivableOperateTime() {
		return receivableOperateTime;
	}
}