package com.buddy.tiki.model.app;

public class VersionInfo {
    private String curVersion;
    private String downloadUrl;
    private boolean force;
    private String updateDesc;

    public String getCurVersion() {
        return this.curVersion;
    }

    public void setCurVersion(String curVersion) {
        this.curVersion = curVersion;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public boolean isForce() {
        return this.force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public String getUpdateDesc() {
        return this.updateDesc;
    }

    public void setUpdateDesc(String updateDesc) {
        this.updateDesc = updateDesc;
    }
}
