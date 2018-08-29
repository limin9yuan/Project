package org.wxcl.amy.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther: cxd
 * @Date: 2018/8/26
 * @Description: 合同
 */
public class ContractModel {
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
    //预算金额
    private BigDecimal budgetMoney;
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
}
