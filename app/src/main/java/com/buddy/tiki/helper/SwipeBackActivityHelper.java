package com.buddy.tiki.helper;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.util.SwipeLayoutUtil;
import com.buddy.tiki.view.SwipeBackLayout;
import com.buddy.tiki.view.SwipeBackLayout.SwipeListener;

public class SwipeBackActivityHelper {
    private Activity f698a;
    private SwipeBackLayout f699b;

    class C03891 implements SwipeListener {
        final /* synthetic */ SwipeBackActivityHelper f697a;

        C03891(SwipeBackActivityHelper this$0) {
            this.f697a = this$0;
        }

        public void onScrollStateChange(int state, float scrollPercent) {
        }

        public void onEdgeTouch(int edgeFlag) {
            SwipeLayoutUtil.convertActivityToTranslucent(this.f697a.f698a);
        }

        public void onScrollOverThreshold() {
        }
    }

    public SwipeBackActivityHelper(Activity activity) {
        this.f698a = activity;
    }

    public void onActivityCreate() {
        this.f698a.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.f698a.getWindow().getDecorView().setBackgroundDrawable(null);
        this.f699b = (SwipeBackLayout) LayoutInflater.from(this.f698a).inflate(C0376R.layout.widget_swipeback_layout, null);
        this.f699b.addSwipeListener(new C03891(this));
    }

    public void onPostCreate() {
        this.f699b.attachToActivity(this.f698a);
    }

    public View findViewById(int id) {
        if (this.f699b != null) {
            return this.f699b.findViewById(id);
        }
        return null;
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return this.f699b;
    }
}
