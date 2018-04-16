package com.bootdo.common.domain;

import java.util.Date;

/**
 * Created by Mingyuan Li on 2018/4/13.
 * Package name: com.bootdo.common.domain.
 * Project name: bootdo.
 */
public class MainCopyPersonDO {
    //主键
    private int Id;
    //客户ID
    private int customerId;
    //员工ID
    private int employeeId;
    //是否主送
    private int mainPerson;
    //操作人
    private Long operator;
    //操作时间
    private Date operateTime;

    public int getId() {
        return Id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getMainPerson() {
        return mainPerson;
    }

    public Long getOperator() {
        return operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setMainPerson(int mainPerson) {
        this.mainPerson = mainPerson;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}