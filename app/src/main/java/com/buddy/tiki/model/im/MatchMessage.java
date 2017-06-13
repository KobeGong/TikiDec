package com.buddy.tiki.model.im;

import com.buddy.tiki.model.user.User;

@Deprecated
public class MatchMessage {
    private User matchUser;
    private String roomId;
    private String session;

    public User getMatchUser() {
        return this.matchUser;
    }

    public void setMatchUser(User matchUser) {
        this.matchUser = matchUser;
    }

    public String getRoomId() {
        return this.roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getSession() {
        return this.session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
