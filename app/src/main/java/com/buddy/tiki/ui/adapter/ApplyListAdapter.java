package com.buddy.tiki.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.user.FollowApply;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.ui.adapter.base.BaseAdapter;
import com.buddy.tiki.util.BitsUtil;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.util.FrescoUtil;
import com.buddy.tiki.util.ResourceUrlUtil;
import com.buddy.tiki.util.UserChatRealmHelper;
import com.buddy.tiki.view.CountDownCircleView;
import com.facebook.drawee.view.SimpleDraweeView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class ApplyListAdapter extends BaseAdapter<ApplyHolder, FollowApply> {
    private static final TikiLog f1659f = TikiLog.getInstance("ApplyListAdapter");

    static class ApplyHolder extends ViewHolder {
        CountDownCircleView f1655a;
        AppCompatTextView f1656b;
        AppCompatTextView f1657c;
        SimpleDraweeView f1658d;

        public ApplyHolder(View itemView) {
            super(itemView);
            this.f1658d = (SimpleDraweeView) itemView.findViewById(C0376R.id.avatar);
            this.f1655a = (CountDownCircleView) itemView.findViewById(C0376R.id.countdown);
            this.f1656b = (AppCompatTextView) itemView.findViewById(C0376R.id.nick);
            this.f1657c = (AppCompatTextView) itemView.findViewById(C0376R.id.accept_button);
        }
    }

    protected /* synthetic */ ViewHolder mo2168b(View view) {
        return m982a(view);
    }

    public ApplyListAdapter(@NonNull Context context) {
        super(context);
    }

    public void setAcceptFriend(String uid) {
        int i = 0;
        while (i < this.a.size()) {
            FollowApply apply = (FollowApply) this.a.get(i);
            if (apply != null && apply.getUser() != null && apply.getUser().getUid().equals(uid)) {
                apply.getUser().setRelation(4);
                break;
            }
            i++;
        }
        notifyItemChanged(i);
    }

    public void addDataList(@NonNull List<FollowApply> list) {
        int preSize = this.a.size();
        this.a.addAll(list);
        notifyItemRangeInserted(preSize, list.size());
    }

    public void addData(@NonNull FollowApply data) {
    }

    protected int mo2165a() {
        return C0376R.layout.item_apply_list;
    }

    protected ApplyHolder m982a(View view) {
        return new ApplyHolder(view);
    }

    public void onBindViewHolder(ApplyHolder holder, int position) {
        FollowApply followApply = (FollowApply) this.a.get(position);
        if (followApply != null && followApply.getUser() != null) {
            FrescoUtil.setImageURI(holder.f1658d, ResourceUrlUtil.getNormalAvatar(followApply.getUser().getAvatar(), DisplayUtil.dip2px(48.0f)));
            holder.f1655a.setLeftTime(followApply.getExpired());
            if (followApply.getUser() != null) {
                holder.f1656b.setText(followApply.getUser().getNick());
            }
            if (BitsUtil.isFriend(followApply.getUser().getRelation())) {
                holder.f1657c.setText(C0376R.string.accpeted);
                holder.itemView.setEnabled(false);
                holder.f1657c.setEnabled(false);
                holder.f1655a.setVisibility(8);
                return;
            }
            holder.f1655a.setVisibility(0);
            holder.f1657c.setText(C0376R.string.accept);
            holder.itemView.setEnabled(true);
            holder.f1657c.setEnabled(true);
            holder.f1657c.setOnClickListener(ApplyListAdapter$$Lambda$1.lambdaFactory$(this, followApply, holder));
            holder.itemView.setOnClickListener(ApplyListAdapter$$Lambda$2.lambdaFactory$(this, followApply, holder));
        }
    }

    /* synthetic */ void m985b(FollowApply followApply, ApplyHolder holder, View v) {
        m977a(followApply, holder);
    }

    /* synthetic */ void m983a(FollowApply followApply, ApplyHolder holder, View v) {
        m977a(followApply, holder);
    }

    private void m977a(FollowApply followApply, ApplyHolder holder) {
        DataLayer.getInstance().getFollowManager().passApplysAction(new String[]{followApply.getUser().getUid()}, new String[]{followApply.getApplyId()}).subscribeOn(Schedulers.io()).filter(ApplyListAdapter$$Lambda$3.lambdaFactory$()).flatMap(ApplyListAdapter$$Lambda$4.lambdaFactory$(followApply)).observeOn(AndroidSchedulers.mainThread()).subscribe(ApplyListAdapter$$Lambda$5.lambdaFactory$(followApply, holder), ApplyListAdapter$$Lambda$6.lambdaFactory$());
    }

    static /* synthetic */ void m978a(FollowApply followApply, ApplyHolder holder, Boolean aBoolean) throws Exception {
        UserChatRealmHelper.getInstance().updateSession(followApply.getUser().getUid());
        holder.f1657c.setText(C0376R.string.accpeted);
        holder.itemView.setEnabled(false);
        holder.f1657c.setEnabled(false);
        holder.f1655a.setVisibility(8);
    }
}
