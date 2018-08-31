package com.dx.server.dcservice.bll;

import com.dx.client.model.datacenter.MaterialBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: DX01
 * @Date: 2018/8/29 09:05
 * @Description:
 */
@Service("materialBll")
@Transactional(rollbackFor = Exception.class)
public class MaterialBllImpl implements IMaterialBll {
    @Override
    public int save(MaterialBean materialBean) {
        return 0;
    }
}
