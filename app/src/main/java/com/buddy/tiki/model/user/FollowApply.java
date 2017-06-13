package com.buddy.tiki.model.user;

public class FollowApply {
    private String applyId;
    private long ctime;
    private long expired;
    private User user;

    public String getApplyId() {
        return this.applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public long getCtime() {
        return this.ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public long getExpired() {
        return this.expired;
    }

    public void setExpired(long expired) {
        this.expired = expired;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
