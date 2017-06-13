package com.buddy.tiki.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.event.ResourceEvent.UseBorderEvent;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.resource.Border;
import com.buddy.tiki.ui.adapter.base.BaseAdapter;
import com.buddy.tiki.util.FrescoUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class BorderAdapter extends BaseAdapter<BorderHolder, Border> {
    private static final TikiLog f1668f = TikiLog.getInstance("BorderAdapter");
    private int f1669g;

    static class BorderHolder extends ViewHolder {
        SimpleDraweeView f1666a;
        SimpleDraweeView f1667b;

        public BorderHolder(View itemView) {
            super(itemView);
            this.f1667b = (SimpleDraweeView) itemView.findViewById(C0376R.id.border_cover);
            this.f1666a = (SimpleDraweeView) itemView.findViewById(C0376R.id.border_gif);
        }
    }

    protected /* synthetic */ ViewHolder mo2168b(View view) {
        return m987a(view);
    }

    public BorderAdapter(@NonNull Context context) {
        super(context);
        this.f1669g = -1;
        this.f1669g = -1;
    }

    protected int mo2165a() {
        return C0376R.layout.item_border;
    }

    protected BorderHolder m987a(View view) {
        return new BorderHolder(view);
    }

    public void addDataList(@NonNull List<Border> list) {
        int preSize = this.a.size();
        this.a.addAll(list);
        notifyItemRangeInserted(preSize, list.size());
    }

    public void addData(@NonNull Border data) {
        int preSize = this.a.size();
        this.a.add(data);
        notifyItemInserted(preSize);
    }

    public void onBindViewHolder(BorderHolder holder, int position) {
        int i = 4;
        Border border = (Border) this.a.get(position);
        if (border != null) {
            boolean z;
            if (!(this.f1669g == position || holder.f1666a.getController() == null)) {
                Animatable animatable = holder.f1666a.getController().getAnimatable();
                if (animatable != null && animatable.isRunning()) {
                    animatable.stop();
                }
            }
            SimpleDraweeView simpleDraweeView = holder.f1666a;
            int i2 = (this.f1669g != position || position == 0) ? 4 : 0;
            simpleDraweeView.setVisibility(i2);
            View view = holder.itemView;
            if (this.f1669g == position) {
                z = true;
            } else {
                z = false;
            }
            view.setSelected(z);
            if (position == 0) {
                ((GenericDraweeHierarchy) holder.f1667b.getHierarchy()).setActualImageScaleType(ScaleType.f4952f);
                FrescoUtil.setImageURI(holder.f1667b, border.getCover());
                holder.f1666a.setVisibility(4);
                holder.itemView.setOnClickListener(BorderAdapter$$Lambda$1.lambdaFactory$(this, holder, border));
                return;
            }
            SimpleDraweeView simpleDraweeView2 = holder.f1667b;
            if (this.f1669g != position) {
                i = 0;
            }
            simpleDraweeView2.setVisibility(i);
            ((GenericDraweeHierarchy) holder.f1667b.getHierarchy()).setActualImageScaleType(ScaleType.f4947a);
            FrescoUtil.setImageURI(holder.f1667b, border.getCover());
            holder.itemView.setOnClickListener(BorderAdapter$$Lambda$2.lambdaFactory$(this, holder, border));
        }
    }

    /* synthetic */ void m990b(BorderHolder holder, Border border, View v) {
        if (this.f1669g != holder.getAdapterPosition()) {
            int preIndex = this.f1669g;
            this.f1669g = holder.getAdapterPosition();
            holder.itemView.setSelected(true);
            EventBus.getDefault().post(new UseBorderEvent(border));
            notifyItemChanged(preIndex);
        }
    }

    /* synthetic */ void m988a(BorderHolder holder, Border border, View view) {
        if (this.f1669g != holder.getAdapterPosition()) {
            int preIndex = this.f1669g;
            this.f1669g = holder.getAdapterPosition();
            notifyItemChanged(preIndex);
            holder.itemView.setSelected(true);
            holder.f1667b.setVisibility(4);
            EventBus.getDefault().post(new UseBorderEvent(border));
            holder.f1666a.setVisibility(0);
            holder.f1666a.setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(border.getSource()).setAutoPlayAnimations(true)).build());
        }
    }
}
