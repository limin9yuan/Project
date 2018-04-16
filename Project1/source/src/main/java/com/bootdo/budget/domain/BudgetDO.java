package com.bootdo.budget.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 项目预算表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:44:13
 */
public class BudgetDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//项目预算编号
	private String budgetId;
	//企业客户编号
	private String customerId;
	//业务编号
	private String businessId;
	//项目编号
	private String projectId;
	//项目计划利润率
	private BigDecimal budgetProfitRate;
	//应收账款总额
	private BigDecimal budgetAccountReceivable;
	//计划成本总额
	private BigDecimal budgetTotalCost;
	//计划是否合规
	private String budgetConformance;
	//服务收入
	private BigDecimal budgetServiceRevenue;
	//税金
	private BigDecimal budgetTax;
	//服务净收入
	private BigDecimal budgetServiceRevenueNet;
	//采购成本
	private BigDecimal budgetPurchaseCost;
	//人工成本
	private BigDecimal budgetLaborCost;
	//差旅成本
	private BigDecimal budgetTravelCost;
	//费用和支持（含税）
	private BigDecimal budgetCost;
	//利润
	private BigDecimal budgetProfit;
	//操作人
	private Long budgetOperator;
	//操作时间
	private Date budgetOperateTime;
	
	
	//负责中心
	private String responsibleCenter;
	//部门
	private String deptId;
	//项目主管
	private String projectSupervisor;
	
	//项目类别
	private String projectGategory;
	
	//部门名称
	private String deptName;
	//客户名称
	private String customerName;
	
	
	//项目经理
	private String projectOwner;
	//合同编号
	private String contractId;
	
	//操作人名称
	private String budgetOperatorName;
	//项目主管名称
	private String projectSupervisorName;
	
	//项目经理名称
	private String projectOwnerName;
		
	//项目名称
	private String ProjectName;
	//项目描述
	private String projectDescription;

		

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
	 * 设置：项目计划利润率
	 */
	public void setBudgetProfitRate(BigDecimal budgetProfitRate) {
		this.budgetProfitRate = budgetProfitRate;
	}
	/**
	 * 获取：项目计划利润率
	 */
	public BigDecimal getBudgetProfitRate() {
		return budgetProfitRate;
	}
	/**
	 * 设置：应收账款总额
	 */
	public void setBudgetAccountReceivable(BigDecimal budgetAccountReceivable) {
		this.budgetAccountReceivable = budgetAccountReceivable;
	}
	/**
	 * 获取：应收账款总额
	 */
	public BigDecimal getBudgetAccountReceivable() {
		return budgetAccountReceivable;
	}
	/**
	 * 设置：计划成本总额
	 */
	public void setBudgetTotalCost(BigDecimal budgetTotalCost) {
		this.budgetTotalCost = budgetTotalCost;
	}
	/**
	 * 获取：计划成本总额
	 */
	public BigDecimal getBudgetTotalCost() {
		return budgetTotalCost;
	}
	/**
	 * 设置：计划是否合规
	 */
	public void setBudgetConformance(String budgetConformance) {
		this.budgetConformance = budgetConformance;
	}
	/**
	 * 获取：计划是否合规
	 */
	public String getBudgetConformance() {
		return budgetConformance;
	}
	/**
	 * 设置：服务收入
	 */
	public void setBudgetServiceRevenue(BigDecimal budgetServiceRevenue) {
		this.budgetServiceRevenue = budgetServiceRevenue;
	}
	/**
	 * 获取：服务收入
	 */
	public BigDecimal getBudgetServiceRevenue() {
		return budgetServiceRevenue;
	}
	/**
	 * 设置：税金
	 */
	public void setBudgetTax(BigDecimal budgetTax) {
		this.budgetTax = budgetTax;
	}
	/**
	 * 获取：税金
	 */
	public BigDecimal getBudgetTax() {
		return budgetTax;
	}
	/**
	 * 设置：服务净收入
	 */
	public void setBudgetServiceRevenueNet(BigDecimal budgetServiceRevenueNet) {
		this.budgetServiceRevenueNet = budgetServiceRevenueNet;
	}
	/**
	 * 获取：服务净收入
	 */
	public BigDecimal getBudgetServiceRevenueNet() {
		return budgetServiceRevenueNet;
	}
	/**
	 * 设置：采购成本
	 */
	public void setBudgetPurchaseCost(BigDecimal budgetPurchaseCost) {
		this.budgetPurchaseCost = budgetPurchaseCost;
	}
	/**
	 * 获取：采购成本
	 */
	public BigDecimal getBudgetPurchaseCost() {
		return budgetPurchaseCost;
	}
	/**
	 * 设置：人工成本
	 */
	public void setBudgetLaborCost(BigDecimal budgetLaborCost) {
		this.budgetLaborCost = budgetLaborCost;
	}
	/**
	 * 获取：人工成本
	 */
	public BigDecimal getBudgetLaborCost() {
		return budgetLaborCost;
	}
	/**
	 * 设置：差旅成本
	 */
	public void setBudgetTravelCost(BigDecimal budgetTravelCost) {
		this.budgetTravelCost = budgetTravelCost;
	}
	/**
	 * 获取：差旅成本
	 */
	public BigDecimal getBudgetTravelCost() {
		return budgetTravelCost;
	}
	/**
	 * 设置：费用和支持（含税）
	 */
	public void setBudgetCost(BigDecimal budgetCost) {
		this.budgetCost = budgetCost;
	}
	/**
	 * 获取：费用和支持（含税）
	 */
	public BigDecimal getBudgetCost() {
		return budgetCost;
	}
	/**
	 * 设置：利润
	 */
	public void setBudgetProfit(BigDecimal budgetProfit) {
		this.budgetProfit = budgetProfit;
	}
	/**
	 * 获取：利润
	 */
	public BigDecimal getBudgetProfit() {
		return budgetProfit;
	}
	/**
	 * 设置：操作人
	 */
	public void setBudgetOperator(Long budgetOperator) {
		this.budgetOperator = budgetOperator;
	}
	/**
	 * 获取：操作人
	 */
	public Long getBudgetOperator() {
		return budgetOperator;
	}
	/**
	 * 设置：操作时间
	 */
	public void setBudgetOperateTime(Date budgetOperateTime) {
		this.budgetOperateTime = budgetOperateTime;
	}
	/**
	 * 获取：操作时间
	 */
	public Date getBudgetOperateTime() {
		return budgetOperateTime;
	}
	public String getResponsibleCenter() {
		return responsibleCenter;
	}
	public void setResponsibleCenter(String responsibleCenter) {
		this.responsibleCenter = responsibleCenter;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getProjectSupervisor() {
		return projectSupervisor;
	}
	public void setProjectSupervisor(String projectSupervisor) {
		this.projectSupervisor = projectSupervisor;
	}
	public String getProjectOwnerName() {
		return projectOwnerName;
	}
	public void setProjectOwnerName(String projectOwnerName) {
		this.projectOwnerName = projectOwnerName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProjectGategory() {
		return projectGategory;
	}
	public void setProjectGategory(String projectGategory) {
		this.projectGategory = projectGategory;
	}
	public String getProjectOwner() {
		return projectOwner;
	}
	public void setProjectOwner(String projectOwner) {
		this.projectOwner = projectOwner;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getBudgetOperatorName() {
		return budgetOperatorName;
	}
	public void setBudgetOperatorName(String budgetOperatorName) {
		this.budgetOperatorName = budgetOperatorName;
	}
	public String getProjectSupervisorName() {
		return projectSupervisorName;
	}
	public void setProjectSupervisorName(String projectSupervisorName) {
		this.projectSupervisorName = projectSupervisorName;
	}
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
}