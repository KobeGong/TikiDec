package com.buddy.tiki.helper;

import com.buddy.tiki.event.UserEvent.ModifyGenderEvent;
import io.reactivex.functions.Action;
import java.lang.invoke.LambdaForm.Hidden;
import org.greenrobot.eventbus.EventBus;

final /* synthetic */ class DialogHelper$$Lambda$35 implements Action {
    private final int f601a;

    private DialogHelper$$Lambda$35(int i) {
        this.a = i;
    }

    public static Action lambdaFactory$(int i) {
        return new DialogHelper$$Lambda$35(i);
    }

    @Hidden
    public void run() {
        EventBus.getDefault().post(new ModifyGenderEvent(this.a));
    }
}
