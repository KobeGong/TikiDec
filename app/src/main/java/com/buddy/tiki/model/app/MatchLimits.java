package com.buddy.tiki.model.app;

public class MatchLimits {
    private String announceText;
    private String announceUrl;
    private long countdown;
    private boolean open;
    private String text;
    private String text2;

    public String getAnnounceText() {
        return this.announceText;
    }

    public void setAnnounceText(String announceText) {
        this.announceText = announceText;
    }

    public String getAnnounceUrl() {
        return this.announceUrl;
    }

    public void setAnnounceUrl(String announceUrl) {
        this.announceUrl = announceUrl;
    }

    public String getText2() {
        return this.text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public long getCountdown() {
        return this.countdown;
    }

    public void setCountdown(long countdown) {
        this.countdown = countdown;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
