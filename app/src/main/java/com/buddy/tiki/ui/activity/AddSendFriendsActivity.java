package com.buddy.tiki.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.fragment.ContactsFragment;

public class AddSendFriendsActivity extends BaseActivity {
    private static final TikiLog f1115a = TikiLog.getInstance("AddSendFriendsActivity");
    private ContactsFragment f1116b;

    @LayoutRes
    protected int mo2115a() {
        return C0376R.layout.activity_blank;
    }

    protected void onActivityCreate(Bundle savedInstanceState) {
        this.f1116b = new ContactsFragment();
        this.f1116b.setArguments(getArguments());
        addFragment(this.f1116b);
    }

    public void setSelectUserCount(int count) {
        if (this.f1116b != null) {
            this.f1116b.setSelectUserCount(count);
        }
    }

    @IdRes
    protected int mo2117b() {
        return C0376R.id.fragment_container;
    }
}
