package com.bootdo.contract.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 出差申请表
 * 
 * @author sw
 * @email 1992lcg@163.com
 * @date 2017-11-30 17:44:01
 */
public class TravelDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//出差申请编号
	private String travelId;
	//企业客户编号
	private String customerId;
	//项目编号
	private String projectId;
	//业务编号
	private String businessId;
	//出差人姓名
	private String travelName;
	//出发地
	private String travelPlaceIssue;
	//目的地
	private String travelPlaceEnded;
	//同行人
	private String travelPartner;
	//拟出差时间
	private String travelDepartureDate;
	//拟返程时间
	private String travelReturnDate;
	//预计费用类别
	private String travelPlanCostType;
	//预计交通方式
	private String travelVisitMode;
	//计划任务信息
	private String travelPlanTask;
	//实际完成结果
	private String travelActualPerformance;
	//未完成原因
	private String travelUncompletedCause;
	//出差任务确认
	private String travelTaskConfirm;
	//审批状态
	private String travelApprovalStatus;
	//操作人
	private String travelOperator;
	//修改时间
	private Date travelOperateTime;
	//创建人
	private Long travelCreator;
	//创建时间
	private Date travelCreateTime;
	//**********************流程相关属性****************************
	//流程任务ID
	private String taskId;
	//审批操作
	private String taskAction;
	
	/**
	 * 设置：审批操作
	 */
	public void setTaskAction(String taskAction) {
		this.taskAction = taskAction;
	}
	/**
	 * 获取：审批操作
	 */
	public String getTaskAction() {
		return taskAction;
	}
	
	/**
	 * 设置：流程任务ID
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * 获取：流程任务ID
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * 设置：出差申请编号
	 */
	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}
	/**
	 * 获取：出差申请编号
	 */
	public String getTravelId() {
		return travelId;
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
	 * 设置：出差人姓名
	 */
	public void setTravelName(String travelName) {
		this.travelName = travelName;
	}
	/**
	 * 获取：出差人姓名
	 */
	public String getTravelName() {
		return travelName;
	}
	/**
	 * 设置：出发地
	 */
	public void setTravelPlaceIssue(String travelPlaceIssue) {
		this.travelPlaceIssue = travelPlaceIssue;
	}
	/**
	 * 获取：出发地
	 */
	public String getTravelPlaceIssue() {
		return travelPlaceIssue;
	}
	/**
	 * 设置：目的地
	 */
	public void setTravelPlaceEnded(String travelPlaceEnded) {
		this.travelPlaceEnded = travelPlaceEnded;
	}
	/**
	 * 获取：目的地
	 */
	public String getTravelPlaceEnded() {
		return travelPlaceEnded;
	}
	/**
	 * 设置：同行人
	 */
	public void setTravelPartner(String travelPartner) {
		this.travelPartner = travelPartner;
	}
	/**
	 * 获取：同行人
	 */
	public String getTravelPartner() {
		return travelPartner;
	}
	/**
	 * 设置：拟出差时间
	 */
	public void setTravelDepartureDate(String travelDepartureDate) {
		this.travelDepartureDate = travelDepartureDate;
	}
	/**
	 * 获取：拟出差时间
	 */
	public String getTravelDepartureDate() {
		return travelDepartureDate;
	}
	/**
	 * 设置：拟返程时间
	 */
	public void setTravelReturnDate(String travelReturnDate) {
		this.travelReturnDate = travelReturnDate;
	}
	/**
	 * 获取：拟返程时间
	 */
	public String getTravelReturnDate() {
		return travelReturnDate;
	}
	/**
	 * 设置：预计费用类别
	 */
	public void setTravelPlanCostType(String travelPlanCostType) {
		this.travelPlanCostType = travelPlanCostType;
	}
	/**
	 * 获取：预计费用类别
	 */
	public String getTravelPlanCostType() {
		return travelPlanCostType;
	}
	/**
	 * 设置：预计交通方式
	 */
	public void setTravelVisitMode(String travelVisitMode) {
		this.travelVisitMode = travelVisitMode;
	}
	/**
	 * 获取：预计交通方式
	 */
	public String getTravelVisitMode() {
		return travelVisitMode;
	}
	/**
	 * 设置：计划任务信息
	 */
	public void setTravelPlanTask(String travelPlanTask) {
		this.travelPlanTask = travelPlanTask;
	}
	/**
	 * 获取：计划任务信息
	 */
	public String getTravelPlanTask() {
		return travelPlanTask;
	}
	/**
	 * 设置：实际完成结果
	 */
	public void setTravelActualPerformance(String travelActualPerformance) {
		this.travelActualPerformance = travelActualPerformance;
	}
	/**
	 * 获取：实际完成结果
	 */
	public String getTravelActualPerformance() {
		return travelActualPerformance;
	}
	/**
	 * 设置：未完成原因
	 */
	public void setTravelUncompletedCause(String travelUncompletedCause) {
		this.travelUncompletedCause = travelUncompletedCause;
	}
	/**
	 * 获取：未完成原因
	 */
	public String getTravelUncompletedCause() {
		return travelUncompletedCause;
	}
	/**
	 * 设置：出差任务确认
	 */
	public void setTravelTaskConfirm(String travelTaskConfirm) {
		this.travelTaskConfirm = travelTaskConfirm;
	}
	/**
	 * 获取：出差任务确认
	 */
	public String getTravelTaskConfirm() {
		return travelTaskConfirm;
	}
	/**
	 * 设置：审批状态
	 */
	public void setTravelApprovalStatus(String travelApprovalStatus) {
		this.travelApprovalStatus = travelApprovalStatus;
	}
	/**
	 * 获取：审批状态
	 */
	public String getTravelApprovalStatus() {
		return travelApprovalStatus;
	}
	/**
	 * 设置：操作人
	 */
	public void setTravelOperator(String travelOperator) {
		this.travelOperator = travelOperator;
	}
	/**
	 * 获取：操作人
	 */
	public String getTravelOperator() {
		return travelOperator;
	}
	/**
	 * 设置：修改时间
	 */
	public void setTravelOperateTime(Date travelOperateTime) {
		this.travelOperateTime = travelOperateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getTravelOperateTime() {
		return travelOperateTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setTravelCreator(Long travelCreator) {
		this.travelCreator = travelCreator;
	}
	/**
	 * 获取：创建人
	 */
	public Long getTravelCreator() {
		return travelCreator;
	}
	/**
	 * 设置：创建时间
	 */
	public void setTravelCreateTime(Date travelCreateTime) {
		this.travelCreateTime = travelCreateTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getTravelCreateTime() {
		return travelCreateTime;
	}
}
