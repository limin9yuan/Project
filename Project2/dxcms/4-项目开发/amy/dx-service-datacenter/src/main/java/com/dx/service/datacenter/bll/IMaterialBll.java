package com.dx.service.datacenter.bll;

import com.dx.client.model.datacenter.MaterialBean;

/**
 * @Auther: DX01
 * @Date: 2018/8/31 09:09
 * @Description:
 */
public interface IMaterialBll {
    public int save(MaterialBean materialBean) throws Exception;
}
