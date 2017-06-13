package com.buddy.tiki.event;

import android.support.annotation.Nullable;
import com.buddy.tiki.model.user.User;

public class CallEvent {

    public static class PreviewSizeChangeEvent {
        private final int f492a;
        private final int f493b;

        public PreviewSizeChangeEvent(int width, int height) {
            this.f492a = width;
            this.f493b = height;
        }

        public int getHeight() {
            return this.f493b;
        }

        public int getWidth() {
            return this.f492a;
        }
    }

    public static class StopCountDownEvent {
        private final String[] f494a;

        public StopCountDownEvent(String[] uids) {
            if (uids == null) {
                this.f494a = null;
            } else {
                this.f494a = (String[]) uids.clone();
            }
        }

        public String[] getUids() {
            return (String[]) this.f494a.clone();
        }
    }

    public static class UpdateStatusEvent {
        public final User f495a;
        public final boolean f496b;

        public UpdateStatusEvent(@Nullable User user, boolean connecting) {
            this.f495a = user;
            this.f496b = connecting;
        }
    }
}
