package im.facechat;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import im.facechat.common.p045b.C1639b;
import java.util.ArrayList;
import java.util.List;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraEnumerationAndroid$CaptureFormat;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.CameraVideoCapturer.CameraEventsHandler;
import org.webrtc.VideoCapturerAndroid;

/* compiled from: Camera1Enumerator */
class C1661e implements CameraEnumerator {
    private static final Class<?> f11270a = C1661e.class;
    private static List<List<CameraEnumerationAndroid$CaptureFormat>> f11271b;
    private final boolean f11272c;

    private static java.util.List<org.webrtc.CameraEnumerationAndroid$CaptureFormat> m8003d(int r9) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1431)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:285)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r0 = 0;
        r1 = f11270a;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Get supported formats for camera index ";
        r2 = r2.append(r3);
        r2 = r2.append(r9);
        r3 = ".";
        r2 = r2.append(r3);
        r2 = r2.toString();
        im.facechat.common.p045b.C1639b.m7916a(r1, r2);
        r4 = android.os.SystemClock.elapsedRealtime();
        r1 = 0;
        r2 = f11270a;	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        r3 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        r3.<init>();	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        r6 = "Opening camera with index ";	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        r3 = r3.append(r6);	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        r3 = r3.append(r9);	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        r3 = r3.toString();	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        im.facechat.common.p045b.C1639b.m7916a(r2, r3);	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        r1 = android.hardware.Camera.open(r9);	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        r6 = r1.getParameters();	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        if (r1 == 0) goto L_0x0049;
    L_0x0046:
        r1.release();
    L_0x0049:
        r1 = new java.util.ArrayList;
        r1.<init>();
        r2 = r6.getSupportedPreviewFpsRange();	 Catch:{ Exception -> 0x0089 }
        if (r2 == 0) goto L_0x00fc;	 Catch:{ Exception -> 0x0089 }
    L_0x0054:
        r0 = r2.size();	 Catch:{ Exception -> 0x0089 }
        r0 = r0 + -1;	 Catch:{ Exception -> 0x0089 }
        r0 = r2.get(r0);	 Catch:{ Exception -> 0x0089 }
        r0 = (int[]) r0;	 Catch:{ Exception -> 0x0089 }
        r2 = 0;	 Catch:{ Exception -> 0x0089 }
        r2 = r0[r2];	 Catch:{ Exception -> 0x0089 }
        r3 = 1;	 Catch:{ Exception -> 0x0089 }
        r0 = r0[r3];	 Catch:{ Exception -> 0x0089 }
        r3 = r2;	 Catch:{ Exception -> 0x0089 }
        r2 = r0;	 Catch:{ Exception -> 0x0089 }
    L_0x0068:
        r0 = r6.getSupportedPreviewSizes();	 Catch:{ Exception -> 0x0089 }
        r6 = r0.iterator();	 Catch:{ Exception -> 0x0089 }
    L_0x0070:
        r0 = r6.hasNext();	 Catch:{ Exception -> 0x0089 }
        if (r0 == 0) goto L_0x00a2;	 Catch:{ Exception -> 0x0089 }
    L_0x0076:
        r0 = r6.next();	 Catch:{ Exception -> 0x0089 }
        r0 = (android.hardware.Camera.Size) r0;	 Catch:{ Exception -> 0x0089 }
        r7 = new org.webrtc.CameraEnumerationAndroid$CaptureFormat;	 Catch:{ Exception -> 0x0089 }
        r8 = r0.width;	 Catch:{ Exception -> 0x0089 }
        r0 = r0.height;	 Catch:{ Exception -> 0x0089 }
        r7.<init>(r8, r0, r3, r2);	 Catch:{ Exception -> 0x0089 }
        r1.add(r7);	 Catch:{ Exception -> 0x0089 }
        goto L_0x0070;
    L_0x0089:
        r0 = move-exception;
        r2 = f11270a;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r6 = "getSupportedFormats() failed on camera index ";
        r3 = r3.append(r6);
        r3 = r3.append(r9);
        r3 = r3.toString();
        im.facechat.common.p045b.C1639b.m7917a(r2, r3, r0);
    L_0x00a2:
        r2 = android.os.SystemClock.elapsedRealtime();
        r0 = f11270a;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "Get supported formats for camera index ";
        r6 = r6.append(r7);
        r6 = r6.append(r9);
        r7 = " done. Time spent: ";
        r6 = r6.append(r7);
        r2 = r2 - r4;
        r2 = r6.append(r2);
        r3 = " ms.";
        r2 = r2.append(r3);
        r2 = r2.toString();
        im.facechat.common.p045b.C1639b.m7916a(r0, r2);
        r0 = r1;
    L_0x00d0:
        return r0;
    L_0x00d1:
        r0 = move-exception;
        r2 = f11270a;	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        r3 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        r3.<init>();	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        r4 = "Open camera failed on camera index ";	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        r3 = r3.append(r4);	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        r3 = r3.append(r9);	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        r3 = r3.toString();	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        im.facechat.common.p045b.C1639b.m7917a(r2, r3, r0);	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        r0 = new java.util.ArrayList;	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        r0.<init>();	 Catch:{ RuntimeException -> 0x00d1, all -> 0x00f5 }
        if (r1 == 0) goto L_0x00d0;
    L_0x00f1:
        r1.release();
        goto L_0x00d0;
    L_0x00f5:
        r0 = move-exception;
        if (r1 == 0) goto L_0x00fb;
    L_0x00f8:
        r1.release();
    L_0x00fb:
        throw r0;
    L_0x00fc:
        r2 = r0;
        r3 = r0;
        goto L_0x0068;
        */
        throw new UnsupportedOperationException("Method not decompiled: im.facechat.e.d(int):java.util.List<org.webrtc.CameraEnumerationAndroid$CaptureFormat>");
    }

    public C1661e() {
        this(true);
    }

    public C1661e(boolean z) {
        this.f11272c = z;
    }

    private static CameraInfo m8002c(int i) {
        CameraInfo cameraInfo = new CameraInfo();
        try {
            Camera.getCameraInfo(i, cameraInfo);
            return cameraInfo;
        } catch (Throwable e) {
            C1639b.m7917a(f11270a, "getCameraInfo failed on index " + i, e);
            return null;
        }
    }

    static synchronized List<CameraEnumerationAndroid$CaptureFormat> m8000a(int i) {
        List<CameraEnumerationAndroid$CaptureFormat> list;
        synchronized (C1661e.class) {
            if (f11271b == null) {
                f11271b = new ArrayList();
                for (int i2 = 0; i2 < CameraEnumerationAndroid.getDeviceCount(); i2++) {
                    f11271b.add(C1661e.m8003d(i2));
                }
            }
            list = (List) f11271b.get(i);
        }
        return list;
    }

    static int m7999a(String str) {
        C1639b.m7916a(f11270a, "getCameraIndex: " + str);
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            if (str.equals(C1661e.m8001b(i))) {
                return i;
            }
        }
        throw new IllegalArgumentException("No such camera: " + str);
    }

    static String m8001b(int i) {
        CameraInfo c = C1661e.m8002c(i);
        if (c == null) {
            return null;
        }
        return "Camera " + i + ", Facing " + (c.facing == 1 ? "front" : "back") + ", Orientation " + c.orientation;
    }

    public String[] getDeviceNames() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            String b = C1661e.m8001b(i);
            if (b != null) {
                arrayList.add(b);
                C1639b.m7916a(f11270a, "Index: " + i + ". " + b);
            } else {
                C1639b.m7921c(f11270a, "Index: " + i + ". Failed to query camera name.");
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public boolean isFrontFacing(String str) {
        CameraInfo c = C1661e.m8002c(C1661e.m7999a(str));
        if (c == null || c.facing != 1) {
            return false;
        }
        return true;
    }

    public boolean isBackFacing(String str) {
        CameraInfo c = C1661e.m8002c(C1661e.m7999a(str));
        return c != null && c.facing == 0;
    }

    public CameraVideoCapturer createCapturer(String str, CameraEventsHandler cameraEventsHandler) {
        return new VideoCapturerAndroid(str, cameraEventsHandler, this.f11272c);
    }
}
