package com.buddy.tiki.model.user;

public class FlwResponse {
    private Friend[] friends;
    private long maxTimestamp;

    public Friend[] getFriends() {
        return this.friends;
    }

    public void setFriends(Friend[] friends) {
        this.friends = friends;
    }

    public long getMaxTimestamp() {
        return this.maxTimestamp;
    }

    public void setMaxTimestamp(long maxTimestamp) {
        this.maxTimestamp = maxTimestamp;
    }
}
