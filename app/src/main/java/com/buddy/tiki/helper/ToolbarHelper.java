package com.buddy.tiki.helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.buddy.tiki.C0376R;

public class ToolbarHelper {
    private static int[] f700a = new int[]{C0376R.attr.windowActionBarOverlay, C0376R.attr.actionBarSize};
    private Context f701b;
    private FrameLayout f702c;
    private View f703d;
    private Toolbar f704e;
    private LayoutInflater f705f;

    public ToolbarHelper(@NonNull Context context, @LayoutRes int layoutId) {
        this.f701b = context;
        this.f705f = LayoutInflater.from(context);
        m145a();
        m147b();
        m146a(layoutId);
    }

    private void m145a() {
        this.f702c = new FrameLayout(this.f701b);
        this.f702c.setLayoutParams(new LayoutParams(-1, -1));
    }

    private void m147b() {
        this.f704e = (Toolbar) this.f705f.inflate(C0376R.layout.widget_toolbar, this.f702c).findViewById(C0376R.id.toolbar);
    }

    private void m146a(int id) {
        this.f703d = this.f705f.inflate(id, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -1);
        TypedArray array = this.f701b.getTheme().obtainStyledAttributes(f700a);
        boolean overlay = array.getBoolean(4, false);
        int toolbarSize = (int) array.getDimension(22, this.f701b.getResources().getDimension(C0376R.dimen.abc_action_bar_default_height_material));
        array.recycle();
        if (overlay) {
            toolbarSize = 0;
        }
        params.topMargin = toolbarSize;
        this.f702c.addView(this.f703d, params);
    }

    public Toolbar getToolbar() {
        return this.f704e;
    }

    public View getContentView() {
        return this.f702c;
    }
}
