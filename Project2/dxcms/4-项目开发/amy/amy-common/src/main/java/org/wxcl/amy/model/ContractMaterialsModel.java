package org.wxcl.amy.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther: cxd
 * @Date: 2018/8/26
 * @Description: 合同物资
 */
public class ContractMaterialsModel {
    //主键
    private UUID id;
    //合同id
    private UUID contractId;
    //适用机构id
    private UUID suitCorpId;
    //适用机构名称
    private String suitCorpName;
    //物资id
    private UUID materialId;
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
    //单价
    private BigDecimal price;
    //数量
    private Double qty;
    //区间数量自
    private Double qtyFrom;
    //区间数量至
    private Double qtyTo;
    //有效期自
    private Date dateFrom;
    //有效期至
    private Date dateTo;
    //合同物资父id
    private UUID parentId;
    //生成日期
    private Date createDate;
    //是否在用
    private Boolean isActived;
    //描述
    private String description;
    //备注
    private String remark;
}
