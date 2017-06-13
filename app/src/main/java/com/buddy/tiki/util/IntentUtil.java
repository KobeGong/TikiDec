package com.buddy.tiki.util;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.text.TextUtils;
import com.buddy.tiki.ChatApp;
import java.io.File;
import org.bytedeco.javacpp.postproc;
import org.bytedeco.javacpp.swscale;

public class IntentUtil {
    public static File getDownloadFolder() {
        return Environment.getExternalStoragePublicDirectory("tiki");
    }

    @SuppressLint({"NewApi"})
    public static long startDownload(String url, String name) {
        Context context = ChatApp.getInstance();
        DownloadManager manager = (DownloadManager) context.getSystemService("download");
        Request request = new Request(Uri.parse(url));
        request.setAllowedNetworkTypes(3);
        request.setVisibleInDownloadsUi(true);
        if (name != null) {
            request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, name);
        } else if (VERSION.SDK_INT >= 11) {
            request.setNotificationVisibility(1);
        }
        try {
            return manager.enqueue(request);
        } catch (IllegalArgumentException e) {
            return -1;
        }
    }

    public static void startInstallApp(String uri) {
        if (!TextUtils.isEmpty(uri)) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(postproc.PP_CPU_CAPS_ALTIVEC);
                intent.setDataAndType(Uri.parse(uri), "application/vnd.android.package-archive");
                ChatApp.getInstance().startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void startInstalledAppDetailsActivity(Context context) {
        if (context != null) {
            Intent i = new Intent();
            i.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            i.addCategory("android.intent.category.DEFAULT");
            i.setData(Uri.parse("package:" + context.getPackageName()));
            i.addFlags(postproc.PP_CPU_CAPS_ALTIVEC);
            i.addFlags(postproc.PP_CPU_CAPS_3DNOW);
            i.addFlags(swscale.SWS_ERROR_DIFFUSION);
            context.startActivity(i);
        }
    }
}
