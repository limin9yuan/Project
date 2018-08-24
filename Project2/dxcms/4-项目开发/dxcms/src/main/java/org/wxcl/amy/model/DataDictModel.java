package org.wxcl.amy.model;

/*
 * @author cxd
 * @create 2018/8/22
 * @since 1.0.0
 * 数据字典
 */

import java.util.Date;
import java.util.UUID;

public class DataDictModel {
    //编号
    private UUID id;
    //标签名
    private String name;
    //数据值
    private String value;
    //类型
    private String type;
    //描述
    private String description;
    //排序（升序）
    private Double sort;
    //父级编号
    private Long parent_id;
    //创建者
    private Long create_by;
    //创建时间
    private Date create_date;
    //更新者
    private Long update_by;
    //更新时间
    private Date update_date;
    //备注信息
    private String remarks;
    //删除标记
    private Boolean del_flag;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSort() {
        return sort;
    }

    public void setSort(Double sort) {
        this.sort = sort;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public Long getCreate_by() {
        return create_by;
    }

    public void setCreate_by(Long create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Long getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(Long update_by) {
        this.update_by = update_by;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Boolean del_flag) {
        this.del_flag = del_flag;
    }
}
