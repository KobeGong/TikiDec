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

public class GiftAdapter extends BaseAdapter<GiftHolder, Gift> {
    private int f1814f = -1;

    static class GiftHolder extends ViewHolder {
        SimpleDraweeView f1809a;
        SimpleDraweeView f1810b;
        WrapContentDraweeView f1811c;
        AppCompatTextView f1812d;
        AppCompatTextView f1813e;

        public GiftHolder(View itemView) {
            super(itemView);
            this.f1809a = (SimpleDraweeView) itemView.findViewById(C0376R.id.gift_cover);
            this.f1810b = (SimpleDraweeView) itemView.findViewById(C0376R.id.gift_gif);
            this.f1812d = (AppCompatTextView) itemView.findViewById(C0376R.id.diamond_cost);
            this.f1813e = (AppCompatTextView) itemView.findViewById(C0376R.id.increase_duration);
            this.f1811c = (WrapContentDraweeView) itemView.findViewById(C0376R.id.gift_badge);
        }
    }

    protected /* synthetic */ ViewHolder mo2168b(View view) {
        return m1035a(view);
    }

    public GiftAdapter(@NonNull Context context, @Nullable OnItemClick<Gift> listener) {
        super(context, (OnItemClick) listener);
    }

    protected int mo2165a() {
        return C0376R.layout.item_gift;
    }

    protected GiftHolder m1035a(View view) {
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
            holder.f1811c.setGivenHeight(this.b.getResources().getDimensionPixelSize(C0376R.dimen.gift_badge_height));
            FrescoUtil.setImageURI(holder.f1811c, gift.getIcon());
            if (!(this.f1814f == -1 || this.f1814f == position || holder.f1810b.getController() == null)) {
                Animatable animatable = holder.f1810b.getController().getAnimatable();
                if (animatable != null && animatable.isRunning()) {
                    animatable.stop();
                }
            }
            holder.f1810b.setVisibility(this.f1814f == position ? 0 : 4);
            if (this.f1814f == position) {
                holder.f1809a.setVisibility(4);
            } else {
                holder.f1809a.setVisibility(0);
                FrescoUtil.setImageURI(holder.f1809a, gift.getCover());
            }
            if (gift.getDiamonds() > 0) {
                holder.f1812d.setText(this.b.getResources().getString(C0376R.string.diamond_num_format, new Object[]{Integer.valueOf(gift.getDiamonds())}));
            } else {
                holder.f1812d.setText(C0376R.string.free);
            }
            holder.f1813e.setText(this.b.getResources().getString(C0376R.string.increase_duration, new Object[]{Integer.valueOf(gift.getDelayeds())}));
            View view = holder.itemView;
            if (this.f1814f == position) {
                z = true;
            }
            view.setSelected(z);
            holder.itemView.setOnClickListener(GiftAdapter$$Lambda$1.lambdaFactory$(this, holder, gift));
        }
    }

    /* synthetic */ void m1036a(GiftHolder holder, Gift gift, View view) {
        if (this.f1814f != holder.getAdapterPosition()) {
            holder.f1810b.setVisibility(0);
            holder.f1810b.setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(gift.getSource()).setAutoPlayAnimations(true)).build());
            int lastIndex = this.f1814f;
            this.f1814f = holder.getAdapterPosition();
            holder.itemView.setSelected(true);
            holder.f1809a.setVisibility(4);
            if (this.d != null) {
                this.d.click(holder.itemView, gift, holder.getAdapterPosition());
            }
            if (lastIndex != -1) {
                notifyItemChanged(lastIndex);
            }
        }
    }
}
