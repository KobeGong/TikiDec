package com.buddy.tiki.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;

public class WebBrowserActivity_ViewBinding implements Unbinder {
    private WebBrowserActivity f1617b;
    private View f1618c;

    @UiThread
    public WebBrowserActivity_ViewBinding(WebBrowserActivity target) {
        this(target, target.getWindow().getDecorView());
    }

    @UiThread
    public WebBrowserActivity_ViewBinding(final WebBrowserActivity target, View source) {
        this.f1617b = target;
        target.mBackButton = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.back_btn, "field 'mBackButton'", AppCompatTextView.class);
        target.mWebTitle = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.web_title, "field 'mWebTitle'", AppCompatTextView.class);
        target.mMoreAction = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.more_action, "field 'mMoreAction'", AppCompatTextView.class);
        View view = Utils.findRequiredView(source, C0376R.id.close_btn, "field 'mCloseButton' and method 'closeWeb'");
        target.mCloseButton = (AppCompatTextView) Utils.castView(view, C0376R.id.close_btn, "field 'mCloseButton'", AppCompatTextView.class);
        this.f1618c = view;
        view.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ WebBrowserActivity_ViewBinding f1616c;

            public void doClick(View p0) {
                target.closeWeb();
            }
        });
        target.mWebView = (WebView) Utils.findRequiredViewAsType(source, C0376R.id.web_view, "field 'mWebView'", WebView.class);
        target.mProgressBar = (ProgressBar) Utils.findRequiredViewAsType(source, C0376R.id.progress_bar, "field 'mProgressBar'", ProgressBar.class);
    }

    @CallSuper
    public void unbind() {
        WebBrowserActivity target = this.f1617b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f1617b = null;
        target.mBackButton = null;
        target.mWebTitle = null;
        target.mMoreAction = null;
        target.mCloseButton = null;
        target.mWebView = null;
        target.mProgressBar = null;
        this.f1618c.setOnClickListener(null);
        this.f1618c = null;
    }
}
