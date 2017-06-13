package com.buddy.tiki.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.FullScreenVideoView;

public class IntroduceActivity_ViewBinding implements Unbinder {
    private IntroduceActivity f1412b;
    private View f1413c;

    @UiThread
    public IntroduceActivity_ViewBinding(IntroduceActivity target) {
        this(target, target.getWindow().getDecorView());
    }

    @UiThread
    public IntroduceActivity_ViewBinding(final IntroduceActivity target, View source) {
        this.f1412b = target;
        target.mVideo = (FullScreenVideoView) Utils.findRequiredViewAsType(source, C0376R.id.video, "field 'mVideo'", FullScreenVideoView.class);
        View view = Utils.findRequiredView(source, C0376R.id.skip_btn, "method 'skipVideo'");
        this.f1413c = view;
        view.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ IntroduceActivity_ViewBinding f1411c;

            public void doClick(View p0) {
                target.skipVideo();
            }
        });
    }

    @CallSuper
    public void unbind() {
        IntroduceActivity target = this.f1412b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f1412b = null;
        target.mVideo = null;
        this.f1413c.setOnClickListener(null);
        this.f1413c = null;
    }
}
