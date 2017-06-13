package com.buddy.tiki.util;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.BuildConfig;
import android.text.TextUtils;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.service.base.ACache;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.schedulers.Schedulers;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.ResponseBody;
import org.bytedeco.javacpp.swscale;

public class FileUtil {
    private static final TikiLog f2401a = TikiLog.getInstance("FileUtil");
    private static File f2402b = null;
    private static File f2403c = null;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String cacheResourceFile(java.lang.String r12, java.lang.String r13, okhttp3.ResponseBody r14) {
        /*
        r9 = 0;
        r10 = -1;
        if (r14 != 0) goto L_0x0007;
    L_0x0004:
        r9 = "";
    L_0x0006:
        return r9;
    L_0x0007:
        r8 = newCacheFile(r12);
        r7 = 0;
        r11 = r12.hashCode();
        switch(r11) {
            case 74710533: goto L_0x006f;
            case 1972874617: goto L_0x0078;
            default: goto L_0x0013;
        };
    L_0x0013:
        r9 = r10;
    L_0x0014:
        switch(r9) {
            case 0: goto L_0x0082;
            case 1: goto L_0x0085;
            default: goto L_0x0017;
        };
    L_0x0017:
        r7 = ".mp3";
    L_0x0019:
        r1 = new java.io.File;
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r9 = r9.append(r13);
        r9 = r9.append(r7);
        r9 = r9.toString();
        r1.<init>(r8, r9);
        r3 = 0;
        r5 = 0;
        r9 = r1.exists();	 Catch:{ IOException -> 0x00d4 }
        if (r9 == 0) goto L_0x003a;
    L_0x0037:
        r1.delete();	 Catch:{ IOException -> 0x00d4 }
    L_0x003a:
        r1.createNewFile();	 Catch:{ IOException -> 0x00d4 }
        r5 = r14.byteStream();	 Catch:{ IOException -> 0x00d4 }
        r4 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x00d4 }
        r4.<init>(r1);	 Catch:{ IOException -> 0x00d4 }
        r6 = 0;
        r9 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = new byte[r9];	 Catch:{ IOException -> 0x0056, all -> 0x00d1 }
    L_0x004b:
        r6 = r5.read(r0);	 Catch:{ IOException -> 0x0056, all -> 0x00d1 }
        if (r6 == r10) goto L_0x008b;
    L_0x0051:
        r9 = 0;
        r4.write(r0, r9, r6);	 Catch:{ IOException -> 0x0056, all -> 0x00d1 }
        goto L_0x004b;
    L_0x0056:
        r2 = move-exception;
        r3 = r4;
    L_0x0058:
        r9 = f2401a;	 Catch:{ all -> 0x00b9 }
        r10 = "cacheResourceFile: ";
        r9.m264e(r10, r2);	 Catch:{ all -> 0x00b9 }
        if (r3 == 0) goto L_0x0064;
    L_0x0061:
        r3.close();	 Catch:{ IOException -> 0x00b0 }
    L_0x0064:
        if (r5 == 0) goto L_0x0069;
    L_0x0066:
        r5.close();	 Catch:{ IOException -> 0x00b0 }
    L_0x0069:
        r14.close();
    L_0x006c:
        r9 = "";
        goto L_0x0006;
    L_0x006f:
        r11 = "Music";
        r11 = r12.equals(r11);
        if (r11 == 0) goto L_0x0013;
    L_0x0077:
        goto L_0x0014;
    L_0x0078:
        r9 = "Avatar";
        r9 = r12.equals(r9);
        if (r9 == 0) goto L_0x0013;
    L_0x0080:
        r9 = 1;
        goto L_0x0014;
    L_0x0082:
        r7 = ".mp3";
        goto L_0x0019;
    L_0x0085:
        r9 = m1542a(r13, r14);
        goto L_0x0006;
    L_0x008b:
        r4.flush();	 Catch:{ IOException -> 0x0056, all -> 0x00d1 }
        r14.close();	 Catch:{ IOException -> 0x0056, all -> 0x00d1 }
        r9 = r1.getAbsolutePath();	 Catch:{ IOException -> 0x0056, all -> 0x00d1 }
        m1543a(r13, r9);	 Catch:{ IOException -> 0x0056, all -> 0x00d1 }
        if (r4 == 0) goto L_0x009d;
    L_0x009a:
        r4.close();	 Catch:{ IOException -> 0x00a7 }
    L_0x009d:
        if (r5 == 0) goto L_0x00a2;
    L_0x009f:
        r5.close();	 Catch:{ IOException -> 0x00a7 }
    L_0x00a2:
        r14.close();
        r3 = r4;
        goto L_0x006c;
    L_0x00a7:
        r2 = move-exception;
        r9 = f2401a;
        r10 = "cacheResourceFile: close ";
        r9.m264e(r10, r2);
        goto L_0x00a2;
    L_0x00b0:
        r2 = move-exception;
        r9 = f2401a;
        r10 = "cacheResourceFile: close ";
        r9.m264e(r10, r2);
        goto L_0x0069;
    L_0x00b9:
        r9 = move-exception;
    L_0x00ba:
        if (r3 == 0) goto L_0x00bf;
    L_0x00bc:
        r3.close();	 Catch:{ IOException -> 0x00c8 }
    L_0x00bf:
        if (r5 == 0) goto L_0x00c4;
    L_0x00c1:
        r5.close();	 Catch:{ IOException -> 0x00c8 }
    L_0x00c4:
        r14.close();
        throw r9;
    L_0x00c8:
        r2 = move-exception;
        r10 = f2401a;
        r11 = "cacheResourceFile: close ";
        r10.m264e(r11, r2);
        goto L_0x00c4;
    L_0x00d1:
        r9 = move-exception;
        r3 = r4;
        goto L_0x00ba;
    L_0x00d4:
        r2 = move-exception;
        goto L_0x0058;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.buddy.tiki.util.FileUtil.cacheResourceFile(java.lang.String, java.lang.String, okhttp3.ResponseBody):java.lang.String");
    }

    public static boolean isAvatarDownload(@NonNull String url) {
        return !TextUtils.isEmpty(m1544b(url));
    }

    private static String m1544b(@NonNull String id) {
        return ACache.get(ChatApp.getInstance()).getAsString(id);
    }

    public static boolean isAvatarInDiskSync(@NonNull String id) {
        String path = m1544b(id);
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        return new File(path).exists();
    }

    public static Observable<String> isAvatarInDiskAsync(@NonNull String id) {
        return Observable.defer(FileUtil$$Lambda$1.lambdaFactory$(id)).subscribeOn(Schedulers.io());
    }

    static /* synthetic */ ObservableSource m1540a(@NonNull String id) throws Exception {
        String path = m1544b(id);
        if (TextUtils.isEmpty(path)) {
            return Observable.just(BuildConfig.VERSION_NAME);
        }
        return new File(path).exists() ? Observable.just(path) : Observable.just(BuildConfig.VERSION_NAME);
    }

    private static synchronized void m1543a(String id, String path) {
        synchronized (FileUtil.class) {
            if (TextUtils.isEmpty(id)) {
                throw new NullPointerException("url is null");
            } else if (TextUtils.isEmpty(path)) {
                throw new NullPointerException("file path is null");
            } else {
                ACache.get(ChatApp.getInstance()).put(id, path);
            }
        }
    }

    private static String m1542a(String id, ResponseBody responseBody) {
        IOException e;
        Throwable th;
        File targetDir = m1545c("Avatar");
        if (targetDir.exists()) {
            File cacheFile = new File(targetDir, id + ".zip");
            FileOutputStream fileOutputStream = null;
            InputStream is = null;
            try {
                if (cacheFile.exists()) {
                    cacheFile.delete();
                }
                cacheFile.createNewFile();
                is = responseBody.byteStream();
                FileOutputStream fos = new FileOutputStream(cacheFile);
                try {
                    byte[] buffer = new byte[swscale.SWS_SPLINE];
                    while (true) {
                        int len = is.read(buffer);
                        if (len == -1) {
                            break;
                        }
                        fos.write(buffer, 0, len);
                    }
                    fos.flush();
                    responseBody.close();
                    File unzipFile = m1541a(cacheFile, targetDir, id);
                    if (unzipFile == null || !unzipFile.exists()) {
                        if (fos != null) {
                            try {
                                fos.close();
                            } catch (IOException e2) {
                                f2401a.m264e("cacheResourceFile: close ", e2);
                            }
                        }
                        if (is != null) {
                            is.close();
                        }
                        responseBody.close();
                        if (cacheFile.exists()) {
                            cacheFile.delete();
                            fileOutputStream = fos;
                        }
                        return BuildConfig.VERSION_NAME;
                    }
                    m1543a(id, unzipFile.getAbsolutePath());
                    String absolutePath = unzipFile.getAbsolutePath();
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e22) {
                            f2401a.m264e("cacheResourceFile: close ", e22);
                        }
                    }
                    if (is != null) {
                        is.close();
                    }
                    responseBody.close();
                    if (!cacheFile.exists()) {
                        return absolutePath;
                    }
                    cacheFile.delete();
                    return absolutePath;
                } catch (IOException e3) {
                    e22 = e3;
                    fileOutputStream = fos;
                    try {
                        f2401a.m264e("cacheResourceFile: ", e22);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e222) {
                                f2401a.m264e("cacheResourceFile: close ", e222);
                                responseBody.close();
                                if (cacheFile.exists()) {
                                    cacheFile.delete();
                                }
                                return BuildConfig.VERSION_NAME;
                            }
                        }
                        if (is != null) {
                            is.close();
                        }
                        responseBody.close();
                        if (cacheFile.exists()) {
                            cacheFile.delete();
                        }
                        return BuildConfig.VERSION_NAME;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e2222) {
                                f2401a.m264e("cacheResourceFile: close ", e2222);
                                responseBody.close();
                                if (cacheFile.exists()) {
                                    cacheFile.delete();
                                }
                                throw th;
                            }
                        }
                        if (is != null) {
                            is.close();
                        }
                        responseBody.close();
                        if (cacheFile.exists()) {
                            cacheFile.delete();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = fos;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                    responseBody.close();
                    if (cacheFile.exists()) {
                        cacheFile.delete();
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e2222 = e4;
                f2401a.m264e("cacheResourceFile: ", e2222);
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (is != null) {
                    is.close();
                }
                responseBody.close();
                if (cacheFile.exists()) {
                    cacheFile.delete();
                }
                return BuildConfig.VERSION_NAME;
            }
        }
        f2401a.m263e("targetDir is not exist");
        return BuildConfig.VERSION_NAME;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.io.File m1541a(java.io.File r12, java.io.File r13, @android.support.annotation.NonNull java.lang.String r14) {
        /*
        r8 = 0;
        r7 = 0;
        r9 = new java.util.zip.ZipInputStream;	 Catch:{ IOException -> 0x00ae }
        r10 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x00ae }
        r10.<init>(r12);	 Catch:{ IOException -> 0x00ae }
        r9.<init>(r10);	 Catch:{ IOException -> 0x00ae }
    L_0x000c:
        r7 = r9.getNextEntry();	 Catch:{ IOException -> 0x008f }
        if (r7 == 0) goto L_0x00aa;
    L_0x0012:
        r10 = r7.getName();	 Catch:{ IOException -> 0x008f }
        r10 = android.text.TextUtils.isEmpty(r10);	 Catch:{ IOException -> 0x008f }
        if (r10 != 0) goto L_0x000c;
    L_0x001c:
        r10 = r7.getName();	 Catch:{ IOException -> 0x008f }
        r11 = "__MACOSX/";
        r10 = r10.equals(r11);	 Catch:{ IOException -> 0x008f }
        if (r10 != 0) goto L_0x000c;
    L_0x0028:
        r10 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x008f }
        r10.<init>();	 Catch:{ IOException -> 0x008f }
        r11 = r13.getPath();	 Catch:{ IOException -> 0x008f }
        r10 = r10.append(r11);	 Catch:{ IOException -> 0x008f }
        r11 = java.io.File.separator;	 Catch:{ IOException -> 0x008f }
        r10 = r10.append(r11);	 Catch:{ IOException -> 0x008f }
        r10 = r10.append(r14);	 Catch:{ IOException -> 0x008f }
        r11 = ".mp3";
        r10 = r10.append(r11);	 Catch:{ IOException -> 0x008f }
        r6 = r10.toString();	 Catch:{ IOException -> 0x008f }
        r10 = r7.isDirectory();	 Catch:{ IOException -> 0x008f }
        if (r10 != 0) goto L_0x000c;
    L_0x004f:
        r3 = 0;
        r2 = new java.io.File;	 Catch:{ IOException -> 0x00b0 }
        r2.<init>(r6);	 Catch:{ IOException -> 0x00b0 }
        r10 = r2.exists();	 Catch:{ IOException -> 0x00b0 }
        if (r10 == 0) goto L_0x005e;
    L_0x005b:
        r2.delete();	 Catch:{ IOException -> 0x00b0 }
    L_0x005e:
        r10 = r2.getParentFile();	 Catch:{ IOException -> 0x00b0 }
        if (r10 == 0) goto L_0x000c;
    L_0x0064:
        r10 = r2.getParentFile();	 Catch:{ IOException -> 0x00b0 }
        r10 = r10.exists();	 Catch:{ IOException -> 0x00b0 }
        if (r10 == 0) goto L_0x000c;
    L_0x006e:
        r4 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x00b0 }
        r10 = 0;
        r4.<init>(r6, r10);	 Catch:{ IOException -> 0x00b0 }
        r10 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = new byte[r10];	 Catch:{ IOException -> 0x0084 }
    L_0x0078:
        r5 = r9.read(r0);	 Catch:{ IOException -> 0x0084 }
        r10 = -1;
        if (r5 == r10) goto L_0x009f;
    L_0x007f:
        r10 = 0;
        r4.write(r0, r10, r5);	 Catch:{ IOException -> 0x0084 }
        goto L_0x0078;
    L_0x0084:
        r1 = move-exception;
        r3 = r4;
    L_0x0086:
        r10 = f2401a;	 Catch:{ IOException -> 0x008f }
        r11 = "fail to extract";
        r10.m264e(r11, r1);	 Catch:{ IOException -> 0x008f }
        goto L_0x000c;
    L_0x008f:
        r1 = move-exception;
        r8 = r9;
    L_0x0091:
        if (r8 == 0) goto L_0x0096;
    L_0x0093:
        r8.close();	 Catch:{ IOException -> 0x00ac }
    L_0x0096:
        r10 = f2401a;
        r11 = "unzipFile: error ";
        r10.m264e(r11, r1);
    L_0x009d:
        r2 = 0;
    L_0x009e:
        return r2;
    L_0x009f:
        r4.flush();	 Catch:{ IOException -> 0x0084 }
        r4.close();	 Catch:{ IOException -> 0x0084 }
        r9.closeEntry();	 Catch:{ IOException -> 0x0084 }
        r8 = r9;
        goto L_0x009e;
    L_0x00aa:
        r8 = r9;
        goto L_0x009d;
    L_0x00ac:
        r10 = move-exception;
        goto L_0x0096;
    L_0x00ae:
        r1 = move-exception;
        goto L_0x0091;
    L_0x00b0:
        r1 = move-exception;
        goto L_0x0086;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.buddy.tiki.util.FileUtil.a(java.io.File, java.io.File, java.lang.String):java.io.File");
    }

    private static synchronized File m1545c(String type) {
        File file;
        synchronized (FileUtil.class) {
            file = new File(ChatApp.getInstance().getFilesDir(), type);
            if (!file.exists()) {
                f2401a.m261d("new Type Internal Cache file result " + file.mkdir());
            }
        }
        return file;
    }

    public static synchronized File newCacheFile(String type) {
        File typeFile;
        synchronized (FileUtil.class) {
            if (f2402b == null || !f2402b.exists()) {
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    f2402b = new File(Environment.getExternalStorageDirectory() + File.separator + "tiki" + File.separator + ".download");
                } else {
                    f2402b = new File(ChatApp.getInstance().getCacheDir() + File.separator + "tiki" + File.separator + ".download");
                }
                if (!f2402b.exists()) {
                    f2401a.m261d("newCacheFile: new file " + f2402b.mkdirs());
                }
            }
            typeFile = new File(f2402b, type);
            if (!typeFile.exists()) {
                f2401a.m261d("newCacheFile: new file " + typeFile.mkdir());
            }
        }
        return typeFile;
    }

    public static byte[] readAsset(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return null;
        }
        try {
            InputStream inputStream = ChatApp.getInstance().getAssets().open(fileName);
            byte[] result = new byte[inputStream.available()];
            inputStream.read(result);
            inputStream.close();
            return result;
        } catch (IOException e) {
            return null;
        }
    }

    public static byte[] readBytes(String fileName) {
        byte[] bArr = null;
        if (!TextUtils.isEmpty(fileName)) {
            File file = new File(fileName);
            if (file.exists()) {
                byte[] bytes = new byte[swscale.SWS_SPLINE];
                try {
                    InputStream inputStream = new FileInputStream(file);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    while (true) {
                        int len = inputStream.read(bytes);
                        if (len == -1) {
                            break;
                        }
                        bos.write(bytes, 0, len);
                    }
                    bos.close();
                    inputStream.close();
                    bArr = bos.toByteArray();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bArr;
    }
}
