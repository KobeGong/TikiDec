package com.buddy.tiki.service.base;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.buddy.tiki.base.TopConfig;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.model.user.SyncFriends;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.util.PreferenceUtil;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Foreground implements ActivityLifecycleCallbacks {
    public static final String f1042a = Foreground.class.getName();
    private static Foreground f1043b;
    private volatile boolean f1044c = false;
    private volatile boolean f1045d = true;
    private Handler f1046e = new Handler();
    private List<Listener> f1047f = new CopyOnWriteArrayList();
    private Runnable f1048g;

    public interface Listener {
        void onBecameBackground();

        void onBecameForeground();
    }

    public static Foreground init(Application application) {
        if (f1043b == null) {
            synchronized (Foreground.class) {
                if (f1043b == null) {
                    f1043b = new Foreground();
                    application.registerActivityLifecycleCallbacks(f1043b);
                }
            }
        }
        return f1043b;
    }

    public static Foreground get(Application application) {
        if (f1043b == null) {
            init(application);
        }
        return f1043b;
    }

    public static Foreground get(Context ctx) {
        if (f1043b != null) {
            return f1043b;
        }
        Context appCtx = ctx.getApplicationContext();
        if (appCtx instanceof Application) {
            init((Application) appCtx);
        }
        throw new IllegalStateException("Foreground is not initialised and cannot obtain the Application object");
    }

    public static Foreground get() {
        if (f1043b != null) {
            return f1043b;
        }
        throw new IllegalStateException("Foreground is not initialised - invoke at least once with parameterised init/get");
    }

    public final boolean isForeground() {
        return this.f1044c;
    }

    public final boolean isBackground() {
        return !this.f1044c;
    }

    public void addListener(Listener listener) {
        this.f1047f.add(listener);
    }

    public void removeListener(Listener listener) {
        this.f1047f.remove(listener);
    }

    public void onActivityResumed(Activity activity) {
        boolean wasBackground = false;
        this.f1045d = false;
        if (!this.f1044c) {
            wasBackground = true;
        }
        this.f1044c = true;
        if (this.f1048g != null) {
            this.f1046e.removeCallbacks(this.f1048g);
        }
        if (wasBackground) {
            if (!TextUtils.isEmpty(TopConfig.f408a)) {
                DataLayer.getInstance().getFollowManager().syncFriendsQuery(PreferenceUtil.getSyncTimepoint()).subscribeOn(Schedulers.io()).subscribe(Foreground$$Lambda$1.lambdaFactory$(), Foreground$$Lambda$2.lambdaFactory$());
                DataLayer.getInstance().getUserManager().userRequest(TopConfig.f408a).subscribeOn(Schedulers.io()).subscribe(Foreground$$Lambda$3.lambdaFactory$(), Foreground$$Lambda$4.lambdaFactory$());
                DataLayer.getInstance().getAppManager().configInfoRequest().subscribeOn(Schedulers.io()).subscribe(Foreground$$Lambda$5.lambdaFactory$(), Foreground$$Lambda$6.lambdaFactory$());
            }
            for (Listener l : this.f1047f) {
                try {
                    l.onBecameForeground();
                } catch (Exception exc) {
                    Log.e(f1042a, "Listener threw exception!", exc);
                }
            }
        }
    }

    static /* synthetic */ void m394a(SyncFriends syncFriends) throws Exception {
    }

    static /* synthetic */ void m398c(Throwable throwable) throws Exception {
    }

    static /* synthetic */ void m395a(User user) throws Exception {
    }

    static /* synthetic */ void m397b(Throwable throwable) throws Exception {
    }

    static /* synthetic */ void m393a(ConfigInfo configInfo) throws Exception {
    }

    static /* synthetic */ void m396a(Throwable throwable) throws Exception {
    }

    public void onActivityPaused(Activity activity) {
        this.f1045d = true;
        if (this.f1048g != null) {
            this.f1046e.removeCallbacks(this.f1048g);
        }
        Handler handler = this.f1046e;
        Runnable lambdaFactory$ = Foreground$$Lambda$7.lambdaFactory$(this);
        this.f1048g = lambdaFactory$;
        handler.postDelayed(lambdaFactory$, 500);
    }

    /* synthetic */ void m399a() {
        if (this.f1044c && this.f1045d) {
            this.f1044c = false;
            for (Listener l : this.f1047f) {
                try {
                    l.onBecameBackground();
                } catch (Exception exc) {
                    Log.e(f1042a, "Listener threw exception!", exc);
                }
            }
        }
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}
