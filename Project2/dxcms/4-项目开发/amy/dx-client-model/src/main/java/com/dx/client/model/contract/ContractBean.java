package com.dx.client.model.contract;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description: 合同
 */
public class ContractBean {
    //主键
    private String id;
    //合同模板id
    private String templateId;
    //合同模板名称
    private String templateName;
    //项目id
    private String projectId;
    //项目名称
    private String projectName;
    //招标方案id
    private String bidSchemeId;
    //招标方案名称
    private String bidSchemeName;
    //审批状态Id
    private String statusId;
    //审批状态名称
    private String statusName;
    //编制机构Id
    private String authorCorpId;
    //编制机构名称
    private String authorCorpName;
    //编制部门Id
    private String authorDeptId;
    //编制部门名称
    private String authorDeptName;
    //起草人Id
    private String authorUserId;
    //起草人
    private String authorUserName;
    //执行部门id
    private String performDeptId;
    //执行部门名称
    private String performDeptName;
    //执行人id
    private String performUserId;
    //执行人
    private String performUserName;
    //合同编号
    private String contractCode;
    //合同名称
    private String contractName;
    //合同甲方id（添加合同页不显示）
    private String companyIdA;
    //合同甲方名称（添加合同页不显示）
    private String companyNameA;
    //合同甲方联系人（添加合同页不显示）
    private String companyContractsA;
    //合同乙方id（添加合同页不显示）
    private String companyIdB;
    //合同乙方名称（添加合同页不显示）
    private String companyNameB;
    //合同联系人（添加合同页不显示）
    private String companyContractsB;
    //货币类型id
    private String currencyTypeId;
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
    private boolean isSubmit;
    //是否开始审批
    private boolean IsApproveBegin;
    //是否审批完成
    private String isApproveFinish;
    //是否终止
    private boolean isStopped;
    //是否在用
    private boolean isActived;
    //是否归档
    private boolean isOnFiled;
    //创建日期
    private Date createDate;
    //创建人Id
    private String createUserId;
    //创建人
    private String createUserName;
    //备注
    private String remark;
    //合同物资明细
    private List<ContractMaterialBean> contractMaterialBeans;
    //适用机构
    private List<ContractSuitBean> contractSuitBeans;
    //发货单位
    private List<ContractDeliverBean> contractDeliverBeans;
    //合同附件
    private List<ContractEnclosureBean> contractEnclosureBeans;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBidSchemeId() {
        return bidSchemeId;
    }

    public void setBidSchemeId(String bidSchemeId) {
        this.bidSchemeId = bidSchemeId;
    }

    public String getBidSchemeName() {
        return bidSchemeName;
    }

    public void setBidSchemeName(String bidSchemeName) {
        this.bidSchemeName = bidSchemeName;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getAuthorCorpId() {
        return authorCorpId;
    }

    public void setAuthorCorpId(String authorCorpId) {
        this.authorCorpId = authorCorpId;
    }

    public String getAuthorCorpName() {
        return authorCorpName;
    }

    public void setAuthorCorpName(String authorCorpName) {
        this.authorCorpName = authorCorpName;
    }

    public String getAuthorDeptId() {
        return authorDeptId;
    }

    public void setAuthorDeptId(String authorDeptId) {
        this.authorDeptId = authorDeptId;
    }

    public String getAuthorDeptName() {
        return authorDeptName;
    }

    public void setAuthorDeptName(String authorDeptName) {
        this.authorDeptName = authorDeptName;
    }

    public String getAuthorUserId() {
        return authorUserId;
    }

    public void setAuthorUserId(String authorUserId) {
        this.authorUserId = authorUserId;
    }

    public String getAuthorUserName() {
        return authorUserName;
    }

    public void setAuthorUserName(String authorUserName) {
        this.authorUserName = authorUserName;
    }

    public String getPerformDeptId() {
        return performDeptId;
    }

    public void setPerformDeptId(String performDeptId) {
        this.performDeptId = performDeptId;
    }

    public String getPerformDeptName() {
        return performDeptName;
    }

    public void setPerformDeptName(String performDeptName) {
        this.performDeptName = performDeptName;
    }

    public String getPerformUserId() {
        return performUserId;
    }

    public void setPerformUserId(String performUserId) {
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

    public String getCompanyIdA() {
        return companyIdA;
    }

    public void setCompanyIdA(String companyIdA) {
        this.companyIdA = companyIdA;
    }

    public String getCompanyNameA() {
        return companyNameA;
    }

    public void setCompanyNameA(String companyNameA) {
        this.companyNameA = companyNameA;
    }

    public String getCompanyContractsA() {
        return companyContractsA;
    }

    public void setCompanyContractsA(String companyContractsA) {
        this.companyContractsA = companyContractsA;
    }

    public String getCompanyIdB() {
        return companyIdB;
    }

    public void setCompanyIdB(String companyIdB) {
        this.companyIdB = companyIdB;
    }

    public String getCompanyNameB() {
        return companyNameB;
    }

    public void setCompanyNameB(String companyNameB) {
        this.companyNameB = companyNameB;
    }

    public String getCompanyContractsB() {
        return companyContractsB;
    }

    public void setCompanyContractsB(String companyContractsB) {
        this.companyContractsB = companyContractsB;
    }

    public String getCurrencyTypeId() {
        return currencyTypeId;
    }

    public void setCurrencyTypeId(String currencyTypeId) {
        this.currencyTypeId = currencyTypeId;
    }

    public String getCurrencyTypeName() {
        return currencyTypeName;
    }

    public void setCurrencyTypeName(String currencyTypeName) {
        this.currencyTypeName = currencyTypeName;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getPerformanceBond() {
        return performanceBond;
    }

    public void setPerformanceBond(BigDecimal performanceBond) {
        this.performanceBond = performanceBond;
    }

    public BigDecimal getWarrantyBond() {
        return warrantyBond;
    }

    public void setWarrantyBond(BigDecimal warrantyBond) {
        this.warrantyBond = warrantyBond;
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

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public boolean isSubmit() {
        return isSubmit;
    }

    public void setSubmit(boolean submit) {
        isSubmit = submit;
    }

    public boolean isApproveBegin() {
        return IsApproveBegin;
    }

    public void setApproveBegin(boolean approveBegin) {
        IsApproveBegin = approveBegin;
    }

    public String getIsApproveFinish() {
        return isApproveFinish;
    }

    public void setIsApproveFinish(String isApproveFinish) {
        this.isApproveFinish = isApproveFinish;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    public boolean isActived() {
        return isActived;
    }

    public void setActived(boolean actived) {
        isActived = actived;
    }

    public boolean isOnFiled() {
        return isOnFiled;
    }

    public void setOnFiled(boolean onFiled) {
        isOnFiled = onFiled;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
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

    public List<ContractMaterialBean> getContractMaterialBeans() {
        return contractMaterialBeans;
    }

    public void setContractMaterialBeans(List<ContractMaterialBean> contractMaterialBeans) {
        this.contractMaterialBeans = contractMaterialBeans;
    }

    public List<ContractSuitBean> getContractSuitBeans() {
        return contractSuitBeans;
    }

    public void setContractSuitBeans(List<ContractSuitBean> contractSuitBeans) {
        this.contractSuitBeans = contractSuitBeans;
    }

    public List<ContractDeliverBean> getContractDeliverBeans() {
        return contractDeliverBeans;
    }

    public void setContractDeliverBeans(List<ContractDeliverBean> contractDeliverBeans) {
        this.contractDeliverBeans = contractDeliverBeans;
    }

    public List<ContractEnclosureBean> getContractEnclosureBeans() {
        return contractEnclosureBeans;
    }

    public void setContractEnclosureBeans(List<ContractEnclosureBean> contractEnclosureBeans) {
        this.contractEnclosureBeans = contractEnclosureBeans;
    }
}
