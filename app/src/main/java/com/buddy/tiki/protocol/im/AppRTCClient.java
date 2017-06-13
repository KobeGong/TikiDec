package com.buddy.tiki.protocol.im;

import android.support.v7.recyclerview.BuildConfig;
import com.buddy.tiki.model.resource.Border;
import com.buddy.tiki.model.resource.Gift;
import com.buddy.tiki.model.user.User;
import org.webrtc.IceCandidate;
import org.webrtc.PeerConnection.IceServer;
import org.webrtc.SessionDescription;

public interface AppRTCClient {

    public interface SignalingEvents {
        void onAcceptFriendRequest(String str, String str2);

        void onApplyFriendRequest(String str, String str2);

        void onBorderReceived(Border border);

        void onChannelClose(String str);

        void onChannelError(String str);

        void onFriendAcceptCall(String str, User user, String str2, String str3, String str4, String str5, int i);

        void onFriendCloseCall(String str);

        void onFriendRejectCall();

        void onFriendRequestCall(String str, String str2, String str3);

        void onGiftReceived(Gift gift);

        void onJoined(String str);

        void onMatch(SignalingParameters signalingParameters, int i);

        void onReceiveCall();

        void onReceivePornMsg(boolean z);

        void onRemoteDescription(SessionDescription sessionDescription);

        void onRemoteIceCandidate(IceCandidate iceCandidate);
    }

    public static class SignalingParameters {
        public final boolean f884a;
        public final User f885b;
        public final String f886c;
        public final String f887d;
        public final String f888e;
        public final IceServer f889f;
        public final String f890g;

        public SignalingParameters(String roomId, boolean initiator, User user, String turnSecKey, String turnUrl, String turnUser) {
            this.f890g = roomId;
            this.f884a = initiator;
            this.f885b = user;
            this.f886c = turnSecKey;
            this.f887d = turnUrl;
            this.f888e = turnUser;
            this.f889f = new IceServer(turnUrl, turnUser, turnSecKey);
        }

        public String toString() {
            return this.f885b == null ? BuildConfig.VERSION_NAME : "[nick = " + this.f885b.getNick() + " uid = " + this.f885b.getUid() + "] initiator = " + this.f884a;
        }
    }

    void disconnectRemote();

    void sendAnswerSdp(SessionDescription sessionDescription);

    void sendLocalIceCandidate(IceCandidate iceCandidate);

    void sendOfferSdp(SessionDescription sessionDescription);
}
