package com.bootdo.common.controller;

import com.bootdo.common.domain.MainCopyPersonDO;
import com.bootdo.common.service.MainCopyPersonService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Mingyuan Li on 2018/4/13.
 * Package name: com.bootdo.common.controller.
 * Project name: bootdo.
 */
@RequestMapping("/common/MainCopyPerson")
@Controller
public class MainCopyPersonController extends BaseController {

    @Autowired
    private MainCopyPersonService mainCopyPersonService;

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<MainCopyPersonDO> taskScheduleJobList = mainCopyPersonService.list(query);
        int total = mainCopyPersonService.count(query);
        PageUtils pageUtils = new PageUtils(taskScheduleJobList, total);
        return pageUtils;
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("contract:contract:add")
    public R save(MainCopyPersonDO mainCopyPerson) {
        mainCopyPerson.setOperator(getUserId());
        if (mainCopyPersonService.save(mainCopyPerson) > 0) {
            return R.ok();
        }
        return R.error();
    }
}
