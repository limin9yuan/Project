package com.bootdo.sales.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 客户项目咨询信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-27 11:53:50
 */
public class ProjectConsultationDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//项目咨询编号
	private String consultationId;
	//姓名
	private String consultationName;
	//手机
	private String consultationPhoneNumber;
	//邮箱
	private String consultationMailbox;
	//公司名称
	private String consultationCompanyName;
	//职位
	private String consultationJob;
	//省份
	private String consultationProvence;
	//城市
	private String consultationCity;
	//区县
	private String consultationCounty;
	//所在行业
	private String consultationIndustry;
	//感兴趣产品
	private String consultationProduct;
	//项目概述
	private String consultationProjectIntroduct;
	//项目规模
	private String consultationProjectScale;
	//附件
	private String consultationAttachment;
	//备注
	private String consultationRmarks;
	//咨询时间
	private Date consultationTime;
	//咨询处理状态
	private String consultationExcuteStatus;
	//咨询处理状态描述
	private String consultationExcuteDescriptio;
	//咨询处理时间
	private Date consultationExcuteTime;

	/**
	 * 设置：项目咨询编号
	 */
	public void setConsultationId(String consultationId) {
		this.consultationId = consultationId;
	}
	/**
	 * 获取：项目咨询编号
	 */
	public String getConsultationId() {
		return consultationId;
	}
	/**
	 * 设置：姓名
	 */
	public void setConsultationName(String consultationName) {
		this.consultationName = consultationName;
	}
	/**
	 * 获取：姓名
	 */
	public String getConsultationName() {
		return consultationName;
	}
	/**
	 * 设置：手机
	 */
	public void setConsultationPhoneNumber(String consultationPhoneNumber) {
		this.consultationPhoneNumber = consultationPhoneNumber;
	}
	/**
	 * 获取：手机
	 */
	public String getConsultationPhoneNumber() {
		return consultationPhoneNumber;
	}
	/**
	 * 设置：邮箱
	 */
	public void setConsultationMailbox(String consultationMailbox) {
		this.consultationMailbox = consultationMailbox;
	}
	/**
	 * 获取：邮箱
	 */
	public String getConsultationMailbox() {
		return consultationMailbox;
	}
	/**
	 * 设置：公司名称
	 */
	public void setConsultationCompanyName(String consultationCompanyName) {
		this.consultationCompanyName = consultationCompanyName;
	}
	/**
	 * 获取：公司名称
	 */
	public String getConsultationCompanyName() {
		return consultationCompanyName;
	}
	/**
	 * 设置：职位
	 */
	public void setConsultationJob(String consultationJob) {
		this.consultationJob = consultationJob;
	}
	/**
	 * 获取：职位
	 */
	public String getConsultationJob() {
		return consultationJob;
	}
	/**
	 * 设置：省份
	 */
	public void setConsultationProvence(String consultationProvence) {
		this.consultationProvence = consultationProvence;
	}
	/**
	 * 获取：省份
	 */
	public String getConsultationProvence() {
		return consultationProvence;
	}
	/**
	 * 设置：城市
	 */
	public void setConsultationCity(String consultationCity) {
		this.consultationCity = consultationCity;
	}
	/**
	 * 获取：城市
	 */
	public String getConsultationCity() {
		return consultationCity;
	}
	/**
	 * 设置：区县
	 */
	public void setConsultationCounty(String consultationCounty) {
		this.consultationCounty = consultationCounty;
	}
	/**
	 * 获取：区县
	 */
	public String getConsultationCounty() {
		return consultationCounty;
	}
	/**
	 * 设置：所在行业
	 */
	public void setConsultationIndustry(String consultationIndustry) {
		this.consultationIndustry = consultationIndustry;
	}
	/**
	 * 获取：所在行业
	 */
	public String getConsultationIndustry() {
		return consultationIndustry;
	}
	/**
	 * 设置：感兴趣产品
	 */
	public void setConsultationProduct(String consultationProduct) {
		this.consultationProduct = consultationProduct;
	}
	/**
	 * 获取：感兴趣产品
	 */
	public String getConsultationProduct() {
		return consultationProduct;
	}
	/**
	 * 设置：项目概述
	 */
	public void setConsultationProjectIntroduct(String consultationProjectIntroduct) {
		this.consultationProjectIntroduct = consultationProjectIntroduct;
	}
	/**
	 * 获取：项目概述
	 */
	public String getConsultationProjectIntroduct() {
		return consultationProjectIntroduct;
	}
	/**
	 * 设置：项目规模
	 */
	public void setConsultationProjectScale(String consultationProjectScale) {
		this.consultationProjectScale = consultationProjectScale;
	}
	/**
	 * 获取：项目规模
	 */
	public String getConsultationProjectScale() {
		return consultationProjectScale;
	}
	/**
	 * 设置：附件
	 */
	public void setConsultationAttachment(String consultationAttachment) {
		this.consultationAttachment = consultationAttachment;
	}
	/**
	 * 获取：附件
	 */
	public String getConsultationAttachment() {
		return consultationAttachment;
	}
	/**
	 * 设置：备注
	 */
	public void setConsultationRmarks(String consultationRmarks) {
		this.consultationRmarks = consultationRmarks;
	}
	/**
	 * 获取：备注
	 */
	public String getConsultationRmarks() {
		return consultationRmarks;
	}
	/**
	 * 设置：咨询时间
	 */
	public void setConsultationTime(Date consultationTime) {
		this.consultationTime = consultationTime;
	}
	/**
	 * 获取：咨询时间
	 */
	public Date getConsultationTime() {
		return consultationTime;
	}
	/**
	 * 设置：咨询处理状态
	 */
	public void setConsultationExcuteStatus(String consultationExcuteStatus) {
		this.consultationExcuteStatus = consultationExcuteStatus;
	}
	/**
	 * 获取：咨询处理状态
	 */
	public String getConsultationExcuteStatus() {
		return consultationExcuteStatus;
	}
	/**
	 * 设置：咨询处理状态描述
	 */
	public void setConsultationExcuteDescriptio(String consultationExcuteDescriptio) {
		this.consultationExcuteDescriptio = consultationExcuteDescriptio;
	}
	/**
	 * 获取：咨询处理状态描述
	 */
	public String getConsultationExcuteDescriptio() {
		return consultationExcuteDescriptio;
	}
	/**
	 * 设置：咨询处理时间
	 */
	public void setConsultationExcuteTime(Date consultationExcuteTime) {
		this.consultationExcuteTime = consultationExcuteTime;
	}
	/**
	 * 获取：咨询处理时间
	 */
	public Date getConsultationExcuteTime() {
		return consultationExcuteTime;
	}
}
