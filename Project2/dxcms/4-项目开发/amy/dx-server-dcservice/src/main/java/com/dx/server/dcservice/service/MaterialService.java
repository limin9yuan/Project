package com.dx.server.dcservice.service;

import com.dx.client.model.datacenter.MaterialBean;
import com.dx.server.dcservice.bll.IMaterialBll;
import com.dx.server.dcservice.service.api.IMaterialService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: cxd
 * @Date: 2018/8/28
 * @Description:
 */
@RefreshScope
@RestController
@RequestMapping("/materialService")
public class MaterialService implements IMaterialService {
    private static final Log log = LogFactory.getLog(MaterialService.class);

    @Autowired
    private IMaterialBll materialBll;

    @RequestMapping("/save")
    public Object save(@RequestBody MaterialBean materialBean){

        return 0;
    }
}
