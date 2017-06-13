package com.buddy.tiki.model.im;

import com.buddy.tiki.model.msg.ChatMsg;
import com.buddy.tiki.model.user.User;

public class CallRequestMessage extends ChatMsg {
    private String callId;
    private User caller;

    public String getCallId() {
        return this.callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public User getCaller() {
        return this.caller;
    }

    public void setCaller(User caller) {
        this.caller = caller;
    }
}
