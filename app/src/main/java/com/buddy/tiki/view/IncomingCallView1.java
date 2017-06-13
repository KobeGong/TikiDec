package com.buddy.tiki.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.recyclerview.BuildConfig;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.im.CallReceiveMessage;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.ui.activity.CallActivity;
import com.buddy.tiki.ui.activity.CallFriendActivity;
import com.buddy.tiki.ui.activity.base.ActivityManager;
import com.buddy.tiki.util.IncomingCallManager;
import com.buddy.tiki.util.UserChatRealmHelper;
import com.jakewharton.rxbinding2.view.RxView;
import im.facechat.Rtc;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.bytedeco.javacpp.postproc;
import org.parceler.Parcels;

public class IncomingCallView1 extends RelativeLayout {
    private static final TikiLog f2685a = TikiLog.getInstance(IncomingCallView.class.getSimpleName());
    private AppCompatTextView f2686b;
    private AppCompatTextView f2687c;
    private AppCompatTextView f2688d;
    private AppCompatTextView f2689e;
    private String f2690f;
    private String f2691g;
    private String f2692h;
    private String f2693i;
    private boolean f2694j;

    public IncomingCallView1(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        this.f2694j = false;
        inflate(context, C0376R.layout.incoming_call_1_notification, this);
        this.f2686b = (AppCompatTextView) findViewById(C0376R.id.title);
        this.f2687c = (AppCompatTextView) findViewById(C0376R.id.text);
        this.f2688d = (AppCompatTextView) findViewById(C0376R.id.accept_button);
        this.f2689e = (AppCompatTextView) findViewById(C0376R.id.reject_button);
        m1704a();
    }

    public IncomingCallView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IncomingCallView1(Context context) {
        this(context, null);
    }

    public IncomingCallView1(Context context, String callId, String sound, String title, String content, String callerUid, String callerNick) {
        this(context);
        this.f2690f = callId;
        this.f2686b.setText(title);
        this.f2687c.setText(content);
        this.f2693i = callerNick;
        this.f2692h = callerUid;
        this.f2691g = sound;
    }

    private void m1704a() {
        RxView.clicks(this.f2688d).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(IncomingCallView1$$Lambda$1.lambdaFactory$(this));
        RxView.clicks(this.f2689e).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(IncomingCallView1$$Lambda$2.lambdaFactory$(this));
    }

    /* synthetic */ void m1710b(Object aVoid) throws Exception {
        try {
            f2685a.m261d("IncomingCallView1 leave room result is " + Rtc.leaveRoom(BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME));
            if (ActivityManager.getInstance().currentActivity() instanceof CallFriendActivity) {
                ActivityManager.getInstance().currentActivity().finish();
            } else if (ActivityManager.getInstance().currentActivity() instanceof CallActivity) {
                CallActivity callActivity = (CallActivity) ActivityManager.getInstance().currentActivity();
                this.f2694j = callActivity.getMatchState();
                if (this.f2694j) {
                    callActivity.stopMatch();
                }
            }
            DataLayer.getInstance().getChatManager().acceptCallAction(this.f2690f).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(IncomingCallView1$$Lambda$5.lambdaFactory$(this), IncomingCallView1$$Lambda$6.lambdaFactory$());
        } catch (Exception e) {
            f2685a.m264e("show alert dialog error:", e);
        }
    }

    /* synthetic */ void m1707a(CallReceiveMessage callReceiveMessage) throws Exception {
        IncomingCallManager.getInstance().dismissAll();
        if (!TextUtils.isEmpty(callReceiveMessage.getRoomId())) {
            Intent intent = new Intent();
            Bundle args = new Bundle();
            args.putBoolean("PARAM_KEY_IS_MATCH", this.f2694j);
            args.putBoolean("PARAM_KEY_FROM_APNS", true);
            args.putBoolean("PARAM_KEY_IS_CALLER", false);
            args.putParcelable("PARAM_KEY_CALL_RECEIVE_MSG", Parcels.wrap(callReceiveMessage));
            intent.setClass(ChatApp.getInstance(), CallFriendActivity.class);
            intent.putExtra("PARAMS_KEY", args);
            intent.setFlags(postproc.PP_CPU_CAPS_ALTIVEC);
            ChatApp.getInstance().startActivity(intent);
        }
    }

    static /* synthetic */ void m1706b(Throwable throwable) throws Exception {
        throwable.printStackTrace();
        f2685a.m261d("error:" + throwable.getMessage());
    }

    /* synthetic */ void m1709a(Object aVoid) throws Exception {
        DataLayer.getInstance().getChatManager().rejectCallAction(this.f2690f).subscribeOn(Schedulers.io()).subscribe(IncomingCallView1$$Lambda$3.lambdaFactory$(this), IncomingCallView1$$Lambda$4.lambdaFactory$());
    }

    /* synthetic */ void m1708a(Boolean aBoolean) throws Exception {
        IncomingCallManager.getInstance().dismiss(this.f2690f);
        User fromUser = new User();
        fromUser.setUid(this.f2692h);
        fromUser.setNick(this.f2693i);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        UserChatRealmHelper.getInstance().insertRejectMessage(realm, this.f2692h, "local_" + UUID.randomUUID().toString(), System.currentTimeMillis(), fromUser);
        realm.commitTransaction();
        realm.close();
    }

    static /* synthetic */ void m1705a(Throwable throwable) throws Exception {
    }
}
