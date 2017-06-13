package com.buddy.tiki.event;

import android.support.annotation.NonNull;
import com.buddy.tiki.model.msg.MatchMessage;

public class FriendCallEvent {

    public static class AcceptEvent {
        public final String f497a;
        public final String f498b;
        public final MatchMessage f499c;

        public AcceptEvent(String roomId, String callId, MatchMessage matchMessage) {
            this.f497a = roomId;
            this.f498b = callId;
            this.f499c = matchMessage;
        }
    }

    public static class CloseEvent {
        public final String f500a;

        public CloseEvent(@NonNull String callId) {
            this.f500a = callId;
        }
    }

    public static class RejectEvent {
        public final String f501a;

        public RejectEvent(@NonNull String callId) {
            this.f501a = callId;
        }
    }
}
