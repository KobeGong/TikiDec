package com.buddy.tiki.view.match;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.util.DisplayUtil;
import org.bytedeco.javacpp.postproc;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class MatchLayout extends ViewGroup {
    private static final TikiLog f3101a = TikiLog.getInstance(MatchLayout.class.getSimpleName());
    private boolean f3102A;
    private View f3103B;
    private View f3104C;
    private ViewDragHelper f3105b;
    private float f3106c;
    private MatchingView f3107d;
    private MatchingView f3108e;
    private MatchingView f3109f;
    private View f3110g;
    private View f3111h;
    private View f3112i;
    private View f3113j;
    private View f3114k;
    private View f3115l;
    private View f3116m;
    private View f3117n;
    private int f3118o;
    private int f3119p;
    private int f3120q;
    private int f3121r;
    private boolean f3122s;
    private boolean f3123t;
    private boolean f3124u;
    private boolean f3125v;
    private int f3126w;
    private boolean f3127x;
    private DragListener f3128y;
    private boolean f3129z;

    public interface DragListener {
        void onFlingDown();

        void onFlingUp();

        void onHorizontalPositionChanged(float f);
    }

    public static class LayoutParams extends MarginLayoutParams {
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            this(width, height, 0, 0);
        }

        public LayoutParams(int width, int height, int x, int y) {
            super(width, height);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
        }
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return m1963a();
    }

    public MatchLayout(Context context) {
        this(context, null);
    }

    public MatchLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatchLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.f3106c = -0.33333334f;
        this.f3125v = true;
        this.f3126w = 0;
        this.f3127x = false;
        this.f3129z = true;
        this.f3102A = true;
        m1944c();
        m1948d();
    }

    private void m1944c() {
        if (!isInEditMode()) {
            this.f3119p = MatchingView.getIndicatorHeight(getContext(), DisplayUtil.getDisplayWidth(), DisplayUtil.getDisplayHeight());
            if (this.f3119p > 0) {
                this.f3118o = this.f3119p * 2;
            } else {
                this.f3118o = DisplayUtil.getDisplayHeight() / 10;
            }
        }
    }

    public void init(@NonNull View matchView1, @NonNull View matchView2, @NonNull View matchView3, @NonNull View horizontalPanel, @NonNull View moveDetectView, @NonNull View localPreview, @NonNull View matchFilter, @NonNull View upGradientMask, @NonNull View faceDetectView) {
        this.f3110g = matchView1;
        this.f3111h = matchView2;
        this.f3112i = matchView3;
        this.f3113j = horizontalPanel;
        this.f3115l = moveDetectView;
        this.f3114k = localPreview;
        this.f3116m = matchFilter;
        this.f3103B = upGradientMask;
        this.f3104C = faceDetectView;
        this.f3120q = 777;
        this.f3121r = 230;
        if (!isInEditMode()) {
            this.f3120q = ((DisplayUtil.getDisplayWidth() * 75) / 100) - DisplayUtil.dip2px(16.0f);
            this.f3121r = DisplayUtil.dip2px(16.0f) + getResources().getDimensionPixelOffset(C0376R.dimen.status_bar_height);
        }
        this.f3107d = (MatchingView) this.f3110g.findViewById(C0376R.id.inner_match_1);
        this.f3108e = (MatchingView) this.f3111h.findViewById(C0376R.id.inner_match_2);
        this.f3109f = (MatchingView) this.f3112i.findViewById(C0376R.id.inner_match_3);
        this.f3122s = false;
        this.f3123t = false;
        this.f3124u = false;
        this.f3125v = true;
        this.f3106c = -0.33333334f;
        this.f3115l.setBackgroundColor(0);
    }

    private void m1948d() {
        final float minVel = 400.0f * getResources().getDisplayMetrics().density;
        this.f3105b = ViewDragHelper.create(this, 1.0f, new Callback(this) {
            final /* synthetic */ MatchLayout f3099b;
            private boolean f3100c = false;

            public int clampViewPositionHorizontal(View child, int left, int dx) {
                int newLeft = left;
                if (child == this.f3099b.f3115l) {
                    int hPanelLeft = this.f3099b.f3113j.getLeft();
                    return (left - dx) + (Math.max(((-this.f3099b.f3113j.getMeasuredWidth()) * 2) / 3, Math.min(hPanelLeft + dx, 0)) - hPanelLeft);
                } else if (child == this.f3099b.f3114k) {
                    return Math.max(0, Math.min(left, this.f3099b.f3110g.getMeasuredWidth() - child.getWidth()));
                } else {
                    return newLeft;
                }
            }

            public int clampViewPositionVertical(View child, int top, int dy) {
                int newTop = top;
                if (child == this.f3099b.f3115l) {
                    return Math.max(-child.getHeight(), Math.min(top, child.getHeight()));
                }
                if (child == this.f3099b.f3114k) {
                    return Math.max(0, Math.min(top, (this.f3099b.f3110g.getHeight() / 2) - this.f3099b.f3114k.getHeight()));
                }
                return newTop;
            }

            public boolean tryCaptureView(View child, int pointerId) {
                boolean result = false;
                if (child == this.f3099b.f3115l) {
                    result = true;
                }
                if (child == this.f3099b.f3114k) {
                    return true;
                }
                return result;
            }

            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                MatchLayout.f3101a.m261d("onEdgeDragStarted:flags:" + edgeFlags);
            }

            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if (releasedChild == this.f3099b.f3115l) {
                    MarginLayoutParams lp;
                    View moveDetectView = this.f3099b.f3115l;
                    if (this.f3099b.f3126w == 1) {
                        MarginLayoutParams lpL = (MarginLayoutParams) this.f3099b.f3113j.getLayoutParams();
                        int leftL = this.f3099b.f3113j.getLeft();
                        int hPanelWidth = this.f3099b.f3113j.getMeasuredWidth();
                        if (leftL >= lpL.leftMargin - (hPanelWidth / 3)) {
                            if (xvel > minVel) {
                                MatchLayout.f3101a.m263e("//\u5de6\u4fa7\u754c\u9762\u663e\u793a//\u6ed1\u52a8\u624b\u52bf\u5411\u53f3");
                                this.f3099b.showLeftPanel();
                            } else if (xvel < (-minVel)) {
                                MatchLayout.f3101a.m263e("//\u5de6\u4fa7\u754c\u9762\u663e\u793a//\u6ed1\u52a8\u624b\u52bf\u5411\u5de6");
                                this.f3099b.hidePanelWithSmoothSlide();
                            } else if (leftL < lpL.leftMargin - (hPanelWidth / 6)) {
                                MatchLayout.f3101a.m263e("//\u5de6\u4fa7\u754c\u9762\u663e\u793a//\u5de6\u4fa7\u754c\u9762\u4e0d\u5230\u4e00\u534a\u663e\u793a");
                                this.f3099b.hidePanelWithSmoothSlide();
                            } else {
                                MatchLayout.f3101a.m263e("//\u5de6\u4fa7\u754c\u9762\u663e\u793a//\u5de6\u4fa7\u754c\u9762\u8d85\u8fc7\u4e00\u534a\u663e\u793a");
                                this.f3099b.showLeftPanel();
                            }
                        } else if (leftL < lpL.leftMargin - (hPanelWidth / 3)) {
                            if (xvel > minVel) {
                                MatchLayout.f3101a.m263e("//\u53f3\u4fa7\u754c\u9762\u663e\u793a//\u6ed1\u52a8\u624b\u52bf\u5411\u53f3");
                                this.f3099b.hidePanelWithSmoothSlide();
                            } else if (xvel < (-minVel)) {
                                MatchLayout.f3101a.m263e("//\u53f3\u4fa7\u754c\u9762\u663e\u793a//\u6ed1\u52a8\u624b\u52bf\u5411\u5de6");
                                this.f3099b.showRightPanel();
                            } else if (leftL > lpL.leftMargin - (hPanelWidth / 2)) {
                                MatchLayout.f3101a.m263e("//\u53f3\u4fa7\u754c\u9762\u663e\u793a//\u53f3\u4fa7\u754c\u9762\u4e0d\u5230\u4e00\u534a\u663e\u793a");
                                this.f3099b.hidePanelWithSmoothSlide();
                            } else {
                                MatchLayout.f3101a.m263e("//\u53f3\u4fa7\u754c\u9762\u663e\u793a//\u53f3\u4fa7\u754c\u9762\u8d85\u8fc7\u4e00\u534a\u663e\u793a");
                                this.f3099b.showRightPanel();
                            }
                        }
                    } else if (this.f3099b.f3126w == 2) {
                        View matchView3;
                        if (yvel > minVel) {
                            MatchLayout.f3101a.m263e("//\u4e0a\u4e0b\u6ed1\u52a8//\u6ed1\u52a8\u624b\u52bf\u5411\u4e0b");
                            matchView3 = this.f3099b.f3112i;
                            this.f3099b.f3105b.smoothSlideViewTo(matchView3, ((MarginLayoutParams) matchView3.getLayoutParams()).leftMargin, 0);
                            this.f3100c = true;
                        } else if (yvel < (-minVel)) {
                            MatchLayout.f3101a.m263e("//\u4e0a\u4e0b\u6ed1\u52a8//\u6ed1\u52a8\u624b\u52bf\u5411\u4e0a");
                            matchView3 = this.f3099b.f3112i;
                            lp = (MarginLayoutParams) matchView3.getLayoutParams();
                            this.f3099b.f3105b.smoothSlideViewTo(matchView3, lp.leftMargin, lp.topMargin - (matchView3.getMeasuredHeight() / 2));
                        } else {
                            int matchView3Top = this.f3099b.f3112i.getTop();
                            if (matchView3Top - ((-this.f3099b.f3112i.getMeasuredHeight()) / 2) < this.f3099b.f3118o) {
                                MatchLayout.f3101a.m263e("//\u4e0a\u4e0b\u6ed1\u52a8//\u6ed1\u52a8\u6ed1\u52a8\u505c\u6b62\u540e\u4e0b\u62c9\u9ad8\u5ea6\u5c0f\u4e8e\u9700\u8981\u5237\u65b0\u7684\u9608\u503c:" + matchView3Top);
                                matchView3 = this.f3099b.f3112i;
                                lp = (MarginLayoutParams) matchView3.getLayoutParams();
                                this.f3099b.f3105b.smoothSlideViewTo(matchView3, lp.leftMargin, lp.topMargin - (matchView3.getMeasuredHeight() / 2));
                            } else {
                                MatchLayout.f3101a.m263e("//\u4e0a\u4e0b\u6ed1\u52a8//\u6ed1\u52a8\u6ed1\u52a8\u505c\u6b62\u540e\u4e0b\u62c9\u9ad8\u5ea6\u5927\u4e8e\u7b49\u4e8e\u9700\u8981\u5237\u65b0\u7684\u9608\u503c:" + matchView3Top);
                                matchView3 = this.f3099b.f3112i;
                                this.f3099b.f3105b.smoothSlideViewTo(matchView3, ((MarginLayoutParams) matchView3.getLayoutParams()).leftMargin, 0);
                                this.f3100c = true;
                            }
                        }
                    }
                    this.f3099b.f3126w = 0;
                    this.f3099b.f3125v = true;
                    lp = (MarginLayoutParams) moveDetectView.getLayoutParams();
                    moveDetectView.layout(lp.leftMargin, lp.topMargin, lp.leftMargin + this.f3099b.f3115l.getMeasuredWidth(), lp.topMargin + this.f3099b.f3115l.getMeasuredHeight());
                }
                this.f3099b.invalidate();
            }

            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
                if (this.f3099b.f3105b.getCapturedView() == this.f3099b.f3112i) {
                    if (state != 2) {
                        if (state == 0) {
                            if (this.f3100c) {
                                MatchLayout.f3101a.m261d("swap match views");
                                this.f3099b.removeView(this.f3099b.f3110g);
                                this.f3099b.addView(this.f3099b.f3110g, 2);
                                this.f3099b.f3122s = false;
                                this.f3099b.f3123t = true;
                                this.f3099b.f3124u = false;
                                View temp = this.f3099b.f3110g;
                                this.f3099b.f3110g = this.f3099b.f3111h;
                                this.f3099b.f3111h = this.f3099b.f3112i;
                                this.f3099b.f3112i = temp;
                                MatchingView tempView = this.f3099b.f3107d;
                                this.f3099b.f3107d = this.f3099b.f3108e;
                                this.f3099b.f3108e = this.f3099b.f3109f;
                                this.f3099b.f3109f = tempView;
                                if (this.f3099b.f3128y != null && this.f3100c) {
                                    this.f3099b.f3128y.onFlingDown();
                                }
                                this.f3100c = false;
                                this.f3099b.invalidate();
                            }
                        } else if (state != 1) {
                        }
                    }
                } else if (this.f3099b.f3105b.getCapturedView() == this.f3099b.f3113j) {
                    MatchLayout.f3101a.m261d("onViewDragStateChanged:mHPanel");
                }
            }

            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                if (changedView == this.f3099b.f3115l) {
                    if (this.f3099b.f3125v) {
                        this.f3099b.f3125v = false;
                        if (Math.abs(dx) > Math.abs(dy)) {
                            MatchLayout.f3101a.m261d("//\u6c34\u5e73\u52a8");
                            if (this.f3099b.f3102A) {
                                this.f3099b.f3126w = 1;
                            }
                        } else if (this.f3099b.f3113j.getLeft() == (-this.f3099b.f3113j.getMeasuredWidth()) / 3) {
                            MatchLayout.f3101a.m261d("//\u5782\u76f4\u52a8");
                            if (this.f3099b.f3129z) {
                                this.f3099b.f3126w = 2;
                            }
                        } else {
                            MatchLayout.f3101a.m261d("//\u5782\u76f4\u52a8//\u7981\u6b62:" + this.f3099b.f3113j.getLeft());
                            this.f3099b.f3126w = 3;
                        }
                    }
                    switch (this.f3099b.f3126w) {
                        case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                            View hPanel = this.f3099b.f3113j;
                            int l = hPanel.getLeft();
                            int r = hPanel.getRight();
                            int t = hPanel.getTop();
                            int b = hPanel.getBottom();
                            int newL = Math.max(l + dx, ((-hPanel.getMeasuredWidth()) * 2) / 3);
                            hPanel.layout(newL, t, hPanel.getMeasuredWidth() + newL, b);
                            this.f3099b.f3106c = (((float) newL) * 1.0f) / ((float) hPanel.getMeasuredWidth());
                            if (this.f3099b.f3128y != null) {
                                this.f3099b.f3128y.onHorizontalPositionChanged((float) (newL - ((-hPanel.getMeasuredWidth()) / 3)));
                                break;
                            }
                            break;
                        case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                            View matchView3 = this.f3099b.f3112i;
                            matchView3.layout(matchView3.getLeft(), matchView3.getTop() + dy, matchView3.getRight(), matchView3.getBottom() + dy);
                            break;
                    }
                } else if (changedView == this.f3099b.f3114k) {
                    this.f3099b.f3120q = left;
                    this.f3099b.f3121r = top;
                } else if (!(changedView == this.f3099b.f3112i || changedView != this.f3099b.f3113j || this.f3099b.f3128y == null)) {
                    this.f3099b.f3128y.onHorizontalPositionChanged((float) (left - ((-this.f3099b.f3113j.getMeasuredWidth()) / 3)));
                }
                this.f3099b.invalidate();
            }

            public int getViewHorizontalDragRange(View child) {
                int range = 0;
                if (this.f3099b.f3115l == child) {
                    range = child.getWidth();
                } else if (this.f3099b.f3114k == child) {
                    range = this.f3099b.f3110g.getWidth();
                }
                if (this.f3099b.f3113j == child) {
                    return this.f3099b.f3113j.getWidth();
                }
                return range;
            }

            public int getViewVerticalDragRange(View child) {
                if (this.f3099b.f3115l == child) {
                    if (this.f3099b.f3113j.getLeft() == (-this.f3099b.f3113j.getMeasuredWidth()) / 3) {
                        return child.getHeight();
                    }
                    return 0;
                } else if (this.f3099b.f3114k == child) {
                    return child.getHeight() / 2;
                } else {
                    return 0;
                }
            }
        });
        this.f3105b.setMinVelocity(minVel);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        MarginLayoutParams lp;
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthSize, heightSize);
        View hPanel = this.f3113j;
        if (hPanel != null) {
            lp = (MarginLayoutParams) hPanel.getLayoutParams();
            if (lp != null) {
                hPanel.measure(MeasureSpec.makeMeasureSpec(((widthSize * 3) - lp.leftMargin) - lp.rightMargin, postproc.PP_CPU_CAPS_3DNOW), MeasureSpec.makeMeasureSpec((heightSize - lp.topMargin) - lp.bottomMargin, postproc.PP_CPU_CAPS_3DNOW));
            }
        }
        View matchView3 = this.f3112i;
        if (matchView3 != null) {
            lp = (MarginLayoutParams) matchView3.getLayoutParams();
            if (lp != null) {
                matchView3.measure(MeasureSpec.makeMeasureSpec((widthSize - lp.leftMargin) - lp.rightMargin, postproc.PP_CPU_CAPS_3DNOW), MeasureSpec.makeMeasureSpec(((heightSize * 2) - lp.topMargin) - lp.bottomMargin, postproc.PP_CPU_CAPS_3DNOW));
            }
        }
        View matchView2 = this.f3111h;
        if (matchView2 != null) {
            lp = (MarginLayoutParams) matchView2.getLayoutParams();
            if (lp != null) {
                matchView2.measure(MeasureSpec.makeMeasureSpec((widthSize - lp.leftMargin) - lp.rightMargin, postproc.PP_CPU_CAPS_3DNOW), MeasureSpec.makeMeasureSpec(((heightSize * 2) - lp.topMargin) - lp.bottomMargin, postproc.PP_CPU_CAPS_3DNOW));
            }
        }
        View matchView1 = this.f3110g;
        if (matchView1 != null) {
            lp = (MarginLayoutParams) matchView1.getLayoutParams();
            if (lp != null) {
                matchView1.measure(MeasureSpec.makeMeasureSpec((widthSize - lp.leftMargin) - lp.rightMargin, postproc.PP_CPU_CAPS_3DNOW), MeasureSpec.makeMeasureSpec(((heightSize * 2) - lp.topMargin) - lp.bottomMargin, postproc.PP_CPU_CAPS_3DNOW));
            }
        }
        View faceDetectView = this.f3104C;
        if (faceDetectView != null) {
            lp = (MarginLayoutParams) faceDetectView.getLayoutParams();
            if (lp != null) {
                faceDetectView.measure(MeasureSpec.makeMeasureSpec((widthSize - lp.leftMargin) - lp.rightMargin, postproc.PP_CPU_CAPS_3DNOW), MeasureSpec.makeMeasureSpec((heightSize - lp.topMargin) - lp.bottomMargin, postproc.PP_CPU_CAPS_3DNOW));
            }
        }
        View moveDetectView = this.f3115l;
        if (moveDetectView != null) {
            lp = (MarginLayoutParams) moveDetectView.getLayoutParams();
            if (lp != null) {
                moveDetectView.measure(MeasureSpec.makeMeasureSpec((widthSize - lp.leftMargin) - lp.rightMargin, postproc.PP_CPU_CAPS_3DNOW), MeasureSpec.makeMeasureSpec((heightSize - lp.topMargin) - lp.bottomMargin, postproc.PP_CPU_CAPS_3DNOW));
            }
        }
        View localPreview = this.f3114k;
        if (localPreview != null) {
            float width = 270.0f;
            float height = 480.0f;
            if (!isInEditMode()) {
                width = ((float) (DisplayUtil.getDisplayWidth() * 25)) / 100.0f;
                height = ((float) (DisplayUtil.getDisplayHeight() * 25)) / 100.0f;
            }
            View money_increment = localPreview.findViewById(C0376R.id.t_money_increment);
            if (money_increment != null) {
                money_increment.measure(-2, -2);
                android.view.ViewGroup.LayoutParams layoutParams = money_increment.getLayoutParams();
                int marginTop = 0;
                int marginBottom = 0;
                int marginLeft = 0;
                int marginRight = 0;
                if (layoutParams instanceof MarginLayoutParams) {
                    marginTop = ((MarginLayoutParams) layoutParams).topMargin;
                    marginBottom = ((MarginLayoutParams) layoutParams).bottomMargin;
                    marginLeft = ((MarginLayoutParams) layoutParams).leftMargin;
                    marginRight = ((MarginLayoutParams) layoutParams).rightMargin;
                }
                if (money_increment.getMeasuredHeight() > 0) {
                    height += (float) ((money_increment.getMeasuredHeight() + marginTop) + marginBottom);
                }
                if (((float) ((money_increment.getMeasuredWidth() + marginLeft) + marginRight)) > width) {
                    width = (float) ((money_increment.getMeasuredWidth() + marginLeft) + marginRight);
                }
            }
            lp = (MarginLayoutParams) localPreview.getLayoutParams();
            if (lp != null) {
                localPreview.measure(MeasureSpec.makeMeasureSpec((((int) width) - lp.leftMargin) - lp.rightMargin, postproc.PP_CPU_CAPS_3DNOW), MeasureSpec.makeMeasureSpec((((int) height) - lp.topMargin) - lp.bottomMargin, postproc.PP_CPU_CAPS_3DNOW));
            }
        }
        this.f3117n = findViewById(C0376R.id.connection_hint);
        if (!(this.f3117n == null || ((MarginLayoutParams) this.f3117n.getLayoutParams()) == null)) {
            this.f3117n.measure(MeasureSpec.makeMeasureSpec(widthSize, postproc.PP_CPU_CAPS_3DNOW), MeasureSpec.makeMeasureSpec(getResources().getDimensionPixelSize(C0376R.dimen.height_normal), postproc.PP_CPU_CAPS_3DNOW));
        }
        View matchFilter = this.f3116m;
        if (matchFilter != null) {
            lp = (MarginLayoutParams) matchFilter.getLayoutParams();
            if (lp != null) {
                matchFilter.measure(MeasureSpec.makeMeasureSpec((widthSize - lp.leftMargin) - lp.rightMargin, postproc.PP_CPU_CAPS_3DNOW), MeasureSpec.makeMeasureSpec((getResources().getDimensionPixelSize(C0376R.dimen.match_filter_height) + getResources().getDimensionPixelOffset(C0376R.dimen.status_bar_height)) + getResources().getDimensionPixelOffset(C0376R.dimen.margin_normal), postproc.PP_CPU_CAPS_3DNOW));
            }
        }
        View upGradientMask = this.f3103B;
        if (upGradientMask != null) {
            lp = (MarginLayoutParams) upGradientMask.getLayoutParams();
            if (lp != null) {
                upGradientMask.measure(MeasureSpec.makeMeasureSpec((widthSize - lp.leftMargin) - lp.rightMargin, postproc.PP_CPU_CAPS_3DNOW), MeasureSpec.makeMeasureSpec((getResources().getDimensionPixelSize(C0376R.dimen.height_xlarge) - lp.topMargin) - lp.bottomMargin, postproc.PP_CPU_CAPS_3DNOW));
            }
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        MarginLayoutParams lp;
        View matchView1 = this.f3110g;
        View matchView2 = this.f3111h;
        View matchView3 = this.f3112i;
        View moveDetectView = this.f3115l;
        View localPreview = this.f3114k;
        View matchFilter = this.f3116m;
        View faceView = this.f3104C;
        View connectionView = this.f3117n;
        if (matchView1 != null) {
            lp = (MarginLayoutParams) matchView1.getLayoutParams();
            if (lp != null) {
                matchView1.layout(lp.leftMargin, lp.topMargin + (((this.f3122s ? 0 : -1) * matchView1.getMeasuredHeight()) / 2), matchView1.getMeasuredWidth() + lp.leftMargin, ((((this.f3122s ? 0 : -1) * matchView1.getMeasuredHeight()) / 2) + lp.topMargin) + matchView1.getMeasuredHeight());
            }
        }
        if (matchView2 != null) {
            lp = (MarginLayoutParams) matchView2.getLayoutParams();
            if (lp != null) {
                matchView2.layout(lp.leftMargin, lp.topMargin + (((this.f3123t ? 0 : -1) * matchView2.getMeasuredHeight()) / 2), matchView2.getMeasuredWidth() + lp.leftMargin, ((((this.f3123t ? 0 : -1) * matchView2.getMeasuredHeight()) / 2) + lp.topMargin) + matchView2.getMeasuredHeight());
            }
        }
        if (matchView3 != null) {
            lp = (MarginLayoutParams) matchView3.getLayoutParams();
            if (lp != null) {
                matchView3.layout(lp.leftMargin, lp.topMargin + (((this.f3124u ? 0 : -1) * matchView3.getMeasuredHeight()) / 2), matchView3.getMeasuredWidth() + lp.leftMargin, ((((this.f3124u ? 0 : -1) * matchView3.getMeasuredHeight()) / 2) + lp.topMargin) + matchView3.getMeasuredHeight());
            }
        }
        m1951e();
        if (faceView != null) {
            lp = (MarginLayoutParams) faceView.getLayoutParams();
            if (lp != null) {
                faceView.layout(lp.leftMargin, lp.topMargin, lp.leftMargin + faceView.getMeasuredWidth(), lp.topMargin + faceView.getMeasuredHeight());
            }
        }
        if (moveDetectView != null) {
            lp = (MarginLayoutParams) moveDetectView.getLayoutParams();
            if (lp != null) {
                moveDetectView.layout(lp.leftMargin, lp.topMargin, lp.leftMargin + moveDetectView.getMeasuredWidth(), lp.topMargin + moveDetectView.getMeasuredHeight());
            }
        }
        if (localPreview != null) {
            localPreview.layout(this.f3120q, this.f3121r, this.f3120q + localPreview.getMeasuredWidth(), this.f3121r + localPreview.getMeasuredHeight());
        }
        if (matchFilter != null) {
            lp = (MarginLayoutParams) matchFilter.getLayoutParams();
            if (lp != null) {
                matchFilter.layout(lp.leftMargin, lp.topMargin, lp.leftMargin + matchFilter.getMeasuredWidth(), lp.topMargin + matchFilter.getMeasuredHeight());
            }
        }
        if (connectionView != null) {
            lp = (MarginLayoutParams) connectionView.getLayoutParams();
            if (lp != null) {
                connectionView.layout(lp.leftMargin, lp.topMargin, lp.leftMargin + connectionView.getMeasuredWidth(), lp.topMargin + connectionView.getMeasuredHeight());
            }
        }
        View upGradientMask = this.f3103B;
        if (upGradientMask != null) {
            lp = (MarginLayoutParams) upGradientMask.getLayoutParams();
            if (lp != null) {
                upGradientMask.layout(lp.leftMargin, lp.topMargin, lp.leftMargin + upGradientMask.getMeasuredWidth(), lp.topMargin + upGradientMask.getMeasuredHeight());
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        boolean shouldInterceptTouchEvent = this.f3105b.shouldInterceptTouchEvent(ev);
        if (this.f3126w == 3) {
            return false;
        }
        return shouldInterceptTouchEvent;
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        this.f3105b.processTouchEvent(event);
        return true;
    }

    public void computeScroll() {
        if (this.f3105b.continueSettling(true)) {
            invalidate();
        }
    }

    protected LayoutParams m1963a() {
        return new LayoutParams(-1, -1);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f3127x = true;
    }

    protected void onDetachedFromWindow() {
        this.f3127x = false;
        super.onDetachedFromWindow();
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    public void setDragListener(DragListener dragListener) {
        this.f3128y = dragListener;
    }

    public void showLeftPanel() {
        m1931a(true);
    }

    private void m1931a(boolean invalidate) {
        View hPanel = this.f3113j;
        this.f3106c = 0.0f;
        f3101a.m261d("showLeftPanel:" + this.f3105b.smoothSlideViewTo(hPanel, 0, 0));
        if (invalidate) {
            invalidate();
        }
    }

    public void hidePanel() {
        this.f3106c = -0.33333334f;
        m1951e();
        postInvalidate();
    }

    public void hidePanelWithSmoothSlide() {
        m1938b(true);
    }

    private void m1938b(boolean invalidate) {
        View hPanel = this.f3113j;
        this.f3106c = -0.33333334f;
        f3101a.m261d("hidePanelWithSmoothSlide:" + this.f3105b.smoothSlideViewTo(hPanel, (-hPanel.getWidth()) / 3, 0));
        if (invalidate) {
            invalidate();
        }
    }

    private void m1951e() {
        View hPanel = this.f3113j;
        if (hPanel != null) {
            MarginLayoutParams lp = (MarginLayoutParams) hPanel.getLayoutParams();
            if (lp != null) {
                int hPanelWidth = hPanel.getMeasuredWidth();
                int childLeft = (int) (((float) hPanelWidth) * this.f3106c);
                hPanel.layout(childLeft, lp.topMargin, childLeft + hPanelWidth, lp.topMargin + hPanel.getMeasuredHeight());
            }
        }
    }

    public void showRightPanel() {
        m1945c(true);
    }

    private void m1945c(boolean invalidate) {
        View hPanel = this.f3113j;
        this.f3106c = -0.6666667f;
        f3101a.m261d("showRightPanel:" + this.f3105b.smoothSlideViewTo(hPanel, ((-hPanel.getWidth()) * 2) / 3, 0));
        if (invalidate) {
            invalidate();
        }
    }

    public void enableVerticalDrag(boolean enable) {
        this.f3129z = enable;
    }

    public void enableHorizontalDrag(boolean enable) {
        this.f3102A = enable;
    }

    public void initMatchWithAnime() {
        f3101a.m261d("initMatch");
        MatchingView innerMatchView1 = this.f3107d;
        MatchingView innerMatchView2 = this.f3108e;
        MatchingView innerMatchView3 = this.f3109f;
        View matchView1 = this.f3110g;
        View matchView2 = this.f3111h;
        View matchView3 = this.f3112i;
        this.f3122s = false;
        this.f3123t = true;
        this.f3124u = false;
        innerMatchView1.setPassort(null, 0);
        innerMatchView1.setStatus(1);
        innerMatchView1.setMatchedUser(null);
        matchView1.layout(0, (-matchView1.getMeasuredHeight()) / 2, matchView1.getMeasuredWidth(), matchView1.getMeasuredHeight() / 2);
        innerMatchView2.setPassort(null, 0);
        innerMatchView2.setStatus(1);
        innerMatchView2.setMatchedUser(null);
        matchView2.layout(0, (-matchView2.getMeasuredHeight()) / 2, matchView2.getMeasuredWidth(), matchView2.getMeasuredHeight() / 2);
        innerMatchView3.setPassort(null, 0);
        innerMatchView3.setStatus(1);
        innerMatchView3.setMatchedUser(null);
        matchView3.layout(0, (-matchView3.getMeasuredHeight()) / 2, matchView3.getMeasuredWidth(), matchView3.getMeasuredHeight() / 2);
        this.f3105b.smoothSlideViewTo(this.f3111h, 0, 0);
    }

    public void initMatch() {
        f3101a.m261d("initMatch");
        MatchingView innerMatchView1 = this.f3107d;
        MatchingView innerMatchView2 = this.f3108e;
        MatchingView innerMatchView3 = this.f3109f;
        View matchView1 = this.f3110g;
        View matchView2 = this.f3111h;
        View matchView3 = this.f3112i;
        this.f3122s = false;
        this.f3123t = true;
        this.f3124u = false;
        innerMatchView1.setPassort(null, 0);
        innerMatchView1.setStatus(1);
        innerMatchView1.setMatchedUser(null);
        matchView1.layout(0, (-matchView1.getMeasuredHeight()) / 2, matchView1.getMeasuredWidth(), matchView1.getMeasuredHeight() / 2);
        innerMatchView2.setPassort(null, 0);
        innerMatchView2.setStatus(1);
        innerMatchView2.setMatchedUser(null);
        matchView2.layout(0, 0, matchView2.getMeasuredWidth(), matchView2.getMeasuredHeight());
        innerMatchView3.setPassort(null, 0);
        innerMatchView3.setStatus(1);
        innerMatchView3.setMatchedUser(null);
        matchView3.layout(0, (-matchView3.getMeasuredHeight()) / 2, matchView3.getMeasuredWidth(), matchView3.getMeasuredHeight() / 2);
        invalidate();
    }

    public void startMatch(String passport, int onlineCount) {
        f3101a.m261d("startMatch");
        MatchingView innerMatchView2 = this.f3108e;
        innerMatchView2.setPassort(passport, onlineCount);
        innerMatchView2.setStatus(1);
    }

    public void reset() {
        f3101a.m261d("reset");
        MatchingView innerMatchView1 = this.f3107d;
        MatchingView innerMatchView2 = this.f3108e;
        MatchingView innerMatchView3 = this.f3109f;
        View matchView1 = this.f3110g;
        View matchView2 = this.f3111h;
        View matchView3 = this.f3112i;
        this.f3122s = false;
        this.f3123t = false;
        this.f3124u = false;
        innerMatchView1.setPassort(null, 0);
        innerMatchView1.setStatus(1);
        innerMatchView1.setMatchedUser(null);
        matchView1.layout(0, (-matchView1.getHeight()) / 2, matchView1.getWidth(), matchView1.getHeight() / 2);
        innerMatchView2.setPassort(null, 0);
        innerMatchView2.setStatus(1);
        innerMatchView2.setMatchedUser(null);
        matchView2.layout(0, (-matchView2.getHeight()) / 2, matchView2.getWidth(), matchView2.getHeight() / 2);
        innerMatchView3.setPassort(null, 0);
        innerMatchView3.setStatus(1);
        innerMatchView3.setMatchedUser(null);
        matchView3.layout(0, (-matchView3.getHeight()) / 2, matchView3.getWidth(), matchView3.getHeight() / 2);
        invalidate();
    }

    public void matched(User matchedUser) {
        f3101a.m261d("matched");
        MatchingView innerMatchView2 = this.f3108e;
        innerMatchView2.setMatchedUser(matchedUser);
        innerMatchView2.setStatus(2);
    }
}
