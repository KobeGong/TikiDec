package com.buddy.tiki.event;

import com.buddy.tiki.model.resource.Border;

public class ResourceEvent {

    public static class UseBorderEvent {
        public Border f502a;

        public UseBorderEvent(Border border) {
            this.f502a = border;
        }
    }

    public static class UseFaceUnityEvent {
        public final String f503a;
        public final int f504b;
        public final String f505c;

        public UseFaceUnityEvent(String path, int index, String cover) {
            this.f503a = path;
            this.f504b = index;
            this.f505c = cover;
        }
    }
}
