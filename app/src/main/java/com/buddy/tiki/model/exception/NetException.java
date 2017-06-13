package com.buddy.tiki.model.exception;

import android.text.TextUtils;

public class NetException extends RuntimeException {
    private int code;
    private String msg;

    public NetException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String toString() {
        return TextUtils.isEmpty(this.msg) ? "code=" + this.code : this.msg + " code = " + this.code;
    }
}
