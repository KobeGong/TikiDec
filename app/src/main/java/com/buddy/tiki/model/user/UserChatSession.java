package com.buddy.tiki.model.user;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.UserChatSessionRealmProxyInterface;

public class UserChatSession extends RealmObject implements UserChatSessionRealmProxyInterface {
    private RealmList<UserChatMessage> messages;
    private String sessionId;
    private long timestamp;
    private int unread;

    public RealmList realmGet$messages() {
        return this.messages;
    }

    public String realmGet$sessionId() {
        return this.sessionId;
    }

    public long realmGet$timestamp() {
        return this.timestamp;
    }

    public int realmGet$unread() {
        return this.unread;
    }

    public void realmSet$messages(RealmList realmList) {
        this.messages = realmList;
    }

    public void realmSet$sessionId(String str) {
        this.sessionId = str;
    }

    public void realmSet$timestamp(long j) {
        this.timestamp = j;
    }

    public void realmSet$unread(int i) {
        this.unread = i;
    }

    public UserChatSession() {
        realmSet$timestamp(0);
    }

    public int getUnread() {
        return realmGet$unread();
    }

    public void setUnread(int unread) {
        realmSet$unread(unread);
    }

    public long getTimestamp() {
        return realmGet$timestamp();
    }

    public void setTimestamp(long timestamp) {
        realmSet$timestamp(timestamp);
    }

    public String getSessionId() {
        return realmGet$sessionId();
    }

    public void setSessionId(String sessionId) {
        realmSet$sessionId(sessionId);
    }

    public RealmList<UserChatMessage> getMessages() {
        return realmGet$messages();
    }

    public void setMessages(RealmList<UserChatMessage> messages) {
        realmSet$messages(messages);
    }
}
