package com.buddy.tiki.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class VerticalViewPager extends ViewPager {

    private class VerticalPageTransformer implements PageTransformer {
        final /* synthetic */ VerticalViewPager f2897a;

        private VerticalPageTransformer(VerticalViewPager verticalViewPager) {
            this.f2897a = verticalViewPager;
        }

        public void transformPage(View view, float position) {
            if (position < -1.0f) {
                view.setAlpha(0.0f);
            } else if (position <= 1.0f) {
                view.setAlpha(1.0f);
                view.setTranslationX(((float) view.getWidth()) * (-position));
                view.setTranslationY(position * ((float) view.getHeight()));
            } else {
                view.setAlpha(0.0f);
            }
        }
    }

    public VerticalViewPager(Context context) {
        super(context);
        m1810a();
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        m1810a();
    }

    private void m1810a() {
        setPageTransformer(true, new VerticalPageTransformer());
        setOverScrollMode(2);
    }

    private MotionEvent m1809a(MotionEvent ev) {
        float width = (float) getWidth();
        float height = (float) getHeight();
        ev.setLocation((ev.getY() / height) * width, (ev.getX() / width) * height);
        return ev;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = super.onInterceptTouchEvent(m1809a(ev));
        m1809a(ev);
        return intercepted;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(m1809a(ev));
    }
}
