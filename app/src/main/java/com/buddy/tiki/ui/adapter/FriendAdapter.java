package com.buddy.tiki.ui.adapter;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.ui.activity.CallActivity;
import com.buddy.tiki.ui.activity.ChatMessageActivity;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.util.DateUtil;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.util.FrescoUtil;
import com.buddy.tiki.util.ResourceUrlUtil;
import com.buddy.tiki.view.RoundTextView;
import com.facebook.drawee.view.SimpleDraweeView;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;

public class FriendAdapter extends RealmBasedRecyclerViewAdapter<TikiUser, FriendHolder> {

    static class FriendHolder extends ViewHolder {
        SimpleDraweeView f1802a;
        AppCompatTextView f1803b;
        AppCompatTextView f1804c;
        RoundTextView f1805d;

        public FriendHolder(View itemView) {
            super(itemView);
            this.f1802a = (SimpleDraweeView) itemView.findViewById(C0376R.id.avatar);
            this.f1803b = (AppCompatTextView) itemView.findViewById(C0376R.id.nick);
            this.f1804c = (AppCompatTextView) itemView.findViewById(C0376R.id.news);
            this.f1805d = (RoundTextView) itemView.findViewById(C0376R.id.badge);
        }
    }

    public FriendAdapter(Context context, RealmResults<TikiUser> realmResults, boolean automaticUpdate, boolean animateResults, String animateExtraColumnName, String animateExtraColumnName2) {
        super(context, realmResults, automaticUpdate, animateResults, animateExtraColumnName, animateExtraColumnName2);
    }

    public FriendHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        return new FriendHolder(this.a.inflate(C0376R.layout.item_friend, viewGroup, false));
    }

    public void onBindRealmViewHolder(FriendHolder holder, int position) {
        TikiUser user = (TikiUser) this.b.get(position);
        if (user != null) {
            if (TextUtils.isEmpty(user.getAvatar()) || !user.getAvatar().startsWith("http://")) {
                holder.f1802a.setImageURI("res://" + getContext().getPackageName() + "/" + C0376R.mipmap.default_tiki_avatar);
            } else {
                FrescoUtil.setImageURI(holder.f1802a, ResourceUrlUtil.getNormalAvatar(user.getAvatar(), DisplayUtil.dip2px(48.0f)));
            }
            holder.f1803b.setText(user.getMark());
            if (TextUtils.isEmpty(user.getLastMessage())) {
                holder.f1804c.setVisibility(8);
            } else {
                holder.f1804c.setVisibility(0);
                String lastMessage = user.getLastMessage();
                if (lastMessage.equals(ChatApp.getInstance().getString(C0376R.string.have_been_friend_tips))) {
                    lastMessage = ChatApp.getInstance().getString(C0376R.string.have_been_friend);
                }
                holder.f1804c.setText(String.format("%s %s", new Object[]{DateUtil.computeTimeDiff(user.getLastMessageTime(), false, true), lastMessage}));
            }
            if (user.getUnread() > 0) {
                holder.f1805d.setText(String.valueOf(user.getUnread()));
                holder.f1805d.setVisibility(0);
            } else {
                holder.f1805d.setVisibility(8);
            }
            holder.itemView.setOnClickListener(FriendAdapter$$Lambda$1.lambdaFactory$(this, user));
        }
    }

    /* synthetic */ void m1033a(TikiUser user, View v) {
        DataLayer.getInstance().getUserManager().userRequest(user.getUid()).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(FriendAdapter$$Lambda$2.lambdaFactory$(), FriendAdapter$$Lambda$3.lambdaFactory$());
        if (getContext() instanceof CallActivity) {
            ChatMessageActivity.launchChatMessageForResult((CallActivity) getContext(), user.getUid(), FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        } else if (getContext() instanceof BaseActivity) {
            ChatMessageActivity.launchChatMessage((BaseActivity) getContext(), user.getUid());
        }
    }

    static /* synthetic */ void m1031a(User user1) throws Exception {
    }

    static /* synthetic */ void m1032a(Throwable throwable) throws Exception {
    }
}
