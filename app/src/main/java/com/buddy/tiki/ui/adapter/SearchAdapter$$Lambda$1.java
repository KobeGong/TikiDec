package com.buddy.tiki.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.util.SchedulersCompat;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SearchAdapter$$Lambda$1 implements OnClickListener {
    private final User f1815a;
    private final SearchHolder f1816b;

    private SearchAdapter$$Lambda$1(User user, SearchHolder searchHolder) {
        this.a = user;
        this.b = searchHolder;
    }

    public static OnClickListener lambdaFactory$(User user, SearchHolder searchHolder) {
        return new SearchAdapter$$Lambda$1(user, searchHolder);
    }

    @Hidden
    public void onClick(View view) {
        DataLayer.getInstance().getFollowManager().applyFriendForSearchAction(this.a).compose(SchedulersCompat.applyIoSchedulers()).filter(SearchAdapter$$Lambda$2.lambdaFactory$()).subscribe(SearchAdapter$$Lambda$3.lambdaFactory$(this.b), SearchAdapter$$Lambda$4.lambdaFactory$());
    }
}
