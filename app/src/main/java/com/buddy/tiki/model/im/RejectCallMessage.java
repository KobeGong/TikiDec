package com.buddy.tiki.model.im;

import com.buddy.tiki.model.msg.ChatMsg;
import com.buddy.tiki.model.user.User;

public class RejectCallMessage extends ChatMsg {
    private String callId;
    private User from;
    private long rejectTime;

    public String getCallId() {
        return this.callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public long getRejectTime() {
        return this.rejectTime;
    }

    public void setRejectTime(long rejectTime) {
        this.rejectTime = rejectTime;
    }
}
