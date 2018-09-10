package com.dx.client.model.tender;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: DX01
 * @Date: 2018/9/6 08:58
 * @Description: 询价单(询比价结果报告）
 * (供应商的报价单，录入到询价单中，通过最低价和人工确认方式确定中标物资，
 * 然后形成询比价结果报告并审批，审批后在合同里生成数据）
 */
public class InquiryReportBean {
    //主键
    private String id;
    //公司id
    private String companyId;
    //公司编码
    private String companyCode;
    //公司名称
    private String companyName;
    //物资id
    private String materialId;
    //物资编码
    private String materilaCode;
    //物资名称
    private String materialName;
    //单位名称
    private String materialUnitName;
    //规格
    private String specification;
    //材质
    private String texture;
    //报价（单价）
    private BigDecimal price;

    //TODO:补充其他属性

    //是否中标
    private boolean isAwarded;
    //是否在用
    private boolean isActived;
    //生成日期
    private Date createDate;
    //生成人Id
    private String createUserId;
    //生成人
    private String createUserName;
    //备注
    private String remark;
}
