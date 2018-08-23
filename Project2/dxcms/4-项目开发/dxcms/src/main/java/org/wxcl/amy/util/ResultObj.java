package org.wxcl.amy.util;

/**
 *
 *
 * @author wangxin
 * @create 2018/8/19
 * @since 1.0.0
 */
public class ResultObj {

    private String code;
    private String msg;
    private String detail;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
