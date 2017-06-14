package com.buddy.tiki.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.recyclerview.BuildConfig;
import android.view.KeyEvent;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.fragment.ChatMessageFragment;

public class ChatMessageActivity extends BaseActivity {
    private static final TikiLog f1399a = TikiLog.getInstance("ChatMessageActivity");
    private ChatMessageFragment f1400b;

    public static void launchChatMessageForResult(BaseActivity baseActivity, String userId, int requestCode) {
        if (baseActivity != null && userId != null && !userId.trim().equals(BuildConfig.VERSION_NAME)) {
            Bundle args = new Bundle();
            args.putString("PARAM_KEY_UID", userId);
            baseActivity.launchActivityForResult(ChatMessageActivity.class, requestCode, args);
        }
    }

    public static void launchChatMessage(BaseActivity baseActivity, String userId) {
        if (baseActivity != null && userId != null && !userId.trim().equals(BuildConfig.VERSION_NAME)) {
            Bundle args = new Bundle();
            args.putString("PARAM_KEY_UID", userId);
            baseActivity.launchActivity(ChatMessageActivity.class, args);
            baseActivity.finish();
        }
    }

    @LayoutRes
    protected int mo2115a() {
        return C0376R.layout.activity_blank;
    }

    protected void onActivityCreate(Bundle savedInstanceState) {
        this.f1400b = new ChatMessageFragment();
        this.f1400b.setArguments(getArguments());
        addFragment(this.f1400b);
    }

    public void showGift(String music, String id, String source) {
        this.f1400b.showGift(true, music, id, source);
    }

    public void hideGiftLayout() {
        if (this.f1400b.isGiftLayoutShowing()) {
            this.f1400b.hideGiftLayout();
        } else {
            this.f1400b.hideSoftKeybord();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4 || !this.f1400b.isGiftLayoutShowing()) {
            return super.onKeyDown(keyCode, event);
        }
        this.f1400b.hideGiftLayout();
        return true;
    }

    @IdRes
    protected int mo2117b() {
        return C0376R.id.fragment_container;
    }
}
