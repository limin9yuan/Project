package com.dx.server.dcservice.bll;

import com.dx.client.model.datacenter.MaterialBean;
/**
 * @Auther: DX01
 * @Date: 2018/8/29 09:04
 * @Description:
 */
public interface IMaterialBll {
    public int save(MaterialBean materialBean) throws Exception;
}
