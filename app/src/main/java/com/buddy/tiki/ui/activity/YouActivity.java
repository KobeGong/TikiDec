package com.buddy.tiki.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.model.user.SyncFriends;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.fragment.YouFragment;
import com.buddy.tiki.util.PreferenceUtil;

public class YouActivity extends BaseActivity {
    @LayoutRes
    protected int mo2115a() {
        return C0376R.layout.activity_blank;
    }

    protected void onActivityCreate(Bundle savedInstanceState) {
        getDataLayer().getFollowManager().syncFriendsQuery(PreferenceUtil.getSyncTimepoint()).subscribe(YouActivity$$Lambda$1.lambdaFactory$(), YouActivity$$Lambda$2.lambdaFactory$());
        addFragment(new YouFragment());
    }

    static /* synthetic */ void m963a(SyncFriends syncFriends) throws Exception {
    }

    static /* synthetic */ void m964a(Throwable throwable) throws Exception {
    }

    @IdRes
    protected int mo2117b() {
        return C0376R.id.fragment_container;
    }
}
