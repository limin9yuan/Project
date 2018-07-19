package com.bootdo.contract.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 硬件明细表
 * @author Administrator
 *
 */
public class ContractHardwareDetailDO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//硬件明细ID
	private Integer hardwareDetailId;
	//合同编号
	private String contractId;
	//名称
	private String hardwareDetailName;
	//品牌
	private String hardwareDetailBrand;
	//型号&规格
	private String hardwareDetailSpecification;
	//单位
	private String hardwareDetailUnit;
	//数量
	private String hardwareDetailNumber;
	//单价
	private BigDecimal hardwareDetailUnitPrice;
	//总价
	private BigDecimal hardwareDetailTotalPrice;
	//创建时间
	private Date hardwareDetailCreationTime;
	//操作人
	private Long hardwareDetailOperator;
	
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public Long getHardwareDetailOperator() {
		return hardwareDetailOperator;
	}
	public void setHardwareDetailOperator(Long hardwareDetailOperator) {
		this.hardwareDetailOperator = hardwareDetailOperator;
	}
	public Integer getHardwareDetailId() {
		return hardwareDetailId;
	}
	public void setHardwareDetailId(Integer hardwareDetailId) {
		this.hardwareDetailId = hardwareDetailId;
	}
	public String getHardwareDetailName() {
		return hardwareDetailName;
	}
	public void setHardwareDetailName(String hardwareDetailName) {
		this.hardwareDetailName = hardwareDetailName;
	}
	public String getHardwareDetailBrand() {
		return hardwareDetailBrand;
	}
	public void setHardwareDetailBrand(String hardwareDetailBrand) {
		this.hardwareDetailBrand = hardwareDetailBrand;
	}
	public String getHardwareDetailSpecification() {
		return hardwareDetailSpecification;
	}
	public void setHardwareDetailSpecification(String hardwareDetailSpecification) {
		this.hardwareDetailSpecification = hardwareDetailSpecification;
	}
	public String getHardwareDetailUnit() {
		return hardwareDetailUnit;
	}
	public void setHardwareDetailUnit(String hardwareDetailUnit) {
		this.hardwareDetailUnit = hardwareDetailUnit;
	}
	public String getHardwareDetailNumber() {
		return hardwareDetailNumber;
	}
	public void setHardwareDetailNumber(String hardwareDetailNumber) {
		this.hardwareDetailNumber = hardwareDetailNumber;
	}
	public BigDecimal getHardwareDetailUnitPrice() {
		return hardwareDetailUnitPrice;
	}
	public void setHardwareDetailUnitPrice(BigDecimal hardwareDetailUnitPrice) {
		this.hardwareDetailUnitPrice = hardwareDetailUnitPrice;
	}
	public BigDecimal getHardwareDetailTotalPrice() {
		return hardwareDetailTotalPrice;
	}
	public void setHardwareDetailTotalPrice(BigDecimal hardwareDetailTotalPrice) {
		this.hardwareDetailTotalPrice = hardwareDetailTotalPrice;
	}
	public Date getHardwareDetailCreationTime() {
		return hardwareDetailCreationTime;
	}
	public void setHardwareDetailCreationTime(Date hardwareDetailCreationTime) {
		this.hardwareDetailCreationTime = hardwareDetailCreationTime;
	}
	
	
}
