package com.buddy.tiki.util;

import com.buddy.tiki.model.user.UserChatMessage;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.List;

public class MessageUtil {
    public static List<UserChatMessage> addMessagesWithTimeline(int page, RealmResults<UserChatMessage> messages) {
        List<UserChatMessage> ret = null;
        if (messages != null && messages.size() > 0) {
            int size = messages.size();
            long time = ((UserChatMessage) messages.get(0)).getTimestamp();
            String date = DateUtil.getChatMessageDate(time);
            ret = new ArrayList();
            int start = size - (page * 10) > 0 ? size - (page * 10) : 0;
            for (int i = start; i < size; i++) {
                UserChatMessage msg = (UserChatMessage) messages.get(i);
                if (i == start) {
                    msg.setShowTime(true);
                }
                String tempDate = DateUtil.getChatMessageDate(msg.getTimestamp());
                if (!tempDate.equals(date)) {
                    date = tempDate;
                    time = msg.getTimestamp();
                    msg.setShowTime(true);
                } else if (msg.getTimestamp() - time > DateUtil.f2351a) {
                    time = msg.getTimestamp();
                    msg.setShowTime(true);
                }
                ret.add(msg);
            }
        }
        return ret;
    }
}
