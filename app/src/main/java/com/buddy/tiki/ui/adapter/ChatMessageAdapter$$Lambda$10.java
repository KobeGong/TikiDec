package com.buddy.tiki.ui.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.buddy.tiki.event.UserEvent.ReSendGiftEvent;
import com.buddy.tiki.model.user.UserChatMessage;
import java.lang.invoke.LambdaForm.Hidden;
import org.greenrobot.eventbus.EventBus;

final /* synthetic */ class ChatMessageAdapter$$Lambda$10 implements OnClickListener {
    private final UserChatMessage f1670a;

    private ChatMessageAdapter$$Lambda$10(UserChatMessage userChatMessage) {
        this.a = userChatMessage;
    }

    public static OnClickListener lambdaFactory$(UserChatMessage userChatMessage) {
        return new ChatMessageAdapter$$Lambda$10(userChatMessage);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        EventBus.getDefault().post(new ReSendGiftEvent(this.a.getMsgId(), this.a.getGiftId()));
    }
}
