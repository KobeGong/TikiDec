package com.buddy.tiki.model.im;

import com.buddy.tiki.model.user.User;
import org.parceler.Parcel;

@Parcel
public class CallReceiveMessage {
    boolean ack;
    String callId;
    long ctime;
    long expires;
    User friend;
    boolean loadoff;
    long noIces;
    int quality;
    boolean restful;
    String roomId;
    String turnSecKey;
    String turnUrl;
    String turnUser;
    int type;

    public boolean isLoadoff() {
        return this.loadoff;
    }

    public boolean isRestful() {
        return this.restful;
    }

    public long getNoIces() {
        return this.noIces;
    }

    public void setNoIces(long noIces) {
        this.noIces = noIces;
    }

    public int getQuality() {
        return this.quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getTurnUser() {
        return this.turnUser;
    }

    public void setTurnUser(String turnUser) {
        this.turnUser = turnUser;
    }

    public String getTurnUrl() {
        return this.turnUrl;
    }

    public void setTurnUrl(String turnUrl) {
        this.turnUrl = turnUrl;
    }

    public String getTurnSecKey() {
        return this.turnSecKey;
    }

    public void setTurnSecKey(String turnSecKey) {
        this.turnSecKey = turnSecKey;
    }

    public boolean getRestful() {
        return this.restful;
    }

    public void setRestful(boolean restful) {
        this.restful = restful;
    }

    public boolean getLoadoff() {
        return this.loadoff;
    }

    public void setLoadoff(boolean loadoff) {
        this.loadoff = loadoff;
    }

    public long getExpires() {
        return this.expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public String getRoomId() {
        return this.roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public User getFriend() {
        return this.friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public boolean isAck() {
        return this.ack;
    }

    public void setAck(boolean ack) {
        this.ack = ack;
    }

    public String getCallId() {
        return this.callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
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
