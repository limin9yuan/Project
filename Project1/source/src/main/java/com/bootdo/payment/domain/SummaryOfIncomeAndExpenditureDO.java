package com.bootdo.payment.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 收支详情总汇
 * 
 * @小平
 * @email 1992lcg@163.com
 * @date 2018-2-7
 */

public class SummaryOfIncomeAndExpenditureDO implements Serializable {
	private static final long serialVersionUID = 1L;

	// 项目编号
	private Long projectId;
	// 工时成本
	private String laborTotalCost;
	// 费用及采购成本
	private String budgetPurchaseCost;
	// 合同完工收入
	private String income;
	// 利润
	private String budgetProfit;
	// 服务
	private String service;
	// 报销
	private String reimbursement;
	// 采购
	private String purchase;
	// 总计
	private String budgetTotalCost;
	// 计划利润率
	private BigDecimal budgetProfitRate;
	// 实际利润率
	private String actual;
	
	
	
	
	

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getLaborTotalCost() {
		return laborTotalCost;
	}

	public void setLaborTotalCost(String laborTotalCost) {
		this.laborTotalCost = laborTotalCost;
	}

	public String getBudgetPurchaseCost() {
		return budgetPurchaseCost;
	}

	public void setBudgetPurchaseCost(String budgetPurchaseCost) {
		this.budgetPurchaseCost = budgetPurchaseCost;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getBudgetProfit() {
		return budgetProfit;
	}

	public void setBudgetProfit(String budgetProfit) {
		this.budgetProfit = budgetProfit;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getReimbursement() {
		return reimbursement;
	}

	public void setReimbursement(String reimbursement) {
		this.reimbursement = reimbursement;
	}

	public String getBudgetTotalCost() {
		return budgetTotalCost;
	}

	public void setBudgetTotalCost(String budgetTotalCost) {
		this.budgetTotalCost = budgetTotalCost;
	}

	public BigDecimal getBudgetProfitRate() {
		return budgetProfitRate;
	}

	public void setBudgetProfitRate(BigDecimal budgetProfitRate) {
		this.budgetProfitRate = budgetProfitRate;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

}