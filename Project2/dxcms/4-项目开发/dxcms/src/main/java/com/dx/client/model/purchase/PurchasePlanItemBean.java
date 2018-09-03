package com.dx.client.model.purchase;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description: 采购计划明细
 */
public class PurchasePlanItemBean {
    //主键
    private String id;
    //采购计划id
    private String purchasePlanId;
    //需求计划明细id
    private String requirePlanItemId;
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
    //物资子码
    private String materialSubArray;
    //采购员Id
    private String purchaserId;
    //采购员名称
    private String purchaserName;
    //需求数量
    private Double requireQty;
    //采购数量
    private Double purchaseQty;
    //预算数量
    private Double budgetQty;
    //参考价格
    private BigDecimal referencePrice;
    //预算价格
    private BigDecimal budgetPrice;
    //需求日期
    private Date requireDate;
    //到货日期
    private Date arriveDate;
    //是否关闭
    private Boolean isClosed;
    //是否已受理
    private Boolean isAccepted;
    //生成日期
    private Date createDate;
    //是否可用
    private Boolean isActived;
    //描述
    private String description;
    //备注(一般用于系统备注)
    private String Remark;
}
