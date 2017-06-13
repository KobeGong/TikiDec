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
import com.buddy.tiki.model.user.UserChatSession;
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
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;

public class SessionAdapter extends RealmBasedRecyclerViewAdapter<UserChatSession, SessionHolder> {

    static class SessionHolder extends ViewHolder {
        SimpleDraweeView f1830a;
        AppCompatTextView f1831b;
        AppCompatTextView f1832c;
        RoundTextView f1833d;

        public SessionHolder(View itemView) {
            super(itemView);
            this.f1830a = (SimpleDraweeView) itemView.findViewById(C0376R.id.avatar);
            this.f1831b = (AppCompatTextView) itemView.findViewById(C0376R.id.nick);
            this.f1832c = (AppCompatTextView) itemView.findViewById(C0376R.id.news);
            this.f1833d = (RoundTextView) itemView.findViewById(C0376R.id.badge);
        }
    }

    public SessionAdapter(Context context, RealmResults<UserChatSession> realmResults, boolean automaticUpdate, boolean animateResults, String animateExtraColumnName, String animateExtraColumnName2) {
        super(context, realmResults, automaticUpdate, animateResults, animateExtraColumnName, animateExtraColumnName2);
    }

    public SessionHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        return new SessionHolder(this.a.inflate(C0376R.layout.item_friend, viewGroup, false));
    }

    public void onBindRealmViewHolder(SessionHolder holder, int position) {
        UserChatSession session = (UserChatSession) this.b.get(position);
        if (session != null) {
            TikiUser user = m1048a(session.getSessionId());
            if (user != null) {
                if (TextUtils.isEmpty(user.getAvatar()) || !user.getAvatar().startsWith("http://")) {
                    holder.f1830a.setImageURI("res://" + getContext().getPackageName() + "/" + C0376R.mipmap.default_tiki_avatar);
                } else {
                    FrescoUtil.setImageURI(holder.f1830a, ResourceUrlUtil.getNormalAvatar(user.getAvatar(), DisplayUtil.dip2px(48.0f)));
                }
                holder.f1831b.setText(user.getMark());
                if (TextUtils.isEmpty(user.getLastMessage())) {
                    holder.f1832c.setVisibility(8);
                } else {
                    holder.f1832c.setVisibility(0);
                    String lastMessage = user.getLastMessage();
                    if (lastMessage.equals(ChatApp.getInstance().getString(C0376R.string.have_been_friend_tips))) {
                        lastMessage = ChatApp.getInstance().getString(C0376R.string.have_been_friend);
                    }
                    holder.f1832c.setText(String.format("%s %s", new Object[]{DateUtil.computeTimeDiff(user.getLastMessageTime(), false, true), lastMessage}));
                }
                if (session.getUnread() > 0) {
                    holder.f1833d.setText(String.valueOf(session.getUnread()));
                    holder.f1833d.setVisibility(0);
                } else {
                    holder.f1833d.setVisibility(8);
                }
                holder.itemView.setOnClickListener(SessionAdapter$$Lambda$1.lambdaFactory$(this, user));
            }
        }
    }

    /* synthetic */ void m1051a(TikiUser user, View v) {
        DataLayer.getInstance().getUserManager().userRequest(user.getUid()).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(SessionAdapter$$Lambda$2.lambdaFactory$(), SessionAdapter$$Lambda$3.lambdaFactory$());
        if (getContext() instanceof CallActivity) {
            ChatMessageActivity.launchChatMessageForResult((CallActivity) getContext(), user.getUid(), FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        } else if (getContext() instanceof BaseActivity) {
            ChatMessageActivity.launchChatMessage((BaseActivity) getContext(), user.getUid());
        }
    }

    static /* synthetic */ void m1049a(User user1) throws Exception {
    }

    static /* synthetic */ void m1050a(Throwable throwable) throws Exception {
    }

    private TikiUser m1048a(String uId) {
        Realm realm = Realm.getDefaultInstance();
        TikiUser user = (TikiUser) realm.where(TikiUser.class).equalTo(Oauth2AccessToken.KEY_UID, uId).findFirst();
        realm.close();
        return user;
    }
}
