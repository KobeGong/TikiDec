package com.buddy.tiki.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.model.resource.Gift;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatRoomGiftAdapter$$Lambda$1 implements OnClickListener {
    private final ChatRoomGiftAdapter f1743a;
    private final GiftHolder f1744b;
    private final Gift f1745c;

    private ChatRoomGiftAdapter$$Lambda$1(ChatRoomGiftAdapter chatRoomGiftAdapter, GiftHolder giftHolder, Gift gift) {
        this.a = chatRoomGiftAdapter;
        this.b = giftHolder;
        this.c = gift;
    }

    public static OnClickListener lambdaFactory$(ChatRoomGiftAdapter chatRoomGiftAdapter, GiftHolder giftHolder, Gift gift) {
        return new ChatRoomGiftAdapter$$Lambda$1(chatRoomGiftAdapter, giftHolder, gift);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m1014a(this.b, this.c, view);
    }
}
