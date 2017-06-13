package com.buddy.tiki.model.user;

import io.realm.RealmObject;
import io.realm.TikiAdministratorRealmProxyInterface;

public class TikiAdministrator extends RealmObject implements TikiAdministratorRealmProxyInterface {
    String avatar;
    int gender;
    boolean isNew;
    String nick;
    String session;
    private long tid;
    boolean uber;
    String uid;
    private boolean vipSend;

    public String realmGet$avatar() {
        return this.avatar;
    }

    public int realmGet$gender() {
        return this.gender;
    }

    public boolean realmGet$isNew() {
        return this.isNew;
    }

    public String realmGet$nick() {
        return this.nick;
    }

    public String realmGet$session() {
        return this.session;
    }

    public long realmGet$tid() {
        return this.tid;
    }

    public boolean realmGet$uber() {
        return this.uber;
    }

    public String realmGet$uid() {
        return this.uid;
    }

    public boolean realmGet$vipSend() {
        return this.vipSend;
    }

    public void realmSet$avatar(String str) {
        this.avatar = str;
    }

    public void realmSet$gender(int i) {
        this.gender = i;
    }

    public void realmSet$isNew(boolean z) {
        this.isNew = z;
    }

    public void realmSet$nick(String str) {
        this.nick = str;
    }

    public void realmSet$session(String str) {
        this.session = str;
    }

    public void realmSet$tid(long j) {
        this.tid = j;
    }

    public void realmSet$uber(boolean z) {
        this.uber = z;
    }

    public void realmSet$uid(String str) {
        this.uid = str;
    }

    public void realmSet$vipSend(boolean z) {
        this.vipSend = z;
    }

    public String getNick() {
        return realmGet$nick();
    }

    public void setNick(String nick) {
        realmSet$nick(nick);
    }

    public int getGender() {
        return realmGet$gender();
    }

    public void setGender(int gender) {
        realmSet$gender(gender);
    }

    public String getUid() {
        return realmGet$uid();
    }

    public void setUid(String uid) {
        realmSet$uid(uid);
    }

    public String getAvatar() {
        return realmGet$avatar();
    }

    public void setAvatar(String avatar) {
        realmSet$avatar(avatar);
    }

    public String getSession() {
        return realmGet$session();
    }

    public void setSession(String session) {
        realmSet$session(session);
    }

    public boolean isNew() {
        return realmGet$isNew();
    }

    public void setNew(boolean aNew) {
        realmSet$isNew(aNew);
    }

    public long getTid() {
        return realmGet$tid();
    }

    public void setTid(long tid) {
        realmSet$tid(tid);
    }

    public boolean isVipSend() {
        return realmGet$vipSend();
    }

    public void setVipSend(boolean vipSend) {
        realmSet$vipSend(vipSend);
    }

    public boolean isUber() {
        return realmGet$uber();
    }

    public void setUber(boolean uber) {
        realmSet$uber(uber);
    }
}
