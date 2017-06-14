package com.buddy.tiki.ui.activity;

import android.os.Bundle;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.fragment.SystemMessageFragment;

public class SystemMessageActivity extends BaseActivity {
    protected int mo2115a() {
        return C0376R.layout.activity_blank;
    }

    protected void onActivityCreate(Bundle savedInstanceState) {
        addFragment(new SystemMessageFragment());
    }

    protected int mo2117b() {
        return C0376R.id.fragment_container;
    }
}
