package com.buddy.tiki.model.msg;

public class ChatMsg {
    boolean ack;
    long ctime;
    String id;
    int type;

    public boolean isAck() {
        return this.ack;
    }

    public void setAck(boolean ack) {
        this.ack = ack;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getCtime() {
        return this.ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }
}
