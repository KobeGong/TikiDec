package com.buddy.tiki.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import butterknife.BindView;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.ui.dialog.LoadingDialog;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.util.FrescoUtil;
import com.buddy.tiki.util.PhotoUtil;
import com.buddy.tiki.util.SchedulersCompat;
import com.buddy.tiki.util.ToastUtil;
import com.buddy.tiki.view.PinchToZoomDraweeView;
import com.buddy.tiki.view.UnlimitedSizeLayout;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.geekint.flying.bitmap.tool.BitmapTool;
import com.igexin.download.Downloads;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AvatarEditActivity extends BaseActivity {
    private static final TikiLog f1125a = TikiLog.getInstance(AvatarEditActivity.class.getSimpleName());
    private int f1126b;
    private int f1127d;
    private int f1128e;
    private String f1129f;
    private String f1130g;
    private String f1131h;
    private int f1132i;
    private int f1133j;
    private RectF f1134k = new RectF();
    private RectF f1135l = new RectF();
    @BindView(2131820704)
    AppCompatTextView mCancel;
    @BindView(2131820705)
    AppCompatTextView mChoose;
    @BindView(2131820702)
    RelativeLayout mChooseArea;
    @BindView(2131820700)
    PinchToZoomDraweeView mImagePreview;
    @BindView(2131820699)
    UnlimitedSizeLayout mImagePreviewLayout;
    @BindView(2131820703)
    RelativeLayout mLowerCover;
    @BindView(2131820698)
    RelativeLayout mMask;
    @BindView(2131820697)
    LinearLayout mRootLayout;
    @BindView(2131820701)
    RelativeLayout mUpperCover;

    class C04201 implements OnGlobalLayoutListener {
        final /* synthetic */ AvatarEditActivity f1124a;

        C04201(AvatarEditActivity this$0) {
            this.f1124a = this$0;
        }

        public void onGlobalLayout() {
            this.f1124a.mMask.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            this.f1124a.f1126b = this.f1124a.mMask.getMeasuredWidth();
            this.f1124a.f1127d = this.f1124a.mMask.getMeasuredHeight();
            if (this.f1124a.f1126b <= 0 || this.f1124a.f1127d <= 0) {
                this.f1124a.f1126b = DisplayUtil.getDisplayWidth();
                this.f1124a.f1127d = DisplayUtil.getDisplayHeight();
            }
            this.f1124a.f1128e = Math.min(this.f1124a.f1126b, this.f1124a.f1127d);
            LayoutParams params = (LayoutParams) this.f1124a.mChooseArea.getLayoutParams();
            int c = this.f1124a.f1128e;
            params.height = c;
            params.width = c;
            this.f1124a.mChooseArea.setLayoutParams(params);
            LayoutParams layoutParams;
            if (this.f1124a.f1126b <= this.f1124a.f1127d) {
                this.f1124a.mRootLayout.setOrientation(1);
                this.f1124a.mRootLayout.setGravity(80);
                layoutParams = (LayoutParams) this.f1124a.mUpperCover.getLayoutParams();
                layoutParams.width = this.f1124a.f1126b;
                layoutParams.height = (this.f1124a.f1127d - this.f1124a.f1126b) / 2;
                this.f1124a.mUpperCover.setLayoutParams(layoutParams);
                layoutParams = (LayoutParams) this.f1124a.mLowerCover.getLayoutParams();
                layoutParams.width = this.f1124a.f1126b;
                layoutParams.height = (this.f1124a.f1127d - this.f1124a.f1126b) / 2;
                this.f1124a.mLowerCover.setLayoutParams(layoutParams);
            } else {
                this.f1124a.mRootLayout.setOrientation(0);
                this.f1124a.mRootLayout.setGravity(GravityCompat.END);
                layoutParams = (LayoutParams) this.f1124a.mUpperCover.getLayoutParams();
                layoutParams.width = (this.f1124a.f1126b - this.f1124a.f1127d) / 2;
                layoutParams.height = this.f1124a.f1127d;
                this.f1124a.mUpperCover.setLayoutParams(layoutParams);
                layoutParams = (LayoutParams) this.f1124a.mLowerCover.getLayoutParams();
                layoutParams.width = (this.f1124a.f1126b - this.f1124a.f1127d) / 2;
                layoutParams.height = this.f1124a.f1127d;
                this.f1124a.mLowerCover.setLayoutParams(layoutParams);
            }
            this.f1124a.m458e();
        }
    }

    protected int mo2115a() {
        return C0376R.layout.activity_avatar_edit;
    }

    protected void mo2116a(Bundle savedInstanceState) {
        m454c();
        m456d();
        m459f();
    }

    protected int mo2117b() {
        return 0;
    }

    private void m454c() {
        Bundle args = getArguments();
        if (args != null) {
            this.f1129f = args.getString("PARAM_KEY_IMAGE_PATH", null);
        }
        this.f1130g = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "photo_preview.jpg").getPath();
    }

    private void m456d() {
        this.mMask.getViewTreeObserver().addOnGlobalLayoutListener(new C04201(this));
    }

    private void m458e() {
        if (!TextUtils.isEmpty(this.f1129f)) {
            File imageFile = new File(this.f1129f);
            if (imageFile.exists() && imageFile.canRead()) {
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(this.f1129f, options);
                int scale = 1;
                while (((double) (options.outWidth * options.outHeight)) * (1.0d / Math.pow((double) scale, 2.0d)) > 4194304.0d) {
                    scale++;
                }
                this.f1132i = options.outWidth;
                this.f1133j = options.outHeight;
                if (scale > 1) {
                    options = new Options();
                    options.inSampleSize = scale;
                    Bitmap bitmap = BitmapFactory.decodeFile(this.f1129f, options);
                    File adjustImage = new File(this.f1130g);
                    if (!adjustImage.exists() || adjustImage.delete()) {
                        PhotoUtil.saveImg2Local(this, bitmap, this.f1130g);
                        this.f1131h = this.f1130g;
                    } else {
                        f1125a.m263e("failed to delete previous adjust image");
                        return;
                    }
                }
                this.f1131h = this.f1129f;
                if (this.f1132i <= 0 || this.f1133j <= 0 || this.f1128e <= 0) {
                    f1125a.m263e("Invalid values: mImageWidth:" + this.f1132i + " mImageHeight:" + this.f1133j + " chooseAreaLength:" + this.f1128e);
                    return;
                }
                int previewWidth;
                int previewHeight;
                UnlimitedSizeLayout.LayoutParams layoutParams;
                if (this.f1132i < this.f1133j) {
                    previewWidth = this.f1128e;
                    previewHeight = (this.f1128e * this.f1133j) / this.f1132i;
                    layoutParams = (UnlimitedSizeLayout.LayoutParams) this.mImagePreview.getLayoutParams();
                    layoutParams.width = previewWidth;
                    layoutParams.height = previewHeight;
                    layoutParams.f2892a = (this.mImagePreviewLayout.getMeasuredWidth() - previewWidth) / 2;
                    layoutParams.f2893b = (this.mImagePreviewLayout.getMeasuredHeight() - previewHeight) / 2;
                    f1125a.m261d("init image preview:x:" + layoutParams.f2892a + " y:" + layoutParams.f2893b + " w:" + layoutParams.width + " h:" + layoutParams.height);
                    this.mImagePreview.setLayoutParams(layoutParams);
                    this.f1134k.set(0.0f, 0.0f, (float) previewWidth, (float) previewHeight);
                    this.f1135l.set(0.0f, ((float) (previewHeight - previewWidth)) / 2.0f, ((float) previewWidth) * 1.0f, ((float) (previewHeight + previewWidth)) / 2.0f);
                } else {
                    previewHeight = this.f1128e;
                    previewWidth = (this.f1128e * this.f1132i) / this.f1133j;
                    layoutParams = (UnlimitedSizeLayout.LayoutParams) this.mImagePreview.getLayoutParams();
                    layoutParams.width = previewWidth;
                    layoutParams.height = previewHeight;
                    layoutParams.f2892a = (this.mImagePreviewLayout.getMeasuredWidth() - previewWidth) / 2;
                    layoutParams.f2893b = (this.mImagePreviewLayout.getMeasuredHeight() - previewHeight) / 2;
                    f1125a.m261d("init image preview:x:" + layoutParams.f2892a + " y:" + layoutParams.f2893b + " w:" + layoutParams.width + " h:" + layoutParams.height);
                    this.mImagePreview.setLayoutParams(layoutParams);
                    this.f1134k.set(0.0f, 0.0f, (float) previewWidth, (float) previewHeight);
                    this.f1135l.set((float) ((previewWidth - previewHeight) / 2), 0.0f, (float) ((previewWidth + previewHeight) / 2), (float) previewHeight);
                }
                Uri uri = Uri.parse(this.f1131h);
                if (uri != null) {
                    Fresco.getImagePipeline().evictFromMemoryCache(uri);
                    Fresco.getImagePipeline().evictFromDiskCache(uri);
                }
                FrescoUtil.setImageURI(this.mImagePreview, Uri.fromFile(new File(this.f1131h)));
                this.mImagePreview.setConstraints(this.f1134k, this.f1135l);
                f1125a.m261d("Rect:" + this.f1134k);
                f1125a.m261d("ChooseArea:" + this.f1135l);
            }
        }
    }

    private void m459f() {
        RxView.clicks(this.mCancel).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(AvatarEditActivity$$Lambda$1.lambdaFactory$(this), AvatarEditActivity$$Lambda$2.lambdaFactory$());
        RxView.clicks(this.mChoose).throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(AvatarEditActivity$$Lambda$3.lambdaFactory$(this), AvatarEditActivity$$Lambda$4.lambdaFactory$());
    }

    /* synthetic */ void m466b(Object aVoid) throws Exception {
        setResult(0);
        finish();
    }

    static /* synthetic */ void m455c(Throwable throwable) throws Exception {
    }

    /* synthetic */ void m463a(Object aVoid) throws Exception {
        LoadingDialog.startLoading((Context) this, (int) C0376R.string.processing);
        Bitmap bitmap = m460g();
        if (bitmap != null && !bitmap.isRecycled()) {
            byte[] data = BitmapTool.bitmap2Bytes(bitmap, CompressFormat.JPEG, 100);
            if (data != null && data.length > 0) {
                getDataLayer().getTikiResManager().uploadAvatar(data).compose(bindToLifecycle()).compose(SchedulersCompat.applyIoSchedulers()).retry(AvatarEditActivity$$Lambda$5.lambdaFactory$()).subscribe(AvatarEditActivity$$Lambda$6.lambdaFactory$(this), AvatarEditActivity$$Lambda$7.lambdaFactory$());
            }
        }
    }

    static /* synthetic */ boolean m448a(Integer integer, Throwable throwable) throws Exception {
        return (throwable instanceof EOFException) && integer.intValue() < 3;
    }

    /* synthetic */ void m464a(String avatarUri) throws Exception {
        LoadingDialog.stopLoading();
        getIntent().putExtra("PARAM_KEY_IMAGE_PATH", avatarUri);
        setResult(-1, getIntent());
        finish();
    }

    static /* synthetic */ void m451b(Throwable throwable) throws Exception {
        LoadingDialog.stopLoading();
        if (throwable instanceof EOFException) {
            ToastUtil.getInstance().show(ChatApp.getInstance(), (int) C0376R.string.upload_avatar_failed);
        }
    }

    static /* synthetic */ void m447a(Throwable throwable) throws Exception {
    }

    private Bitmap m460g() {
        RectF cropRect = this.mImagePreview.getIntersectRect();
        RectF wholeRect = this.mImagePreview.getWholeRect();
        f1125a.m261d("cropRect:" + cropRect);
        f1125a.m261d("wholeRect:" + wholeRect);
        if (cropRect != null && cropRect.width() > 0.0f && cropRect.height() > 0.0f && wholeRect != null && wholeRect.width() > 0.0f && wholeRect.height() > 0.0f) {
            try {
                float offsetX = -wholeRect.left;
                float offsetY = -wholeRect.top;
                wholeRect.offset(offsetX, offsetY);
                cropRect.offset(offsetX, offsetY);
                float scaleFactor = ((float) this.f1132i) / wholeRect.width();
                Matrix transformMatrix = new Matrix();
                transformMatrix.postScale(scaleFactor, scaleFactor);
                transformMatrix.mapRect(cropRect);
                BitmapRegionDecoder bitmapRegionDecoder = BitmapRegionDecoder.newInstance(new FileInputStream(this.f1129f), false);
                Options options = new Options();
                options.inJustDecodeBounds = true;
                Rect decodeRect = new Rect((int) cropRect.left, (int) cropRect.top, (int) cropRect.right, (int) cropRect.bottom);
                bitmapRegionDecoder.decodeRegion(decodeRect, options);
                int scale = 1;
                while (((double) (options.outWidth * options.outHeight)) * (1.0d / Math.pow((double) scale, 2.0d)) > 4194304.0d) {
                    scale++;
                }
                options = new Options();
                options.inSampleSize = scale;
                Bitmap bitmap = bitmapRegionDecoder.decodeRegion(decodeRect, options);
                if (bitmap == null) {
                    return bitmap;
                }
                if (bitmap.getWidth() <= 400 && bitmap.getHeight() <= 400) {
                    return bitmap;
                }
                int scaledW;
                int scaledH;
                if (bitmap.getWidth() > bitmap.getHeight()) {
                    scaledW = Downloads.STATUS_BAD_REQUEST;
                    scaledH = (bitmap.getHeight() * Downloads.STATUS_BAD_REQUEST) / bitmap.getWidth();
                } else {
                    scaledW = (bitmap.getWidth() * Downloads.STATUS_BAD_REQUEST) / bitmap.getHeight();
                    scaledH = Downloads.STATUS_BAD_REQUEST;
                }
                return Bitmap.createScaledBitmap(bitmap, scaledW, scaledH, false);
            } catch (IOException e) {
            }
        }
        return null;
    }
}
