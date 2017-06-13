package com.buddy.tiki.view.video;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.MediaController;
import java.util.ArrayList;
import java.util.Iterator;

public class AndroidMediaController extends MediaController implements IMediaController {
    private ActionBar f3354a;
    private ArrayList<View> f3355b = new ArrayList();

    public AndroidMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
        m2090a(context);
    }

    public AndroidMediaController(Context context, boolean useFastForward) {
        super(context, useFastForward);
        m2090a(context);
    }

    public AndroidMediaController(Context context) {
        super(context);
        m2090a(context);
    }

    private void m2090a(Context context) {
    }

    public void setSupportActionBar(@Nullable ActionBar actionBar) {
        this.f3354a = actionBar;
        if (isShowing()) {
            actionBar.show();
        } else {
            actionBar.hide();
        }
    }

    public void show() {
        super.show();
        if (this.f3354a != null) {
            this.f3354a.show();
        }
    }

    public void hide() {
        super.hide();
        if (this.f3354a != null) {
            this.f3354a.hide();
        }
        Iterator it = this.f3355b.iterator();
        while (it.hasNext()) {
            ((View) it.next()).setVisibility(8);
        }
        this.f3355b.clear();
    }

    public void showOnce(@NonNull View view) {
        this.f3355b.add(view);
        view.setVisibility(0);
        show();
    }
}
