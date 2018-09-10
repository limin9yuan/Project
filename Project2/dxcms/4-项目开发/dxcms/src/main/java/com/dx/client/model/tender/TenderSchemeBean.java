package com.dx.client.model.tender;

import java.util.Date;

/**
 * @Auther: DX01
 * @Date: 2018/9/4 13:38
 * @Description: 招标方案（富文本方式）
 */
public class TenderSchemeBean {
    //主键
    private String id;
    //立项id
    private String projectId;
    //编号
    private String code;
    //名称
    private String name;

    //TODO:补充其他属性

    //是否已提交
    private  boolean isSubmit;
    //是否开始审批
    private boolean isApproveBegin;
    //是否审批完成
    private boolean isApproveFinish;
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
}
