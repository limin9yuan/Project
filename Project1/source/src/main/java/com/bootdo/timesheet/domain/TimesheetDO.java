
package com.bootdo.timesheet.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.bootdo.approval.domain.AssignmentDO;


/**
 * 工时信息表
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-14 17:52:48
 */
public class TimesheetDO extends AssignmentDO implements Serializable {
	private static final long serialVersionUID = 1L;


	//工时信息编号
	private String timesheetId;
	//项目编号
	private String projectId;
	//项目名称
	private  String projectName;
 	//员工工号
	private Long employeeId;
	//员工姓名
	private String timesheetName;
	//项目类别
	private String timesheetProjectCagegory;
	//日期
	private Date timesheetDate;
	//项目经理
	private String timesheetPm;
	//正常工时
	private BigDecimal timesheetNormal;
	//加班工时
	private BigDecimal timesheetOvertime;
	//审核通过加班工时
	private BigDecimal timesheetApproved;
	//是否计划
	private Integer timesheetPlanned;
	//计划任务
	private String timesheetPlan;
	//工作内容
	private String timesheetContent;
	//出现问题
	private String timesheetProblem;
	//关联工作委托编号
	private String timesheetAssignmentId;
	//审批状态
	private Integer timesheetApprovalStatus;
	//操作时间
	private Date timesheetOperateTime;
	//业务名称
	private String assignmenttaskName;

	//提取今天的日期
	 private String date8;
	//提取的timesheetid
	private String idDate1;
	// 提取的工作内容1
	private String contentDate1;
	//提取的工作正常时间
	private BigDecimal normalDate1;
	//提取的工作超时时间
    private  BigDecimal overDate1;

	//提取的timesheetid
	private String idDate2;
	// 提取的工作内容2
	private String contentDate2;
	//提取的工作正常时间
	private BigDecimal normalDate2;
	//提取的工作超时时间
	private  BigDecimal overDate2;

	//提取的timesheetid
	private String idDate3;
	// 提取的工作内容3
	private String contentDate3;
	//提取的工作正常时间
	private BigDecimal normalDate3;
	//提取的工作超时时间
	private  BigDecimal overDate3;

	//提取的timesheetid
	private String idDate4;
	// 提取的工作内容4
	private String contentDate4;
	//提取的工作正常时间
	private BigDecimal normalDate4;
	//提取的工作超时时间
	private  BigDecimal overDate4;

	//提取的timesheetid
	private String idDate5;
	// 提取的工作内容5
	private String contentDate5;
	//提取的工作正常时间
	private BigDecimal normalDate5;
	//提取的工作超时时间
	private  BigDecimal overDate5;

	//提取的timesheetid
	private String idDate6;
	// 提取的工作内容6
	private String contentDate6;
	//提取的工作正常时间
	private BigDecimal normalDate6;
	//提取的工作超时时间
	private  BigDecimal overDate6;

	//提取的timesheetid
	private String idDate7;
	// 提取的工作内容7
	private String contentDate7;
	//提取的工作正常时间
	private BigDecimal normalDate7;
	//提取的工作超时时间
	private  BigDecimal overDate7;



	/**
	 * 设置：工时信息编号
	 */
	public void setTimesheetId(String timesheetId) {
		this.timesheetId = timesheetId;
	}
	/**
	 * 获取：工时信息编号
	 */
	public String getTimesheetId() {
		return timesheetId;
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
	 * 设置：员工工号
	 */
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * 获取：员工工号
	 */
	public Long getEmployeeId() {
		return employeeId;
	}
	/**
	 * 设置：员工姓名
	 */
	public void setTimesheetName(String timesheetName) {
		this.timesheetName = timesheetName;
	}
	/**
	 * 获取：员工姓名
	 */
	public String getTimesheetName() {
		return timesheetName;
	}
	/**
	 * 设置：项目类别
	 */
	public void setTimesheetProjectCagegory(String timesheetProjectCagegory) {
		this.timesheetProjectCagegory = timesheetProjectCagegory;
	}
	/**
	 * 获取：项目类别
	 */
	public String getTimesheetProjectCagegory() {
		return timesheetProjectCagegory;
	}
	/**
	 * 设置：日期
	 */
	public void setTimesheetDate(Date timesheetDate) {
		this.timesheetDate = timesheetDate;
	}
	/**
	 * 获取：日期
	 */
	public Date getTimesheetDate() {
		return timesheetDate;
	}
	/**
	 * 设置：项目经理
	 */
	public void setTimesheetPm(String timesheetPm) {
		this.timesheetPm = timesheetPm;
	}
	/**
	 * 获取：项目经理
	 */
	public String getTimesheetPm() {
		return timesheetPm;
	}
	/**
	 * 设置：正常工时
	 */
	public void setTimesheetNormal(BigDecimal timesheetNormal) {
		this.timesheetNormal = timesheetNormal;
	}
	/**
	 * 获取：正常工时
	 */
	public BigDecimal getTimesheetNormal() {
		return timesheetNormal;
	}
	/**
	 * 设置：加班工时
	 */
	public void setTimesheetOvertime(BigDecimal timesheetOvertime) {
		this.timesheetOvertime = timesheetOvertime;
	}
	/**
	 * 获取：加班工时
	 */
	public BigDecimal getTimesheetOvertime() {
		return timesheetOvertime;
	}
	/**
	 * 设置：审核通过加班工时
	 */
	public void setTimesheetApproved(BigDecimal timesheetApproved) {
		this.timesheetApproved = timesheetApproved;
	}
	/**
	 * 获取：审核通过加班工时
	 */
	public BigDecimal getTimesheetApproved() {
		return timesheetApproved;
	}
	/**
	 * 设置：是否计划
	 */
	public void setTimesheetPlanned(Integer timesheetPlanned) {
		this.timesheetPlanned = timesheetPlanned;
	}
	/**
	 * 获取：是否计划
	 */
	public Integer getTimesheetPlanned() {
		return timesheetPlanned;
	}
	/**
	 * 设置：计划任务
	 */
	public void setTimesheetPlan(String timesheetPlan) {
		this.timesheetPlan = timesheetPlan;
	}
	/**
	 * 获取：计划任务
	 */
	public String getTimesheetPlan() {
		return timesheetPlan;
	}
	/**
	 * 设置：工作内容
	 */
	public void setTimesheetContent(String timesheetContent) {
		this.timesheetContent = timesheetContent;
	}
	/**
	 * 获取：工作内容
	 */
	public String getTimesheetContent() {
		return timesheetContent;
	}
	/**
	 * 设置：出现问题
	 */
	public void setTimesheetProblem(String timesheetProblem) {
		this.timesheetProblem = timesheetProblem;
	}
	/**
	 * 获取：出现问题
	 */
	public String getTimesheetProblem() {
		return timesheetProblem;
	}
	/**
	 * 设置：关联工作委托编号
	 */
	public void setTimesheetAssignmentId(String timesheetAssignmentId) {
		this.timesheetAssignmentId = timesheetAssignmentId;
	}
	/**
	 * 获取：关联工作委托编号
	 */
	public String getTimesheetAssignmentId() {
		return timesheetAssignmentId;
	}
	/**
	 * 设置：审批状态
	 */
	public void setTimesheetApprovalStatus(Integer timesheetApprovalStatus) {
		this.timesheetApprovalStatus = timesheetApprovalStatus;
	}
	/**
	 * 获取：审批状态
	 */
	public Integer getTimesheetApprovalStatus() {
		return timesheetApprovalStatus;
	}
	/**
	 * 设置：操作时间
	 */
	public void setTimesheetOperateTime(Date timesheetOperateTime) {
		this.timesheetOperateTime = timesheetOperateTime;
	}
	/**
	 * 获取：操作时间
	 */
	public Date getTimesheetOperateTime() {
		return timesheetOperateTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}




	public String getContentDate1() {
		return contentDate1;
	}

	public void setContentDate1(String contentDate1) {
		this.contentDate1 = contentDate1;
	}

	public BigDecimal getNormalDate1() {
		return normalDate1;
	}

	public void setNormalDate1(BigDecimal normalDate1) {
		this.normalDate1 = normalDate1;
	}

	public BigDecimal getOverDate1() {
		return overDate1;
	}

	public void setOverDate1(BigDecimal overDate1) {
		this.overDate1 = overDate1;
	}

	public String getContentDate2() {
		return contentDate2;
	}

	public void setContentDate2(String contentDate2) {
		this.contentDate2 = contentDate2;
	}

	public BigDecimal getNormalDate2() {
		return normalDate2;
	}

	public void setNormalDate2(BigDecimal normalDate2) {
		this.normalDate2 = normalDate2;
	}

	public BigDecimal getOverDate2() {
		return overDate2;
	}

	public void setOverDate2(BigDecimal overDate2) {
		this.overDate2 = overDate2;
	}

	public String getContentDate3() {
		return contentDate3;
	}

	public void setContentDate3(String contentDate3) {
		this.contentDate3 = contentDate3;
	}

	public BigDecimal getNormalDate3() {
		return normalDate3;
	}

	public void setNormalDate3(BigDecimal normalDate3) {
		this.normalDate3 = normalDate3;
	}

	public BigDecimal getOverDate3() {
		return overDate3;
	}

	public void setOverDate3(BigDecimal overDate3) {
		this.overDate3 = overDate3;
	}

	public String getContentDate4() {
		return contentDate4;
	}

	public void setContentDate4(String contentDate4) {
		this.contentDate4 = contentDate4;
	}

	public BigDecimal getNormalDate4() {
		return normalDate4;
	}

	public void setNormalDate4(BigDecimal normalDate4) {
		this.normalDate4 = normalDate4;
	}

	public BigDecimal getOverDate4() {
		return overDate4;
	}

	public void setOverDate4(BigDecimal overDate4) {
		this.overDate4 = overDate4;
	}

	public String getContentDate5() {
		return contentDate5;
	}

	public void setContentDate5(String contentDate5) {
		this.contentDate5 = contentDate5;
	}

	public BigDecimal getNormalDate5() {
		return normalDate5;
	}

	public void setNormalDate5(BigDecimal normalDate5) {
		this.normalDate5 = normalDate5;
	}

	public BigDecimal getOverDate5() {
		return overDate5;
	}

	public void setOverDate5(BigDecimal overDate5) {
		this.overDate5 = overDate5;
	}

	public String getContentDate6() {
		return contentDate6;
	}

	public void setContentDate6(String contentDate6) {
		this.contentDate6 = contentDate6;
	}

	public BigDecimal getNormalDate6() {
		return normalDate6;
	}

	public void setNormalDate6(BigDecimal normalDate6) {
		this.normalDate6 = normalDate6;
	}

	public BigDecimal getOverDate6() {
		return overDate6;
	}

	public void setOverDate6(BigDecimal overDate6) {
		this.overDate6 = overDate6;
	}

	public String getContentDate7() {
		return contentDate7;
	}

	public void setContentDate7(String contentDate7) {
		this.contentDate7 = contentDate7;
	}

	public BigDecimal getNormalDate7() {
		return normalDate7;
	}

	public void setNormalDate7(BigDecimal normalDate7) {
		this.normalDate7 = normalDate7;
	}

	public BigDecimal getOverDate7() {
		return overDate7;
	}

	public void setOverDate7(BigDecimal overDate7) {
		this.overDate7 = overDate7;
	}

	public String getIdDate1() {
		return idDate1;
	}

	public void setIdDate1(String idDate1) {
		this.idDate1 = idDate1;
	}

	public String getIdDate2() {
		return idDate2;
	}

	public void setIdDate2(String idDate2) {
		this.idDate2 = idDate2;
	}

	public String getIdDate3() {
		return idDate3;
	}

	public void setIdDate3(String idDate3) {
		this.idDate3 = idDate3;
	}

	public String getIdDate4() {
		return idDate4;
	}

	public void setIdDate4(String idDate4) {
		this.idDate4 = idDate4;
	}

	public String getIdDate5() {
		return idDate5;
	}

	public void setIdDate5(String idDate5) {
		this.idDate5 = idDate5;
	}

	public String getIdDate6() {
		return idDate6;
	}

	public void setIdDate6(String idDate6) {
		this.idDate6 = idDate6;
	}

	public String getIdDate7() {
		return idDate7;
	}

	public void setIdDate7(String idDate7) {
		this.idDate7 = idDate7;
	}

	public String getDate8() {
		return date8;
	}

	public void setDate8(String date8) {
		this.date8 = date8;
	}

	public String getAssignmenttaskName() {
		return assignmenttaskName;
	}

	public void setAssignmenttaskName(String assignmenttaskName) {
		this.assignmenttaskName = assignmenttaskName;
	}
}



