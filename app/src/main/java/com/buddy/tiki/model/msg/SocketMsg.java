package com.buddy.tiki.model.msg;

import java.util.List;

public class SocketMsg<T> {
    private int cmd;
    private double ctime;
    private T data;
    private int dataType;
    private String from;
    private int status;
    private List<String> tos;

    public int getCmd() {
        return this.cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public double getCtime() {
        return this.ctime;
    }

    public void setCtime(double ctime) {
        this.ctime = ctime;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getTos() {
        return this.tos;
    }

    public void setTos(List<String> tos) {
        this.tos = tos;
    }
}
