package com.bootdo.ireport.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-07-26 10:04:49
 */
public class IreportDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer ireportid;

	/**
	 * 设置：
	 */
	public void setIreportid(Integer ireportid) {
		this.ireportid = ireportid;
	}
	/**
	 * 获取：
	 */
	public Integer getIreportid() {
		return ireportid;
	}
}
