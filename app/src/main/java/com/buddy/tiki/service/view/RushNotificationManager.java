package com.buddy.tiki.service.view;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.base.Constants;
import com.buddy.tiki.event.StateEvent.MatchStateEvent;
import com.buddy.tiki.helper.CustomMessageHelper;
import com.buddy.tiki.helper.MediaHelper;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.service.base.Foreground;
import com.buddy.tiki.ui.activity.CallActivity;
import com.buddy.tiki.ui.activity.base.ActivityManager;
import com.buddy.tiki.view.RushView;
import im.facechat.Rtc;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

public class RushNotificationManager {
    private static final TikiLog f1104a = TikiLog.getInstance(RushNotificationManager.class.getSimpleName());
    private volatile boolean f1105b;
    private Disposable f1106c;
    private WeakReference<RushView> f1107d;

    private static class SingletonHolder {
        private static final RushNotificationManager f1103a = new RushNotificationManager();
    }

    private RushNotificationManager() {
        this.f1105b = false;
    }

    public static RushNotificationManager getInstance() {
        return SingletonHolder.f1103a;
    }

    public void showRushNotification(@Nullable Context context, String text) {
        if (context == null) {
            context = ChatApp.getInstance();
        }
        if (this.f1105b) {
            f1104a.m269w("broadCasting...");
            return;
        }
        MatchStateEvent event = (MatchStateEvent) EventBus.getDefault().getStickyEvent(MatchStateEvent.class);
        if (event != null && event.f506a) {
            int state = Rtc.getCurrentState();
            if (state == 12 || state == 11) {
                Activity activity = ActivityManager.getInstance().currentActivity();
                if (activity != null && (activity instanceof CallActivity) && ((CallActivity) activity).isVipUser()) {
                    f1104a.m269w("it's VIP");
                    return;
                }
            }
            f1104a.m269w("I don't care");
            return;
        }
        this.f1105b = true;
        CustomMessageHelper.getInstance().ignoreNext(true);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (!(this.f1107d == null || this.f1107d.get() == null)) {
            windowManager.removeView((View) this.f1107d.get());
        }
        RushView rushView = new RushView(context);
        this.f1107d = new WeakReference(rushView);
        String packageName = context.getPackageName();
        if (windowManager != null) {
            LayoutParams params = new LayoutParams();
            params.width = -1;
            params.height = -2;
            params.gravity = 49;
            params.x = 0;
            params.y = 0;
            params.dimAmount = 0.0f;
            params.flags = 262306;
            params.format = -3;
            if (VERSION.SDK_INT >= 19) {
                params.type = 2005;
            } else {
                params.type = 2002;
            }
            params.packageName = packageName;
            rushView.setText(text);
            windowManager.addView(rushView, params);
            this.f1106c = Observable.timer(60, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(RushNotificationManager$$Lambda$1.lambdaFactory$(this));
            if (!Foreground.get().isForeground()) {
                MediaHelper.INSTANCE.playMusic(ChatApp.getInstance(), Constants.f407b, false);
            }
        }
    }

    /* synthetic */ void m424a(Long aLong) throws Exception {
        dismiss(null);
    }

    public boolean isBroadCasting() {
        return this.f1105b;
    }

    public void grabOrderSuccess() {
        if (this.f1107d != null && this.f1107d.get() != null) {
            ((RushView) this.f1107d.get()).showSuccess();
        }
    }

    public void doAction() {
        if (this.f1106c != null && !this.f1106c.isDisposed()) {
            this.f1106c.dispose();
        }
    }

    public void dismiss(@Nullable Context context) {
        if (context == null) {
            context = ChatApp.getInstance().getApplicationContext();
        }
        CustomMessageHelper.getInstance().ignoreNext(false);
        if (!(this.f1106c == null || this.f1106c.isDisposed())) {
            this.f1106c.dispose();
        }
        if (!(this.f1107d == null || this.f1107d.get() == null)) {
            RushView rushView = (RushView) this.f1107d.get();
            this.f1107d.clear();
            ((WindowManager) context.getSystemService("window")).removeView(rushView);
        }
        this.f1107d = null;
        this.f1105b = false;
    }
}
