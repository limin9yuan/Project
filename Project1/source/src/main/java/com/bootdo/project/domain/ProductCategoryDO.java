package com.bootdo.project.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 产品分类信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-29 11:15:42
 */
public class ProductCategoryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//产品编号
	private String productId;
	//产品名称
	private String productName;
	//产品描述
	private String productDescription;
	//附件
	private String productAttachment;
	//备注
	private String productRemars;
	//操作人
	private Long productRecorder;
	//修改时间
	private Date productRecordTime;
	//创建人
	private Long productCreator;
	//创建时间
	private Date productCreateTime;

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
	/**
	 * 设置：产品描述
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	/**
	 * 获取：产品描述
	 */
	public String getProductDescription() {
		return productDescription;
	}
	/**
	 * 设置：附件
	 */
	public void setProductAttachment(String productAttachment) {
		this.productAttachment = productAttachment;
	}
	/**
	 * 获取：附件
	 */
	public String getProductAttachment() {
		return productAttachment;
	}
	/**
	 * 设置：备注
	 */
	public void setProductRemars(String productRemars) {
		this.productRemars = productRemars;
	}
	/**
	 * 获取：备注
	 */
	public String getProductRemars() {
		return productRemars;
	}
	/**
	 * 设置：操作人
	 */
	public void setProductRecorder(Long productRecorder) {
		this.productRecorder = productRecorder;
	}
	/**
	 * 获取：操作人
	 */
	public Long getProductRecorder() {
		return productRecorder;
	}
	/**
	 * 设置：修改时间
	 */
	public void setProductRecordTime(Date productRecordTime) {
		this.productRecordTime = productRecordTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getProductRecordTime() {
		return productRecordTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setProductCreator(Long productCreator) {
		this.productCreator = productCreator;
	}
	/**
	 * 获取：创建人
	 */
	public Long getProductCreator() {
		return productCreator;
	}
	/**
	 * 设置：创建时间
	 */
	public void setProductCreateTime(Date productCreateTime) {
		this.productCreateTime = productCreateTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getProductCreateTime() {
		return productCreateTime;
	}
}
