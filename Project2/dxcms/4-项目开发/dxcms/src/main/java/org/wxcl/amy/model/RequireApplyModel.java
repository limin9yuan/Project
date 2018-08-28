package org.wxcl.amy.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/*
 * @author cxd
 * @create 2018/8/21
 * @since 1.0.0
 * 采购申请
 */
public class RequireApplyModel {
    //主键
    private UUID id;
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
    //受理部门id
    private UUID acceptDeptId;
    //受理部门名称
    private String acceptDeptName;
    //编号
    private String planNo;
    //名称
    private String name;
    //业务日期
    private Date businessDate;
    //库存日期(当前计算库存的时点)
    private Date stockDate;
    //总金额
    private BigDecimal totalMoney;
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

    public UUID getAcceptDeptId() {
        return acceptDeptId;
    }

    public void setAcceptDeptId(UUID acceptDeptId) {
        this.acceptDeptId = acceptDeptId;
    }

    public String getAcceptDeptName() {
        return acceptDeptName;
    }

    public void setAcceptDeptName(String acceptDeptName) {
        this.acceptDeptName = acceptDeptName;
    }

    public String getPlanNo() {
        return planNo;
    }

    public void setPlanNo(String planNo) {
        this.planNo = planNo;
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
