package com.bootdo.contract.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 合同信息表
 * 
 * @author sw
 * @email 1992lcg@163.com
 * @date 2017-11-29 14:50:54
 */
public class ContractDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//合同编号
	private String contractId;
	//企业客户编号
	private String customerId;
	//业务编号
	private String businessId;
	//合同名称
	private String contractName;
	//申请人姓名
	private String contractApplicantName;
	//建设单位
	private String contractBuildCompany;
	//合同类型
	private String contractType;
	//合同种类
	private String contractCategory;
	//合同总金额
	private BigDecimal contractTotalPrice;
	//合同发起部门
	private String contractDept;
	//业务发起人
	private String contractApplicant;
	//合同拟定人
	private String contractDraftPerson;
	//销售负责人
	private String contractSales;
	//提交评审时间
	private String contractCommitTime;
	//关联合同编号
	private String contractRelatedId;
	//发票类型
	private String contractInvoiceType;
	//预计开具发票时间
	private String contractInvoiceTime;
	//硬件设备明细表
	private String contractHardwareList;
	//软件功能列表
	private String contractSoftwareList;
	//项目经理
	private String contractProjectManagement;
	//合同信息备注
	private String contractRemarks;
	//附件
	private String contractAttachment;
	//审批状态
	private String contractApprovalStatus;
	//操作人
	private String contractOperator;
	//操作时间
	private Date contractOperateTime;
	// 項目编号
	private String projectId;
	//操作人姓名
	private String contractOperatorName;
	
	//收款合计金额
	private BigDecimal contractReceivablePrice;
	//**********************流程相关属性****************************
	//流程任务ID
	private String taskId;
	//审批操作
	private String taskAction;
	//客户姓名
	private String customerName;
	//业务名称
	private String businessName;
	//项目名称
	private String projectName;
	//审批说明
	private String taskComment;
	//流程实例ID
	private String processInstanceId;

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

	public String getCustomerName() {
		return customerName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTaskId() {
		return taskId;
	}

	public String getTaskAction() {
		return taskAction;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public void setTaskAction(String taskAction) {
		this.taskAction = taskAction;
	}

	public String getContractOperatorName() {
		return contractOperatorName;
	}
	public void setContractOperatorName(String contractOperatorName) {
		this.contractOperatorName = contractOperatorName;
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
	 * 设置：合同名称
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	/**
	 * 获取：合同名称
	 */
	public String getContractName() {
		return contractName;
	}
	/**
	 * 设置：申请人姓名
	 */
	public void setContractApplicantName(String contractApplicantName) {
		this.contractApplicantName = contractApplicantName;
	}
	/**
	 * 获取：申请人姓名
	 */
	public String getContractApplicantName() {
		return contractApplicantName;
	}
	/**
	 * 设置：建设单位
	 */
	public void setContractBuildCompany(String contractBuildCompany) {
		this.contractBuildCompany = contractBuildCompany;
	}
	/**
	 * 获取：建设单位
	 */
	public String getContractBuildCompany() {
		return contractBuildCompany;
	}
	/**
	 * 设置：合同类型
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	/**
	 * 获取：合同类型
	 */
	public String getContractType() {
		return contractType;
	}
	/**
	 * 设置：合同种类
	 */
	public void setContractCategory(String contractCategory) {
		this.contractCategory = contractCategory;
	}
	/**
	 * 获取：合同种类
	 */
	public String getContractCategory() {
		return contractCategory;
	}
	/**
	 * 设置：合同总金额
	 */
	public void setContractTotalPrice(BigDecimal contractTotalPrice) {
		this.contractTotalPrice = contractTotalPrice;
	}
	/**
	 * 获取：合同总金额
	 */
	public BigDecimal getContractTotalPrice() {
		return contractTotalPrice;
	}
	/**
	 * 设置：合同发起部门
	 */
	public void setContractDept(String contractDept) {
		this.contractDept = contractDept;
	}
	/**
	 * 获取：合同发起部门
	 */
	public String getContractDept() {
		return contractDept;
	}
	/**
	 * 设置：业务发起人
	 */
	public void setContractApplicant(String contractApplicant) {
		this.contractApplicant = contractApplicant;
	}
	/**
	 * 获取：业务发起人
	 */
	public String getContractApplicant() {
		return contractApplicant;
	}
	/**
	 * 设置：合同拟定人
	 */
	public void setContractDraftPerson(String contractDraftPerson) {
		this.contractDraftPerson = contractDraftPerson;
	}
	/**
	 * 获取：合同拟定人
	 */
	public String getContractDraftPerson() {
		return contractDraftPerson;
	}
	/**
	 * 设置：销售负责人
	 */
	public void setContractSales(String contractSales) {
		this.contractSales = contractSales;
	}
	/**
	 * 获取：销售负责人
	 */
	public String getContractSales() {
		return contractSales;
	}
	/**
	 * 设置：提交评审时间
	 */
	public void setContractCommitTime(String contractCommitTime) {
		this.contractCommitTime = contractCommitTime;
	}
	/**
	 * 获取：提交评审时间
	 */
	public String getContractCommitTime() {
		return contractCommitTime;
	}
	/**
	 * 设置：关联合同编号
	 */
	public void setContractRelatedId(String contractRelatedId) {
		this.contractRelatedId = contractRelatedId;
	}
	/**
	 * 获取：关联合同编号
	 */
	public String getContractRelatedId() {
		return contractRelatedId;
	}
	/**
	 * 设置：发票类型
	 */
	public void setContractInvoiceType(String contractInvoiceType) {
		this.contractInvoiceType = contractInvoiceType;
	}
	/**
	 * 获取：发票类型
	 */
	public String getContractInvoiceType() {
		return contractInvoiceType;
	}
	/**
	 * 设置：预计开具发票时间
	 */
	public void setContractInvoiceTime(String contractInvoiceTime) {
		this.contractInvoiceTime = contractInvoiceTime;
	}
	/**
	 * 获取：预计开具发票时间
	 */
	public String getContractInvoiceTime() {
		return contractInvoiceTime;
	}
	/**
	 * 设置：硬件设备明细表
	 */
	public void setContractHardwareList(String contractHardwareList) {
		this.contractHardwareList = contractHardwareList;
	}
	/**
	 * 获取：硬件设备明细表
	 */
	public String getContractHardwareList() {
		return contractHardwareList;
	}
	/**
	 * 设置：软件功能列表
	 */
	public void setContractSoftwareList(String contractSoftwareList) {
		this.contractSoftwareList = contractSoftwareList;
	}
	/**
	 * 获取：软件功能列表
	 */
	public String getContractSoftwareList() {
		return contractSoftwareList;
	}
	/**
	 * 设置：项目经理
	 */
	public void setContractProjectManagement(String contractProjectManagement) {
		this.contractProjectManagement = contractProjectManagement;
	}
	/**
	 * 获取：项目经理
	 */
	public String getContractProjectManagement() {
		return contractProjectManagement;
	}
	/**
	 * 设置：合同信息备注
	 */
	public void setContractRemarks(String contractRemarks) {
		this.contractRemarks = contractRemarks;
	}
	/**
	 * 获取：合同信息备注
	 */
	public String getContractRemarks() {
		return contractRemarks;
	}
	/**
	 * 设置：附件
	 */
	public void setContractAttachment(String contractAttachment) {
		this.contractAttachment = contractAttachment;
	}
	/**
	 * 获取：附件
	 */
	public String getContractAttachment() {
		return contractAttachment;
	}
	/**
	 * 设置：审批状态
	 */
	public void setContractApprovalStatus(String contractApprovalStatus) {
		this.contractApprovalStatus = contractApprovalStatus;
	}
	/**
	 * 获取：审批状态
	 */
	public String getContractApprovalStatus() {
		return contractApprovalStatus;
	}
	/**
	 * 设置：操作人
	 */
	public void setContractOperator(String contractOperator) {
		this.contractOperator = contractOperator;
	}
	/**
	 * 获取：操作人
	 */
	public String getContractOperator() {
		return contractOperator;
	}
	/**
	 * 设置：操作时间
	 */
	public void setContractOperateTime(Date contractOperateTime) {
		this.contractOperateTime = contractOperateTime;
	}
	/**
	 * 获取：操作时间
	 */
	public Date getContractOperateTime() {
		return contractOperateTime;
	}
	/**
	 * 项目编号
	 */
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public BigDecimal getContractReceivablePrice() {
		return contractReceivablePrice;
	}

	public void setContractReceivablePrice(BigDecimal contractReceivablePrice) {
		this.contractReceivablePrice = contractReceivablePrice;
	}
	
}
