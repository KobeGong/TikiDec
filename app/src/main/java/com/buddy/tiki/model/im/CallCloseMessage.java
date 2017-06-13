package com.buddy.tiki.model.im;

import com.buddy.tiki.model.msg.ChatMsg;
import com.buddy.tiki.model.user.User;

public class CallCloseMessage extends ChatMsg {
    private String callId;
    private User from;

    public String getCallId() {
        return this.callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }
}
