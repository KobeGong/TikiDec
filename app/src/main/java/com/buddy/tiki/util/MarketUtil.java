package com.buddy.tiki.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.base.BusinessDomain;
import com.buddy.tiki.model.constant.ChannelKeys;

public final class MarketUtil {
    public static void launchMarket(Context context, String market) {
        Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(market));
        if (viewIntent.resolveActivity(context.getPackageManager()) != null) {
            try {
                context.startActivity(viewIntent);
                return;
            } catch (Exception e) {
                m1553a(context);
                return;
            }
        }
        m1553a(context);
    }

    private static void m1553a(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW");
        if (BusinessDomain.f405c.equalsIgnoreCase(ChannelKeys.GOOGLE_MARKET)) {
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.buddy.tiki"));
        } else {
            intent.setData(Uri.parse("http://app.qq.com/#id=detail&appid=1105564888"));
        }
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            ToastUtil.getInstance().show(context, (int) C0376R.string.not_found_webbrowser);
        }
    }
}
