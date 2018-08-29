package com.dx.client.model.contract;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description: 合同
 */
public class ContractBean {
    //主键
    private UUID id;
    //合同模板id
    private UUID templateId;
    //立项id
    private UUID projectId;
    //招标方案id
    private UUID bidSchemeId;
    //审批状态Id
    private UUID statusId;
    //审批状态名称
    private String statusName;
    //编制机构Id
    private UUID authorCorpId;
    //编制机构名称
    private String authorCorpName;
    //编制部门Id
    private UUID authorDeptId;
    //编制部门名称
    private String authorDeptName;
    //编制人Id
    private UUID authorUserId;
    //编制人
    private String authorUserName;
    //执行部门id
    private UUID performDeptId;
    //执行部门名称
    private String performDeptName;
    //执行人id
    private UUID performUserId;
    //执行人
    private String performUserName;
    //合同编号
    private String contractCode;
    //合同名称
    private String contractName;
    //合同甲方id（添加合同页不显示）
    private UUID companyIdA;
    //合同甲方名称（添加合同页不显示）
    private UUID companyNameA;
    //合同甲方联系人（添加合同页不显示）
    private UUID companyContractsA;
    //合同乙方id（添加合同页不显示）
    private UUID companyIdB;
    //合同乙方名称（添加合同页不显示）
    private UUID companyNameB;
    //合同联系人（添加合同页不显示）
    private UUID companyContractsB;
    //货币类型id
    private UUID currencyTypeId;
    //货币类型名称
    private String currencyTypeName;
    //税率
    private double taxRate;
    //履约保证金
    private BigDecimal performanceBond;
    //质保金
    private BigDecimal warrantyBond;
    //预算金额
    private BigDecimal budgetMoney;
    //总金额
    private BigDecimal totalMoney;
    //有效期自
    private Date dateFrom;
    //有效期至
    private Date dateTo;
    //是否已提交
    private Boolean isSubmit;
    //是否开始审批
    private Boolean IsApproveBegin;
    //是否审批完成
    private String isApproveFinish;
    //是否在用
    private String isActived;
    //生成日期
    private Date createDate;
    //生成人Id
    private UUID createUserId;
    //生成人
    private String createUserName;
    //备注
    private String remark;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTemplateId() {
        return templateId;
    }

    public void setTemplateId(UUID templateId) {
        this.templateId = templateId;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public UUID getBidSchemeId() {
        return bidSchemeId;
    }

    public void setBidSchemeId(UUID bidSchemeId) {
        this.bidSchemeId = bidSchemeId;
    }

    public UUID getStatusId() {
        return statusId;
    }

    public void setStatusId(UUID statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public UUID getAuthorCorpId() {
        return authorCorpId;
    }

    public void setAuthorCorpId(UUID authorCorpId) {
        this.authorCorpId = authorCorpId;
    }

    public String getAuthorCorpName() {
        return authorCorpName;
    }

    public void setAuthorCorpName(String authorCorpName) {
        this.authorCorpName = authorCorpName;
    }

    public UUID getAuthorDeptId() {
        return authorDeptId;
    }

    public void setAuthorDeptId(UUID authorDeptId) {
        this.authorDeptId = authorDeptId;
    }

    public String getAuthorDeptName() {
        return authorDeptName;
    }

    public void setAuthorDeptName(String authorDeptName) {
        this.authorDeptName = authorDeptName;
    }

    public UUID getAuthorUserId() {
        return authorUserId;
    }

    public void setAuthorUserId(UUID authorUserId) {
        this.authorUserId = authorUserId;
    }

    public String getAuthorUserName() {
        return authorUserName;
    }

    public void setAuthorUserName(String authorUserName) {
        this.authorUserName = authorUserName;
    }

    public UUID getPerformDeptId() {
        return performDeptId;
    }

    public void setPerformDeptId(UUID performDeptId) {
        this.performDeptId = performDeptId;
    }

    public String getPerformDeptName() {
        return performDeptName;
    }

    public void setPerformDeptName(String performDeptName) {
        this.performDeptName = performDeptName;
    }

    public UUID getPerformUserId() {
        return performUserId;
    }

    public void setPerformUserId(UUID performUserId) {
        this.performUserId = performUserId;
    }

    public String getPerformUserName() {
        return performUserName;
    }

    public void setPerformUserName(String performUserName) {
        this.performUserName = performUserName;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public UUID getCompanyIdA() {
        return companyIdA;
    }

    public void setCompanyIdA(UUID companyIdA) {
        this.companyIdA = companyIdA;
    }

    public UUID getCompanyNameA() {
        return companyNameA;
    }

    public void setCompanyNameA(UUID companyNameA) {
        this.companyNameA = companyNameA;
    }

    public UUID getCompanyContractsA() {
        return companyContractsA;
    }

    public void setCompanyContractsA(UUID companyContractsA) {
        this.companyContractsA = companyContractsA;
    }

    public UUID getCompanyIdB() {
        return companyIdB;
    }

    public void setCompanyIdB(UUID companyIdB) {
        this.companyIdB = companyIdB;
    }

    public UUID getCompanyNameB() {
        return companyNameB;
    }

    public void setCompanyNameB(UUID companyNameB) {
        this.companyNameB = companyNameB;
    }

    public UUID getCompanyContractsB() {
        return companyContractsB;
    }

    public void setCompanyContractsB(UUID companyContractsB) {
        this.companyContractsB = companyContractsB;
    }

    public BigDecimal getBudgetMoney() {
        return budgetMoney;
    }

    public void setBudgetMoney(BigDecimal budgetMoney) {
        this.budgetMoney = budgetMoney;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Boolean getSubmit() {
        return isSubmit;
    }

    public void setSubmit(Boolean submit) {
        isSubmit = submit;
    }

    public Boolean getApproveBegin() {
        return IsApproveBegin;
    }

    public void setApproveBegin(Boolean approveBegin) {
        IsApproveBegin = approveBegin;
    }

    public String getIsApproveFinish() {
        return isApproveFinish;
    }

    public void setIsApproveFinish(String isApproveFinish) {
        this.isApproveFinish = isApproveFinish;
    }

    public String getIsActived() {
        return isActived;
    }

    public void setIsActived(String isActived) {
        this.isActived = isActived;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public UUID getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(UUID createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
