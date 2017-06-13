package com.buddy.tiki.model.base;

public class HttpResult<T> {
    private int code;
    private String msg;
    private T result;

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

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("code=" + this.code + " msg=" + this.msg);
        if (this.result != null) {
            sb.append(this.result.toString());
        }
        return sb.toString();
    }
}
