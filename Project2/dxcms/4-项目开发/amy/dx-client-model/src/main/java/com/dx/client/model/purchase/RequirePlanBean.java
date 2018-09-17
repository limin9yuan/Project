package com.dx.client.model.purchase;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description: 需求计划
 */
public class RequirePlanBean {
    //主键
    private String id;
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
    //编制人Id
    private String authorUserId;
    //编制人
    private String authorUserName;
    //采购部门Id
    private String purchaseDeptId;
    //采购部门
    private String purchaseDeptName;
    //需求计划类型Id
    private String requireTypeId;
    //需求计划类型
    private String requireTypeName;
    //编号
    private String code;
    //名称
    private String name;
    //业务时间(原版本:归属日期)
    private Date businessDate;
    //库存日期(当前计算库存的时点)
    private Date stockDate;
    //预算金额
    private BigDecimal budgetMoney;
    //总金额(明细总金额)
    private BigDecimal totalMoney;
    //是否已提交
    private  boolean isSubmit;
    //是否开始审批
    private boolean isApproveBegin;
    //是否审批完成
    private boolean isApproveFinish;
    //申请理由
    private String applyReason;
    //是否在用
    private boolean isActived;
    //创建日期
    private Date createDate;
    //创建人Id
    private String createUserId;
    //创建人
    private String createUserName;
    //备注
    private String remark;
    //需求计划明细列表
    private List<RequirePlanItemBean> requirePlanItemBeans;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPurchaseDeptId() {
        return purchaseDeptId;
    }

    public void setPurchaseDeptId(String purchaseDeptId) {
        this.purchaseDeptId = purchaseDeptId;
    }

    public String getPurchaseDeptName() {
        return purchaseDeptName;
    }

    public void setPurchaseDeptName(String purchaseDeptName) {
        this.purchaseDeptName = purchaseDeptName;
    }

    public String getRequireTypeId() {
        return requireTypeId;
    }

    public void setRequireTypeId(String requireTypeId) {
        this.requireTypeId = requireTypeId;
    }

    public String getRequireTypeName() {
        return requireTypeName;
    }

    public void setRequireTypeName(String requireTypeName) {
        this.requireTypeName = requireTypeName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
    }

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
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

    public boolean isSubmit() {
        return isSubmit;
    }

    public void setSubmit(boolean submit) {
        isSubmit = submit;
    }

    public boolean isApproveBegin() {
        return isApproveBegin;
    }

    public void setApproveBegin(boolean approveBegin) {
        isApproveBegin = approveBegin;
    }

    public boolean isApproveFinish() {
        return isApproveFinish;
    }

    public void setApproveFinish(boolean approveFinish) {
        isApproveFinish = approveFinish;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public boolean isActived() {
        return isActived;
    }

    public void setActived(boolean actived) {
        isActived = actived;
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

    public List<RequirePlanItemBean> getRequirePlanItemBeans() {
        return requirePlanItemBeans;
    }

    public void setRequirePlanItemBeans(List<RequirePlanItemBean> requirePlanItemBeans) {
        this.requirePlanItemBeans = requirePlanItemBeans;
    }
}
