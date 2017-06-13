package com.buddy.tiki.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.view.View;

public class RippleUtil {
    @TargetApi(21)
    public static void setRippleBackground(View view) {
        if (view != null && VERSION.SDK_INT >= 21) {
            TypedArray typedArray = view.getContext().obtainStyledAttributes(new int[]{16843868});
            view.setBackgroundResource(typedArray.getResourceId(0, 0));
            typedArray.recycle();
        }
    }

    public static void setDefaultBackground(View view) {
        if (view != null) {
            TypedArray typedArray = view.getContext().obtainStyledAttributes(new int[]{16843534});
            view.setBackgroundResource(typedArray.getResourceId(0, 0));
            typedArray.recycle();
        }
    }

    @TargetApi(21)
    public static void setRippleBackground(Context context, View... views) {
        int i = 0;
        if (VERSION.SDK_INT >= 21 && context != null) {
            TypedArray typedArray = context.obtainStyledAttributes(new int[]{16843868});
            int backgroundResource = typedArray.getResourceId(0, 0);
            int length = views.length;
            while (i < length) {
                View view = views[i];
                if (view != null) {
                    view.setBackgroundResource(backgroundResource);
                }
                i++;
            }
            typedArray.recycle();
        }
    }
}
