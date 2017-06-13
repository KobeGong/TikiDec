package com.buddy.tiki.model.user;

import android.text.TextUtils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import io.realm.RealmObject;
import io.realm.TikiUserRealmProxyInterface;
import org.parceler.Parcel;
import org.parceler.Parcel.Serialization;

@Parcel(analyze = {TikiUser.class}, value = Serialization.BEAN)
public class TikiUser extends RealmObject implements TikiUserRealmProxyInterface {
    private String FirstPinYin;
    private String PinYin;
    private String avatar;
    private int gender;
    private String lastMessage;
    private long lastMessageTime;
    private String mark;
    private String nick;
    private boolean oper;
    private int relation;
    private boolean showPinyin;
    private long tid;
    private String uid;
    private int unread;

    public String realmGet$avatar() {
        return this.avatar;
    }

    public int realmGet$gender() {
        return this.gender;
    }

    public String realmGet$lastMessage() {
        return this.lastMessage;
    }

    public long realmGet$lastMessageTime() {
        return this.lastMessageTime;
    }

    public String realmGet$mark() {
        return this.mark;
    }

    public String realmGet$nick() {
        return this.nick;
    }

    public boolean realmGet$oper() {
        return this.oper;
    }

    public int realmGet$relation() {
        return this.relation;
    }

    public long realmGet$tid() {
        return this.tid;
    }

    public String realmGet$uid() {
        return this.uid;
    }

    public int realmGet$unread() {
        return this.unread;
    }

    public void realmSet$avatar(String str) {
        this.avatar = str;
    }

    public void realmSet$gender(int i) {
        this.gender = i;
    }

    public void realmSet$lastMessage(String str) {
        this.lastMessage = str;
    }

    public void realmSet$lastMessageTime(long j) {
        this.lastMessageTime = j;
    }

    public void realmSet$mark(String str) {
        this.mark = str;
    }

    public void realmSet$nick(String str) {
        this.nick = str;
    }

    public void realmSet$oper(boolean z) {
        this.oper = z;
    }

    public void realmSet$relation(int i) {
        this.relation = i;
    }

    public void realmSet$tid(long j) {
        this.tid = j;
    }

    public void realmSet$uid(String str) {
        this.uid = str;
    }

    public void realmSet$unread(int i) {
        this.unread = i;
    }

    public boolean isOper() {
        return realmGet$oper();
    }

    public void setOper(boolean oper) {
        realmSet$oper(oper);
    }

    public boolean isShowPinyin() {
        return this.showPinyin;
    }

    public void setShowPinyin(boolean showPinyin) {
        this.showPinyin = showPinyin;
    }

    public String getPinYin() {
        return this.PinYin;
    }

    public void setPinYin(String pinYin) {
        this.PinYin = pinYin;
    }

    public String getFirstPinYin() {
        return this.FirstPinYin;
    }

    public void setFirstPinYin(String firstPinYin) {
        this.FirstPinYin = firstPinYin;
    }

    public long getTid() {
        return realmGet$tid();
    }

    public void setTid(long tid) {
        realmSet$tid(tid);
    }

    public String getAvatar() {
        return realmGet$avatar();
    }

    public void setAvatar(String avatar) {
        realmSet$avatar(avatar);
    }

    public int getGender() {
        return realmGet$gender();
    }

    public void setGender(int gender) {
        realmSet$gender(gender);
    }

    public String getNick() {
        if (TextUtils.isEmpty(realmGet$nick())) {
            return ChatApp.getInstance().getString(C0376R.string.anonymous);
        }
        return realmGet$nick();
    }

    public void setNick(String nick) {
        realmSet$nick(nick);
    }

    public int getRelation() {
        return realmGet$relation();
    }

    public void setRelation(int relation) {
        realmSet$relation(relation);
    }

    public String getUid() {
        return realmGet$uid();
    }

    public void setUid(String uid) {
        realmSet$uid(uid);
    }

    public String getLastMessage() {
        return realmGet$lastMessage();
    }

    public void setLastMessage(String lastMessage) {
        realmSet$lastMessage(lastMessage);
    }

    public String getMark() {
        if (TextUtils.isEmpty(realmGet$mark())) {
            return getNick();
        }
        return realmGet$mark();
    }

    public void setMark(String mark) {
        realmSet$mark(mark);
    }

    public int getUnread() {
        return realmGet$unread();
    }

    public void setUnread(int unread) {
        realmSet$unread(unread);
    }

    public long getLastMessageTime() {
        return realmGet$lastMessageTime();
    }

    public void setLastMessageTime(long lastMessageTime) {
        realmSet$lastMessageTime(lastMessageTime);
    }
}
