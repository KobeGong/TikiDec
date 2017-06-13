package com.buddy.tiki.protocol.im;

import android.support.annotation.Nullable;
import com.buddy.tiki.model.msg.MatchMessage;
import com.buddy.tiki.model.resource.Gift;

public interface FacechatIMEvents {
    void onApplyFriendRequest(String str, String str2);

    void onBalanceMsg();

    void onError(int i, String str);

    void onGiftReceived(Gift gift);

    void onJoinRoom(String str, String str2);

    void onLeaveRoom(String str, String str2);

    void onMatch(MatchMessage matchMessage);

    void onReceivePornMsg(boolean z);

    void onRoomMessage(String str, String str2);

    void onRoomSession(String str, String str2);

    void onStateChange(int i, @Nullable String str);

    void onWebSocketState(int i);
}
