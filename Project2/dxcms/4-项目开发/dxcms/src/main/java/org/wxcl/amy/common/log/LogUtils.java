/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: LogUtils
 * Author:   wangxin
 * Date:     2018/7/31 17:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.wxcl.amy.common.log;

/**
 *
 *
 * @author wangxin
 * @create 2018/7/31
 * @since 1.0.0
 */
public class LogUtils {

    /**
     * 程序启动时捕捉 --log.app.name=aa 变量放到系统参数中，
     * 给logback配置文件使用
     * @param args
     */
    public static void initLogParams(String[] args){
        for (String arg:args) {
            if(arg.startsWith("--log.app.name=")){
                String[] strs =arg.split("=");
                System.setProperty("log.app.name",strs[1]);
            }
        }
    }
}
