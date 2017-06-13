package com.buddy.tiki.ui.activity;

import com.buddy.tiki.view.BottomNavigationView.OnNavigationViewClick;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$6 implements OnNavigationViewClick {
    private final CallFriendActivity f1337a;

    private CallFriendActivity$$Lambda$6(CallFriendActivity callFriendActivity) {
        this.a = callFriendActivity;
    }

    public static OnNavigationViewClick lambdaFactory$(CallFriendActivity callFriendActivity) {
        return new CallFriendActivity$$Lambda$6(callFriendActivity);
    }

    @Hidden
    public void onItemClick(int i) {
        this.a.m701b(i);
    }
}
