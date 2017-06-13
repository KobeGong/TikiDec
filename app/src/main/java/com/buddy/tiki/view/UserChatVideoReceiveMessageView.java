package com.buddy.tiki.view;

import android.content.Context;
import android.util.AttributeSet;
import com.buddy.tiki.C0376R;

public class UserChatVideoReceiveMessageView extends ShapedView {
    public UserChatVideoReceiveMessageView(Context context) {
        this(context, null);
    }

    public UserChatVideoReceiveMessageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UserChatVideoReceiveMessageView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
    }

    protected int getShapeId() {
        return C0376R.mipmap.bubble_chat_4;
    }
}
