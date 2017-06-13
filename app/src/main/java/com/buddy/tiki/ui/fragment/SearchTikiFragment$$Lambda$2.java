package com.buddy.tiki.ui.fragment;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SearchTikiFragment$$Lambda$2 implements OnEditorActionListener {
    private final SearchTikiFragment f2198a;

    private SearchTikiFragment$$Lambda$2(SearchTikiFragment searchTikiFragment) {
        this.a = searchTikiFragment;
    }

    public static OnEditorActionListener lambdaFactory$(SearchTikiFragment searchTikiFragment) {
        return new SearchTikiFragment$$Lambda$2(searchTikiFragment);
    }

    @Hidden
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return this.a.m1392a(textView, i, keyEvent);
    }
}
