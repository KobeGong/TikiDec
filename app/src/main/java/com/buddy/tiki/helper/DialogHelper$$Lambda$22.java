package com.buddy.tiki.helper;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.model.event.Notice;
import com.buddy.tiki.ui.activity.WebBrowserActivity;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$22 implements OnClickListener {
    private final Context f577a;
    private final Notice f578b;

    private DialogHelper$$Lambda$22(Context context, Notice notice) {
        this.a = context;
        this.b = notice;
    }

    public static OnClickListener lambdaFactory$(Context context, Notice notice) {
        return new DialogHelper$$Lambda$22(context, notice);
    }

    @Hidden
    public void onClick(View view) {
        WebBrowserActivity.launchWeb(this.a, this.b.getUrl());
    }
}
