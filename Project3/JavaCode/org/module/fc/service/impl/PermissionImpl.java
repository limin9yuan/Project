package org.module.fc.service.impl;

import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.module.fc.service.PermissionService;

/**
 * Created by Mingyuan Li on 2018/11/27.
 * Package name: org.module.fc.service.impl.
 * Project name: extgr.
 */
public class PermissionImpl extends BaseServiceImpl implements PermissionService {

    @Override
    public Dto savePermission(Dto inDto) {
        // TODO 自动生成方法存根
        Dto outDto = new BaseDto();

        g4Dao.insert("savePermission", inDto);
        outDto.put("success", new Boolean(true));
        outDto.put("msg", "权限添加成功!");
        return outDto;
    }

    @Override
    public Dto deletePermission(Dto inDto) {
        // TODO 自动生成方法存根
        Dto outDto = new BaseDto();

        g4Dao.delete("deletePermission", inDto);
        outDto.put("success", new Boolean(true));
        outDto.put("msg", "恢复供热成功!");
        return outDto;
    }
}
