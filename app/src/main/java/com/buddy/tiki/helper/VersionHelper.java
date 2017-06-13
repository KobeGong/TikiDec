package com.buddy.tiki.helper;

import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.MimeTypeMap;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.model.app.VersionInfo;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.util.ToastUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.lang.ref.WeakReference;

public class VersionHelper {
    private static final TikiLog f766a = TikiLog.getInstance("VersionHelper");
    private long f767b = -1;
    private WeakReference<BaseActivity> f768c;
    private boolean f769d;
    private BroadcastReceiver f770e = new C03911(this);

    class C03911 extends BroadcastReceiver {
        final /* synthetic */ VersionHelper f765a;

        C03911(VersionHelper this$0) {
            this.f765a = this$0;
        }

        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra("extra_download_id", -1);
            if (id != -1 && id == this.f765a.f767b) {
                Query query = new Query();
                query.setFilterById(new long[]{id});
                Observable.just((DownloadManager) ((BaseActivity) this.f765a.f768c.get()).getSystemService("download")).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).flatMap(VersionHelper$1$$Lambda$1.lambdaFactory$(query)).observeOn(AndroidSchedulers.mainThread()).filter(VersionHelper$1$$Lambda$2.lambdaFactory$()).subscribe(VersionHelper$1$$Lambda$3.lambdaFactory$(), VersionHelper$1$$Lambda$4.lambdaFactory$());
            }
        }

        static /* synthetic */ ObservableSource m177a(Query query, DownloadManager downloadManager) throws Exception {
            Cursor c = downloadManager.query(query);
            String uri = null;
            if (c.moveToFirst()) {
                uri = c.getString(c.getColumnIndex("local_filename"));
                if (uri != null) {
                    uri = Uri.fromFile(new File(uri)).toString();
                }
            }
            c.close();
            return Observable.just(uri);
        }

        static /* synthetic */ boolean m179a(String s) throws Exception {
            return s != null;
        }
    }

    public VersionHelper(BaseActivity activity) {
        this.f768c = new WeakReference(activity);
        this.f769d = false;
    }

    public void destroy() {
        if (this.f768c != null && this.f768c.get() != null && this.f769d) {
            ((BaseActivity) this.f768c.get()).unregisterReceiver(this.f770e);
        }
    }

    public void showDownloadDialog(@NonNull VersionInfo versionInfo) {
        if (this.f768c != null && this.f768c.get() != null) {
            if (!versionInfo.isForce()) {
                PreferenceUtil.setUpgradeShowtime();
            }
            DialogHelper.INSTANCE.showUpdateDialog((Context) this.f768c.get(), versionInfo, VersionHelper$$Lambda$1.lambdaFactory$(this, versionInfo));
        }
    }

    /* synthetic */ void m184a(@NonNull VersionInfo versionInfo, View v) {
        m182a(versionInfo);
        v.setEnabled(false);
    }

    private void m182a(VersionInfo versionInfo) {
        if (this.f768c != null && this.f768c.get() != null) {
            ToastUtil.getInstance().show((int) C0376R.string.start_downloading);
            Request request = new Request(Uri.parse(versionInfo.getDownloadUrl()));
            request.setAllowedOverRoaming(false);
            request.setMimeType(MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(versionInfo.getDownloadUrl())));
            request.setNotificationVisibility(0);
            request.setVisibleInDownloadsUi(true);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "tiki.apk");
            this.f767b = ((DownloadManager) ((BaseActivity) this.f768c.get()).getSystemService("download")).enqueue(request);
            ((BaseActivity) this.f768c.get()).registerReceiver(this.f770e, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
            this.f769d = true;
        }
    }
}
