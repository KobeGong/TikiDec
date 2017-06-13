package com.buddy.tiki.view.match;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.util.DisplayUtil;

public class MatchingLayout extends RelativeLayout {
    private static final TikiLog f3153a = TikiLog.getInstance(MatchingLayout.class.getSimpleName());
    private final float f3154b;
    private MatchingView f3155c;
    private MatchingView f3156d;
    private boolean f3157e;
    private Handler f3158f;
    private int f3159g;
    private int f3160h;
    private int f3161i;

    private class SimpleAnimatorListener implements AnimatorListener {
        final /* synthetic */ MatchingLayout f3145c;

        private SimpleAnimatorListener(MatchingLayout matchingLayout) {
            this.f3145c = matchingLayout;
        }

        public void onAnimationStart(Animator animation) {
        }

        public void onAnimationEnd(Animator animation) {
            this.f3145c.m1970a("onAnimationEnd");
        }

        public void onAnimationCancel(Animator animation) {
        }

        public void onAnimationRepeat(Animator animation) {
        }
    }

    class C05251 extends SimpleAnimatorListener {
        final /* synthetic */ MatchingLayout f3146a;

        C05251(MatchingLayout this$0) {
            this.f3146a = this$0;
            super();
        }

        public void onAnimationEnd(Animator animation) {
            MatchingView preMatchView = this.f3146a.getPreMatchViewInternal();
            this.f3146a.removeView(preMatchView);
            this.f3146a.addView(preMatchView);
            this.f3146a.f3156d = this.f3146a.getCurrentMatchViewInternal();
            this.f3146a.f3155c = preMatchView;
            this.f3146a.f3155c.setTranslationY((float) (-this.f3146a.f3161i));
        }
    }

    public MatchingLayout(Context context) {
        this(context, null);
    }

    public MatchingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatchingLayout(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        this.f3154b = 5000.0f;
        this.f3157e = true;
        if (isInEditMode()) {
            this.f3161i = 1920;
        } else if (context instanceof Activity) {
            this.f3161i = DisplayUtil.getRawHeight((Activity) context);
        } else {
            this.f3161i = DisplayUtil.getDisplayHeight();
        }
        this.f3159g = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
        this.f3160h = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        this.f3158f = new Handler(Looper.getMainLooper());
    }

    public void setLogEnabled(boolean logEnabled) {
        this.f3157e = logEnabled;
    }

    private void m1970a(String msg) {
        if (this.f3157e) {
            f3153a.m261d("MatchingViewPlayer: " + msg);
        }
    }

    public void initMatching() {
        this.f3158f.post(MatchingLayout$$Lambda$1.lambdaFactory$(this));
    }

    private synchronized void m1968a() {
        m1970a("initMatching");
        MatchingView pre = getPreMatchViewInternal();
        pre.setPassort(null, 0);
        pre.setStatus(1);
        pre.setMatchedUser(null);
        pre.setTranslationY(0.0f);
        if (this.f3155c != null) {
            this.f3155c.setStatus(1);
            this.f3155c.setMatchedUser(null);
            this.f3155c.setTranslationY((float) (-this.f3161i));
        }
    }

    private MatchingView getPreMatchViewInternal() {
        if (this.f3156d == null) {
            this.f3156d = new MatchingView(getContext());
            addView(this.f3156d, 0);
            this.f3156d.setTranslationY((float) (-this.f3161i));
        }
        return this.f3156d;
    }

    public synchronized MatchingView getCurrentMatchView() {
        return getCurrentMatchViewInternal();
    }

    private MatchingView getCurrentMatchViewInternal() {
        if (this.f3155c == null) {
            this.f3155c = new MatchingView(getContext());
            addView(this.f3155c, getChildCount());
            this.f3155c.setTranslationY((float) (-this.f3161i));
        }
        return this.f3155c;
    }

    public synchronized int getPreMatchViewStatus() {
        return m1965a(this.f3156d);
    }

    public synchronized int getCurMatchViewStatus() {
        return m1965a(this.f3155c);
    }

    private int m1965a(MatchingView view) {
        if (view == null) {
            return 0;
        }
        float ty = view.getTranslationY();
        if (ty == 0.0f) {
            return 2;
        }
        if (ty == ((float) (-this.f3161i))) {
            return 1;
        }
        return -1;
    }

    /* synthetic */ void m1993a(String passport, int onlineCount) {
        m1977b(passport, onlineCount);
    }

    public void startMatch(String passport, int onlineCount) {
        this.f3158f.post(MatchingLayout$$Lambda$2.lambdaFactory$(this, passport, onlineCount));
    }

    private synchronized void m1977b(String passport, int onlineCount) {
        m1970a("startMatch");
        m1968a();
        getPreMatchViewInternal().setPassort(passport, onlineCount);
        getPreMatchViewInternal().setStatus(1);
    }

    /* synthetic */ void m1991a(User matchedUser) {
        m1975b(matchedUser);
    }

    public void matched(User matchedUser) {
        this.f3158f.post(MatchingLayout$$Lambda$3.lambdaFactory$(this, matchedUser));
    }

    private synchronized void m1975b(User matchedUser) {
        m1970a("matched");
        m1968a();
        getPreMatchViewInternal().setMatchedUser(matchedUser);
        getPreMatchViewInternal().setStatus(2);
    }

    public void connectFailed() {
        this.f3158f.post(MatchingLayout$$Lambda$4.lambdaFactory$(this));
    }

    private synchronized void m1973b() {
        m1970a("connectFailed");
        getPreMatchViewInternal().setStatus(3);
    }

    public void leaveRoom() {
        this.f3158f.post(MatchingLayout$$Lambda$5.lambdaFactory$(this));
    }

    private synchronized void m1979c() {
        m1970a("leaveRoom");
        getPreMatchViewInternal().setStatus(4);
    }

    public void reset() {
        this.f3158f.post(MatchingLayout$$Lambda$6.lambdaFactory$(this));
    }

    private synchronized void m1981d() {
        m1970a("reset");
        m1983e();
        if (this.f3156d != null) {
            this.f3156d.setTranslationY((float) (-this.f3161i));
        }
        if (this.f3155c != null) {
            this.f3155c.setTranslationY((float) (-this.f3161i));
        }
    }

    private void m1983e() {
        int i = getChildCount() - 1;
        while (i >= 0) {
            if (!(getChildAt(i) == this.f3156d || getChildAt(i) == this.f3155c)) {
                removeViewAt(i);
            }
            i--;
        }
    }

    public void playUp() {
        this.f3158f.post(MatchingLayout$$Lambda$7.lambdaFactory$(this));
    }

    private synchronized void m1984f() {
        m1970a("playUp");
        ObjectAnimator.ofFloat(getCurrentMatchViewInternal(), MatchingView.TRANSLATION_Y, new float[]{view.getTranslationY(), (float) (-this.f3161i)}).start();
    }

    /* synthetic */ void m1992a(Runnable endTask) {
        m1976b(endTask);
    }

    public void playDown(Runnable endTask) {
        this.f3158f.post(MatchingLayout$$Lambda$8.lambdaFactory$(this, endTask));
    }

    private synchronized void m1976b(final Runnable endTask) {
        m1970a("playDown");
        ObjectAnimator animator = ObjectAnimator.ofFloat(getCurrentMatchViewInternal(), MatchingView.TRANSLATION_Y, new float[]{view.getTranslationY(), 0.0f});
        animator.addListener(new C05251(this));
        animator.addListener(new SimpleAnimatorListener(this) {
            final /* synthetic */ MatchingLayout f3148b;

            public void onAnimationEnd(Animator animation) {
                if (endTask != null) {
                    this.f3148b.f3158f.post(endTask);
                }
            }
        });
        animator.start();
    }

    /* synthetic */ void m1990a(int velocity, Runnable callback) {
        m1974b(velocity, callback);
    }

    public void playFling(int velocity, Runnable callback) {
        this.f3158f.post(MatchingLayout$$Lambda$9.lambdaFactory$(this, velocity, callback));
    }

    private synchronized void m1974b(int velocity, final Runnable callback) {
        m1970a("playFling:v:" + velocity);
        MatchingView view = getCurrentMatchViewInternal();
        if (Math.abs(velocity) < this.f3159g) {
            if (callback != null) {
                callback.run();
            }
        } else if (velocity >= this.f3159g) {
            newTy = view.getTranslationY() + m1964a(Math.min(velocity, this.f3160h));
            if (newTy < ((float) (-this.f3161i))) {
                newTy = (float) (-this.f3161i);
            } else if (newTy > 0.0f) {
                newTy = 0.0f;
            }
            animator = ObjectAnimator.ofFloat(view, MatchingView.TRANSLATION_Y, new float[]{view.getTranslationY(), newTy});
            animator.addListener(new SimpleAnimatorListener(this) {
                final /* synthetic */ MatchingLayout f3150b;

                public void onAnimationEnd(Animator animation) {
                    if (callback != null) {
                        callback.run();
                    }
                }
            });
            animator.start();
        } else if (velocity < (-this.f3159g)) {
            newTy = view.getTranslationY() + m1964a(Math.max(-this.f3160h, velocity));
            if (newTy < ((float) (-this.f3161i))) {
                newTy = (float) (-this.f3161i);
            } else if (newTy > 0.0f) {
                newTy = 0.0f;
            }
            animator = ObjectAnimator.ofFloat(view, MatchingView.TRANSLATION_Y, new float[]{view.getTranslationY(), newTy});
            animator.addListener(new SimpleAnimatorListener(this) {
                final /* synthetic */ MatchingLayout f3152b;

                public void onAnimationEnd(Animator animation) {
                    if (callback != null) {
                        callback.run();
                    }
                }
            });
            animator.start();
        }
    }

    private float m1964a(int velocity) {
        return (((Math.signum((float) velocity) * ((float) velocity)) * ((float) velocity)) / 5000.0f) / 2.0f;
    }
}
