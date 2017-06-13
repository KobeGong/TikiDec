package com.buddy.tiki.model.msg;

import java.util.Map;

public class ApnsMsg {
    private int badges;
    private String content;
    private Map<String, String> params;
    private String sound;
    private String title;
    private int type;

    public ApnsMsg(int badges, String content, Map<String, String> params) {
        this.badges = badges;
        this.content = content;
        this.params = params;
    }

    public String getSound() {
        return this.sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBadges() {
        return this.badges;
    }

    public void setBadges(int badges) {
        this.badges = badges;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getParams() {
        return this.params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
