package com.buddy.tiki.model.app;

public class OperInfo {
    private boolean avatar3dOff;
    private boolean beautyOff;
    private String[] complaintContents;
    private boolean faceDectOff;
    private int genderRate;
    private IconHint iconHint;
    private String[] rateOptions;
    private boolean showFaceunityDownload;
    private String[] switchContents;
    private String[] switchImages;
    private boolean videoRecOff;

    public boolean isFaceDectOff() {
        return this.faceDectOff;
    }

    public void setFaceDectOff(boolean faceDectOff) {
        this.faceDectOff = faceDectOff;
    }

    public boolean isAvatar3dOff() {
        return this.avatar3dOff;
    }

    public void setAvatar3dOff(boolean avatar3dOff) {
        this.avatar3dOff = avatar3dOff;
    }

    public boolean isBeautyOff() {
        return this.beautyOff;
    }

    public void setBeautyOff(boolean beautyOff) {
        this.beautyOff = beautyOff;
    }

    public boolean isVideoRecOff() {
        return this.videoRecOff;
    }

    public void setVideoRecOff(boolean videoRecOff) {
        this.videoRecOff = videoRecOff;
    }

    public boolean isShowFaceunityDownload() {
        return this.showFaceunityDownload;
    }

    public void setShowFaceunityDownload(boolean showFaceunityDownload) {
        this.showFaceunityDownload = showFaceunityDownload;
    }

    public int getGenderRate() {
        return this.genderRate;
    }

    public void setGenderRate(int genderRate) {
        this.genderRate = genderRate;
    }

    public String[] getRateOptions() {
        return this.rateOptions;
    }

    public void setRateOptions(String[] rateOptions) {
        this.rateOptions = rateOptions;
    }

    public String[] getComplaintContents() {
        return this.complaintContents;
    }

    public void setComplaintContents(String[] complaintContents) {
        this.complaintContents = complaintContents;
    }

    public IconHint getIconHint() {
        return this.iconHint;
    }

    public void setIconHint(IconHint iconHint) {
        this.iconHint = iconHint;
    }

    public String[] getSwitchContents() {
        return this.switchContents;
    }

    public void setSwitchContents(String[] switchContents) {
        this.switchContents = switchContents;
    }

    public String[] getSwitchImages() {
        return this.switchImages;
    }

    public void setSwitchImages(String[] switchImages) {
        this.switchImages = switchImages;
    }
}
