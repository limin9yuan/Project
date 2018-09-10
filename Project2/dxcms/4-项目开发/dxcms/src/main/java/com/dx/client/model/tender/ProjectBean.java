package com.dx.client.model.tender;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: DX01
 * @Date: 2018/9/4 12:40
 * @Description: 立项申请（富文本方式）
 */
public class ProjectBean {
    //主键
    private String id;
    //开支方案id
    private String expeSchemeBeanId;
    //编号
    private String code;
    //名称
    private String name;
    //预算金额
    private BigDecimal budgetMoney;

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
