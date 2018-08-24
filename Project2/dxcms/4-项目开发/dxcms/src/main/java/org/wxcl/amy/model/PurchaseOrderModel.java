package org.wxcl.amy.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther: cxd
 * @Date: 2018/8/22 14:11
 * @Description: 采购订单
 */
public class PurchaseOrderModel {
    //主键
    private UUID id;
    //订单类型id（订单、发货通知单、到货通知单）
    private UUID typeId;
    //订单类型名称
    private String typeName;
    //审批状态id
    private UUID statusId;
    //审批状态名称
    private String statusName;
    //编制机构id
    private UUID authorCorpId;
    //编制机构名称
    private String authorCorpName;
    //编制部门id
    private UUID authorDeptId;
    //编制部门名称
    private String authorDeptName;
    //编制人id
    private UUID authorUserId;
    //编制人名称
    private String authorUserName;
    //执行机构id
    private UUID performCorpId;
    //执行机构名称
    private String performCorpName;
    //执行人id
    private UUID performUserId;
    //执行人
    private String performUserName;
    //单据编号
    private String billNo;
    //关联单据编号
    private String relatedBillNo;
    //单据名称
    private String billName;
    //供应商id
    private UUID companyId;
    //供应商名称
    private String companyName;
    //发货单位id
    private UUID deliverCompanyId;
    //发货单位名称
    private String deliverCompanyName;
    //合同id
    private UUID contractId;
    //合同编号
    private String contractNo;
    //税率
    private double taxRate;
    //业务日期
    private Date businessDate;
    //总金额
    private BigDecimal totalMoney;
    //到货地址
    private String arriveAddress;
    //支付方式
    private String paymentType;
    //是否变更
    private Boolean isChanged;
    //是否已提交
    private Boolean isSubmit;
    //是否开始审批
    private Boolean IsApproveBegin;
    //是否审批完成
    private String isApproveFinish;
    //终止
    private Boolean isStoped;
    //是否在用
    private Boolean isActived;
    //是否已上传
    private Boolean isPushed;
    //生成日期
    private Date createDate;
    //生成人
    private String createUser;
    //备注
    private String remark;
    //打印人
    private String printUser;
    //打印日期
    private Date printDate;
    //打印次数
    private Integer printTimes;
}
