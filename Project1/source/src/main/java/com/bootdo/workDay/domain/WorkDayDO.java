package com.bootdo.workDay.domain;

import java.io.Serializable;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-02 16:57:14
 */
public class WorkDayDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//工作日
	private Integer workDay;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：工作日
	 */
	public void setWorkDay(Integer workDay) {
		this.workDay = workDay;
	}
	/**
	 * 获取：工作日
	 */
	public Integer getWorkDay() {
		return workDay;
	}
}
