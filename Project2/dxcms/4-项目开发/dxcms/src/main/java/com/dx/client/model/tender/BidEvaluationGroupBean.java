package com.dx.client.model.tender;

/**
 * @Auther: DX01
 * @Date: 2018/9/5 11:51
 * @Description: 评标委员会（评标人员）
 */
public class BidEvaluationGroupBean {
    //主键
    private String id;
    //招标方案id
    private String tenderSchemeId;
    //评标小组类型id（如技术组、商务组）
    private String groupTypeId;
    //评标小组类型名称（如技术组、商务组）
    private String groupTypeName;
    //评标人id
    private String userId;
    //评标人
    private String userName;
    //是否在用
    private boolean isActived;
    //创建人id
    private String createUserId;
    //创建人
    private String createUserName;
    //备注
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenderSchemeId() {
        return tenderSchemeId;
    }

    public void setTenderSchemeId(String tenderSchemeId) {
        this.tenderSchemeId = tenderSchemeId;
    }

    public String getGroupTypeId() {
        return groupTypeId;
    }

    public void setGroupTypeId(String groupTypeId) {
        this.groupTypeId = groupTypeId;
    }

    public String getGroupTypeName() {
        return groupTypeName;
    }

    public void setGroupTypeName(String groupTypeName) {
        this.groupTypeName = groupTypeName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isActived() {
        return isActived;
    }

    public void setActived(boolean actived) {
        isActived = actived;
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
}
