package org.wxcl.amy.utils.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/8/30 11:41
 * @Description:
 */
public class ResultUtil {
    public static final String CODE_SUCCESS="1";
    public static final String CODE_FAIL="0";

    public static final String MSG_SUCCESS="操作成功";
    public static final String MSG_FAIL="操作失败";

    public static ResultMsg success(){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(CODE_SUCCESS);
        resultMsg.setMsg(MSG_SUCCESS);
        resultMsg.setDetail("");
        return resultMsg;
    }

    public static ResultMsg fail(){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(CODE_FAIL);
        resultMsg.setMsg(MSG_FAIL);
        resultMsg.setDetail("");
        return resultMsg;
    }

    public static ResultMsg success(Object data){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(CODE_SUCCESS);
        resultMsg.setMsg(MSG_SUCCESS);
        resultMsg.setDetail("");
        resultMsg.setData(data);
        return resultMsg;
    }

    public static ResultMsg fail(Object data){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(CODE_FAIL);
        resultMsg.setMsg(MSG_FAIL);
        resultMsg.setDetail("");
        resultMsg.setData(data);
        return resultMsg;
    }

    public static ResultMsg success(String detail){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(CODE_SUCCESS);
        resultMsg.setMsg(MSG_SUCCESS);
        resultMsg.setDetail(detail);
        return resultMsg;
    }

    public static ResultMsg fail(String detail){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(CODE_FAIL);
        resultMsg.setMsg(MSG_FAIL);
        resultMsg.setDetail(detail);
        return resultMsg;
    }

    public static ResultMsg success(String detail,Object data){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(CODE_SUCCESS);
        resultMsg.setMsg(MSG_SUCCESS);
        resultMsg.setDetail(detail);
        resultMsg.setData(data);
        return resultMsg;
    }

    public static ResultMsg fail(String detail,Object data){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(CODE_FAIL);
        resultMsg.setMsg(MSG_FAIL);
        resultMsg.setDetail(detail);
        resultMsg.setData(data);
        return resultMsg;
    }

    public static ResultMsg fail(String detail,Exception exception){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(CODE_FAIL);
        resultMsg.setMsg(MSG_FAIL);
        resultMsg.setDetail(detail+","+exception.getMessage());
        return resultMsg;
    }

    public static ResultMsg fail(Exception exception){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(CODE_FAIL);
        resultMsg.setMsg(MSG_FAIL);
        resultMsg.setDetail(exception.getMessage());
        return resultMsg;
    }

    public static ResultMsg fail(String detail,Object data,Exception exception){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(CODE_FAIL);
        resultMsg.setMsg(MSG_FAIL);
        resultMsg.setDetail(detail+","+exception.getMessage());
        resultMsg.setData(data);
        return resultMsg;
    }

    public static ResultMsg fail(Object data,Exception exception){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(CODE_FAIL);
        resultMsg.setMsg(MSG_FAIL);
        resultMsg.setDetail(exception.getMessage());
        resultMsg.setData(data);
        return resultMsg;
    }

    public static Object validate(Boolean result){
        Map<String, Boolean> map = new HashMap<>();
        map.put("valid", result);
        return map;
    }


}
