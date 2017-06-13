package com.buddy.tiki.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

public class DrawableUtil {
    public static void tintDrawable(Context context, ImageView imageView, @DrawableRes int drawableId, @FloatRange(from = 0.0d, to = 1.0d) float percent, @ColorInt int startColor, @ColorInt int endColor) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        ConstantState state = drawable.getConstantState();
        Drawable wrapDrawable = DrawableCompat.wrap(state == null ? drawable : state.newDrawable().mutate());
        wrapDrawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        DrawableCompat.setTint(wrapDrawable, getInterceptColor(startColor, endColor, percent));
        imageView.setImageDrawable(wrapDrawable);
    }

    @ColorInt
    public static int getInterceptColor(@ColorInt int startColor, @ColorInt int endColor, @FloatRange(from = 0.0d, to = 1.0d) float percent) {
        int startRed = Color.red(startColor);
        int startGreen = Color.green(startColor);
        int startBlue = Color.blue(startColor);
        return Color.rgb(((int) (((float) (Color.red(endColor) - startRed)) * percent)) + startColor, ((int) (((float) (Color.green(endColor) - startGreen)) * percent)) + startGreen, ((int) (((float) (Color.blue(endColor) - startBlue)) * percent)) + startBlue);
    }
}
