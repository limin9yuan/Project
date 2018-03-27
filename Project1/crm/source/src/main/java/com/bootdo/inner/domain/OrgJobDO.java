package com.bootdo.inner.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 内部组织机构_岗位
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-24 13:49:26
 */
public class OrgJobDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//岗位编号
	private String jobId;
	//部门编号
	private String deptId;
	//岗位名称
	private String jobName;
	//所属部门
	private String jobDept;
	//级别名称
	private String jobRank;
	//级别描述
	private String jobRankDescription;
	//岗位描述
	private String jobDescrription;
	//备注
	private String jobRemarks;
	//操作人
	private Long jobOperator;
	//操作人姓名
	private String jobOperatorName;
	//操作时间
	private Date jobOperateTime;
	//岗位设立时间
	private Date createJobDate;

	
	/**
	 * 设置：岗位编号
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	/**
	 * 获取：岗位编号
	 */
	public String getJobId() {
		return jobId;
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
	 * 设置：岗位名称
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	/**
	 * 获取：岗位名称
	 */
	public String getJobName() {
		return jobName;
	}
	/**
	 * 设置：所属部门
	 */
	public void setJobDept(String jobDept) {
		this.jobDept = jobDept;
	}
	/**
	 * 获取：所属部门
	 */
	public String getJobDept() {
		return jobDept;
	}
	/**
	 * 设置：级别名称
	 */
	public void setJobRank(String jobRank) {
		this.jobRank = jobRank;
	}
	/**
	 * 获取：级别名称
	 */
	public String getJobRank() {
		return jobRank;
	}
	/**
	 * 设置：级别描述
	 */
	public void setJobRankDescription(String jobRankDescription) {
		this.jobRankDescription = jobRankDescription;
	}
	/**
	 * 获取：级别描述
	 */
	public String getJobRankDescription() {
		return jobRankDescription;
	}
	/**
	 * 设置：岗位描述
	 */
	public void setJobDescrription(String jobDescrription) {
		this.jobDescrription = jobDescrription;
	}
	/**
	 * 获取：岗位描述
	 */
	public String getJobDescrription() {
		return jobDescrription;
	}
	/**
	 * 设置：备注
	 */
	public void setJobRemarks(String jobRemarks) {
		this.jobRemarks = jobRemarks;
	}
	/**
	 * 获取：备注
	 */
	public String getJobRemarks() {
		return jobRemarks;
	}
	/**
	 * 设置：操作人
	 */
	public void setJobOperator(Long jobOperator) {
		this.jobOperator = jobOperator;
	}
	/**
	 * 获取：操作人
	 */
	public Long getJobOperator() {
		return jobOperator;
	}
	/**
	 * 设置：操作时间
	 */
	public void setJobOperateTime(Date jobOperateTime) {
		this.jobOperateTime = jobOperateTime;
	}
	/**
	 * 获取：操作时间
	 */
	public Date getJobOperateTime() {
		return jobOperateTime;
	}
	public String getJobOperatorName() {
		return jobOperatorName;
	}
	public void setJobOperatorName(String jobOperatorName) {
		this.jobOperatorName = jobOperatorName;
	}
	/**
	 * 岗位设立时间
	 */
	public Date getCreateJobDate() {
		return createJobDate;
	}
	public void setCreateJobDate(Date createJobDate) {
		this.createJobDate = createJobDate;
	}
	


}
