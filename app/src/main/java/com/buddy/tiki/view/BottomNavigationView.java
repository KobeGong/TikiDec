package com.buddy.tiki.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.TypedArray;
import android.os.SystemClock;
import android.support.annotation.CheckResult;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.recyclerview.BuildConfig;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.event.ResourceEvent.UseFaceUnityEvent;
import com.buddy.tiki.event.StateEvent.MatchStateEvent;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.service.view.RushNotificationManager;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.util.DrawableUtil;
import com.buddy.tiki.util.FUUtil;
import com.buddy.tiki.util.PreferenceUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;
import org.bytedeco.javacpp.avutil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tourguide.tourguide.TourGuide;

public class BottomNavigationView extends RelativeLayout {
    private OnNavigationViewClick f2532a;
    private DoubleLayerImageView f2533b;
    private final RealmChangeListener<RealmResults<TikiUser>> f2534c;
    private final OnSharedPreferenceChangeListener f2535d;
    private ExploreImageView f2536e;
    private DoubleLayerImageView f2537f;
    private SimpleDraweeView f2538g;
    private SimpleDraweeView f2539h;
    private ImageView f2540i;
    private View f2541j;
    private AtomicInteger f2542k;
    private boolean f2543l;
    private int f2544m;
    private RealmResults<TikiUser> f2545n;
    private boolean f2546o;
    private RelativeLayout f2547p;
    private WeakReference<TourGuide> f2548q;

    public interface OnNavigationViewClick {
        void onItemClick(int i);
    }

    class C04881 implements OnClickListener {
        final /* synthetic */ BottomNavigationView f2521a;
        private long f2522b = 0;

        C04881(BottomNavigationView this$0) {
            this.f2521a = this$0;
        }

        public void onClick(View v) {
            if (SystemClock.elapsedRealtime() - this.f2522b >= 1200) {
                this.f2522b = SystemClock.elapsedRealtime();
                if (!this.f2521a.f2546o) {
                    switch (this.f2521a.f2542k.get()) {
                        case avutil.AV_SAMPLE_FMT_NONE /*-1*/:
                            if (!(this.f2521a.f2548q == null || this.f2521a.f2548q.get() == null)) {
                                PreferenceUtil.setExploreButtonTips();
                                ((TourGuide) this.f2521a.f2548q.get()).cleanUp();
                                this.f2521a.f2548q.clear();
                                this.f2521a.f2548q = null;
                            }
                            this.f2521a.startMatch();
                            return;
                        case avutil.AV_SAMPLE_FMT_S64P /*11*/:
                            this.f2521a.stopMatch();
                            return;
                        case avutil.AV_PIX_FMT_XYZ12BE /*111*/:
                        case avutil.AV_PIX_FMT_NV16 /*112*/:
                            if (this.f2521a.f2532a != null) {
                                this.f2521a.f2532a.onItemClick(6);
                            }
                            this.f2521a.f2542k.set(-1);
                            return;
                        default:
                            return;
                    }
                } else if (this.f2521a.f2532a != null) {
                    this.f2521a.f2532a.onItemClick(7);
                }
            }
        }
    }

    /* synthetic */ void m1660a(RealmResults element) {
        int unread;
        Number number = element.max("unread");
        if (number == null) {
            unread = 0;
        } else {
            unread = number.intValue();
        }
        this.f2533b.setUnreadNum(unread);
    }

    /* synthetic */ void m1658a(SharedPreferences sharedPreferences, String key) {
        if (key.equals("TIKI_APPLY_NUM")) {
            this.f2533b.setApplyNum(PreferenceUtil.getApplyNumber());
        }
    }

    public BottomNavigationView(Context context) {
        this(context, null);
    }

    public BottomNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.f2534c = BottomNavigationView$$Lambda$1.lambdaFactory$(this);
        this.f2535d = BottomNavigationView$$Lambda$2.lambdaFactory$(this);
        TypedArray array = context.obtainStyledAttributes(attrs, C0376R.styleable.BottomNavigationView, defStyleAttr, 0);
        this.f2546o = array.getBoolean(1, false);
        array.recycle();
        m1639a(context);
    }

    private void m1639a(Context context) {
        this.f2542k = new AtomicInteger(-1);
        this.f2543l = false;
        inflate(context, C0376R.layout.widget_bottom_navigation_view, this);
        this.f2533b = (DoubleLayerImageView) findViewById(C0376R.id.left_button);
        this.f2536e = (ExploreImageView) findViewById(C0376R.id.center_button);
        this.f2537f = (DoubleLayerImageView) findViewById(C0376R.id.right_button);
        this.f2538g = (SimpleDraweeView) findViewById(C0376R.id.top_button);
        this.f2539h = (SimpleDraweeView) findViewById(C0376R.id.hide_left_button);
        this.f2540i = (ImageView) findViewById(C0376R.id.hide_right_button);
        this.f2541j = findViewById(C0376R.id.indicator);
        this.f2547p = (RelativeLayout) findViewById(C0376R.id.send_gift_tip);
        if (this.f2546o) {
            LayoutParams layoutParams = this.f2536e.getLayoutParams();
            layoutParams.width = getResources().getDimensionPixelOffset(C0376R.dimen.bottom_navi_icon_size);
            layoutParams.height = getResources().getDimensionPixelOffset(C0376R.dimen.bottom_navi_icon_size);
            this.f2536e.setLayoutParams(layoutParams);
            layoutParams = this.f2536e.f2647b.getLayoutParams();
            layoutParams.width = getResources().getDimensionPixelOffset(C0376R.dimen.bottom_navi_icon_size);
            layoutParams.height = getResources().getDimensionPixelOffset(C0376R.dimen.bottom_navi_icon_size);
            this.f2536e.f2647b.setLayoutParams(layoutParams);
            this.f2539h.setAlpha(1.0f);
            this.f2540i.setAlpha(1.0f);
            this.f2539h.setVisibility(0);
            this.f2540i.setVisibility(0);
            this.f2533b.setVisibility(8);
            this.f2537f.setVisibility(8);
            this.f2538g.setVisibility(8);
        } else {
            this.f2539h.setEnabled(false);
            this.f2540i.setEnabled(false);
        }
        m1651f();
        this.f2533b.setOnClickListener(BottomNavigationView$$Lambda$3.lambdaFactory$(this));
        this.f2537f.setOnClickListener(BottomNavigationView$$Lambda$4.lambdaFactory$(this));
        this.f2538g.setOnClickListener(BottomNavigationView$$Lambda$5.lambdaFactory$(this));
        this.f2539h.setOnClickListener(BottomNavigationView$$Lambda$6.lambdaFactory$(this));
        this.f2540i.setOnClickListener(BottomNavigationView$$Lambda$7.lambdaFactory$(this));
        this.f2536e.setOnClickListener(new C04881(this));
        if (!isInEditMode()) {
            this.f2544m = DisplayUtil.getDisplayWidth();
        }
        m1640a(FUUtil.getInstance().getCurrentCoverUrl());
    }

    /* synthetic */ void m1664e(View v) {
        if (!(this.f2532a == null || this.f2542k.get() == avutil.AV_PIX_FMT_XYZ12BE || this.f2546o)) {
            this.f2532a.onItemClick(2);
        }
        this.f2542k.set(avutil.AV_PIX_FMT_XYZ12BE);
    }

    /* synthetic */ void m1663d(View v) {
        if (!(this.f2532a == null || this.f2542k.get() == avutil.AV_PIX_FMT_NV16 || this.f2546o)) {
            this.f2532a.onItemClick(3);
        }
        this.f2542k.set(avutil.AV_PIX_FMT_NV16);
    }

    /* synthetic */ void m1662c(View v) {
        if (this.f2532a != null && this.f2542k.get() == -1 && !this.f2546o) {
            this.f2532a.onItemClick(0);
        }
    }

    /* synthetic */ void m1661b(View v) {
        if (this.f2532a == null) {
            return;
        }
        if (this.f2542k.get() == 11 || this.f2546o) {
            this.f2532a.onItemClick(0);
        }
    }

    /* synthetic */ void m1659a(View v) {
        if (this.f2532a == null) {
            return;
        }
        if (this.f2542k.get() == 11 || this.f2546o) {
            this.f2532a.onItemClick(5);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventBus.getDefault().register(this);
        this.f2545n = Realm.getDefaultInstance().where(TikiUser.class).findAll();
        if (this.f2545n.isValid() && !this.f2545n.isEmpty()) {
            this.f2533b.setUnreadNum(this.f2545n.max("unread").intValue());
        }
        this.f2545n.addChangeListener(this.f2534c);
        this.f2533b.setApplyNum(PreferenceUtil.getApplyNumber());
        PreferenceUtil.getUserSharedPreference().registerOnSharedPreferenceChangeListener(this.f2535d);
    }

    protected void onDetachedFromWindow() {
        PreferenceUtil.getUserSharedPreference().unregisterOnSharedPreferenceChangeListener(this.f2535d);
        EventBus.getDefault().unregister(this);
        if (this.f2545n.isValid()) {
            this.f2545n.removeChangeListeners();
        }
        super.onDetachedFromWindow();
        this.f2532a = null;
    }

    public void registerCallback(@NonNull OnNavigationViewClick onNavigationViewClick) {
        this.f2532a = onNavigationViewClick;
    }

    @UiThread
    public void setBanned(boolean banned) {
        this.f2543l = banned;
        m1653g();
    }

    @UiThread
    @CheckResult
    public boolean onBackCall() {
        if (this.f2542k.get() != 11) {
            return false;
        }
        if (this.f2532a != null) {
            this.f2532a.onItemClick(4);
        }
        m1645c();
        this.f2542k.set(-1);
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFaceUChanged(UseFaceUnityEvent event) {
        m1640a(event.f505c);
    }

    private void m1640a(String cover) {
        GenericDraweeHierarchy hierarchy;
        RoundingParams roundingParams;
        if (TextUtils.isEmpty(cover)) {
            this.f2538g.setImageURI(BuildConfig.VERSION_NAME);
            this.f2539h.setImageURI(BuildConfig.VERSION_NAME);
            hierarchy = (GenericDraweeHierarchy) this.f2538g.getHierarchy();
            roundingParams = hierarchy.getRoundingParams();
            if (roundingParams != null) {
                roundingParams.setBorderWidth(0.0f);
                hierarchy.setRoundingParams(roundingParams);
            }
            hierarchy = (GenericDraweeHierarchy) this.f2539h.getHierarchy();
            roundingParams = hierarchy.getRoundingParams();
            if (roundingParams != null) {
                roundingParams.setBorderWidth(0.0f);
                hierarchy.setRoundingParams(roundingParams);
                return;
            }
            return;
        }
        this.f2538g.setImageURI(cover);
        this.f2539h.setImageURI(cover);
        hierarchy = (GenericDraweeHierarchy) this.f2538g.getHierarchy();
        roundingParams = hierarchy.getRoundingParams();
        if (roundingParams != null) {
            roundingParams.setBorderWidth((float) DisplayUtil.dip2px(1.5f));
            roundingParams.setBorderColor(-1);
            hierarchy.setRoundingParams(roundingParams);
        }
        hierarchy = (GenericDraweeHierarchy) this.f2539h.getHierarchy();
        roundingParams = hierarchy.getRoundingParams();
        if (roundingParams != null) {
            roundingParams.setBorderWidth((float) DisplayUtil.dip2px(1.5f));
            roundingParams.setBorderColor(-1);
            hierarchy.setRoundingParams(roundingParams);
        }
    }

    @UiThread
    public void translateView(float translationX, @FloatRange(from = 0.0d, to = 1.0d) float translationPercent) {
        boolean towardLeft;
        if (translationX > 0.0f) {
            towardLeft = true;
        } else {
            towardLeft = false;
        }
        translationX = Math.abs(translationX);
        float scaleSize = 1.0f - (0.25f * translationPercent);
        float actualTranslationX = translationX / 5.0f;
        this.f2533b.setTranslationX(actualTranslationX);
        this.f2533b.setScaleX(scaleSize);
        this.f2533b.setScaleY(scaleSize);
        this.f2537f.setTranslationX(-actualTranslationX);
        this.f2537f.setScaleX(scaleSize);
        this.f2537f.setScaleY(scaleSize);
        this.f2536e.m1695a(translationPercent);
        this.f2538g.setAlpha(1.0f - translationPercent);
        this.f2541j.setScaleX(translationPercent);
        this.f2541j.setAlpha(translationPercent);
        this.f2541j.setTranslationX(((float) (towardLeft ? -1 : 1)) * ((((((float) this.f2544m) / 2.0f) - (((float) (towardLeft ? this.f2533b.getWidth() : this.f2537f.getWidth())) / 2.0f)) - (((float) this.f2544m) / 5.0f)) * translationPercent));
        this.f2533b.translationView(actualTranslationX, translationPercent);
        this.f2537f.translationView(-actualTranslationX, translationPercent);
        DrawableUtil.tintDrawable(getContext(), this.f2536e.f2647b, C0376R.mipmap.tabbar_explore_60, translationPercent, -1, ContextCompat.getColor(getContext(), C0376R.color.bottomNaviTintColor));
        if (translationPercent == 1.0f) {
            this.f2542k.set(towardLeft ? avutil.AV_PIX_FMT_XYZ12BE : avutil.AV_PIX_FMT_NV16);
            this.f2538g.setVisibility(8);
        } else {
            this.f2538g.setVisibility(0);
        }
        if (translationX == 0.0f) {
            this.f2542k.set(-1);
            this.f2538g.setEnabled(true);
            m1651f();
            if (this.f2543l) {
                this.f2536e.setAlpha(0.5f);
                return;
            } else {
                this.f2536e.setAlpha(1.0f);
                return;
            }
        }
        this.f2538g.setEnabled(false);
        this.f2536e.setAlpha(1.0f);
    }

    private void m1643b() {
        m1647d();
        m1649e();
        AnimatorSet beforeAnimator = new AnimatorSet();
        final AnimatorSet afterAnimator = new AnimatorSet();
        ObjectAnimator unmatchLeftAnimator = ObjectAnimator.ofFloat(this.f2533b, "alpha", new float[]{1.0f, 0.0f});
        ObjectAnimator unmatchRightAniamtor = ObjectAnimator.ofFloat(this.f2537f, "alpha", new float[]{1.0f, 0.0f});
        ObjectAnimator unmatchTopAnimator = ObjectAnimator.ofFloat(this.f2538g, "alpha", new float[]{1.0f, 0.0f});
        final ObjectAnimator matchLeftAnimator = ObjectAnimator.ofFloat(this.f2539h, "alpha", new float[]{0.0f, 1.0f});
        final ObjectAnimator matchRightAnimator = ObjectAnimator.ofFloat(this.f2540i, "alpha", new float[]{0.0f, 1.0f});
        beforeAnimator.setDuration(500).setInterpolator(new FastOutLinearInInterpolator());
        afterAnimator.setDuration(500).setInterpolator(new FastOutLinearInInterpolator());
        beforeAnimator.addListener(new AnimatorListener(this) {
            final /* synthetic */ BottomNavigationView f2526d;

            public void onAnimationStart(Animator animation) {
            }

            public void onAnimationEnd(Animator animation) {
                this.f2526d.f2533b.setVisibility(8);
                this.f2526d.f2537f.setVisibility(8);
                this.f2526d.f2538g.setVisibility(8);
                this.f2526d.f2536e.setEnabled(true);
                this.f2526d.f2540i.setEnabled(true);
                this.f2526d.f2539h.setEnabled(true);
                this.f2526d.f2539h.setVisibility(0);
                this.f2526d.f2540i.setVisibility(0);
                afterAnimator.playTogether(new Animator[]{matchLeftAnimator, matchRightAnimator});
                afterAnimator.start();
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
        beforeAnimator.playTogether(new Animator[]{unmatchLeftAnimator, unmatchRightAniamtor, unmatchTopAnimator});
        beforeAnimator.start();
    }

    private void m1645c() {
        this.f2539h.setEnabled(false);
        this.f2540i.setEnabled(false);
        m1649e();
        AnimatorSet beforeAnimator = new AnimatorSet();
        final AnimatorSet afterAnimator = new AnimatorSet();
        final ObjectAnimator unmatchLeftAnimator = ObjectAnimator.ofFloat(this.f2533b, "alpha", new float[]{0.0f, 1.0f});
        final ObjectAnimator unmatchRightAniamtor = ObjectAnimator.ofFloat(this.f2537f, "alpha", new float[]{0.0f, 1.0f});
        final ObjectAnimator unmatchTopAnimator = ObjectAnimator.ofFloat(this.f2538g, "alpha", new float[]{0.0f, 1.0f});
        ObjectAnimator matchLeftAnimator = ObjectAnimator.ofFloat(this.f2539h, "alpha", new float[]{1.0f, 0.0f});
        ObjectAnimator matchRightAnimator = ObjectAnimator.ofFloat(this.f2540i, "alpha", new float[]{1.0f, 0.0f});
        afterAnimator.setDuration(500).setInterpolator(new FastOutLinearInInterpolator());
        beforeAnimator.setDuration(500).setInterpolator(new FastOutLinearInInterpolator());
        beforeAnimator.addListener(new AnimatorListener(this) {
            final /* synthetic */ BottomNavigationView f2531e;

            public void onAnimationStart(Animator animation) {
            }

            public void onAnimationEnd(Animator animation) {
                this.f2531e.f2539h.setVisibility(8);
                this.f2531e.f2540i.setVisibility(8);
                this.f2531e.f2533b.setEnabled(true);
                this.f2531e.f2537f.setEnabled(true);
                this.f2531e.f2538g.setEnabled(true);
                this.f2531e.f2533b.setVisibility(0);
                this.f2531e.f2537f.setVisibility(0);
                this.f2531e.f2538g.setVisibility(0);
                afterAnimator.playTogether(new Animator[]{unmatchLeftAnimator, unmatchRightAniamtor, unmatchTopAnimator});
                afterAnimator.start();
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
        beforeAnimator.playTogether(new Animator[]{matchLeftAnimator, matchRightAnimator});
        beforeAnimator.start();
    }

    private void m1647d() {
        this.f2533b.setEnabled(false);
        this.f2537f.setEnabled(false);
        this.f2536e.setEnabled(false);
        this.f2538g.setEnabled(false);
    }

    private void m1649e() {
        if (!this.f2546o) {
            String uri = BuildConfig.VERSION_NAME;
            Log.e("TAG", "startCenterAnimation: matchState " + this.f2542k.get());
            if (this.f2542k.get() == -1) {
                uri = "res://" + getContext().getPackageName() + "/" + C0376R.raw.launch;
                this.f2536e.f2646a.setAlpha(0.0f);
            } else if (this.f2542k.get() == 11) {
                uri = "res://" + getContext().getPackageName() + "/" + C0376R.raw.close;
                postDelayed(BottomNavigationView$$Lambda$8.lambdaFactory$(this), 1050);
            }
            this.f2536e.f2647b.setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(uri).setAutoPlayAnimations(true)).build());
        }
    }

    /* synthetic */ void m1657a() {
        this.f2536e.f2646a.setAlpha(0.1f);
    }

    private void m1651f() {
        if (this.f2546o) {
            this.f2536e.f2647b.setImageURI("res://" + getContext().getPackageName() + "/" + C0376R.mipmap.tabbar_close_32);
            return;
        }
        Log.e("TAG", "setAnimatedDrawable: matchState " + this.f2542k.get());
        String uri = "res://" + getContext().getPackageName() + "/" + C0376R.raw.launch;
        if (this.f2542k.get() == -1) {
            uri = "res://" + getContext().getPackageName() + "/" + C0376R.mipmap.tabbar_explore_60;
        } else if (this.f2542k.get() == 11) {
            uri = "res://" + getContext().getPackageName() + "/" + C0376R.mipmap.tabbar_close_32;
        }
        if (!TextUtils.isEmpty(uri)) {
            this.f2536e.f2647b.setImageURI(uri);
        }
    }

    private void m1653g() {
        ExploreImageView exploreImageView = this.f2536e;
        float f = (!this.f2543l || this.f2542k.get() == avutil.AV_PIX_FMT_XYZ12BE || this.f2542k.get() == avutil.AV_PIX_FMT_NV16) ? 1.0f : 0.5f;
        exploreImageView.setAlpha(f);
    }

    public void setSendGiftTipVisibility(boolean visible) {
        if (visible) {
            this.f2547p.setVisibility(0);
        } else {
            this.f2547p.setVisibility(8);
        }
    }

    public View getExploreButton() {
        return this.f2536e;
    }

    public void setTourGuide(TourGuide tourguide) {
        this.f2548q = new WeakReference(tourguide);
    }

    public void startMatch(boolean useCallback) {
        if (!this.f2543l) {
            RushNotificationManager.getInstance().dismiss(getContext());
            EventBus.getDefault().postSticky(new MatchStateEvent(true));
            if (useCallback && this.f2532a != null) {
                this.f2532a.onItemClick(1);
            }
            m1643b();
            this.f2542k.set(11);
        }
    }

    public void startMatch() {
        startMatch(true);
    }

    public void stopMatch() {
        EventBus.getDefault().postSticky(new MatchStateEvent(false));
        if (this.f2532a != null) {
            this.f2532a.onItemClick(4);
        }
        m1645c();
        this.f2542k.set(-1);
    }
}
