package com.buddy.tiki.util;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import com.igexin.download.Downloads;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PhotoUtil {
    private static String f2445a = new File(f2447c, "photo.jpg").getPath();
    private static String f2446b = new File(f2447c, "photo.jpg").getPath();
    private static File f2447c = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

    private static void m1597a() {
        if (!f2447c.exists()) {
            f2447c.mkdirs();
        }
    }

    public static Intent getCameraIntent() {
        m1597a();
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", Uri.fromFile(new File(f2445a)));
        return intent;
    }

    public static Intent getCameraIntent(String photoName) {
        m1597a();
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", Uri.fromFile(new File(f2447c, photoName)));
        return intent;
    }

    public static Intent getGalleryIntent() {
        m1597a();
        return new Intent("android.intent.action.PICK", Media.INTERNAL_CONTENT_URI);
    }

    public static String getPickedCameraPath(Context context, Intent data) {
        if (data != null) {
            return m1596a(context, data);
        }
        try {
            return f2445a;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getPickedCameraPath(String photoPath) {
        return f2447c + "/" + photoPath;
    }

    public static String getPickedGalleryPath(Context context, Intent data) {
        String path = null;
        if (data == null) {
            return path;
        }
        Uri uri = data.getData();
        if (uri != null) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{Downloads._DATA}, null, null, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        path = cursor.getString(0);
                    }
                } catch (Exception e) {
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            } else {
                path = uri.getPath();
            }
            if (cursor != null) {
                cursor.close();
            }
        }
        return path;
    }

    private static java.lang.String m1596a(android.content.Context r15, android.content.Intent r16) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.buddy.tiki.util.PhotoUtil.a(android.content.Context, android.content.Intent):java.lang.String. bs: [B:2:0x001a, B:13:0x0066]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:285)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r0 = 1;
        r2 = new java.lang.String[r0];
        r0 = 0;
        r3 = "_data";
        r2[r0] = r3;
        r13 = 0;
        r1 = r16.getData();
        r0 = r15.getContentResolver();
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r10 = r0.query(r1, r2, r3, r4, r5);
        if (r10 != 0) goto L_0x0025;
    L_0x001a:
        r13 = r1.getPath();	 Catch:{ Exception -> 0x0088, all -> 0x009c }
    L_0x001e:
        if (r10 == 0) goto L_0x0024;
    L_0x0020:
        r10.close();
        r10 = 0;
    L_0x0024:
        return r13;
    L_0x0025:
        r0 = r1.toString();	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r3 = "content://com.android.providers.media.documents/document/image";	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r0 = r0.contains(r3);	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        if (r0 == 0) goto L_0x0090;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
    L_0x0031:
        r9 = 0;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r0 = r1.toString();	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r14 = android.net.Uri.decode(r0);	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r0 = ":";	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r11 = r14.lastIndexOf(r0);	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r0 = r11 + 1;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r9 = r14.substring(r0);	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r3 = r15.getContentResolver();	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r4 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r0.<init>();	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r5 = " _id = ";	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r0 = r0.append(r5);	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r0 = r0.append(r9);	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r6 = r0.toString();	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r7 = 0;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r8 = 0;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r5 = r2;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r12 = r3.query(r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r0 = r12.moveToFirst();	 Catch:{ Exception -> 0x0078, all -> 0x0080 }
        if (r0 == 0) goto L_0x0071;	 Catch:{ Exception -> 0x0078, all -> 0x0080 }
    L_0x006c:
        r0 = 0;	 Catch:{ Exception -> 0x0078, all -> 0x0080 }
        r13 = r12.getString(r0);	 Catch:{ Exception -> 0x0078, all -> 0x0080 }
    L_0x0071:
        if (r12 == 0) goto L_0x001e;
    L_0x0073:
        r12.close();	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r12 = 0;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        goto L_0x001e;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
    L_0x0078:
        r0 = move-exception;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        if (r12 == 0) goto L_0x001e;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
    L_0x007b:
        r12.close();	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r12 = 0;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        goto L_0x001e;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
    L_0x0080:
        r0 = move-exception;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        if (r12 == 0) goto L_0x0087;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
    L_0x0083:
        r12.close();	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r12 = 0;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
    L_0x0087:
        throw r0;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
    L_0x0088:
        r0 = move-exception;
        if (r10 == 0) goto L_0x0024;
    L_0x008b:
        r10.close();
        r10 = 0;
        goto L_0x0024;
    L_0x0090:
        r0 = r10.moveToFirst();	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        if (r0 == 0) goto L_0x001e;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
    L_0x0096:
        r0 = 0;	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        r13 = r10.getString(r0);	 Catch:{ Exception -> 0x0088, all -> 0x009c }
        goto L_0x001e;
    L_0x009c:
        r0 = move-exception;
        if (r10 == 0) goto L_0x00a3;
    L_0x009f:
        r10.close();
        r10 = 0;
    L_0x00a3:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.buddy.tiki.util.PhotoUtil.a(android.content.Context, android.content.Intent):java.lang.String");
    }

    public static String saveImg2Local(Context context, Bitmap bitmap, String fileAbsolutePath) {
        if (TextUtils.isEmpty(fileAbsolutePath) || context == null || bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        File file = new File(fileAbsolutePath);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
        }
        return file.getAbsolutePath();
    }

    public double[] getLatiLongInfo(String filePath) {
        ExifInterface exifInterface;
        double[] locations = new double[2];
        try {
            ExifInterface exif = new ExifInterface(filePath);
            try {
                locations[0] = Double.parseDouble(exif.getAttribute("GPSLatitude"));
                locations[1] = Double.parseDouble(exif.getAttribute("GPSLongitude"));
                exifInterface = exif;
                return locations;
            } catch (IOException e) {
                exifInterface = exif;
                return null;
            }
        } catch (IOException e2) {
            return null;
        }
    }
}
