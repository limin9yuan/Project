package org.wxcl.amy.util;

import java.util.HashMap;
import java.util.Map;

public class ResultUtil {

    public static final String CODE_SUCCESS="1";
    public static final String CODE_FAIL="0";

    public static final String MSG_SUCCESS="操作成功";
    public static final String MSG_FAIL="操作失败";

    public static Object success(){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(CODE_SUCCESS);
        resultObj.setMsg(MSG_SUCCESS);
        resultObj.setDetail("");
        return resultObj;
    }

    public static Object fail(){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(CODE_FAIL);
        resultObj.setMsg(MSG_FAIL);
        resultObj.setDetail("");
        return resultObj;
    }

    public static Object success(Object data){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(CODE_SUCCESS);
        resultObj.setMsg(MSG_SUCCESS);
        resultObj.setDetail("");
        resultObj.setData(data);
        return resultObj;
    }

    public static Object fail(Object data){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(CODE_FAIL);
        resultObj.setMsg(MSG_FAIL);
        resultObj.setDetail("");
        resultObj.setData(data);
        return resultObj;
    }

    public static Object success(String detail){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(CODE_SUCCESS);
        resultObj.setMsg(MSG_SUCCESS);
        resultObj.setDetail(detail);
        return resultObj;
    }

    public static Object fail(String detail){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(CODE_FAIL);
        resultObj.setMsg(MSG_FAIL);
        resultObj.setDetail(detail);
        return resultObj;
    }

    public static Object success(String detail,Object data){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(CODE_SUCCESS);
        resultObj.setMsg(MSG_SUCCESS);
        resultObj.setDetail(detail);
        resultObj.setData(data);
        return resultObj;
    }

    public static Object fail(String detail,Object data){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(CODE_FAIL);
        resultObj.setMsg(MSG_FAIL);
        resultObj.setDetail(detail);
        resultObj.setData(data);
        return resultObj;
    }

    public static Object fail(String detail,Exception exception){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(CODE_FAIL);
        resultObj.setMsg(MSG_FAIL);
        resultObj.setDetail(detail+","+exception.getMessage());
        return resultObj;
    }

    public static Object fail(Exception exception){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(CODE_FAIL);
        resultObj.setMsg(MSG_FAIL);
        resultObj.setDetail(exception.getMessage());
        return resultObj;
    }

    public static Object fail(String detail,Object data,Exception exception){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(CODE_FAIL);
        resultObj.setMsg(MSG_FAIL);
        resultObj.setDetail(detail+","+exception.getMessage());
        resultObj.setData(data);
        return resultObj;
    }

    public static Object fail(Object data,Exception exception){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(CODE_FAIL);
        resultObj.setMsg(MSG_FAIL);
        resultObj.setDetail(exception.getMessage());
        resultObj.setData(data);
        return resultObj;
    }

    public static Object validate(Boolean result){
        Map<String, Boolean> map = new HashMap<>();
        map.put("valid", result);
        return map;
    }


}