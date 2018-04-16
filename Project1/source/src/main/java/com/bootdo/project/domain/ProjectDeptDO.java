package com.bootdo.project.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2018-01-24 17:08:45
 */
public class ProjectDeptDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//项目编号
	private String projectId;
	//部门编号
	private String deptId;
	//操作人
	private Long operator;
	//操作时间
	private Date operateTime;

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
	 * 设置：部门编号
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：部门编号
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * 设置：操作人
	 */
	public void setOperator(Long operator) {
		this.operator = operator;
	}
	/**
	 * 获取：操作人
	 */
	public Long getOperator() {
		return operator;
	}
	/**
	 * 设置：操作时间
	 */
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	/**
	 * 获取：操作时间
	 */
	public Date getOperateTime() {
		return operateTime;
	}

}
