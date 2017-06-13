package com.buddy.tiki.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.user.TikiUser;
import com.buddy.tiki.ui.activity.AddSendFriendsActivity;
import com.buddy.tiki.ui.adapter.base.BaseAdapter;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.util.FrescoUtil;
import com.buddy.tiki.util.ResourceUrlUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AddSendFriendsAdapter extends BaseAdapter<FriendHolder, TikiUser> {
    private static final TikiLog f1639f = TikiLog.getInstance("AddSendFriendsAdapter");
    private Set<String> f1640g;
    private List<TikiUser> f1641h;
    private boolean f1642i = false;
    private String f1643j;

    static class FriendHolder extends ViewHolder {
        SimpleDraweeView f1630a;
        AppCompatImageView f1631b;
        AppCompatTextView f1632c;
        AppCompatTextView f1633d;

        public FriendHolder(View itemView) {
            super(itemView);
            this.f1630a = (SimpleDraweeView) itemView.findViewById(C0376R.id.friend_avatar);
            this.f1631b = (AppCompatImageView) itemView.findViewById(C0376R.id.select_user);
            this.f1632c = (AppCompatTextView) itemView.findViewById(C0376R.id.friend_name);
            this.f1633d = (AppCompatTextView) itemView.findViewById(C0376R.id.pinyin);
        }
    }

    protected /* synthetic */ ViewHolder mo2168b(View view) {
        return m973a(view);
    }

    public AddSendFriendsAdapter(@NonNull Context context, @NonNull List<TikiUser> users, @NonNull String uId) {
        super(context);
        this.f1641h = users;
        this.f1640g = new HashSet();
        this.f1643j = uId;
        this.f1640g.add(uId);
    }

    public void setSelectUsers(Set<String> selectUsers) {
        this.f1640g = selectUsers;
    }

    public void addDataList(@NonNull List<TikiUser> list) {
    }

    public void addData(@NonNull TikiUser data) {
    }

    public void setData(@NonNull List<TikiUser> users) {
        this.f1640g.clear();
        this.f1640g.add(this.f1643j);
        this.f1641h = users;
        notifyDataSetChanged();
    }

    public void selectAll(boolean selectAll) {
        this.f1642i = selectAll;
        if (!selectAll) {
            this.f1640g.clear();
            this.f1640g.add(this.f1643j);
        } else if (this.f1641h != null) {
            for (TikiUser user : this.f1641h) {
                this.f1640g.add(user.getUid());
            }
        }
        notifyDataSetChanged();
        ((AddSendFriendsActivity) this.b).setSelectUserCount(this.f1640g.size());
    }

    public int getItemCount() {
        if (this.f1641h == null) {
            return 0;
        }
        return this.f1641h.size();
    }

    public Set<String> getSelectUser() {
        return this.f1640g;
    }

    protected int mo2165a() {
        return C0376R.layout.item_add_friend;
    }

    protected FriendHolder m973a(View view) {
        return new FriendHolder(view);
    }

    public void onBindViewHolder(FriendHolder holder, int position) {
        TikiUser user = (TikiUser) this.f1641h.get(position);
        if (user != null && user.isValid() && user.getFirstPinYin() != null) {
            if (TextUtils.isEmpty(user.getAvatar()) || !user.getAvatar().startsWith("http://")) {
                holder.f1630a.setImageURI("res://" + this.b.getPackageName() + "/" + C0376R.mipmap.default_tiki_avatar);
            } else {
                FrescoUtil.setImageURI(holder.f1630a, ResourceUrlUtil.getNormalAvatar(user.getAvatar(), DisplayUtil.dip2px(36.0f)));
            }
            holder.f1632c.setText(user.getMark());
            if (this.f1642i) {
                holder.f1631b.setBackgroundResource(C0376R.mipmap.icon_gouxuan);
            } else if (this.f1640g.contains(user.getUid())) {
                holder.f1631b.setBackgroundResource(C0376R.mipmap.icon_gouxuan);
            } else {
                holder.f1631b.setBackgroundResource(C0376R.mipmap.icon_kexuan);
            }
            if (user.getUid().equals(this.f1643j)) {
                holder.f1631b.setBackgroundResource(C0376R.mipmap.icon_gouxuan_moren);
            }
            RxView.clicks(holder.f1631b).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(AddSendFriendsAdapter$$Lambda$1.lambdaFactory$(this, user, holder));
            if (user.isShowPinyin()) {
                holder.f1633d.setVisibility(0);
                holder.f1633d.setText(user.getFirstPinYin());
                return;
            }
            holder.f1633d.setVisibility(8);
        }
    }

    /* synthetic */ void m974a(TikiUser user, FriendHolder holder, Object aVoid) throws Exception {
        if (!user.getUid().equals(this.f1643j)) {
            if (this.f1640g.contains(user.getUid())) {
                this.f1640g.remove(user.getUid());
                holder.f1631b.setBackgroundResource(C0376R.mipmap.icon_kexuan);
            } else {
                this.f1640g.add(user.getUid());
                holder.f1631b.setBackgroundResource(C0376R.mipmap.icon_gouxuan);
            }
            ((AddSendFriendsActivity) this.b).setSelectUserCount(this.f1640g.size());
        }
    }
}
