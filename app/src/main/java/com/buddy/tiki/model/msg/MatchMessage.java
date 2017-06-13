package com.buddy.tiki.model.msg;

import android.support.v7.recyclerview.BuildConfig;
import android.text.TextUtils;
import com.buddy.tiki.model.user.User;

public class MatchMessage {
    private boolean blured;
    private boolean clockMode;
    private long clockPrice;
    private boolean connected;
    private long matchTime;
    private User matchUser;
    private String roomId;
    private String session;
    private boolean uber;

    public boolean isBlured() {
        return this.blured;
    }

    public void setBlured(boolean blured) {
        this.blured = blured;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

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

    public boolean isClockMode() {
        return this.clockMode;
    }

    public void setClockMode(boolean clockMode) {
        this.clockMode = clockMode;
    }

    public boolean isUber() {
        return this.uber;
    }

    public void setUber(boolean uber) {
        this.uber = uber;
    }

    public boolean isParseValid() {
        return (TextUtils.isEmpty(this.roomId) || this.matchUser == null) ? false : true;
    }

    public long getClockPrice() {
        return this.clockPrice;
    }

    public void setClockPrice(long clockPrice) {
        this.clockPrice = clockPrice;
    }

    public long getMatchTime() {
        return this.matchTime;
    }

    public void setMatchTime(long matchTime) {
        this.matchTime = matchTime;
    }

    public String toString() {
        return BuildConfig.VERSION_NAME;
    }
}
