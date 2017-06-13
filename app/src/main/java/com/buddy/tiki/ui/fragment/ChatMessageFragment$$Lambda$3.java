package com.buddy.tiki.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$3 implements OnRefreshListener {
    private final ChatMessageFragment f2081a;

    private ChatMessageFragment$$Lambda$3(ChatMessageFragment chatMessageFragment) {
        this.a = chatMessageFragment;
    }

    public static OnRefreshListener lambdaFactory$(ChatMessageFragment chatMessageFragment) {
        return new ChatMessageFragment$$Lambda$3(chatMessageFragment);
    }

    @Hidden
    public void onRefresh() {
        this.a.m1286e();
    }
}
