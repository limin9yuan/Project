package org.wxcl.amy.model;

import java.util.Date;
import java.util.UUID;

/**
 * @Auther: cxd
 * @Date: 2018/8/26
 * @Description: 合同的适用机构
 */
public class ContractSuitModel {
    //主键
    private UUID id;
    //适用机构id
    private UUID suitCorpId;
    //适用机构名称
    private String suitCorpName;
    //创建时间
    private Date createDate;
    //是否在用
    private Boolean isActived;

}
