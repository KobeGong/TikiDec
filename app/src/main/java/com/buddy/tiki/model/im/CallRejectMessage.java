package com.buddy.tiki.model.im;

import com.buddy.tiki.model.msg.ChatMsg;

public class CallRejectMessage extends ChatMsg {
    private String callId;

    public String getCallId() {
        return this.callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }
}
