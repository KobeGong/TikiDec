package com.buddy.tiki.ui.activity;

import android.os.Bundle;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.fragment.ProfileFragment;

public class ProfileActivity extends BaseActivity {
    protected int mo2115a() {
        return C0376R.layout.activity_blank;
    }

    protected void mo2116a(Bundle savedInstanceState) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(getArguments());
        addFragment(fragment);
    }

    protected int mo2117b() {
        return C0376R.id.fragment_container;
    }
}
