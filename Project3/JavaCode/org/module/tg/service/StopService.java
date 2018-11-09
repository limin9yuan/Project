package org.module.tg.service;

import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

/**
 * Created by Mingyuan Li on 2018/11/5.
 * Package name: org.module.tg.service.
 * Project name: extgr.
 */
public interface StopService extends BaseService {
    /**
     * 保存停供
     * @param pDto
     * @return
     */
    public Dto saveStop(Dto pDto);

    /**
     * update停供
     * @param pDto
     * @return
     */
    public Dto updateStop(Dto pDto);

    /**
     * 恢复供热
     * @param pDto
     * @return
     */
    public Dto startHeat(Dto pDto);

    /**
     * 删除停供
     * @param pDto
     * @return
     */
    public Dto deleteStop(Dto pDto);
}
