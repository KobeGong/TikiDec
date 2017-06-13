package com.buddy.tiki.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.base.TopConfig;
import com.buddy.tiki.model.user.User;
import com.buddy.tiki.ui.adapter.base.BaseAdapter;
import com.buddy.tiki.util.BitsUtil;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.util.FrescoUtil;
import com.buddy.tiki.util.ResourceUrlUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class SearchAdapter extends BaseAdapter<SearchHolder, User> {

    static class SearchHolder extends ViewHolder {
        SimpleDraweeView f1820a;
        AppCompatTextView f1821b;
        AppCompatTextView f1822c;

        public SearchHolder(View itemView) {
            super(itemView);
            this.f1820a = (SimpleDraweeView) itemView.findViewById(C0376R.id.avatar);
            this.f1821b = (AppCompatTextView) itemView.findViewById(C0376R.id.nick);
            this.f1822c = (AppCompatTextView) itemView.findViewById(C0376R.id.send_button);
        }
    }

    protected /* synthetic */ ViewHolder mo2168b(View view) {
        return m1043a(view);
    }

    public SearchAdapter(@NonNull Context context) {
        super(context);
    }

    public void addDataList(@NonNull List<User> list) {
    }

    public void addData(@NonNull User data) {
        int size = this.a.size();
        this.a.add(data);
        notifyItemInserted(size);
    }

    protected int mo2165a() {
        return C0376R.layout.item_search;
    }

    protected SearchHolder m1043a(View view) {
        return new SearchHolder(view);
    }

    public void onBindViewHolder(SearchHolder holder, int position) {
        User user = (User) this.a.get(position);
        if (user != null) {
            FrescoUtil.setImageURI(holder.f1820a, ResourceUrlUtil.getNormalAvatar(user.getAvatar(), DisplayUtil.dip2px(48.0f)));
            holder.f1821b.setText(user.getMark());
            if (user.getUid().equals(TopConfig.f408a)) {
                holder.f1822c.setVisibility(8);
            } else if (BitsUtil.isFriend(user.getRelation())) {
                holder.f1822c.setVisibility(0);
                holder.f1822c.setEnabled(false);
                holder.f1822c.setText(C0376R.string.have_been_friend);
            } else {
                holder.f1822c.setVisibility(0);
                holder.f1822c.setEnabled(true);
                holder.f1822c.setText(C0376R.string.add);
                holder.f1822c.setOnClickListener(SearchAdapter$$Lambda$1.lambdaFactory$(user, holder));
            }
        }
    }

    static /* synthetic */ void m1039a(SearchHolder holder, Boolean aBoolean) throws Exception {
        holder.f1822c.setEnabled(false);
        holder.f1822c.setText(C0376R.string.request_sent);
    }

    static /* synthetic */ void m1040a(Throwable throwable) throws Exception {
    }
}
