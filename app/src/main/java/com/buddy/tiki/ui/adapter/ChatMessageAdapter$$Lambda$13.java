package com.buddy.tiki.ui.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.buddy.tiki.event.UserEvent.ReSendTextEvent;
import com.buddy.tiki.model.user.UserChatMessage;
import java.lang.invoke.LambdaForm.Hidden;
import org.greenrobot.eventbus.EventBus;

final /* synthetic */ class ChatMessageAdapter$$Lambda$13 implements OnClickListener {
    private final UserChatMessage f1673a;

    private ChatMessageAdapter$$Lambda$13(UserChatMessage userChatMessage) {
        this.a = userChatMessage;
    }

    public static OnClickListener lambdaFactory$(UserChatMessage userChatMessage) {
        return new ChatMessageAdapter$$Lambda$13(userChatMessage);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        EventBus.getDefault().post(new ReSendTextEvent(this.a.getMsgId(), this.a.getMsgText()));
    }
}
