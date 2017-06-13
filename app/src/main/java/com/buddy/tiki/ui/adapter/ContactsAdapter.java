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
import com.buddy.tiki.ui.activity.ChatMessageActivity;
import com.buddy.tiki.ui.activity.base.BaseActivity;
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

public class ContactsAdapter extends BaseAdapter<FriendHolder, TikiUser> {
    private static final TikiLog f1761f = TikiLog.getInstance("ContactsAdapter");
    private Set<String> f1762g;
    private List<TikiUser> f1763h;
    private boolean f1764i = false;
    private String f1765j;
    private boolean f1766k = false;

    static class FriendHolder extends ViewHolder {
        SimpleDraweeView f1757a;
        AppCompatImageView f1758b;
        AppCompatTextView f1759c;
        AppCompatTextView f1760d;

        public FriendHolder(View itemView) {
            super(itemView);
            this.f1757a = (SimpleDraweeView) itemView.findViewById(C0376R.id.friend_avatar);
            this.f1758b = (AppCompatImageView) itemView.findViewById(C0376R.id.select_user);
            this.f1759c = (AppCompatTextView) itemView.findViewById(C0376R.id.friend_name);
            this.f1760d = (AppCompatTextView) itemView.findViewById(C0376R.id.pinyin);
        }
    }

    protected /* synthetic */ ViewHolder mo2168b(View view) {
        return m1017a(view);
    }

    public ContactsAdapter(@NonNull Context context, @NonNull List<TikiUser> users, @NonNull String uId, boolean fromVideoRecord) {
        super(context);
        this.f1763h = users;
        if (fromVideoRecord) {
            this.f1762g = new HashSet();
            this.f1765j = uId;
            this.f1762g.add(uId);
        }
        this.f1766k = fromVideoRecord;
    }

    public void setSelectUsers(Set<String> selectUsers) {
        this.f1762g = selectUsers;
    }

    public void addDataList(@NonNull List<TikiUser> list) {
    }

    public void addData(@NonNull TikiUser data) {
    }

    public void setData(@NonNull List<TikiUser> users) {
        if (this.f1766k) {
            this.f1762g.add(this.f1765j);
        }
        this.f1763h = users;
        notifyDataSetChanged();
    }

    public void selectAll(boolean selectAll) {
        this.f1764i = selectAll;
        if (!selectAll) {
            this.f1762g.clear();
            this.f1762g.add(this.f1765j);
        } else if (this.f1763h != null) {
            for (TikiUser user : this.f1763h) {
                this.f1762g.add(user.getUid());
            }
        }
        notifyDataSetChanged();
        ((AddSendFriendsActivity) this.b).setSelectUserCount(this.f1762g.size());
    }

    public int getItemCount() {
        if (this.f1763h == null) {
            return 0;
        }
        return this.f1763h.size();
    }

    public Set<String> getSelectUser() {
        return this.f1762g;
    }

    protected int mo2165a() {
        return C0376R.layout.item_add_friend;
    }

    protected FriendHolder m1017a(View view) {
        return new FriendHolder(view);
    }

    public void onBindViewHolder(FriendHolder holder, int position) {
        TikiUser user = (TikiUser) this.f1763h.get(position);
        if (user != null && user.isValid() && user.getFirstPinYin() != null) {
            if (TextUtils.isEmpty(user.getAvatar()) || !user.getAvatar().startsWith("http://")) {
                holder.f1757a.setImageURI("res://" + this.b.getPackageName() + "/" + C0376R.mipmap.default_tiki_avatar);
            } else {
                FrescoUtil.setImageURI(holder.f1757a, ResourceUrlUtil.getNormalAvatar(user.getAvatar(), DisplayUtil.dip2px(36.0f)));
            }
            holder.f1759c.setText(user.getMark());
            if (this.f1766k) {
                if (this.f1764i) {
                    holder.f1758b.setBackgroundResource(C0376R.mipmap.icon_gouxuan);
                } else if (this.f1762g.contains(user.getUid())) {
                    holder.f1758b.setBackgroundResource(C0376R.mipmap.icon_gouxuan);
                } else {
                    holder.f1758b.setBackgroundResource(C0376R.mipmap.icon_kexuan);
                }
                if (user.getUid().equals(this.f1765j)) {
                    holder.f1758b.setBackgroundResource(C0376R.mipmap.icon_gouxuan_moren);
                }
                RxView.clicks(holder.f1758b).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(ContactsAdapter$$Lambda$1.lambdaFactory$(this, user, holder));
            } else {
                holder.f1758b.setVisibility(8);
                holder.itemView.setOnClickListener(ContactsAdapter$$Lambda$2.lambdaFactory$(this, user));
            }
            if (user.isShowPinyin()) {
                holder.f1760d.setVisibility(0);
                holder.f1760d.setText(user.getFirstPinYin());
                if (user.getFirstPinYin().equals("{")) {
                    holder.f1760d.setText("#");
                    return;
                }
                return;
            }
            holder.f1760d.setVisibility(8);
        }
    }

    /* synthetic */ void m1019a(TikiUser user, FriendHolder holder, Object aVoid) throws Exception {
        if (!user.getUid().equals(this.f1765j)) {
            if (this.f1762g.contains(user.getUid())) {
                this.f1762g.remove(user.getUid());
                holder.f1758b.setBackgroundResource(C0376R.mipmap.icon_kexuan);
            } else {
                this.f1762g.add(user.getUid());
                holder.f1758b.setBackgroundResource(C0376R.mipmap.icon_gouxuan);
            }
            ((AddSendFriendsActivity) this.b).setSelectUserCount(this.f1762g.size());
        }
    }

    /* synthetic */ void m1018a(TikiUser user, View v) {
        ChatMessageActivity.launchChatMessage((BaseActivity) this.b, user.getUid());
    }
}
