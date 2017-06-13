package com.buddy.tiki.model.resource;

public class Gift extends SystemResource {
    private int delayeds;
    private String icon;
    private int limit;
    private String music;
    private int tikis;

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getDelayeds() {
        return this.delayeds;
    }

    public void setDelayeds(int delayeds) {
        this.delayeds = delayeds;
    }

    public int getTikis() {
        return this.tikis;
    }

    public void setTikis(int tikis) {
        this.tikis = tikis;
    }

    public String getMusic() {
        return this.music;
    }

    public void setMusic(String music) {
        this.music = music;
    }
}
