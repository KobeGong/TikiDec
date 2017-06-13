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

public class IncomingCallView extends RelativeLayout {
    private static final TikiLog f2695a = TikiLog.getInstance(IncomingCallView.class.getSimpleName());
    private AppCompatTextView f2696b;
    private AppCompatTextView f2697c;
    private AppCompatTextView f2698d;
    private String f2699e;
    private String f2700f;
    private String f2701g;
    private boolean f2702h;

    public IncomingCallView(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        this.f2702h = false;
        inflate(context, C0376R.layout.incoming_call_notification, this);
        this.f2696b = (AppCompatTextView) findViewById(C0376R.id.nick);
        this.f2697c = (AppCompatTextView) findViewById(C0376R.id.accept_button);
        this.f2698d = (AppCompatTextView) findViewById(C0376R.id.reject_button);
        m1711a();
    }

    public IncomingCallView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IncomingCallView(Context context) {
        this(context, null);
    }

    public IncomingCallView(Context context, String callId, String callerNick, String callerUid) {
        this(context);
        this.f2699e = callId;
        this.f2700f = callerNick;
        this.f2701g = callerUid;
        if (!TextUtils.isEmpty(this.f2700f)) {
            this.f2696b.setText(this.f2700f);
        }
    }

    private void m1711a() {
        RxView.clicks(this.f2697c).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(IncomingCallView$$Lambda$1.lambdaFactory$(this), IncomingCallView$$Lambda$2.lambdaFactory$());
        RxView.clicks(this.f2698d).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(IncomingCallView$$Lambda$3.lambdaFactory$(this));
    }

    /* synthetic */ void m1718b(Object aVoid) throws Exception {
        f2695a.m261d("IncomingCallView leave room result is " + Rtc.leaveRoom(BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME));
        if (ActivityManager.getInstance().currentActivity() instanceof CallFriendActivity) {
            ActivityManager.getInstance().currentActivity().finish();
        } else if (ActivityManager.getInstance().currentActivity() instanceof CallActivity) {
            CallActivity callActivity = (CallActivity) ActivityManager.getInstance().currentActivity();
            this.f2702h = callActivity.getMatchState();
            if (this.f2702h) {
                callActivity.stopMatch();
            }
        }
        DataLayer.getInstance().getChatManager().acceptCallAction(this.f2699e).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(IncomingCallView$$Lambda$6.lambdaFactory$(this), IncomingCallView$$Lambda$7.lambdaFactory$());
    }

    /* synthetic */ void m1715a(CallReceiveMessage callReceiveMessage) throws Exception {
        IncomingCallManager.getInstance().dismissAll();
        if (!TextUtils.isEmpty(callReceiveMessage.getRoomId()) && ActivityManager.getInstance().currentActivity() != null) {
            Intent intent = new Intent();
            Bundle args = new Bundle();
            args.putBoolean("PARAM_KEY_IS_MATCH", this.f2702h);
            args.putBoolean("PARAM_KEY_IS_CALLER", false);
            args.putParcelable("PARAM_KEY_CALL_RECEIVE_MSG", Parcels.wrap(callReceiveMessage));
            intent.setClass(ChatApp.getInstance(), CallFriendActivity.class);
            intent.putExtra("PARAMS_KEY", args);
            f2695a.m261d("start call friend:" + ActivityManager.getInstance().currentActivity());
            intent.setFlags(postproc.PP_CPU_CAPS_ALTIVEC);
            ChatApp.getInstance().startActivity(intent);
        }
    }

    /* synthetic */ void m1717a(Object aVoid) throws Exception {
        DataLayer.getInstance().getChatManager().rejectCallAction(this.f2699e).subscribeOn(Schedulers.io()).subscribe(IncomingCallView$$Lambda$4.lambdaFactory$(this), IncomingCallView$$Lambda$5.lambdaFactory$());
    }

    /* synthetic */ void m1716a(Boolean aBoolean) throws Exception {
        IncomingCallManager.getInstance().dismiss(this.f2699e);
        User fromUser = new User();
        fromUser.setUid(this.f2701g);
        fromUser.setNick(this.f2700f);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        UserChatRealmHelper.getInstance().insertRejectMessage(realm, this.f2701g, "local_" + UUID.randomUUID().toString(), System.currentTimeMillis(), fromUser);
        realm.commitTransaction();
        realm.close();
    }

    static /* synthetic */ void m1712a(Throwable throwable) throws Exception {
    }
}
