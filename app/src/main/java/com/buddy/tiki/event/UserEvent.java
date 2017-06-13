package com.buddy.tiki.event;

import com.buddy.tiki.model.wechat.WxToken;

public class UserEvent {

    public static class AcceptFriendEvent {
        public final String[] f507a;
        public final String[] f508b;

        public AcceptFriendEvent(String[] applyIds, String[] toUids) {
            String[] strArr = null;
            this.f507a = applyIds == null ? null : (String[]) applyIds.clone();
            if (toUids != null) {
                strArr = (String[]) toUids.clone();
            }
            this.f508b = strArr;
        }

        public AcceptFriendEvent(String applyId, String toUid) {
            this.f507a = new String[]{applyId};
            this.f508b = new String[]{toUid};
        }
    }

    public static class DeleteFriendEvent {
        public final String f509a;

        public DeleteFriendEvent(String uid) {
            this.f509a = uid;
        }
    }

    public static class InputDiamondsDialogDismissEvent {
    }

    public static class InputDiamondsEvent {
        public final int f510a;

        public InputDiamondsEvent(int diamonds) {
            this.f510a = diamonds;
        }
    }

    public static class ModifyGenderEvent {
        public final int f511a;

        public ModifyGenderEvent(int gender) {
            this.f511a = gender;
        }
    }

    public static class ModifyProfileEvent {
        public final String f512a;
        public final int f513b;
        public final String f514c;

        public ModifyProfileEvent(String nick, int gender, String avatar) {
            this.f512a = nick;
            this.f513b = gender;
            this.f514c = avatar;
        }
    }

    public static class PassportChangeEvent {
        public final String f515a;

        public PassportChangeEvent(String passport) {
            this.f515a = passport;
        }
    }

    public static class ReSendGiftEvent {
        public final String f516a;
        public final String f517b;

        public ReSendGiftEvent(String msgId, String giftId) {
            this.f516a = msgId;
            this.f517b = giftId;
        }
    }

    public static class ReSendTextEvent {
        public final String f518a;
        public final String f519b;

        public ReSendTextEvent(String msgId, String text) {
            this.f518a = msgId;
            this.f519b = text;
        }
    }

    public static class UploadStateEvent {
        public final String[] f520a;
        public final String[] f521b;
        public final int f522c;
        public final int f523d;
        public final String f524e;
        public final String f525f;
        public final boolean f526g;

        public UploadStateEvent(String[] ids, String picUrl, String videoUrl, String[] tos, int diamonds, int timelen, boolean isAllFriends) {
            this.f520a = ids;
            this.f524e = picUrl;
            this.f525f = videoUrl;
            this.f521b = tos;
            this.f522c = diamonds;
            this.f523d = timelen;
            this.f526g = isAllFriends;
        }
    }

    public static class WxTokenEvent {
        public final boolean f527a;
        public final WxToken f528b;

        public WxTokenEvent(boolean isNew, WxToken wxToken) {
            this.f527a = isNew;
            this.f528b = wxToken;
        }
    }
}
