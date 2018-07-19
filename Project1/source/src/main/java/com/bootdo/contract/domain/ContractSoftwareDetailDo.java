package com.bootdo.contract.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 软件明细表
 * @author Administrator
 *
 */
public class ContractSoftwareDetailDo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//软件明细ID
	private int softwaresetailId;
	//合同编号
	private String contractId;
	//系统
	private String softwaresetailSystem;
	//模块
	private String softwaresetailModel;
	//创建时间
	private Date softwaresetailCreationTime;
	//操作人
	private Long softwareDetailOperator;
	
	
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public Long getSoftwareDetailOperator() {
		return softwareDetailOperator;
	}
	public void setSoftwareDetailOperator(Long softwareDetailOperator) {
		this.softwareDetailOperator = softwareDetailOperator;
	}
	/**
	 * 软件明细ID
	 * @return
	 */
	public int getSoftwaresetailId() {
		return softwaresetailId;
	}
	/**
	 * 软件明细ID
	 * @return
	 */
	public void setSoftwaresetailId(int softwaresetailId) {
		this.softwaresetailId = softwaresetailId;
	}
	/**
	 * 系统
	 * @return
	 */
	public String getSoftwaresetailSystem() {
		return softwaresetailSystem;
	}
	/**
	 * 系统
	 * @return
	 */
	public void setSoftwaresetailSystem(String softwaresetailSystem) {
		this.softwaresetailSystem = softwaresetailSystem;
	}
	/**
	 * 模块
	 * @return
	 */
	public String getSoftwaresetailModel() {
		return softwaresetailModel;
	}
	/**
	 * 模块
	 * @return
	 */
	public void setSoftwaresetailModel(String softwaresetailModel) {
		this.softwaresetailModel = softwaresetailModel;
	}
	/**
	 * 创建时间
	 * @return
	 */
	public Date getSoftwaresetailCreationTime() {
		return softwaresetailCreationTime;
	}
	/**
	 * 创建时间
	 * @return
	 */
	public void setSoftwaresetailCreationTime(Date softwaresetailCreationTime) {
		this.softwaresetailCreationTime = softwaresetailCreationTime;
	}
	
	
}
