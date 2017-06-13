package com.buddy.tiki.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.model.resource.Gift;
import com.buddy.tiki.ui.adapter.base.BaseAdapter;
import com.buddy.tiki.ui.adapter.base.BaseAdapter.OnItemClick;
import com.buddy.tiki.util.FrescoUtil;
import com.buddy.tiki.view.WrapContentDraweeView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class ChatRoomGiftAdapter extends BaseAdapter<GiftHolder, Gift> {
    private int f1751f = -1;

    static class GiftHolder extends ViewHolder {
        SimpleDraweeView f1746a;
        SimpleDraweeView f1747b;
        WrapContentDraweeView f1748c;
        AppCompatTextView f1749d;
        AppCompatTextView f1750e;

        public GiftHolder(View itemView) {
            super(itemView);
            this.f1746a = (SimpleDraweeView) itemView.findViewById(C0376R.id.gift_cover);
            this.f1747b = (SimpleDraweeView) itemView.findViewById(C0376R.id.gift_gif);
            this.f1749d = (AppCompatTextView) itemView.findViewById(C0376R.id.diamond_cost);
            this.f1750e = (AppCompatTextView) itemView.findViewById(C0376R.id.increase_duration);
            this.f1748c = (WrapContentDraweeView) itemView.findViewById(C0376R.id.gift_badge);
        }
    }

    protected /* synthetic */ ViewHolder mo2168b(View view) {
        return m1013a(view);
    }

    public ChatRoomGiftAdapter(@NonNull Context context, @Nullable OnItemClick<Gift> listener) {
        super(context, (OnItemClick) listener);
    }

    protected int mo2165a() {
        return C0376R.layout.item_gift_chatroom;
    }

    protected GiftHolder m1013a(View view) {
        return new GiftHolder(view);
    }

    public void addDataList(@NonNull List<Gift> list) {
        int preSize = this.a.size();
        this.a.addAll(list);
        notifyItemChanged(preSize, Integer.valueOf(list.size()));
    }

    public void addData(@NonNull Gift data) {
    }

    public void onBindViewHolder(GiftHolder holder, int position) {
        boolean z = false;
        Gift gift = (Gift) this.a.get(position);
        if (gift != null) {
            holder.f1748c.setGivenHeight(this.b.getResources().getDimensionPixelSize(C0376R.dimen.gift_badge_height));
            FrescoUtil.setImageURI(holder.f1748c, gift.getIcon());
            if (!(this.f1751f == -1 || this.f1751f == position || holder.f1747b.getController() == null)) {
                Animatable animatable = holder.f1747b.getController().getAnimatable();
                if (animatable != null && animatable.isRunning()) {
                    animatable.stop();
                }
            }
            holder.f1747b.setVisibility(this.f1751f == position ? 0 : 4);
            if (this.f1751f == position) {
                holder.f1746a.setVisibility(4);
            } else {
                holder.f1746a.setVisibility(0);
                FrescoUtil.setImageURI(holder.f1746a, gift.getCover(), true);
            }
            if (gift.getDiamonds() > 0) {
                holder.f1749d.setText(this.b.getResources().getString(C0376R.string.diamond_num_format, new Object[]{Integer.valueOf(gift.getDiamonds())}));
            } else {
                holder.f1749d.setText(C0376R.string.free);
            }
            View view = holder.itemView;
            if (this.f1751f == position) {
                z = true;
            }
            view.setSelected(z);
            holder.itemView.setOnClickListener(ChatRoomGiftAdapter$$Lambda$1.lambdaFactory$(this, holder, gift));
        }
    }

    /* synthetic */ void m1014a(GiftHolder holder, Gift gift, View view) {
        if (this.f1751f != holder.getAdapterPosition()) {
            holder.f1747b.setVisibility(0);
            holder.f1747b.setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(gift.getSource()).setAutoPlayAnimations(true)).build());
            int lastIndex = this.f1751f;
            this.f1751f = holder.getAdapterPosition();
            holder.itemView.setSelected(true);
            holder.f1746a.setVisibility(4);
            if (this.d != null) {
                this.d.click(holder.itemView, gift, holder.getAdapterPosition());
            }
            if (lastIndex != -1) {
                notifyItemChanged(lastIndex);
            }
        }
    }
}
