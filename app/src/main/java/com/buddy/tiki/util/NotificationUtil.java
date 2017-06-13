package com.buddy.tiki.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.constant.MsgDataType;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.service.base.Foreground;
import com.buddy.tiki.service.view.RushNotificationManager;
import com.buddy.tiki.ui.activity.ApplyListActivity;
import com.buddy.tiki.ui.activity.CallActivity;
import com.buddy.tiki.ui.activity.SplashActivity;
import com.igexin.download.Downloads;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import io.realm.Realm;
import org.bytedeco.javacpp.avcodec.AVCodecContext;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.postproc;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationUtil {
    private static final TikiLog f2433a = TikiLog.getInstance("NotificationUtil");

    @MainThread
    public static void showNotification(@NonNull Context context, String payload) {
        int NOTIFY_ID = 999;
        f2433a.m261d("receive msg " + payload);
        JSONObject chatMsg = null;
        try {
            chatMsg = new JSONObject(payload);
        } catch (JSONException e) {
        }
        if (chatMsg != null) {
            Intent intent;
            int msgType = chatMsg.optInt("type");
            String content = chatMsg.optString("text");
            String title = chatMsg.optString(Downloads.COLUMN_TITLE);
            int pendingIntentFlag = avutil.AV_CPU_FLAG_AVXSLOW;
            Uri soundUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + C0376R.raw.sms);
            switch (msgType) {
                case MsgDataType.MATCH /*232*/:
                case MsgDataType.DELETE_FRIEND_MSG /*245*/:
                    return;
                case MsgDataType.APPLY_FRIEND_MSG /*233*/:
                    intent = new Intent(context, ApplyListActivity.class);
                    pendingIntentFlag = postproc.PP_CPU_CAPS_ALTIVEC;
                    NOTIFY_ID = PointerIconCompat.TYPE_CONTEXT_MENU;
                    break;
                case MsgDataType.ACCEPT_FRIEND_MSG /*234*/:
                    intent = new Intent(context, CallActivity.class);
                    pendingIntentFlag = avutil.AV_CPU_FLAG_AVXSLOW;
                    NOTIFY_ID = PointerIconCompat.TYPE_HAND;
                    break;
                case MsgDataType.GIFT /*235*/:
                case MsgDataType.BORDER /*236*/:
                    intent = new Intent(context, CallActivity.class);
                    pendingIntentFlag = avutil.AV_CPU_FLAG_AVXSLOW;
                    NOTIFY_ID = PointerIconCompat.TYPE_DEFAULT;
                    break;
                case MsgDataType.CALL_FRIEND_MSG /*237*/:
                    f2433a.m261d("CALL_FRIEND_MSG:237 msg:" + payload);
                    IncomingCallManager.getInstance().show(ChatApp.getInstance(), chatMsg.optString("callId"), chatMsg.optString("sound"), title, content, chatMsg.optString(Oauth2AccessToken.KEY_UID), null);
                    return;
                case MsgDataType.CALL_REJECT_MSG /*239*/:
                    f2433a.m261d("CALL_REJECT_MSG:239 msg:" + payload);
                    String uid = chatMsg.optString(Oauth2AccessToken.KEY_UID);
                    if (!TextUtils.isEmpty(uid)) {
                        Realm realm = Realm.getDefaultInstance();
                        TikiUser tikiUser = (TikiUser) realm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, uid).findFirst();
                        if (tikiUser != null && tikiUser.isLoaded() && tikiUser.isValid()) {
                            realm.beginTransaction();
                            tikiUser.setLastMessageTime(System.currentTimeMillis());
                            tikiUser.setLastMessage(ChatApp.getInstance().getResources().getString(C0376R.string.video_message_reject));
                            realm.commitTransaction();
                        }
                        realm.close();
                        return;
                    }
                    return;
                case MsgDataType.CALL_CLOSE_MSG /*240*/:
                    f2433a.m261d("CALL_CLOSE_MSG:240 msg:" + payload);
                    return;
                case MsgDataType.CALL_RECEIVE_MSG /*242*/:
                    f2433a.m261d("CALL_RECEIVE_MSG:242 msg:" + payload);
                    return;
                case MsgDataType.CALL_ACCEPT_MSG /*243*/:
                    f2433a.m261d("CALL_ACCEPT_MSG:243");
                    return;
                case AVCodecContext.FF_PROFILE_H264_HIGH_444_PREDICTIVE /*244*/:
                    f2433a.m261d("CALL_OFFLINE_MSG:244 msg:" + payload);
                    IncomingCallManager.getInstance().dismiss(chatMsg.optString("callId"));
                    return;
                case avutil.AV_PIX_FMT_YUV420P14LE /*302*/:
                    soundUri = null;
                    RushNotificationManager.getInstance().showRushNotification(context, content);
                    if (!Foreground.get().isForeground() && !PowerUtil.isScreenOn()) {
                        PowerUtil.screenOn();
                        intent = new Intent(context, SplashActivity.class);
                        break;
                    }
                    return;
                    break;
                default:
                    intent = new Intent();
                    break;
            }
            Builder builder = new Builder(context).setLargeIcon(BitmapFactory.decodeResource(context.getResources(), C0376R.mipmap.ic_launch)).setSmallIcon(C0376R.mipmap.tiki_small_notification).setVibrate(new long[]{300, 100, 300, 100}).setLights(-13976833, avutil.AV_PIX_FMT_YUV420P12LE, PointerIconCompat.TYPE_DEFAULT).setContentIntent(PendingIntent.getActivity(context, 0, intent, pendingIntentFlag)).setContentText(content).setContentTitle(title).setAutoCancel(true).setPriority(2).setVisibility(1).setTicker("Tiki");
            if (soundUri != null) {
                builder.setSound(soundUri);
            }
            ((NotificationManager) context.getSystemService("notification")).notify(NOTIFY_ID, builder.build());
        }
    }

    public static void clearAllNotification(@NonNull Context context) {
        ((NotificationManager) context.getSystemService("notification")).cancelAll();
    }
}
