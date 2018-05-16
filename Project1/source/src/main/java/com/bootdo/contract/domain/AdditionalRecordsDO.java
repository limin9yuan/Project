package com.bootdo.contract.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 合同增补记录
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2018-01-04 13:10:31
 */
public class AdditionalRecordsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//合同增补记录编号
	private String recordId;
	//合同编号
	private String contractId;
	//申请人姓名id
	private String recordName;
	//建设单位
	private String recordBulidCompany;
	//增补总金额
	private BigDecimal recordTotalPrice;
	//增补内容描述
	private String recordDescription;
	//增补原因
	private String recordReason;
	//销售负责人
	private String recordSales;
	//正文附件
	private String recordAttachment;
	//关联合同编号
	private String recordRelatedContractId;
	//硬件设备明细表
	private String recordHardwareEquipmentList;
	//软件功能列表
	private String recordSoftwareFunctionList;
	//备注
	private String recordRemarks;
	//审批状态
	private String recordApprovalStatus;
	//创建人
	private Long recordOperator; 
	//创建人姓名
	private String recordOperatorName; 
	//提交评审时间
	private String recordCommitTime;
	//项目编号
	private String projectId;
	//合同名称
	private String contractName;
	//预计开发票时间
	private String preInvoiceDate;
	
	//客户名称
	private String customerName;
	//业务名称
	private String businessName;
	//项目名称
	private String projectName;
	//客户编号
	private String customerId;
	//业务编号
	private String businessId;
	//销售负责人名称
	private String recordSalesName;
	//合同类型
    private String contractType;
	//合同种类
	private String contractCategory;
	//申请人姓名名称
	private String contractRecordName;
	//主送人id
	private String mainPersonId;
	//抄送人id
	private String copyPersonId;
	
	//**********************流程相关属性****************************
	//流程任务ID
	private String taskId;
	//审批操作
	private String taskAction;
	//审批说明
	private String taskComment;
	//流程实例ID
	private String processInstanceId;

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

	public String getTaskComment() {
		return taskComment;
	}

	public void setTaskComment(String taskComment) {
		this.taskComment = taskComment;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

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


	public String getRecordOperatorName() {
		return recordOperatorName;
	}
	public void setRecordOperatorName(String recordOperatorName) {
		this.recordOperatorName = recordOperatorName;
	}
	/**
	 * 设置：合同增补记录编号
	 */
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	/**
	 * 获取：合同增补记录编号
	 */
	public String getRecordId() {
		return recordId;
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
	 * 设置：申请人姓名
	 */
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
	/**
	 * 获取：申请人姓名
	 */
	public String getRecordName() {
		return recordName;
	}
	/**
	 * 设置：建设单位
	 */
	public void setRecordBulidCompany(String recordBulidCompany) {
		this.recordBulidCompany = recordBulidCompany;
	}
	/**
	 * 获取：建设单位
	 */
	public String getRecordBulidCompany() {
		return recordBulidCompany;
	}
	/**
	 * 设置：增补总金额
	 */
	public void setRecordTotalPrice(BigDecimal recordTotalPrice) {
		this.recordTotalPrice = recordTotalPrice;
	}
	/**
	 * 获取：增补总金额
	 */
	public BigDecimal getRecordTotalPrice() {
		return recordTotalPrice;
	}
	/**
	 * 设置：增补内容描述
	 */
	public void setRecordDescription(String recordDescription) {
		this.recordDescription = recordDescription;
	}
	/**
	 * 获取：增补内容描述
	 */
	public String getRecordDescription() {
		return recordDescription;
	}
	/**
	 * 设置：增补原因
	 */
	public void setRecordReason(String recordReason) {
		this.recordReason = recordReason;
	}
	/**
	 * 获取：增补原因
	 */
	public String getRecordReason() {
		return recordReason;
	}
	/**
	 * 设置：销售负责人
	 */
	public void setRecordSales(String recordSales) {
		this.recordSales = recordSales;
	}
	/**
	 * 获取：销售负责人
	 */
	public String getRecordSales() {
		return recordSales;
	}
	/**
	 * 设置：正文附件
	 */
	public void setRecordAttachment(String recordAttachment) {
		this.recordAttachment = recordAttachment;
	}
	/**
	 * 获取：正文附件
	 */
	public String getRecordAttachment() {
		return recordAttachment;
	}
	/**
	 * 设置：关联合同编号
	 */
	public void setRecordRelatedContractId(String recordRelatedContractId) {
		this.recordRelatedContractId = recordRelatedContractId;
	}
	/**
	 * 获取：关联合同编号
	 */
	public String getRecordRelatedContractId() {
		return recordRelatedContractId;
	}
	/**
	 * 设置：硬件设备明细表
	 */
	public void setRecordHardwareEquipmentList(String recordHardwareEquipmentList) {
		this.recordHardwareEquipmentList = recordHardwareEquipmentList;
	}
	/**
	 * 获取：硬件设备明细表
	 */
	public String getRecordHardwareEquipmentList() {
		return recordHardwareEquipmentList;
	}
	/**
	 * 设置：软件功能列表
	 */
	public void setRecordSoftwareFunctionList(String recordSoftwareFunctionList) {
		this.recordSoftwareFunctionList = recordSoftwareFunctionList;
	}
	/**
	 * 获取：软件功能列表
	 */
	public String getRecordSoftwareFunctionList() {
		return recordSoftwareFunctionList;
	}
	/**
	 * 设置：备注
	 */
	public void setRecordRemarks(String recordRemarks) {
		this.recordRemarks = recordRemarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRecordRemarks() {
		return recordRemarks;
	}
	/**
	 * 设置：审批状态
	 */
	public void setRecordApprovalStatus(String recordApprovalStatus) {
		this.recordApprovalStatus = recordApprovalStatus;
	}
	/**
	 * 获取：审批状态
	 */
	public String getRecordApprovalStatus() {
		return recordApprovalStatus;
	}
	/**
	 * 设置：创建人
	 */
	public void setRecordOperator(Long recordOperator) {
		this.recordOperator = recordOperator;
	}
	/**
	 * 获取：创建人
	 */
	public Long getRecordOperator() {
		return recordOperator;
	}
	/**
	 * 设置：提交评审时间
	 */
	public void setRecordCommitTime(String recordCommitTime) {
		this.recordCommitTime = recordCommitTime;
	}
	/**
	 * 获取：提交评审时间
	 */
	public String getRecordCommitTime() {
		return recordCommitTime;
	}
	/**
	 * 设置：
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：
	 */
	public String getProjectId() {
		return projectId;
	}
	/**
	 * 设置：
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	/**
	 * 获取：
	 */
	public String getContractName() {
		return contractName;
	}
	/**
	 * 设置：
	 */
	public void setPreInvoiceDate(String preInvoiceDate) {
		this.preInvoiceDate = preInvoiceDate;
	}
	/**
	 * 获取：
	 */
	public String getPreInvoiceDate() {
		return preInvoiceDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getRecordSalesName() {
		return recordSalesName;
	}
	public void setRecordSalesName(String recordSalesName) {
		this.recordSalesName = recordSalesName;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public String getContractCategory() {
		return contractCategory;
	}
	public void setContractCategory(String contractCategory) {
		this.contractCategory = contractCategory;
	}
	public String getContractRecordName() {
		return contractRecordName;
	}
	public void setContractRecordName(String contractRecordName) {
		this.contractRecordName = contractRecordName;
	}
}
