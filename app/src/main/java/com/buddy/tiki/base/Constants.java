package com.buddy.tiki.base;

import android.net.Uri;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;

public interface Constants {
    public static final Uri f406a = Uri.parse("android.resource://" + ChatApp.getInstance().getPackageName() + "/" + C0376R.raw.call_friend);
    public static final Uri f407b = Uri.parse("android.resource://" + ChatApp.getInstance().getPackageName() + "/" + C0376R.raw.sms);
}
