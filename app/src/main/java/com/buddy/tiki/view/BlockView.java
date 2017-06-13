package com.buddy.tiki.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.model.app.MatchLimits;

public class BlockView extends LinearLayout {
    private AppCompatTextView f2507a;
    private AppCompatTextView f2508b;
    private AppCompatTextView f2509c;

    public BlockView(Context context) {
        this(context, null);
    }

    public BlockView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BlockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        m1635a(context);
    }

    private void m1635a(Context context) {
        inflate(context, C0376R.layout.widget_block_view, this);
        this.f2507a = (AppCompatTextView) findViewById(C0376R.id.text0);
        this.f2508b = (AppCompatTextView) findViewById(C0376R.id.text1);
        this.f2509c = (AppCompatTextView) findViewById(C0376R.id.text2);
    }

    public void fillData(@NonNull Context context, @NonNull MatchLimits limits) {
        this.f2507a.setText(limits.getText());
        this.f2508b.setText(limits.getText2());
        this.f2509c.setText(limits.getAnnounceText());
        if (!TextUtils.isEmpty(limits.getAnnounceUrl())) {
            this.f2509c.setOnClickListener(BlockView$$Lambda$1.lambdaFactory$(context, limits));
        }
    }
}
