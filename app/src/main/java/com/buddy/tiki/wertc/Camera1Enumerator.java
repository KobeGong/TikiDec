package com.buddy.tiki.wertc;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import java.util.ArrayList;
import java.util.List;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat.FramerateRange;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.CameraVideoCapturer.CameraEventsHandler;
import org.webrtc.Logging;
import org.webrtc.Size;
import org.webrtc.VideoCapturerAndroid;

public class Camera1Enumerator implements CameraEnumerator {
    private static List<List<CaptureFormat>> f3566a;
    private final boolean f3567b;

    private static java.util.List<org.webrtc.CameraEnumerationAndroid.CaptureFormat> m2198d(int r24) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0128 in list []
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:285)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r20 = "Camera1Enumerator";
        r21 = new java.lang.StringBuilder;
        r21.<init>();
        r22 = "Get supported formats for camera index ";
        r21 = r21.append(r22);
        r0 = r21;
        r1 = r24;
        r21 = r0.append(r1);
        r22 = ".";
        r21 = r21.append(r22);
        r21 = r21.toString();
        org.webrtc.Logging.d(r20, r21);
        r16 = android.os.SystemClock.elapsedRealtime();
        r4 = 0;
        r20 = "Camera1Enumerator";	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r21 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r21.<init>();	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r22 = "Opening camera with index ";	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r21 = r21.append(r22);	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r0 = r21;	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r1 = r24;	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r21 = r0.append(r1);	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r21 = r21.toString();	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        org.webrtc.Logging.d(r20, r21);	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r4 = android.hardware.Camera.open(r24);	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r12 = r4.getParameters();	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        if (r4 == 0) goto L_0x0050;
    L_0x004d:
        r4.release();
    L_0x0050:
        r7 = new java.util.ArrayList;
        r7.<init>();
        r6 = 0;
        r11 = 0;
        r10 = r12.getSupportedPreviewFpsRange();	 Catch:{ Exception -> 0x00a5 }
        if (r10 == 0) goto L_0x0078;	 Catch:{ Exception -> 0x00a5 }
    L_0x005d:
        r20 = r10.size();	 Catch:{ Exception -> 0x00a5 }
        r20 = r20 + -1;	 Catch:{ Exception -> 0x00a5 }
        r0 = r20;	 Catch:{ Exception -> 0x00a5 }
        r20 = r10.get(r0);	 Catch:{ Exception -> 0x00a5 }
        r20 = (int[]) r20;	 Catch:{ Exception -> 0x00a5 }
        r0 = r20;	 Catch:{ Exception -> 0x00a5 }
        r0 = (int[]) r0;	 Catch:{ Exception -> 0x00a5 }
        r13 = r0;	 Catch:{ Exception -> 0x00a5 }
        r20 = 0;	 Catch:{ Exception -> 0x00a5 }
        r6 = r13[r20];	 Catch:{ Exception -> 0x00a5 }
        r20 = 1;	 Catch:{ Exception -> 0x00a5 }
        r11 = r13[r20];	 Catch:{ Exception -> 0x00a5 }
    L_0x0078:
        r20 = r12.getSupportedPreviewSizes();	 Catch:{ Exception -> 0x00a5 }
        r14 = r20.iterator();	 Catch:{ Exception -> 0x00a5 }
    L_0x0080:
        r20 = r14.hasNext();	 Catch:{ Exception -> 0x00a5 }
        if (r20 == 0) goto L_0x00c8;	 Catch:{ Exception -> 0x00a5 }
    L_0x0086:
        r15 = r14.next();	 Catch:{ Exception -> 0x00a5 }
        r15 = (android.hardware.Camera.Size) r15;	 Catch:{ Exception -> 0x00a5 }
        r20 = new org.webrtc.CameraEnumerationAndroid$CaptureFormat;	 Catch:{ Exception -> 0x00a5 }
        r0 = r15.width;	 Catch:{ Exception -> 0x00a5 }
        r21 = r0;	 Catch:{ Exception -> 0x00a5 }
        r0 = r15.height;	 Catch:{ Exception -> 0x00a5 }
        r22 = r0;	 Catch:{ Exception -> 0x00a5 }
        r0 = r20;	 Catch:{ Exception -> 0x00a5 }
        r1 = r21;	 Catch:{ Exception -> 0x00a5 }
        r2 = r22;	 Catch:{ Exception -> 0x00a5 }
        r0.<init>(r1, r2, r6, r11);	 Catch:{ Exception -> 0x00a5 }
        r0 = r20;	 Catch:{ Exception -> 0x00a5 }
        r7.add(r0);	 Catch:{ Exception -> 0x00a5 }
        goto L_0x0080;
    L_0x00a5:
        r18 = move-exception;
        r20 = "Camera1Enumerator";
        r21 = new java.lang.StringBuilder;
        r21.<init>();
        r22 = "getSupportedFormats() failed on camera index ";
        r21 = r21.append(r22);
        r0 = r21;
        r1 = r24;
        r21 = r0.append(r1);
        r21 = r21.toString();
        r0 = r20;
        r1 = r21;
        r2 = r18;
        org.webrtc.Logging.e(r0, r1, r2);
    L_0x00c8:
        r8 = android.os.SystemClock.elapsedRealtime();
        r20 = "Camera1Enumerator";
        r21 = new java.lang.StringBuilder;
        r21.<init>();
        r22 = "Get supported formats for camera index ";
        r21 = r21.append(r22);
        r0 = r21;
        r1 = r24;
        r21 = r0.append(r1);
        r22 = " done. Time spent: ";
        r21 = r21.append(r22);
        r22 = r8 - r16;
        r21 = r21.append(r22);
        r22 = " ms.";
        r21 = r21.append(r22);
        r21 = r21.toString();
        org.webrtc.Logging.d(r20, r21);
    L_0x00fa:
        return r7;
    L_0x00fb:
        r19 = move-exception;
        r20 = "Camera1Enumerator";	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r21 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r21.<init>();	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r22 = "Open camera failed on camera index ";	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r21 = r21.append(r22);	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r0 = r21;	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r1 = r24;	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r21 = r0.append(r1);	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r21 = r21.toString();	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r0 = r20;	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r1 = r21;	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r2 = r19;	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        org.webrtc.Logging.e(r0, r1, r2);	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r5 = new java.util.ArrayList;	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        r5.<init>();	 Catch:{ RuntimeException -> 0x00fb, all -> 0x012a }
        if (r4 == 0) goto L_0x0128;
    L_0x0125:
        r4.release();
    L_0x0128:
        r7 = r5;
        goto L_0x00fa;
    L_0x012a:
        r20 = move-exception;
        if (r4 == 0) goto L_0x0130;
    L_0x012d:
        r4.release();
    L_0x0130:
        throw r20;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.buddy.tiki.wertc.Camera1Enumerator.d(int):java.util.List<org.webrtc.CameraEnumerationAndroid$CaptureFormat>");
    }

    public Camera1Enumerator() {
        this(true);
    }

    public Camera1Enumerator(boolean captureToTexture) {
        this.f3567b = captureToTexture;
    }

    private static CameraInfo m2197c(int index) {
        CameraInfo info = new CameraInfo();
        try {
            Camera.getCameraInfo(index, info);
            return info;
        } catch (Exception var3) {
            Logging.e("Camera1Enumerator", "getCameraInfo failed on index " + index, var3);
            return null;
        }
    }

    static synchronized List<CaptureFormat> m2193a(int cameraId) {
        List<CaptureFormat> list;
        synchronized (Camera1Enumerator.class) {
            if (f3566a == null) {
                f3566a = new ArrayList();
                for (int i = 0; i < CameraEnumerationAndroid.getDeviceCount(); i++) {
                    f3566a.add(m2198d(i));
                }
            }
            list = (List) f3566a.get(cameraId);
        }
        return list;
    }

    static List<Size> m2194a(List<Camera.Size> cameraSizes) {
        ArrayList sizes = new ArrayList();
        for (Camera.Size size : cameraSizes) {
            sizes.add(new Size(size.width, size.height));
        }
        return sizes;
    }

    static List<FramerateRange> m2196b(List<int[]> arrayRanges) {
        ArrayList ranges = new ArrayList();
        for (int[] range : arrayRanges) {
            ranges.add(new FramerateRange(range[0], range[1]));
        }
        return ranges;
    }

    static int m2192a(String deviceName) {
        Logging.d("Camera1Enumerator", "getCameraIndex: " + deviceName);
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            if (deviceName.equals(CameraEnumerationAndroid.getDeviceName(i))) {
                return i;
            }
        }
        throw new IllegalArgumentException("No such camera: " + deviceName);
    }

    static String m2195b(int index) {
        CameraInfo info = m2197c(index);
        if (info == null) {
            return null;
        }
        return "Camera " + index + ", Facing " + (info.facing == 1 ? "front" : "back") + ", Orientation " + info.orientation;
    }

    public String[] getDeviceNames() {
        ArrayList namesList = new ArrayList();
        for (int namesArray = 0; namesArray < Camera.getNumberOfCameras(); namesArray++) {
            String name = m2195b(namesArray);
            if (name != null) {
                namesList.add(name);
                Logging.d("Camera1Enumerator", "Index: " + namesArray + ". " + name);
            } else {
                Logging.e("Camera1Enumerator", "Index: " + namesArray + ". Failed to query camera name.");
            }
        }
        return (String[]) namesList.toArray(new String[namesList.size()]);
    }

    public boolean isFrontFacing(String deviceName) {
        CameraInfo info = m2197c(m2192a(deviceName));
        if (info == null || info.facing != 1) {
            return false;
        }
        return true;
    }

    public boolean isBackFacing(String deviceName) {
        CameraInfo info = m2197c(m2192a(deviceName));
        return info != null && info.facing == 0;
    }

    public CameraVideoCapturer createCapturer(String deviceName, CameraEventsHandler eventsHandler) {
        return new VideoCapturerAndroid(deviceName, eventsHandler, this.f3567b);
    }
}
