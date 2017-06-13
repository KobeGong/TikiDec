package com.buddy.tiki.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

public class CenterRadioButton extends AppCompatRadioButton {
    public CenterRadioButton(Context context) {
        super(context);
    }

    public CenterRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CenterRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        float textWidth = getPaint().measureText(getText().toString());
        int drawablePadding = getCompoundDrawablePadding();
        Drawable leftDrawable = drawables[0];
        if (leftDrawable != null) {
            canvas.translate((((float) getWidth()) - ((((float) leftDrawable.getIntrinsicWidth()) + textWidth) + ((float) drawablePadding))) / 2.0f, 0.0f);
        }
        super.onDraw(canvas);
    }
}
