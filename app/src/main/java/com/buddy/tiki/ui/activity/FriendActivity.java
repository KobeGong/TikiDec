package com.buddy.tiki.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.model.user.SyncFriends;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.fragment.FriendFragment;
import com.buddy.tiki.util.PreferenceUtil;
import im.facechat.Rtc;

public class FriendActivity extends BaseActivity {
    @LayoutRes
    protected int mo2115a() {
        return C0376R.layout.activity_blank;
    }

    protected void mo2116a(Bundle savedInstanceState) {
        Rtc.initialize(getApplication());
        getDataLayer().getFollowManager().syncFriendsQuery(PreferenceUtil.getSyncTimepoint()).subscribe(FriendActivity$$Lambda$1.lambdaFactory$(), FriendActivity$$Lambda$2.lambdaFactory$());
        addFragment(new FriendFragment());
    }

    static /* synthetic */ void m718a(SyncFriends syncFriends) throws Exception {
    }

    static /* synthetic */ void m719a(Throwable throwable) throws Exception {
    }

    @IdRes
    protected int mo2117b() {
        return C0376R.id.fragment_container;
    }

    protected void onDestroy() {
        Rtc.shutdown();
        super.onDestroy();
    }
}
