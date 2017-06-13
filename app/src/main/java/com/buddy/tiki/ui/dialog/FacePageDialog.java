package com.buddy.tiki.ui.dialog;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.ui.FaceDividerItemDecoration;
import com.buddy.tiki.ui.adapter.FacePageAdapter;
import com.buddy.tiki.ui.dialog.base.BaseBottomSheetFragment;
import com.buddy.tiki.util.SchedulersCompat;
import io.reactivex.disposables.Disposable;
import java.util.List;

public class FacePageDialog extends BaseBottomSheetFragment {
    private static final TikiLog f1867c = TikiLog.getInstance(FacePageDialog.class.getSimpleName());
    private RecyclerView f1868d;
    private int f1869e = -1;
    private Disposable f1870f;
    private FacePageAdapter f1871g;

    protected int mo2176a() {
        return getResources().getDimensionPixelOffset(C0376R.dimen.bottom_faceu_dialog_peek_height);
    }

    protected int mo2178b() {
        return C0376R.layout.dialog_face;
    }

    protected int mo2179c() {
        return C0376R.style.BottomDialogStyle;
    }

    protected void mo2177a(View view) {
        this.f1868d = (RecyclerView) view.findViewById(C0376R.id.data_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5);
        gridLayoutManager.setInitialPrefetchItemCount(5);
        this.f1868d.setLayoutManager(gridLayoutManager);
        int spacing = getResources().getDimensionPixelOffset(C0376R.dimen.padding_mini);
        this.f1868d.addItemDecoration(new FaceDividerItemDecoration(spacing, spacing, true));
        ItemAnimator itemAnimator = this.f1868d.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(true);
        }
    }

    protected void mo2180d() {
        this.f1870f = DataLayer.getInstance().getResourceManager().sysFaceunityRequest(1).compose(SchedulersCompat.applyIoSchedulers()).filter(FacePageDialog$$Lambda$1.lambdaFactory$()).subscribe(FacePageDialog$$Lambda$2.lambdaFactory$(this), FacePageDialog$$Lambda$3.lambdaFactory$());
    }

    static /* synthetic */ boolean m1082b(List faceUnities) throws Exception {
        return (faceUnities == null || faceUnities.size() == 0) ? false : true;
    }

    /* synthetic */ void m1084a(int index) {
        this.f1869e = index;
    }

    /* synthetic */ void m1086a(List faceUnities) throws Exception {
        this.f1871g = new FacePageAdapter(getContext(), faceUnities, this.f1869e, FacePageDialog$$Lambda$4.lambdaFactory$(this));
        this.f1868d.setAdapter(this.f1871g);
    }

    static /* synthetic */ void m1081a(Throwable throwable) throws Exception {
    }

    public void onPause() {
        if (!(this.f1870f == null || this.f1870f.isDisposed())) {
            this.f1870f.dispose();
        }
        super.onPause();
    }
}
