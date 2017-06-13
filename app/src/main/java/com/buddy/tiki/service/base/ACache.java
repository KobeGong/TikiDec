package com.buddy.tiki.service.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Process;
import android.support.v7.recyclerview.BuildConfig;
import android.text.TextUtils;
import com.buddy.tiki.log.TikiLog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.bytedeco.javacpp.swscale;
import org.json.JSONArray;
import org.json.JSONObject;

public class ACache {
    private static final TikiLog f1019a = TikiLog.getInstance(ACache.class.getSimpleName());
    private static Map<String, ACache> f1020b = new HashMap();
    private ACacheManager f1021c;

    public class ACacheManager {
        protected File f1012a;
        final /* synthetic */ ACache f1013b;
        private final AtomicLong f1014c;
        private final AtomicInteger f1015d;
        private final long f1016e;
        private final int f1017f;
        private final Map<File, Long> f1018g;

        class C04111 implements Runnable {
            final /* synthetic */ ACacheManager f1011a;

            C04111(ACacheManager this$1) {
                this.f1011a = this$1;
            }

            public void run() {
                int size = 0;
                int count = 0;
                File[] cachedFiles = this.f1011a.f1012a.listFiles();
                if (cachedFiles != null) {
                    for (File cachedFile : cachedFiles) {
                        size = (int) (((long) size) + this.f1011a.m356b(cachedFile));
                        count++;
                        this.f1011a.f1018g.put(cachedFile, Long.valueOf(cachedFile.lastModified()));
                    }
                    this.f1011a.f1014c.set((long) size);
                    this.f1011a.f1015d.set(count);
                }
            }
        }

        private ACacheManager(ACache this$0, File cacheDir, long sizeLimit, int countLimit) {
            this.f1013b = this$0;
            this.f1018g = Collections.synchronizedMap(new HashMap());
            this.f1012a = cacheDir;
            this.f1016e = sizeLimit;
            this.f1017f = countLimit;
            this.f1014c = new AtomicLong();
            this.f1015d = new AtomicInteger();
            m351a();
        }

        private void m351a() {
            new Thread(new C04111(this)).start();
        }

        private void m354a(File file) {
            int curCacheCount = this.f1015d.get();
            while (curCacheCount + 1 > this.f1017f) {
                this.f1014c.addAndGet(-m361c());
                curCacheCount = this.f1015d.addAndGet(-1);
            }
            this.f1015d.addAndGet(1);
            long valueSize = m356b(file);
            long curCacheSize = this.f1014c.get();
            while (curCacheSize + valueSize > this.f1016e) {
                curCacheSize = this.f1014c.addAndGet(-m361c());
            }
            this.f1014c.addAndGet(valueSize);
            Long currentTime = Long.valueOf(System.currentTimeMillis());
            file.setLastModified(currentTime.longValue());
            this.f1018g.put(file, currentTime);
        }

        private File m350a(String key) {
            File file = m358b(key);
            Long currentTime = Long.valueOf(System.currentTimeMillis());
            file.setLastModified(currentTime.longValue());
            this.f1018g.put(file, currentTime);
            return file;
        }

        private File m358b(String key) {
            return new File(this.f1012a, key.hashCode() + BuildConfig.VERSION_NAME);
        }

        private boolean m364c(String key) {
            return m350a(key).delete();
        }

        private void m360b() {
            this.f1018g.clear();
            this.f1014c.set(0);
            File[] files = this.f1012a.listFiles();
            if (files != null) {
                for (File f : files) {
                    f.delete();
                }
            }
        }

        private long m361c() {
            if (this.f1018g.isEmpty()) {
                return 0;
            }
            Long oldestUsage = null;
            File mostLongUsedFile = null;
            Set<Entry<File, Long>> entries = this.f1018g.entrySet();
            synchronized (this.f1018g) {
                for (Entry<File, Long> entry : entries) {
                    if (mostLongUsedFile == null) {
                        mostLongUsedFile = (File) entry.getKey();
                        oldestUsage = (Long) entry.getValue();
                    } else {
                        Long lastValueUsage = (Long) entry.getValue();
                        if (lastValueUsage.longValue() < oldestUsage.longValue()) {
                            oldestUsage = lastValueUsage;
                            mostLongUsedFile = (File) entry.getKey();
                        }
                    }
                }
            }
            long fileSize = m356b(mostLongUsedFile);
            if (!mostLongUsedFile.delete()) {
                return fileSize;
            }
            this.f1018g.remove(mostLongUsedFile);
            return fileSize;
        }

        private long m356b(File file) {
            return file.length();
        }
    }

    private static class Utils {
        private static boolean m382c(String str) {
            return m386d(str.getBytes());
        }

        private static boolean m386d(byte[] data) {
            String[] strs = m389g(data);
            if (strs != null && strs.length == 2) {
                String saveTimeStr = strs[0];
                while (saveTimeStr.startsWith("0")) {
                    saveTimeStr = saveTimeStr.substring(1, saveTimeStr.length());
                }
                if (System.currentTimeMillis() > (1000 * Long.valueOf(strs[1]).longValue()) + Long.valueOf(saveTimeStr).longValue()) {
                    return true;
                }
            }
            return false;
        }

        private static String m377b(int second, String strInfo) {
            return m368a(second) + strInfo;
        }

        private static byte[] m379b(int second, byte[] data2) {
            byte[] data1 = m368a(second).getBytes();
            byte[] retdata = new byte[(data1.length + data2.length)];
            System.arraycopy(data1, 0, retdata, 0, data1.length);
            System.arraycopy(data2, 0, retdata, data1.length, data2.length);
            return retdata;
        }

        private static String m385d(String strInfo) {
            if (strInfo == null || !m388f(strInfo.getBytes())) {
                return strInfo;
            }
            return strInfo.substring(strInfo.indexOf(32) + 1, strInfo.length());
        }

        private static byte[] m387e(byte[] data) {
            if (m388f(data)) {
                return m374a(data, m366a(data, ' ') + 1, data.length);
            }
            return data;
        }

        private static boolean m388f(byte[] data) {
            return data != null && data.length > 15 && data[13] == (byte) 45 && m366a(data, ' ') > 14;
        }

        private static String[] m389g(byte[] data) {
            if (!m388f(data)) {
                return null;
            }
            String saveDate = new String(m374a(data, 0, 13));
            String deleteAfter = new String(m374a(data, 14, m366a(data, ' ')));
            return new String[]{saveDate, deleteAfter};
        }

        private static int m366a(byte[] data, char c) {
            for (int i = 0; i < data.length; i++) {
                if (data[i] == c) {
                    return i;
                }
            }
            return -1;
        }

        private static byte[] m374a(byte[] original, int from, int to) {
            int newLength = to - from;
            if (newLength < 0) {
                throw new IllegalArgumentException(from + " > " + to);
            }
            byte[] copy = new byte[newLength];
            System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
            return copy;
        }

        private static String m368a(int second) {
            String currentTime = System.currentTimeMillis() + BuildConfig.VERSION_NAME;
            while (currentTime.length() < 13) {
                currentTime = "0" + currentTime;
            }
            return currentTime + "-" + second + ' ';
        }

        private static byte[] m383c(Bitmap bm) {
            if (bm == null) {
                return null;
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(CompressFormat.PNG, 100, baos);
            return baos.toByteArray();
        }

        private static Bitmap m390h(byte[] b) {
            if (b.length == 0) {
                return null;
            }
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        }

        private static Bitmap m375b(Drawable drawable) {
            if (drawable == null) {
                return null;
            }
            int w = drawable.getIntrinsicWidth();
            int h = drawable.getIntrinsicHeight();
            Bitmap bitmap = Bitmap.createBitmap(w, h, drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, w, h);
            drawable.draw(canvas);
            return bitmap;
        }

        private static Drawable m384d(Bitmap bm) {
            if (bm == null) {
                return null;
            }
            return new BitmapDrawable(bm);
        }
    }

    private ACache(File cacheDir, long max_size, int max_count) {
        if (cacheDir.exists() || cacheDir.mkdirs()) {
            this.f1021c = new ACacheManager(cacheDir, max_size, max_count);
            return;
        }
        throw new RuntimeException("can't make dirs in " + cacheDir.getAbsolutePath());
    }

    public static ACache get(Context ctx) {
        return get(ctx, "Tiki");
    }

    public static ACache get(Context ctx, String cacheName) {
        return get(new File(ctx.getCacheDir(), cacheName), 50000000, (int) ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public static ACache get(File cacheDir) {
        return get(cacheDir, 50000000, (int) ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public static ACache get(Context ctx, long max_zise, int max_count) {
        return get(new File(ctx.getCacheDir(), "Tiki"), max_zise, max_count);
    }

    public static ACache get(File cacheDir, long max_zise, int max_count) {
        ACache manager = (ACache) f1020b.get(cacheDir.getAbsoluteFile() + m391a());
        if (manager != null) {
            return manager;
        }
        manager = new ACache(cacheDir, max_zise, max_count);
        f1020b.put(cacheDir.getAbsolutePath() + m391a(), manager);
        return manager;
    }

    private static String m391a() {
        return "_" + Process.myPid();
    }

    public void put(String key, String value) {
        IOException e;
        Throwable th;
        File file = this.f1021c.m358b(key);
        BufferedWriter out = null;
        try {
            BufferedWriter out2 = new BufferedWriter(new FileWriter(file), swscale.SWS_SPLINE);
            try {
                out2.write(value);
                if (out2 != null) {
                    try {
                        out2.flush();
                        out2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                this.f1021c.m354a(file);
                out = out2;
            } catch (IOException e3) {
                e2 = e3;
                out = out2;
                try {
                    e2.printStackTrace();
                    if (out != null) {
                        try {
                            out.flush();
                            out.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    this.f1021c.m354a(file);
                } catch (Throwable th2) {
                    th = th2;
                    if (out != null) {
                        try {
                            out.flush();
                            out.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    this.f1021c.m354a(file);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                out = out2;
                if (out != null) {
                    out.flush();
                    out.close();
                }
                this.f1021c.m354a(file);
                throw th;
            }
        } catch (IOException e4) {
            e222 = e4;
            e222.printStackTrace();
            if (out != null) {
                out.flush();
                out.close();
            }
            this.f1021c.m354a(file);
        }
    }

    public void put(String key, String value, int saveTime) {
        put(key, Utils.m377b(saveTime, value));
    }

    public String getAsString(String key) {
        IOException e;
        Throwable th;
        String str = null;
        File file = this.f1021c.m350a(key);
        if (file.exists()) {
            BufferedReader in = null;
            try {
                BufferedReader in2 = new BufferedReader(new FileReader(file));
                try {
                    String readString = BuildConfig.VERSION_NAME;
                    while (true) {
                        String currentLine = in2.readLine();
                        if (currentLine == null) {
                            break;
                        }
                        readString = readString + currentLine;
                    }
                    if (Utils.m382c(readString)) {
                        if (in2 != null) {
                            try {
                                in2.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (true) {
                            remove(key);
                        }
                    } else {
                        str = Utils.m385d(readString);
                        if (in2 != null) {
                            try {
                                in2.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        if (null != null) {
                            remove(key);
                        }
                    }
                } catch (IOException e3) {
                    e22 = e3;
                    in = in2;
                    try {
                        e22.printStackTrace();
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        if (null != null) {
                            remove(key);
                        }
                        return str;
                    } catch (Throwable th2) {
                        th = th2;
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        if (null != null) {
                            remove(key);
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    in = in2;
                    if (in != null) {
                        in.close();
                    }
                    if (null != null) {
                        remove(key);
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e2222 = e4;
                e2222.printStackTrace();
                if (in != null) {
                    in.close();
                }
                if (null != null) {
                    remove(key);
                }
                return str;
            }
        }
        return str;
    }

    public void put(String key, JSONObject value) {
        put(key, value.toString());
    }

    public void put(String key, JSONObject value, int saveTime) {
        put(key, value.toString(), saveTime);
    }

    public JSONObject getAsJSONObject(String key) {
        try {
            return new JSONObject(getAsString(key));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void put(String key, JSONArray value) {
        put(key, value.toString());
    }

    public void put(String key, JSONArray value, int saveTime) {
        put(key, value.toString(), saveTime);
    }

    public JSONArray getAsJSONArray(String key) {
        String JSONString = getAsString(key);
        if (TextUtils.isEmpty(JSONString)) {
            f1019a.m263e("getAsJSONArray: key is empty or null");
            return null;
        }
        try {
            return new JSONArray(JSONString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void put(String key, byte[] value) {
        Exception e;
        Throwable th;
        File file = this.f1021c.m358b(key);
        FileOutputStream out = null;
        try {
            FileOutputStream out2 = new FileOutputStream(file);
            try {
                out2.write(value);
                if (out2 != null) {
                    try {
                        out2.flush();
                        out2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                this.f1021c.m354a(file);
                out = out2;
            } catch (Exception e3) {
                e = e3;
                out = out2;
                try {
                    e.printStackTrace();
                    if (out != null) {
                        try {
                            out.flush();
                            out.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    this.f1021c.m354a(file);
                } catch (Throwable th2) {
                    th = th2;
                    if (out != null) {
                        try {
                            out.flush();
                            out.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    this.f1021c.m354a(file);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                out = out2;
                if (out != null) {
                    out.flush();
                    out.close();
                }
                this.f1021c.m354a(file);
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
            if (out != null) {
                out.flush();
                out.close();
            }
            this.f1021c.m354a(file);
        }
    }

    public void put(String key, byte[] value, int saveTime) {
        put(key, Utils.m379b(saveTime, value));
    }

    public byte[] getAsBinary(String key) {
        Exception e;
        Throwable th;
        byte[] bArr = null;
        RandomAccessFile RAFile = null;
        try {
            File file = this.f1021c.m350a(key);
            if (file.exists()) {
                RandomAccessFile RAFile2 = new RandomAccessFile(file, "r");
                try {
                    byte[] byteArray = new byte[((int) RAFile2.length())];
                    RAFile2.read(byteArray);
                    if (Utils.m386d(byteArray)) {
                        if (RAFile2 != null) {
                            try {
                                RAFile2.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (true) {
                            remove(key);
                        }
                        RAFile = RAFile2;
                    } else {
                        bArr = Utils.m387e(byteArray);
                        if (RAFile2 != null) {
                            try {
                                RAFile2.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        if (null != null) {
                            remove(key);
                        }
                        RAFile = RAFile2;
                    }
                } catch (Exception e3) {
                    e = e3;
                    RAFile = RAFile2;
                    try {
                        e.printStackTrace();
                        if (RAFile != null) {
                            try {
                                RAFile.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        if (null != null) {
                            remove(key);
                        }
                        return bArr;
                    } catch (Throwable th2) {
                        th = th2;
                        if (RAFile != null) {
                            try {
                                RAFile.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        if (null != null) {
                            remove(key);
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    RAFile = RAFile2;
                    if (RAFile != null) {
                        RAFile.close();
                    }
                    if (null != null) {
                        remove(key);
                    }
                    throw th;
                }
            }
            if (RAFile != null) {
                try {
                    RAFile.close();
                } catch (IOException e22222) {
                    e22222.printStackTrace();
                }
            }
            if (null != null) {
                remove(key);
            }
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
            if (RAFile != null) {
                RAFile.close();
            }
            if (null != null) {
                remove(key);
            }
            return bArr;
        }
        return bArr;
    }

    public void put(String key, Serializable value) {
        put(key, value, -1);
    }

    public void put(String key, Serializable value, int saveTime) {
        ByteArrayOutputStream byteArrayOutputStream;
        Exception e;
        Throwable th;
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos2 = new ObjectOutputStream(baos);
                try {
                    oos2.writeObject(value);
                    byte[] data = baos.toByteArray();
                    if (saveTime != -1) {
                        put(key, data, saveTime);
                    } else {
                        put(key, data);
                    }
                    if (oos2 != null) {
                        try {
                            oos2.close();
                        } catch (IOException e2) {
                            oos = oos2;
                            byteArrayOutputStream = baos;
                            return;
                        }
                    }
                    oos = oos2;
                    byteArrayOutputStream = baos;
                } catch (Exception e3) {
                    e = e3;
                    oos = oos2;
                    byteArrayOutputStream = baos;
                    try {
                        e.printStackTrace();
                        if (oos == null) {
                            try {
                                oos.close();
                            } catch (IOException e4) {
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (oos != null) {
                            try {
                                oos.close();
                            } catch (IOException e5) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    oos = oos2;
                    byteArrayOutputStream = baos;
                    if (oos != null) {
                        oos.close();
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
                byteArrayOutputStream = baos;
                e.printStackTrace();
                if (oos == null) {
                    oos.close();
                }
            } catch (Throwable th4) {
                th = th4;
                byteArrayOutputStream = baos;
                if (oos != null) {
                    oos.close();
                }
                throw th;
            }
        } catch (Exception e7) {
            e = e7;
            e.printStackTrace();
            if (oos == null) {
                oos.close();
            }
        }
    }

    public Object getAsObject(String key) {
        Exception e;
        Throwable th;
        Object reObject = null;
        byte[] data = getAsBinary(key);
        if (data != null) {
            ByteArrayInputStream bais = null;
            ObjectInputStream ois = null;
            try {
                ObjectInputStream ois2;
                ByteArrayInputStream bais2 = new ByteArrayInputStream(data);
                try {
                    ois2 = new ObjectInputStream(bais2);
                } catch (Exception e2) {
                    e = e2;
                    bais = bais2;
                    try {
                        e.printStackTrace();
                        if (bais != null) {
                            try {
                                bais.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (ois != null) {
                            try {
                                ois.close();
                            } catch (IOException e32) {
                                e32.printStackTrace();
                            }
                        }
                        return reObject;
                    } catch (Throwable th2) {
                        th = th2;
                        if (bais != null) {
                            try {
                                bais.close();
                            } catch (IOException e322) {
                                e322.printStackTrace();
                            }
                        }
                        if (ois != null) {
                            try {
                                ois.close();
                            } catch (IOException e3222) {
                                e3222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bais = bais2;
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                    throw th;
                }
                try {
                    reObject = ois2.readObject();
                    if (bais2 != null) {
                        try {
                            bais2.close();
                        } catch (IOException e32222) {
                            e32222.printStackTrace();
                        }
                    }
                    if (ois2 != null) {
                        try {
                            ois2.close();
                        } catch (IOException e322222) {
                            e322222.printStackTrace();
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    ois = ois2;
                    bais = bais2;
                    e.printStackTrace();
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                    return reObject;
                } catch (Throwable th4) {
                    th = th4;
                    ois = ois2;
                    bais = bais2;
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
                e.printStackTrace();
                if (bais != null) {
                    bais.close();
                }
                if (ois != null) {
                    ois.close();
                }
                return reObject;
            }
        }
        return reObject;
    }

    public void put(String key, Bitmap value) {
        put(key, Utils.m383c(value));
    }

    public void put(String key, Bitmap value, int saveTime) {
        put(key, Utils.m383c(value), saveTime);
    }

    public Bitmap getAsBitmap(String key) {
        if (getAsBinary(key) == null) {
            return null;
        }
        return Utils.m390h(getAsBinary(key));
    }

    public void put(String key, Drawable value) {
        put(key, Utils.m375b(value));
    }

    public void put(String key, Drawable value, int saveTime) {
        put(key, Utils.m375b(value), saveTime);
    }

    public Drawable getAsDrawable(String key) {
        if (getAsBinary(key) == null) {
            return null;
        }
        return Utils.m384d(Utils.m390h(getAsBinary(key)));
    }

    public File file(String key) {
        File f = this.f1021c.m358b(key);
        return f.exists() ? f : null;
    }

    public boolean remove(String key) {
        return this.f1021c.m364c(key);
    }

    public void clear() {
        this.f1021c.m360b();
    }
}
