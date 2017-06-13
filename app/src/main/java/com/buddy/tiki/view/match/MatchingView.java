package com.buddy.tiki.view.match;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.recyclerview.BuildConfig;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.drawable.LoopTransitionDrawable;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.app.OperInfo;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.util.FrescoUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.view.DotTailTextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.Random;
import org.bytedeco.javacpp.postproc;
import org.bytedeco.javacpp.swscale;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class MatchingView extends RelativeLayout {
    public static final int[] f3165a = new int[]{34, 30, 35, 52};
    public static final int[] f3166b = new int[]{10, 10, 10, 52};
    private static final TikiLog f3167c = TikiLog.getInstance(MatchingView.class.getSimpleName());
    private static final String[] f3168d = new String[]{"\u665a\u4e0a8\u70b9-12\u70b9\uff0c\u804a\u5929\u9ad8\u5cf0\uff0c\u8bb0\u5f97\u6765\u54df\uff01", "\u6536\u5230\u793c\u7269\u6253\u8d4f\u540e\uff0c\u53ef\u4ee5\u5230\u4e2a\u4eba\u4e3b\u9875\u63d0\u73b0\u54e6", "\u7cfb\u7edf\u624b\u52a8\u7206\u6599\uff0cX-MAN\u5df2\u7ecf\u4e0a\u7ebf\uff0c\u6b63\u5728\u968f\u673a\u9001\u793c\uff01", "\u4e3b\u4eba\uff0c\u4f60\u7684\u5c0f\u91d1\u5e93\u5feb\u8981\u997f\u6b7b\u4e86\uff0c\u5145\u70b9\u94bb\u77f3\u62a2\u6551\u4e00\u4e0b\uff01", "\u524d\u65b9\u51fa\u73b0\u4e00\u679a\u5c0f\u9c9c\u8089\uff0c\u7cfb\u7edf\u6b63\u5728\u4e3a\u4f60\u6355\u83b7\u3002", "\u7cfb\u7edf\u6b63\u5728\u52aa\u529b\u53ec\u5524X-MAN\uff0c\u610f\u5916\u60ca\u559c\u7b49\u7740\u4f60\u54e6\uff01", "\u6b63\u5728\u8fdb\u5165\u9ad8\u989c\u503c\u5730\u5e26\uff0c\u524d\u65b9\u9ad8\u80fd\u9884\u8b66\uff01", "\u8fd0\u8425\u5c0f\u7f16\u5154\u5b50\u63d2\u64ad\u4e00\u6761\u5e7f\u544a\uff1a\u8bf7\u6765\u516c\u4f17\u53f7\uff08HelloTiki\uff09\u8c03\u620f\u6211\uff01", "\u5728\u5fae\u4fe1\u516c\u4f17\u53f7TikiApp\u5145\u503c\u6709\u4f18\u60e0\u54df~", "\u6e2f\u771f\uff0c\u4e0d\u8981\u5728\u89c6\u9891\u4e2d\u6d89\u9ec4\u6d89\u6bd2\uff0c\u7b54\u5e94\u6211\u505a\u4e2a\u597d\u5b9d\u5b9d\uff0c\u8ba4\u771f\u8138\uff01", "\u4e0b\u62c9\u624b\u52bf\uff1a\u53ef\u7ee7\u7eed\u5bfb\u627e\u65b0\u670b\u53cb\u54e6", "\u53f3\u6ed1\u624b\u52bf\uff1a\u53ef\u8fdb\u5165\u597d\u53cb\u5217\u8868", "\u5de6\u6ed1\u624b\u52bf\uff1a\u53ef\u8fdb\u5165\u4e2a\u4eba\u4e3b\u9875"};
    private LinearLayout f3169e;
    private LinearLayout f3170f;
    private TextView f3171g;
    private TextView f3172h;
    private LoopTransitionDrawable f3173i;
    private Handler f3174j;
    private int f3175k;
    private AnimationDrawable f3176l;
    private String[] f3177m;
    private Runnable f3178n;
    private AppCompatTextView f3179o;
    private AppCompatTextView f3180p;
    private DotTailTextView f3181q;
    private TextView f3182r;
    private boolean f3183s;
    private int f3184t;
    private User f3185u;
    private String[] f3186v;
    private Random f3187w;
    private String f3188x;
    private int f3189y;
    private SimpleDraweeView f3190z;

    class C05291 implements Runnable {
        final /* synthetic */ MatchingView f3164a;

        C05291(MatchingView this$0) {
            this.f3164a = this$0;
        }

        public void run() {
            String[] currentStrings = MatchingView.f3168d;
            if (this.f3164a.f3177m != null) {
                currentStrings = this.f3164a.f3177m;
            }
            this.f3164a.f3172h.setText(currentStrings[this.f3164a.f3175k]);
            this.f3164a.f3175k = (this.f3164a.f3175k + 1) % currentStrings.length;
            this.f3164a.f3174j.postDelayed(this, 5000);
        }
    }

    public MatchingView(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        this.f3174j = new Handler();
        this.f3175k = 0;
        this.f3178n = new C05291(this);
        this.f3183s = false;
        this.f3184t = 1;
        this.f3187w = new Random();
        f3167c.m261d("MatchingView()");
        getOperInfo();
        inflate(context, C0376R.layout.view_matching, this);
        measure(MeasureSpec.makeMeasureSpec(DisplayUtil.getDisplayWidth(), postproc.PP_CPU_CAPS_3DNOW), MeasureSpec.makeMeasureSpec(DisplayUtil.getDisplayHeight(), postproc.PP_CPU_CAPS_3DNOW));
        this.f3182r = (TextView) findViewById(C0376R.id.matching_title);
        this.f3169e = (LinearLayout) findViewById(C0376R.id.info_layout);
        LayoutParams layoutParams = (LayoutParams) this.f3169e.getLayoutParams();
        layoutParams.setMargins(0, (DisplayUtil.getDisplayHeight() - this.f3169e.getMeasuredHeight()) / 2, 0, 0);
        this.f3169e.setLayoutParams(layoutParams);
        this.f3171g = (TextView) findViewById(C0376R.id.match_indicator);
        this.f3171g.setVisibility(4);
        this.f3172h = (TextView) findViewById(C0376R.id.matching_info);
        this.f3170f = (LinearLayout) findViewById(C0376R.id.match_layout);
        this.f3190z = (SimpleDraweeView) findViewById(C0376R.id.avatar);
        this.f3179o = (AppCompatTextView) findViewById(C0376R.id.nick);
        this.f3180p = (AppCompatTextView) findViewById(C0376R.id.match_info);
        this.f3181q = new DotTailTextView(this.f3180p, false);
        this.f3173i = new LoopTransitionDrawable((long) this.f3187w.nextInt(2000));
        this.f3173i.setColors(new int[]{Color.parseColor("#FFDC1E"), Color.parseColor("#19FFE1"), Color.parseColor("#1973FF"), Color.parseColor("#F519FF"), Color.parseColor("#FF7D19")});
        this.f3173i.setInterval(PointerIconCompat.TYPE_DEFAULT);
        this.f3173i.setReverseDrawOrder(false);
        this.f3173i.setShapeOnly(false);
        setBackground(this.f3173i);
        this.f3176l = (AnimationDrawable) ((AppCompatImageView) findViewById(C0376R.id.tiki_loading)).getDrawable();
    }

    public MatchingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatchingView(Context context) {
        this(context, null);
    }

    public static int getIndicatorHeight(Context context, int width, int height) {
        MatchingView matchingView = new MatchingView(context);
        matchingView.measure(MeasureSpec.makeMeasureSpec(width, postproc.PP_CPU_CAPS_3DNOW), MeasureSpec.makeMeasureSpec(height, postproc.PP_CPU_CAPS_3DNOW));
        return matchingView.f3171g.getMeasuredHeight();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        m1999b();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m2003d();
    }

    private synchronized void m1999b() {
        m2001c();
    }

    private void m2001c() {
        switch (this.f3184t) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                m2008i();
                m2007h();
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                m2009j();
                m2006g();
                break;
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                m2009j();
                m2007h();
                break;
        }
        this.f3183s = true;
    }

    private synchronized void m2003d() {
        m2004e();
    }

    private void m2004e() {
        m2009j();
        m2007h();
        this.f3183s = false;
    }

    private void m2005f() {
        m2004e();
        m2001c();
    }

    private void m2006g() {
    }

    private void m2007h() {
    }

    private void m2008i() {
        if (this.f3176l != null) {
            this.f3176l.start();
        }
        this.f3174j.post(this.f3178n);
    }

    private void m2009j() {
        if (this.f3176l != null) {
            this.f3176l.stop();
        }
        this.f3174j.removeCallbacks(this.f3178n);
    }

    public synchronized void setStatus(int status) {
        switch (status) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                this.f3169e.setVisibility(0);
                this.f3170f.setVisibility(8);
                if (TextUtils.isEmpty(this.f3188x)) {
                    this.f3182r.setText(C0376R.string.finding_new_friend);
                } else if (this.f3189y < 10) {
                    this.f3182r.setText(String.format(getResources().getString(C0376R.string.less_than_n_person_using_passport), new Object[]{Integer.valueOf(10)}));
                } else {
                    this.f3182r.setText(C0376R.string.finding_new_friend);
                }
                this.f3184t = 1;
                m2005f();
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                this.f3169e.setVisibility(8);
                this.f3170f.setVisibility(0);
                this.f3180p.setText(C0376R.string.matched_connecting);
                this.f3181q.start();
                this.f3184t = 2;
                m2005f();
                break;
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                this.f3169e.setVisibility(8);
                this.f3170f.setVisibility(0);
                this.f3181q.stop();
                this.f3180p.setText(C0376R.string.matched_connection_failed);
                this.f3184t = 3;
                m2005f();
                break;
            case swscale.SWS_CS_FCC /*4*/:
                this.f3169e.setVisibility(8);
                this.f3170f.setVisibility(0);
                this.f3181q.stop();
                this.f3180p.setText(C0376R.string.matched_leave_room);
                this.f3184t = 4;
                m2005f();
                break;
        }
    }

    public void setPassort(String passort, int onlines) {
        this.f3188x = passort;
        this.f3189y = onlines;
    }

    public void setMatchedUser(User matchedUser) {
        this.f3185u = matchedUser;
        if (this.f3185u == null) {
            this.f3179o.setText(BuildConfig.VERSION_NAME);
            this.f3179o.setCompoundDrawablesRelative(null, null, null, null);
            this.f3190z.setImageURI((Uri) null);
            return;
        }
        this.f3179o.setText(this.f3185u.getNick());
        Drawable drawableGender = null;
        switch (this.f3185u.getGender()) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                drawableGender = ResourcesCompat.getDrawable(getResources(), C0376R.drawable.icon_male_white, null);
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                drawableGender = ResourcesCompat.getDrawable(getResources(), C0376R.drawable.icon_female_white, null);
                break;
        }
        if (drawableGender != null) {
            drawableGender.setBounds(0, 0, drawableGender.getMinimumWidth(), drawableGender.getMinimumHeight());
        }
        this.f3179o.setCompoundDrawablesRelative(null, null, drawableGender, null);
        FrescoUtil.setImageURI(this.f3190z, this.f3185u.getAvatar());
    }

    private void m2010k() {
        if (this.f3186v != null && this.f3186v.length <= 0) {
        }
    }

    private void getOperInfo() {
        DataLayer.getInstance().getAppManager().getOperInfoCache().compose(SchedulersCompat.applyIoSchedulers()).subscribe(MatchingView$$Lambda$1.lambdaFactory$(this), MatchingView$$Lambda$2.lambdaFactory$());
    }

    /* synthetic */ void m2011a(OperInfo operInfo) throws Exception {
        if (operInfo != null) {
            this.f3177m = operInfo.getSwitchContents();
            this.f3186v = operInfo.getSwitchImages();
            m2010k();
        }
    }
}
