package com.buddy.tiki.model.im;

import java.io.Serializable;

public class VideoStatics implements Serializable {
    int callType;
    float cpu;
    long downspeed;
    float electric;
    long endTime;
    boolean finish;
    int linkType;
    boolean meHangup;
    String passport;
    float receiveloss;
    long remindBandwidth;
    int reportStatus;
    String roomId;
    float sendloss;
    long startTime;
    String touid;
    long upspeed;

    public VideoStatics(VideoStatics videoStatics) {
        if (videoStatics != null) {
            init(videoStatics.getRoomId(), videoStatics.getTouid(), videoStatics.getStartTime(), videoStatics.getEndTime(), videoStatics.getUpspeed(), videoStatics.getDownspeed(), videoStatics.getRemindBandwidth(), videoStatics.getCallType(), videoStatics.isFinish(), videoStatics.getLinkType(), videoStatics.getSendloss(), videoStatics.getReceiveloss(), videoStatics.getElectric(), videoStatics.getCpu(), videoStatics.getPassport(), videoStatics.isMeHangup());
        }
    }

    public VideoStatics(String roomId, String touid, long startTime, long endTime, long upspeed, long downspeed, long remindBandwidth, int callType, boolean finish, int linkType, float sendloss, float receiveloss, float electric, float cpu, String passport, boolean meHangup) {
        init(roomId, touid, startTime, endTime, upspeed, downspeed, remindBandwidth, callType, finish, linkType, sendloss, receiveloss, electric, cpu, passport, meHangup);
    }

    private void init(String roomId, String touid, long startTime, long endTime, long upspeed, long downspeed, long remindBandwidth, int callType, boolean finish, int linkType, float sendloss, float receiveloss, float electric, float cpu, String passport, boolean meHangup) {
        this.roomId = roomId;
        this.touid = touid;
        this.startTime = startTime;
        this.endTime = endTime;
        this.upspeed = upspeed;
        this.downspeed = downspeed;
        this.remindBandwidth = remindBandwidth;
        this.callType = callType;
        this.finish = finish;
        this.linkType = linkType;
        this.sendloss = sendloss;
        this.receiveloss = receiveloss;
        this.electric = electric;
        this.cpu = cpu;
        this.reportStatus = 0;
        this.passport = passport;
        this.meHangup = meHangup;
    }

    public String getPassport() {
        return this.passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public boolean isMeHangup() {
        return this.meHangup;
    }

    public void setMeHangup(boolean meHangup) {
        this.meHangup = meHangup;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getReportStatus() {
        return this.reportStatus;
    }

    public void setReportStatus(int reportStatus) {
        this.reportStatus = reportStatus;
    }

    public float getElectric() {
        return this.electric;
    }

    public void setElectric(float electric) {
        this.electric = electric;
    }

    public float getCpu() {
        return this.cpu;
    }

    public void setCpu(float cpu) {
        this.cpu = cpu;
    }

    public String getRoomId() {
        return this.roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTouid() {
        return this.touid;
    }

    public void setTouid(String touid) {
        this.touid = touid;
    }

    public long getUpspeed() {
        return this.upspeed;
    }

    public void setUpspeed(long upspeed) {
        this.upspeed = upspeed;
    }

    public long getDownspeed() {
        return this.downspeed;
    }

    public void setDownspeed(long downspeed) {
        this.downspeed = downspeed;
    }

    public long getRemindBandwidth() {
        return this.remindBandwidth;
    }

    public void setRemindBandwidth(long remindBandwidth) {
        this.remindBandwidth = remindBandwidth;
    }

    public int getCallType() {
        return this.callType;
    }

    public void setCallType(int callType) {
        this.callType = callType;
    }

    public boolean isFinish() {
        return this.finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public int getLinkType() {
        return this.linkType;
    }

    public void setLinkType(int linkType) {
        this.linkType = linkType;
    }

    public float getSendloss() {
        return this.sendloss;
    }

    public void setSendloss(float sendloss) {
        this.sendloss = sendloss;
    }

    public float getReceiveloss() {
        return this.receiveloss;
    }

    public void setReceiveloss(float receiveloss) {
        this.receiveloss = receiveloss;
    }
}
