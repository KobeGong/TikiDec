package com.buddy.tiki.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.ui.adapter.base.BaseAdapter;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.util.FrescoUtil;
import com.buddy.tiki.util.ResourceUrlUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import io.realm.RealmResults;
import java.util.List;

public class SendFriendsAdapter extends BaseAdapter<FriendHolder, TikiUser> {
    private static final TikiLog f1824f = TikiLog.getInstance("SendFriendsAdapter");
    private RealmResults<TikiUser> f1825g;

    static class FriendHolder extends ViewHolder {
        SimpleDraweeView f1823a;

        public FriendHolder(View itemView) {
            super(itemView);
            this.f1823a = (SimpleDraweeView) itemView.findViewById(C0376R.id.friend_avatar);
        }
    }

    protected /* synthetic */ ViewHolder mo2168b(View view) {
        return m1046a(view);
    }

    public SendFriendsAdapter(@NonNull Context context, @NonNull RealmResults<TikiUser> users) {
        super(context);
        this.f1825g = users;
    }

    public void addDataList(@NonNull List<TikiUser> list) {
    }

    public void addData(@NonNull TikiUser data) {
    }

    public void setData(@NonNull RealmResults<TikiUser> users) {
        this.f1825g = users;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if (this.f1825g == null) {
            return 0;
        }
        return this.f1825g.size();
    }

    protected int mo2165a() {
        return C0376R.layout.item_friend_avatar;
    }

    protected FriendHolder m1046a(View view) {
        return new FriendHolder(view);
    }

    public void onBindViewHolder(FriendHolder holder, int position) {
        TikiUser user = (TikiUser) this.f1825g.get(position);
        if (user != null) {
            if (TextUtils.isEmpty(user.getAvatar()) || !user.getAvatar().startsWith("http://")) {
                holder.f1823a.setImageURI("res://" + this.b.getPackageName() + "/" + C0376R.mipmap.default_tiki_avatar);
            } else {
                FrescoUtil.setImageURI(holder.f1823a, ResourceUrlUtil.getNormalAvatar(user.getAvatar(), DisplayUtil.dip2px(36.0f)));
            }
        }
    }
}
