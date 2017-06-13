package com.buddy.tiki.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.recyclerview.BuildConfig;
import android.view.KeyEvent;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.protocol.ui.BackHandleInterface;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.fragment.base.BaseFragment;
import java.util.List;

public class FragmentContainerActivity extends BaseActivity {
    private String f1403a;
    private Bundle f1404b;

    @LayoutRes
    protected int mo2115a() {
        return C0376R.layout.activity_blank;
    }

    protected void mo2116a(Bundle savedInstanceState) {
        m714c();
        addFragment((BaseFragment) Fragment.instantiate(this, this.f1403a, this.f1404b));
    }

    @IdRes
    protected int mo2117b() {
        return C0376R.id.fragment_container;
    }

    private void m714c() {
        Bundle args = getArguments();
        if (args != null) {
            this.f1403a = args.getString("PARAM_KEY_FRAGMENT", BuildConfig.VERSION_NAME);
            this.f1404b = args.getBundle("PARAM_KEY_FRAGMENT_ARGS");
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        return (fragments.get(fragments.size() + -1) instanceof BackHandleInterface) || super.onKeyDown(keyCode, event);
    }
}
