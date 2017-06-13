package com.buddy.tiki.model.user;

public class FlwApplyResponse {
    private FollowApply[] applys;
    private long maxTimestamp;

    public FollowApply[] getApplys() {
        return this.applys;
    }

    public void setApplys(FollowApply[] applys) {
        this.applys = applys;
    }

    public long getMaxTimestamp() {
        return this.maxTimestamp;
    }

    public void setMaxTimestamp(long maxTimestamp) {
        this.maxTimestamp = maxTimestamp;
    }
}
