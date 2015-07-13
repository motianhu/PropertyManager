package com.smona.app.propertymanager.data.bean;

public class PropertyWrapDataBean<T> {
    private boolean success;
    private String msg;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WrapDataBean [success=" + success + ", msg=" + msg + ", data="
                + data + "]";
    }
}
