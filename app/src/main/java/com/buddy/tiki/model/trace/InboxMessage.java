package com.buddy.tiki.model.trace;

public class InboxMessage {
    private int amount;
    private String content;
    private long ctime;
    private int diamonds;
    private int type;
    private String url;
    private String urlMark;

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCtime() {
        return this.ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public int getDiamonds() {
        return this.diamonds;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlMark() {
        return this.urlMark;
    }

    public void setUrlMark(String urlMark) {
        this.urlMark = urlMark;
    }
}
