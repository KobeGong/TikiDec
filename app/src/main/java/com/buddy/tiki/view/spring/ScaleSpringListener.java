package com.buddy.tiki.view.spring;

import android.view.View;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringUtil;

public class ScaleSpringListener extends SimpleSpringListener {
    private final View f3196a;

    public ScaleSpringListener(View mView) {
        this.f3196a = mView;
    }

    public void onSpringUpdate(Spring spring) {
        float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0.0d, 1.0d, 1.0d, 0.5d);
        this.f3196a.setScaleX(mappedValue);
        this.f3196a.setScaleY(mappedValue);
    }
}
