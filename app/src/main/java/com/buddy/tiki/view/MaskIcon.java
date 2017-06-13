package com.buddy.tiki.view;

import android.content.Context;
import android.support.v7.recyclerview.BuildConfig;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.buddy.tiki.event.ResourceEvent.UseFaceUnityEvent;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.util.FUUtil;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MaskIcon extends SimpleDraweeView {
    private String f2741a;

    public MaskIcon(Context context) {
        this(context, null);
    }

    public MaskIcon(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaskIcon(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.f2741a = FUUtil.getInstance().getCurrentCoverUrl();
        m1733e();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventBus.getDefault().register(this);
    }

    protected void onDetachedFromWindow() {
        EventBus.getDefault().unregister(this);
        super.onDetachedFromWindow();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFaceUChanged(UseFaceUnityEvent event) {
        this.f2741a = event.f505c;
        m1733e();
    }

    private void m1733e() {
        GenericDraweeHierarchy hierarchy;
        RoundingParams roundingParams;
        if (TextUtils.isEmpty(this.f2741a)) {
            setImageURI(BuildConfig.VERSION_NAME);
            hierarchy = (GenericDraweeHierarchy) getHierarchy();
            roundingParams = hierarchy.getRoundingParams();
            if (roundingParams != null) {
                roundingParams.setBorderWidth(0.0f);
                hierarchy.setRoundingParams(roundingParams);
                return;
            }
            return;
        }
        setImageURI(this.f2741a);
        hierarchy = (GenericDraweeHierarchy) getHierarchy();
        roundingParams = hierarchy.getRoundingParams();
        if (roundingParams != null) {
            roundingParams.setBorderWidth((float) DisplayUtil.dip2px(1.5f));
            roundingParams.setBorderColor(-1);
            hierarchy.setRoundingParams(roundingParams);
        }
    }
}
