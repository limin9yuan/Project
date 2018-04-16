package com.bootdo.contract.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 交付计划表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-12-18 10:17:48
 */
public class PlanDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//交付计划ID
	private Long planId;
	//合同编号
	private Long contractId;
	//计划交付时间
	private Date planDeliveryDate;
	//计划交付内容
	private String planDeliveryContent;
	//附件
	private String planAttachment;
	//备注
	private String planRemarks;
	//操作人
	private String planOperator;
	//操作时间
	private Date planOperateTime;

	/**
	 * 设置：交付计划ID
	 */
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	/**
	 * 获取：交付计划ID
	 */
	public Long getPlanId() {
		return planId;
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
	 * 设置：计划交付时间
	 */
	public void setPlanDeliveryDate(Date planDeliveryDate) {
		this.planDeliveryDate = planDeliveryDate;
	}
	/**
	 * 获取：计划交付时间
	 */
	public Date getPlanDeliveryDate() {
		return planDeliveryDate;
	}
	/**
	 * 设置：计划交付内容
	 */
	public void setPlanDeliveryContent(String planDeliveryContent) {
		this.planDeliveryContent = planDeliveryContent;
	}
	/**
	 * 获取：计划交付内容
	 */
	public String getPlanDeliveryContent() {
		return planDeliveryContent;
	}
	/**
	 * 设置：附件
	 */
	public void setPlanAttachment(String planAttachment) {
		this.planAttachment = planAttachment;
	}
	/**
	 * 获取：附件
	 */
	public String getPlanAttachment() {
		return planAttachment;
	}
	/**
	 * 设置：备注
	 */
	public void setPlanRemarks(String planRemarks) {
		this.planRemarks = planRemarks;
	}
	/**
	 * 获取：备注
	 */
	public String getPlanRemarks() {
		return planRemarks;
	}
	/**
	 * 设置：操作人
	 */
	public void setPlanOperator(String planOperator) {
		this.planOperator = planOperator;
	}
	/**
	 * 获取：操作人
	 */
	public String getPlanOperator() {
		return planOperator;
	}
	/**
	 * 设置：操作时间
	 */
	public void setPlanOperateTime(Date planOperateTime) {
		this.planOperateTime = planOperateTime;
	}
	/**
	 * 获取：操作时间
	 */
	public Date getPlanOperateTime() {
		return planOperateTime;
	}
}
