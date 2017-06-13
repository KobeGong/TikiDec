package com.buddy.tiki.ui.activity.base;

import android.os.Bundle;
import android.view.View;
import com.buddy.tiki.helper.SwipeBackActivityHelper;
import com.buddy.tiki.util.SwipeLayoutUtil;
import com.buddy.tiki.view.SwipeBackLayout;

public abstract class BaseSwipeActivity extends BaseActivity {
    private SwipeBackActivityHelper f1624a;

    protected abstract void m969c();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.f1624a = new SwipeBackActivityHelper(this);
        this.f1624a.onActivityCreate();
        m969c();
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.f1624a.onPostCreate();
    }

    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v != null || this.f1624a == null) {
            return v;
        }
        return this.f1624a.findViewById(id);
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return this.f1624a.getSwipeBackLayout();
    }

    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    public void scrollToFinishActivity() {
        SwipeLayoutUtil.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }
}
