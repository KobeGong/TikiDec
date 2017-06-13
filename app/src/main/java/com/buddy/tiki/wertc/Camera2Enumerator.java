package com.buddy.tiki.wertc;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.v4.view.PointerIconCompat;
import android.util.AndroidException;
import android.util.Range;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat.FramerateRange;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.CameraVideoCapturer.CameraEventsHandler;
import org.webrtc.Logging;
import org.webrtc.Size;

@TargetApi(21)
public class Camera2Enumerator implements CameraEnumerator {
    private static final Map<String, List<CaptureFormat>> f3570c = new HashMap();
    final Context f3571a;
    final CameraManager f3572b;

    public Camera2Enumerator(Context context) {
        this.f3571a = context;
        this.f3572b = (CameraManager) context.getSystemService("camera");
    }

    public static boolean isSupported() {
        return false;
    }

    static int m2199a(Range<Integer>[] fpsRanges) {
        return fpsRanges.length == 0 ? PointerIconCompat.TYPE_DEFAULT : ((Integer) fpsRanges[0].getUpper()).intValue() < PointerIconCompat.TYPE_DEFAULT ? PointerIconCompat.TYPE_DEFAULT : 1;
    }

    static List<Size> m2201a(CameraCharacteristics cameraCharacteristics) {
        StreamConfigurationMap streamMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        int supportLevel = ((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue();
        List<Size> sizes = m2204a(streamMap.getOutputSizes(SurfaceTexture.class));
        if (VERSION.SDK_INT >= 22 || supportLevel != 2) {
            return sizes;
        }
        Rect activeArraySize = (Rect) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        List<Size> arrayList = new ArrayList();
        for (Size size : sizes) {
            if (activeArraySize.width() * size.height == activeArraySize.height() * size.width) {
                arrayList.add(size);
            }
        }
        return arrayList;
    }

    static List<CaptureFormat> m2202a(CameraManager cameraManager, String cameraId) {
        Map var2 = f3570c;
        synchronized (f3570c) {
            if (f3570c.containsKey(cameraId)) {
                List<CaptureFormat> list = (List) f3570c.get(cameraId);
                return list;
            }
            Logging.d("Camera2Enumerator", "Get supported formats for camera index " + cameraId + ".");
            long startTimeMs = SystemClock.elapsedRealtime();
            try {
                CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraId);
                StreamConfigurationMap streamMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                Range[] fpsRanges = (Range[]) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
                List<FramerateRange> framerateRanges = m2203a(fpsRanges, m2199a(fpsRanges));
                List<Size> sizes = m2201a(cameraCharacteristics);
                int defaultMaxFps = 0;
                for (FramerateRange endTimeMs : framerateRanges) {
                    defaultMaxFps = Math.max(defaultMaxFps, endTimeMs.max);
                }
                ArrayList formatList1 = new ArrayList();
                for (Size size : sizes) {
                    long minFrameDurationNs = 0;
                    try {
                        minFrameDurationNs = streamMap.getOutputMinFrameDuration(SurfaceTexture.class, new android.util.Size(size.width, size.height));
                    } catch (Exception e) {
                    }
                    int maxFps = minFrameDurationNs == 0 ? defaultMaxFps : ((int) Math.round(1.0E9d / ((double) minFrameDurationNs))) * PointerIconCompat.TYPE_DEFAULT;
                    formatList1.add(new CaptureFormat(size.width, size.height, 0, maxFps));
                    Logging.d("Camera2Enumerator", "Format: " + size.width + "x" + size.height + "@" + maxFps);
                }
                f3570c.put(cameraId, formatList1);
                Logging.d("Camera2Enumerator", "Get supported formats for camera index " + cameraId + " done. Time spent: " + (SystemClock.elapsedRealtime() - startTimeMs) + " ms.");
                return formatList1;
            } catch (Exception var19) {
                Logging.e("Camera2Enumerator", "getCameraCharacteristics(): " + var19);
                return new ArrayList();
            }
        }
    }

    private static List<Size> m2204a(android.util.Size[] cameraSizes) {
        ArrayList sizes = new ArrayList();
        android.util.Size[] var2 = cameraSizes;
        int var3 = cameraSizes.length;
        for (int var4 = 0; var4 < var3; var4++) {
            android.util.Size size = var2[var4];
            sizes.add(new Size(size.getWidth(), size.getHeight()));
        }
        return sizes;
    }

    static List<FramerateRange> m2203a(Range<Integer>[] arrayRanges, int unitFactor) {
        ArrayList ranges = new ArrayList();
        Range<Integer>[] var3 = arrayRanges;
        int var4 = arrayRanges.length;
        for (int var5 = 0; var5 < var4; var5++) {
            Range range = var3[var5];
            ranges.add(new FramerateRange(((Integer) range.getLower()).intValue() * unitFactor, ((Integer) range.getUpper()).intValue() * unitFactor));
        }
        return ranges;
    }

    public String[] getDeviceNames() {
        try {
            return this.f3572b.getCameraIdList();
        } catch (AndroidException var2) {
            Logging.e("Camera2Enumerator", "Camera access exception: " + var2);
            return new String[0];
        }
    }

    public boolean isFrontFacing(String deviceName) {
        CameraCharacteristics characteristics = m2200a(deviceName);
        return characteristics != null && ((Integer) characteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 0;
    }

    public boolean isBackFacing(String deviceName) {
        CameraCharacteristics characteristics = m2200a(deviceName);
        return characteristics != null && ((Integer) characteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 1;
    }

    public CameraVideoCapturer createCapturer(String deviceName, CameraEventsHandler eventsHandler) {
        return new Camera2Capturer(this.f3571a, deviceName, eventsHandler);
    }

    private CameraCharacteristics m2200a(String deviceName) {
        try {
            return this.f3572b.getCameraCharacteristics(deviceName);
        } catch (AndroidException var3) {
            Logging.e("Camera2Enumerator", "Camera access exception: " + var3);
            return null;
        }
    }
}
