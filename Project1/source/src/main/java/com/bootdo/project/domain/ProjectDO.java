package com.bootdo.project.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 项目信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-28 09:52:05
 */
public class ProjectDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//项目编号
	private String projectId;
	//企业客户编号
	private String customerId;
	//项目集合编号
	private String collectionId;
	//项目名称
	private String projectName;
	//销售负责人
	private String projectSales;
	//项目开始时间
	private String projectBeginDate;
	//项目结束时间
	private String projectEndDate;
	//项目经理
	private String projectOwner;
	//员工姓名
	private String employeeName;
	//研发经理
	private String projectManager;
	//研发开始时间
	private String projectDevelopmentBeginDate;
	//研发结束时间
	private String projectDevelopmentEndDate;
	//项目类型
	private String projectGategory;
	//项目阶段
	private String projectPhase;
	//项目描述
	private String projectDescription;
	//旧项目编号
	private String projectOldId;
	//备注
	private String projectRemarks;
	//操作人
	private Long projectOperator;
	//修改时间
	private Date projectOperateTime;
	//创建人
	private Long projectCreator;
	//创建时间
	private Date projectCreateTime;
	//流程种类
	private String fllowType;
	//是否分包
	private String ifOutSource;
	//是否签订合同
    private String deliveryStatus;
	//关联售前项目编号
	private String projectRelatedId;
	//项目经理名称
	private String projectOwnerName;
	
	//上级部门ID，一级部门为0
	private Long parentId;
	//售前项目名称
	private String developmentProjectName;
	
	//研发经理名称
	private String projectManagerName;
	//销售负责人名称
     private String projectSalesName;
	//客户名称
     private String projectCustomerName;
    
    
    //合同及中心占比
    private String ContractCenterratio;
    //客户名称
    private String customerName;
    
    //部门编号
    private String deptId;
    
   //部门名称
    private String deptName;
    //操作人名称
  	private String projectOperatorName;  	
    //创建人名称
  	private String projectCreatorName;

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
	 * 设置：企业客户编号
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取：企业客户编号
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * 设置：项目集合编号
	 */
	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}
	/**
	 * 获取：项目集合编号
	 */
	public String getCollectionId() {
		return collectionId;
	}
	/**
	 * 设置：项目名称
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * 获取：项目名称
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * 设置：销售负责人
	 */
	public void setProjectSales(String projectSales) {
		this.projectSales = projectSales;
	}
	/**
	 * 获取：销售负责人
	 */
	public String getProjectSales() {
		return projectSales;
	}
	/**
	 * 设置：项目开始时间
	 */
	public void setProjectBeginDate(String projectBeginDate) {
		this.projectBeginDate = projectBeginDate;
	}
	/**
	 * 获取：项目开始时间
	 */
	public String getProjectBeginDate() {
		return projectBeginDate;
	}
	/**
	 * 设置：项目结束时间
	 */
	public void setProjectEndDate(String projectEndDate) {
		this.projectEndDate = projectEndDate;
	}
	/**
	 * 获取：项目结束时间
	 */
	public String getProjectEndDate() {
		return projectEndDate;
	}
	/**
	 * 设置：项目经理
	 */
	public void setProjectOwner(String projectOwner) {
		this.projectOwner = projectOwner;
	}
	/**
	 * 获取：项目经理
	 */
	public String getProjectOwner() {
		return projectOwner;
	}
	/**
	 * 设置：研发经理
	 */
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	/**
	 * 获取：研发经理
	 */
	public String getProjectManager() {
		return projectManager;
	}
	/**
	 * 设置：研发开始时间
	 */
	public void setProjectDevelopmentBeginDate(String projectDevelopmentBeginDate) {
		this.projectDevelopmentBeginDate = projectDevelopmentBeginDate;
	}
	/**
	 * 获取：研发开始时间
	 */
	public String getProjectDevelopmentBeginDate() {
		return projectDevelopmentBeginDate;
	}
	/**
	 * 设置：研发结束时间
	 */
	public void setProjectDevelopmentEndDate(String projectDevelopmentEndDate) {
		this.projectDevelopmentEndDate = projectDevelopmentEndDate;
	}
	/**
	 * 获取：研发结束时间
	 */
	public String getProjectDevelopmentEndDate() {
		return projectDevelopmentEndDate;
	}
	/**
	 * 设置：项目类型
	 */
	public void setProjectGategory(String projectGategory) {
		this.projectGategory = projectGategory;
	}
	/**
	 * 获取：项目类型
	 */
	public String getProjectGategory() {
		return projectGategory;
	}
	/**
	 * 设置：项目阶段
	 */
	public void setProjectPhase(String projectPhase) {
		this.projectPhase = projectPhase;
	}
	/**
	 * 获取：项目阶段
	 */
	public String getProjectPhase() {
		return projectPhase;
	}
	/**
	 * 设置：项目描述
	 */
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	/**
	 * 获取：项目描述
	 */
	public String getProjectDescription() {
		return projectDescription;
	}
	/**
	 * 设置：旧项目编号
	 */
	public void setProjectOldId(String projectOldId) {
		this.projectOldId = projectOldId;
	}
	/**
	 * 获取：旧项目编号
	 */
	public String getProjectOldId() {
		return projectOldId;
	}
	/**
	 * 设置：备注
	 */
	public void setProjectRemarks(String projectRemarks) {
		this.projectRemarks = projectRemarks;
	}
	/**
	 * 获取：备注
	 */
	public String getProjectRemarks() {
		return projectRemarks;
	}
	/**
	 * 设置：操作人
	 */
	public void setProjectOperator(Long projectOperator) {
		this.projectOperator = projectOperator;
	}
	/**
	 * 获取：操作人
	 */
	public Long getProjectOperator() {
		return projectOperator;
	}
	/**
	 * 设置：修改时间
	 */
	public void setProjectOperateTime(Date projectOperateTime) {
		this.projectOperateTime = projectOperateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getProjectOperateTime() {
		return projectOperateTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setProjectCreator(Long projectCreator) {
		this.projectCreator = projectCreator;
	}
	/**
	 * 获取：创建人
	 */
	public Long getProjectCreator() {
		return projectCreator;
	}
	/**
	 * 设置：创建时间
	 */
	public void setProjectCreateTime(Date projectCreateTime) {
		this.projectCreateTime = projectCreateTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getProjectCreateTime() {
		return projectCreateTime;
	}
	/**
	 * 设置：流程种类
	 */
	public void setFllowType(String fllowType) {
		this.fllowType = fllowType;
	}
	/**
	 * 获取：流程种类
	 */
	public String getFllowType() {
		return fllowType;
	}
	/**
	 * 设置：是否分包
	 */
	public void setIfOutSource(String ifOutSource) {
		this.ifOutSource = ifOutSource;
	}
	/**
	 * 获取：是否分包
	 */
	public String getIfOutSource() {
		return ifOutSource;
	}
	
	/**
	 * 设置：是否签订合同
	 */
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	/**
	 * 获取：是否签订合同
	 */
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	/**
	 * 设置：关联售前项目编号
	 */
	public void setProjectRelatedId(String projectRelatedId) {
		this.projectRelatedId = projectRelatedId;
	}
	/**
	 * 获取：关联售前项目编号
	 */
	public String getProjectRelatedId() {
		return projectRelatedId;
	}
	/**
	 * 设置：项目经理名称
	 */
	public void setProjectOwnerName(String projectOwnerName) {
		this.projectOwnerName = projectOwnerName;
	}
	/**
	 * 获取：项目经理名称
	 */
	public String getProjectOwnerName() {
		return projectOwnerName;
	}
	
	
	
	/**
	 * 获取：合同及中心占比
	 */
	public String getContractCenterratio() {
		return ContractCenterratio;
	}
	/**
	 * 设置：合同及中心占比
	 */
	public void setContractCenterratio(String contractCenterratio) {
		ContractCenterratio = contractCenterratio;
	}
	
	/**
	 * 获取：上级部门ID，一级部门为0
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/**
	 * 设置：上级部门ID，一级部门为0
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	/**
	 * 设置：售前项目名称
	 */
	public void setDevelopmentProjectName(String developmentProjectName) {
		this.developmentProjectName = developmentProjectName;
	}
	/**
	 * 获取：售前项目名称
	 */
	public String getDevelopmentProjectName() {
		return developmentProjectName;
	}
	
	/**
	 * 设置：研发经理名称
	 */
	public void setProjectManagerName(String projectManagerName) {
		this.projectManagerName = projectManagerName;
	}
	
	/**
	 * 获取：研发经理名称
	 */
	public String getProjectManagerName() {
		return projectManagerName;
	}
	/**
	 * 设置：销售负责人名称
	 */
	public void setProjectSalesName(String projectSalesName) {
		this.projectSalesName = projectSalesName;
	}
	/**
	 * 获取：销售负责人名称
	 */
	public String getProjectSalesName() {
		return projectSalesName;
	}
	/**
	 * 设置：客户名称
	 */
	public void setProjectCustomerName(String projectCustomerName) {
		this.projectCustomerName = projectCustomerName;
	}
	/**
	 * 获取：客户名称
	 */
	public String getProjectCustomerName() {
		return projectCustomerName;
	}
	public String getProjectOperatorName() {
		return projectOperatorName;
	}
	public void setProjectOperatorName(String projectOperatorName) {
		this.projectOperatorName = projectOperatorName;
	}
	public String getProjectCreatorName() {
		return projectCreatorName;
	}
	public void setProjectCreatorName(String projectCreatorName) {
		this.projectCreatorName = projectCreatorName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}
