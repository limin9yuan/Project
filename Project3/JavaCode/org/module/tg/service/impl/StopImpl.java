package org.module.tg.service.impl;

import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.module.tg.service.StopService;

/**
 * Created by Mingyuan Li on 2018/11/5.
 * Package name: org.module.tg.service.impl.
 * Project name: extgr.
 */
public class StopImpl  extends BaseServiceImpl implements StopService {
    @Override
    public Dto saveStop(Dto inDto) {
        // TODO 自动生成方法存根
        Dto outDto = new BaseDto();

        g4Dao.insert("saveStop", inDto);
        outDto.put("success", new Boolean(true));
        outDto.put("msg", "停供添加成功!");
        return outDto;
    }

    @Override
    public Dto updateStop(Dto pDto) {
        // TODO 自动生成方法存根
        Dto outDto = new BaseDto();

        g4Dao.update("updateStop", pDto);
        outDto.put("success", new Boolean(true));
        outDto.put("msg", "停供更新添加成功!");
        return outDto;
    }

    @Override
    public Dto startHeat(Dto pDto) {
        // TODO 自动生成方法存根
        Dto outDto = new BaseDto();

        g4Dao.update("startHeat", pDto);
        outDto.put("success", new Boolean(true));
        outDto.put("msg", "恢复供热成功!");
        return outDto;
    }

    @Override
    public Dto deleteStop(Dto inDto) {
        // TODO 自动生成方法存根
        Dto outDto = new BaseDto();

        g4Dao.delete("deleteStop", inDto);
        outDto.put("success", new Boolean(true));
        outDto.put("msg", "恢复供热成功!");
        return outDto;
    }
}
