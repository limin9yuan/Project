package com.dx.client.model.purchase;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description: 采购订单
 */
public class PurchaseOrderBean {
    //主键
    private String id;
    //订单类型id（订单、发货通知单、到货通知单）
    private String typeId;
    //订单类型名称
    private String typeName;
    //审批状态id
    private String statusId;
    //审批状态名称
    private String statusName;
    //编制机构id
    private String authorCorpId;
    //编制机构名称
    private String authorCorpName;
    //编制部门id
    private String authorDeptId;
    //编制部门名称
    private String authorDeptName;
    //编制人id
    private String authorUserId;
    //编制人名称
    private String authorUserName;
    //执行机构id
    private String performCorpId;
    //执行机构名称
    private String performCorpName;
    //执行人id
    private String executerId;
    //执行人
    private String executerName;
    //单据编号
    private String code;
    //关联单据编号（前端无需处理）
    private String relatedCode;
    //单据名称
    private String name;
    //供应商id
    private String companyId;
    //供应商名称
    private String companyName;
    //发货单位id
    private String deliverCompanyId;
    //发货单位名称
    private String deliverCompanyName;
    //合同id
    private String contractId;
    //合同编号
    private String contractCode;
    //税率
    private double taxRate;
    //采购计划id
    private String purchasePlanId;
    //采购计划编码
    private String purchasePlanCode;
    //货源计划id
    private String allotPlanId;
    //货源计划编码
    private String allotPlanCode;
    //业务日期
    private Date businessDate;
    //总金额
    private BigDecimal totalMoney;
    //到货地址（交货地点）
    private String arriveAddress;
    //付款方式及期限
    private String paymentType;
    //质量标准
    private String qualityStandard;
    //是否变更
    private boolean isChanged;
    //是否已提交
    private boolean isSubmit;
    //是否开始审批
    private boolean IsApproveBegin;
    //是否审批完成
    private String isApproveFinish;
    //终止
    private boolean isStopped;
    //是否在用
    private boolean isActived;
    //是否已上传
    private boolean isPushed;
    //创建日期
    private Date createDate;
    //创建人Id
    private String createUserId;
    //创建人
    private String createUserName;
    //备注
    private String remark;
    //打印人id
    private String printUserId;
    //打印人
    private String printUserName;
    //打印日期
    private Date printDate;
    //打印次数
    private int printTimes;

}
