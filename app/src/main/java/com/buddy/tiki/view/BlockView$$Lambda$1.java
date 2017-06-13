package com.buddy.tiki.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.model.app.MatchLimits;
import com.buddy.tiki.ui.activity.WebBrowserActivity;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class BlockView$$Lambda$1 implements OnClickListener {
    private final Context f2505a;
    private final MatchLimits f2506b;

    private BlockView$$Lambda$1(Context context, MatchLimits matchLimits) {
        this.a = context;
        this.b = matchLimits;
    }

    public static OnClickListener lambdaFactory$(Context context, MatchLimits matchLimits) {
        return new BlockView$$Lambda$1(context, matchLimits);
    }

    @Hidden
    public void onClick(View view) {
        WebBrowserActivity.launchWeb(this.a, this.b.getAnnounceUrl());
    }
}
