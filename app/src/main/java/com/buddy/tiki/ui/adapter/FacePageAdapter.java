package com.buddy.tiki.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.BuildConfig;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.drawable.MaterialProgressDrawable;
import com.buddy.tiki.event.ResourceEvent.UseFaceUnityEvent;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.resource.FaceUnity;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.ui.adapter.base.BaseAdapter;
import com.buddy.tiki.util.FileUtil;
import com.buddy.tiki.util.FrescoUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.view.PageGridView.PagingAdapter;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.List;
import java.util.Vector;
import org.greenrobot.eventbus.EventBus;

public class FacePageAdapter extends BaseAdapter<FaceViewHolder, FaceUnity> implements PagingAdapter<FaceUnity> {
    private static final TikiLog f1794f = TikiLog.getInstance("FacePageAdapter");
    private Vector<Integer> f1795g = new Vector();
    private volatile int f1796h;
    private OnSelectedCallback f1797i;

    public static class FaceViewHolder extends ViewHolder {
        SimpleDraweeView f1791a;
        AppCompatImageView f1792b;
        ProgressBar f1793c;

        public FaceViewHolder(View itemView) {
            super(itemView);
            this.f1791a = (SimpleDraweeView) itemView.findViewById(C0376R.id.face_cover);
            this.f1792b = (AppCompatImageView) itemView.findViewById(C0376R.id.download_btn);
            this.f1793c = (ProgressBar) itemView.findViewById(C0376R.id.download_progress);
        }
    }

    public interface OnSelectedCallback {
        void onSelected(int i);
    }

    protected /* synthetic */ ViewHolder mo2168b(View view) {
        return m1024a(view);
    }

    public FacePageAdapter(@NonNull Context context, @NonNull List<FaceUnity> list, int index, OnSelectedCallback callback) {
        super(context);
        this.a.addAll(list);
        this.f1796h = index;
        this.f1797i = callback;
    }

    public List<FaceUnity> getData() {
        return this.a;
    }

    public FaceUnity getEmpty() {
        return null;
    }

    public void addDataList(@NonNull List<FaceUnity> list) {
    }

    public void addData(@NonNull FaceUnity data) {
    }

    protected int mo2165a() {
        return C0376R.layout.item_face_single;
    }

    protected FaceViewHolder m1024a(View view) {
        return new FaceViewHolder(view);
    }

    public void onBindViewHolder(FaceViewHolder holder, int position) {
        FaceUnity object = this.a.get(position);
        if (object == null) {
            holder.itemView.setSelected(false);
            holder.itemView.setAlpha(1.0f);
            holder.f1792b.setVisibility(8);
            holder.f1793c.setVisibility(8);
            holder.f1791a.setImageURI(BuildConfig.VERSION_NAME);
            holder.itemView.setOnClickListener(null);
            return;
        }
        if (this.f1796h != position) {
            holder.itemView.setSelected(false);
        } else {
            holder.itemView.setSelected(true);
        }
        FaceUnity faceUnity = object;
        ((GenericDraweeHierarchy) holder.f1791a.getHierarchy()).setProgressBarImage(new MaterialProgressDrawable(this.b, holder.f1791a));
        FrescoUtil.setImageURI(holder.f1791a, faceUnity.getCover());
        if (this.f1795g.contains(Integer.valueOf(position))) {
            holder.f1792b.setVisibility(8);
            holder.f1793c.setVisibility(0);
            holder.itemView.setAlpha(0.5f);
            return;
        }
        holder.f1793c.setVisibility(8);
        holder.f1792b.setVisibility(0);
        FileUtil.isAvatarInDiskAsync(faceUnity.getId()).compose(SchedulersCompat.applyIoSchedulers()).subscribe(FacePageAdapter$$Lambda$1.lambdaFactory$(this, holder, position, faceUnity), FacePageAdapter$$Lambda$2.lambdaFactory$());
    }

    /* synthetic */ void m1029b(FaceViewHolder holder, int position, FaceUnity faceUnity, String path) throws Exception {
        boolean aBoolean;
        int i = 0;
        if (TextUtils.isEmpty(path)) {
            aBoolean = false;
        } else {
            aBoolean = true;
        }
        AppCompatImageView appCompatImageView = holder.f1792b;
        if (aBoolean) {
            i = 8;
        }
        appCompatImageView.setVisibility(i);
        if (aBoolean) {
            holder.itemView.setOnClickListener(FacePageAdapter$$Lambda$6.lambdaFactory$(this, holder, position, path, faceUnity));
        } else {
            holder.itemView.setOnClickListener(FacePageAdapter$$Lambda$7.lambdaFactory$(this, holder, faceUnity, position));
        }
    }

    /* synthetic */ void m1030b(FaceViewHolder holder, int position, String path, FaceUnity faceUnity, View v) {
        showSelected(holder, position, path, faceUnity.getCover());
    }

    /* synthetic */ void m1027a(FaceViewHolder holder, FaceUnity faceUnity, int position, View v) {
        startDownload(holder, faceUnity, position);
    }

    public void showSelected(FaceViewHolder holder, int position, String path, String cover) {
        if (this.f1796h == position) {
            holder.itemView.setSelected(false);
            this.f1796h = -1;
            this.f1797i.onSelected(-1);
            EventBus.getDefault().post(new UseFaceUnityEvent(BuildConfig.VERSION_NAME, -1, BuildConfig.VERSION_NAME));
            return;
        }
        int lastPos = this.f1796h;
        this.f1796h = position;
        notifyItemChanged(lastPos);
        holder.itemView.setSelected(true);
        this.f1797i.onSelected(position);
        EventBus.getDefault().post(new UseFaceUnityEvent(path, position, cover));
    }

    public void startDownload(FaceViewHolder holder, FaceUnity faceUnity, int position) {
        if (!this.f1795g.contains(Integer.valueOf(position))) {
            this.f1795g.add(Integer.valueOf(position));
            holder.itemView.setAlpha(0.5f);
            holder.f1792b.setVisibility(8);
            holder.f1793c.setVisibility(0);
            DataLayer.getInstance().getDownloadApiManager().downloadFile(faceUnity.getIosSrc(), faceUnity.getId(), "Avatar").observeOn(AndroidSchedulers.mainThread()).subscribe(FacePageAdapter$$Lambda$3.lambdaFactory$(this, holder, position, faceUnity), FacePageAdapter$$Lambda$4.lambdaFactory$(holder));
        }
    }

    /* synthetic */ void m1025a(FaceViewHolder holder, int position, FaceUnity faceUnity, String path) throws Exception {
        holder.itemView.setAlpha(1.0f);
        this.f1795g.remove(Integer.valueOf(position));
        holder.f1793c.setVisibility(8);
        holder.itemView.setOnClickListener(FacePageAdapter$$Lambda$5.lambdaFactory$(this, holder, position, path, faceUnity));
    }

    /* synthetic */ void m1026a(FaceViewHolder holder, int position, String path, FaceUnity faceUnity, View v) {
        showSelected(holder, position, path, faceUnity.getCover());
    }

    static /* synthetic */ void m1021a(FaceViewHolder holder, Throwable throwable) throws Exception {
        holder.itemView.setAlpha(1.0f);
        holder.f1793c.setVisibility(8);
        holder.f1792b.setVisibility(8);
    }
}
