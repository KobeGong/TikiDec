package com.buddy.tiki.model.user;

public class SyncFriends {
    private String[] deleteds;
    private Friend[] modifies;
    private long timepoint;

    public String[] getDeleteds() {
        return this.deleteds;
    }

    public void setDeleteds(String[] deleteds) {
        this.deleteds = deleteds;
    }

    public Friend[] getModifies() {
        return this.modifies;
    }

    public void setModifies(Friend[] modifies) {
        this.modifies = modifies;
    }

    public long getTimepoint() {
        return this.timepoint;
    }

    public void setTimepoint(long timepoint) {
        this.timepoint = timepoint;
    }
}
