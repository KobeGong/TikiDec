package com.buddy.tiki.model.user;

import android.text.TextUtils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import org.parceler.Parcel;

@Parcel
public class User {
    int applys;
    String area;
    String areaName;
    String areaflag;
    String avatar;
    String blockText;
    String blockUrl;
    boolean blocked;
    long diamonds;
    int friends;
    int gender;
    String inviteUrl;
    String mark;
    int messages;
    boolean needReport;
    String nick;
    boolean oper;
    int relation;
    boolean resetGender;
    long tid;
    long tikis;
    long trice;
    boolean uber;
    String uid;
    boolean vipSend;
    String weiboUrl;
    boolean working;

    public User(TikiAdministrator administrator) {
        this.avatar = administrator.getAvatar();
        this.gender = administrator.getGender();
        this.uber = administrator.isUber();
        this.vipSend = administrator.isVipSend();
        this.nick = administrator.getNick();
        this.tid = administrator.getTid();
        this.uid = administrator.getUid();
    }

    public boolean isUber() {
        return this.uber;
    }

    public void setUber(boolean uber) {
        this.uber = uber;
    }

    public long getTrice() {
        return this.trice;
    }

    public void setTrice(long trice) {
        this.trice = trice;
    }

    public String getAreaName() {
        return this.areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaflag() {
        return this.areaflag;
    }

    public void setAreaflag(String areaflag) {
        this.areaflag = areaflag;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isResetGender() {
        return this.resetGender;
    }

    public void setResetGender(boolean resetGender) {
        this.resetGender = resetGender;
    }

    public boolean isNeedReport() {
        return this.needReport;
    }

    public void setNeedReport(boolean needReport) {
        this.needReport = needReport;
    }

    public int getMessages() {
        return this.messages;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }

    public long getDiamonds() {
        return this.diamonds;
    }

    public void setDiamonds(long diamonds) {
        this.diamonds = diamonds;
    }

    public long getTikis() {
        return this.tikis;
    }

    public void setTikis(long tikis) {
        this.tikis = tikis;
    }

    public int getApplys() {
        return this.applys;
    }

    public void setApplys(int applys) {
        this.applys = applys;
    }

    public int getFriends() {
        return this.friends;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }

    public String getWeiboUrl() {
        return this.weiboUrl;
    }

    public void setWeiboUrl(String weiboUrl) {
        this.weiboUrl = weiboUrl;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getNick() {
        if (TextUtils.isEmpty(this.nick)) {
            return ChatApp.getInstance().getString(C0376R.string.anonymous);
        }
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getRelation() {
        return this.relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMark() {
        if (TextUtils.isEmpty(this.mark)) {
            return getNick();
        }
        return this.mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public long getTid() {
        return this.tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public String getInviteUrl() {
        return this.inviteUrl;
    }

    public void setInviteUrl(String inviteUrl) {
        this.inviteUrl = inviteUrl;
    }

    public boolean isVipSend() {
        return this.vipSend;
    }

    public void setVipSend(boolean vipSend) {
        this.vipSend = vipSend;
    }

    public String getBlockText() {
        return this.blockText;
    }

    public void setBlockText(String blockText) {
        this.blockText = blockText;
    }

    public String getBlockUrl() {
        return this.blockUrl;
    }

    public void setBlockUrl(String blockUrl) {
        this.blockUrl = blockUrl;
    }

    public boolean isBlocked() {
        return this.blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isOper() {
        return this.oper;
    }

    public void setOper(boolean oper) {
        this.oper = oper;
    }

    public boolean isWorking() {
        return this.working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }
}
