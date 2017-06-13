package com.buddy.tiki.model.im;

import com.buddy.tiki.model.msg.ChatMsg;
import com.buddy.tiki.model.user.User;
import org.parceler.Parcel;

@Parcel
public class VideoMessage extends ChatMsg {
    String cover;
    User from;
    int tikiPrice;
    int timelen;
    String videoId;

    public String getCover() {
        return this.cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public User getFrom() {
        return this.from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public int getTimelen() {
        return this.timelen;
    }

    public void setTimelen(int timelen) {
        this.timelen = timelen;
    }

    public String getVideoId() {
        return this.videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public int getTikiPrice() {
        return this.tikiPrice;
    }

    public void setTikiPrice(int tikiPrice) {
        this.tikiPrice = tikiPrice;
    }
}
