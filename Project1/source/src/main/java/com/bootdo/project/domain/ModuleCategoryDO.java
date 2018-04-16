package com.bootdo.project.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 模块分类信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-29 11:15:05
 */
public class ModuleCategoryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//模块编号
	private String moduleId;
	//产品编号
	private String productId;
	//模块名称
	private String moduleName;
	//模块描述
	private String moduleDescription;
	//附件
	private String moduleAttachment;
	//备注
	private String moduleRemark;
	//操作人
	private Long moduleRecorder;
	//修改时间
	private Date moduleRecordTime;
	//创建人
	private Long moduleCreator;
	//创建时间
	private Date moduleCreateTime;
	//产品名称
	private String productName;

	/**
	 * 设置：模块编号
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	/**
	 * 获取：模块编号
	 */
	public String getModuleId() {
		return moduleId;
	}
	/**
	 * 设置：产品编号
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * 获取：产品编号
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * 设置：模块名称
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	/**
	 * 获取：模块名称
	 */
	public String getModuleName() {
		return moduleName;
	}
	/**
	 * 设置：模块描述
	 */
	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}
	/**
	 * 获取：模块描述
	 */
	public String getModuleDescription() {
		return moduleDescription;
	}
	/**
	 * 设置：附件
	 */
	public void setModuleAttachment(String moduleAttachment) {
		this.moduleAttachment = moduleAttachment;
	}
	/**
	 * 获取：附件
	 */
	public String getModuleAttachment() {
		return moduleAttachment;
	}
	/**
	 * 设置：备注
	 */
	public void setModuleRemark(String moduleRemark) {
		this.moduleRemark = moduleRemark;
	}
	/**
	 * 获取：备注
	 */
	public String getModuleRemark() {
		return moduleRemark;
	}
	/**
	 * 设置：操作人
	 */
	public void setModuleRecorder(Long moduleRecorder) {
		this.moduleRecorder = moduleRecorder;
	}
	/**
	 * 获取：操作人
	 */
	public Long getModuleRecorder() {
		return moduleRecorder;
	}
	/**
	 * 设置：修改时间
	 */
	public void setModuleRecordTime(Date moduleRecordTime) {
		this.moduleRecordTime = moduleRecordTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getModuleRecordTime() {
		return moduleRecordTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setModuleCreator(Long moduleCreator) {
		this.moduleCreator = moduleCreator;
	}
	/**
	 * 获取：创建人
	 */
	public Long getModuleCreator() {
		return moduleCreator;
	}
	/**
	 * 设置：创建时间
	 */
	public void setModuleCreateTime(Date moduleCreateTime) {
		this.moduleCreateTime = moduleCreateTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getModuleCreateTime() {
		return moduleCreateTime;
	}
	/**
	 * 设置：产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：产品名称
	 */
	public String getProductName() {
		return productName;
	}
	
}

