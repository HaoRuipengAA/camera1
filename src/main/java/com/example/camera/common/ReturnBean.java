package com.example.camera.common;

public class ReturnBean {
    /**
     * 是否成功
     */
    private boolean success = true;
    /**
     * 返回 code
     */
    private String code = "0000";
    /**
     * 提示信息
     */
    private String msg = "操作成功";
    /**
     * 返回数据
     */
    private Object data;

    /**
     * 失败结果
     *
     * @param code 异常code
     * @param msg  提示信息
     */
    public void failure(String code, String msg) {
        this.code = code;
        this.success = false;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ReturnBean{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
