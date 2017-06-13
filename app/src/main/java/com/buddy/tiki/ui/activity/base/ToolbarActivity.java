package com.buddy.tiki.ui.activity.base;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import com.buddy.tiki.helper.ToolbarHelper;

public abstract class ToolbarActivity extends BaseActivity {
    protected Toolbar f1625a;
    private ToolbarHelper f1626b;

    public void setContentView(@LayoutRes int layoutResID) {
        this.f1626b = new ToolbarHelper(this, layoutResID);
        this.f1625a = this.f1626b.getToolbar();
        setContentView(this.f1626b.getContentView());
        setSupportActionBar(this.f1625a);
        onCreateCustomToolbar(this.f1625a);
    }

    public void onCreateCustomToolbar(Toolbar toolbar) {
        toolbar.setContentInsetsRelative(0, 0);
    }
}
