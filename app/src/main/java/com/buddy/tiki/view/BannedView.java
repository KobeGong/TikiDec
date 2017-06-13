package com.buddy.tiki.view;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ui.activity.WebBrowserActivity;

public class BannedView extends LinearLayout {
    private TextView f2503a;
    private AppCompatButton f2504b;

    public BannedView(Context context) {
        this(context, null);
    }

    public BannedView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannedView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflate(context, C0376R.layout.widget_banned_view, this);
        this.f2503a = (TextView) findViewById(C0376R.id.banned_content);
        this.f2504b = (AppCompatButton) findViewById(C0376R.id.unlock_button);
    }

    public void setBannedContent(String content, String url) {
        setOnClickListener(BannedView$$Lambda$1.lambdaFactory$());
        this.f2503a.setText(content);
        if (!TextUtils.isEmpty(url)) {
            this.f2504b.setOnClickListener(BannedView$$Lambda$2.lambdaFactory$(this, url));
        }
        setVisibility(0);
    }

    static /* synthetic */ void m1633a(View v) {
    }

    /* synthetic */ void m1634a(String url, View v) {
        WebBrowserActivity.launchWeb(getContext(), url);
    }

    public void hideBannedContent() {
        setOnClickListener(null);
        setVisibility(8);
    }
}
