package im.facechat;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build.VERSION;
import im.facechat.common.p045b.C1639b;
import org.webrtc.ThreadUtils.ThreadChecker;

/* compiled from: AppRTCProximitySensor */
class C1632c implements SensorEventListener {
    private static final Class<?> f11200a = C1632c.class;
    private final ThreadChecker f11201b = new ThreadChecker();
    private final Runnable f11202c;
    private final SensorManager f11203d;
    private Sensor f11204e = null;
    private boolean f11205f = false;

    private C1632c(Context context, Runnable runnable) {
        C1639b.m7916a(f11200a, "AppRTCProximitySensor" + C1660d.m7996a());
        this.f11202c = runnable;
        this.f11203d = (SensorManager) context.getSystemService("sensor");
    }

    static C1632c m7880a(Context context, Runnable runnable) {
        return new C1632c(context, runnable);
    }

    public boolean m7883a() {
        this.f11201b.checkIsOnValidThread();
        C1639b.m7916a(f11200a, "start" + C1660d.m7996a());
        if (!m7881d()) {
            return false;
        }
        this.f11203d.registerListener(this, this.f11204e, 3);
        return true;
    }

    public void m7884b() {
        this.f11201b.checkIsOnValidThread();
        C1639b.m7916a(f11200a, "stop" + C1660d.m7996a());
        if (this.f11204e != null) {
            this.f11203d.unregisterListener(this, this.f11204e);
        }
    }

    public boolean m7885c() {
        this.f11201b.checkIsOnValidThread();
        return this.f11205f;
    }

    public final void onAccuracyChanged(Sensor sensor, int i) {
        this.f11201b.checkIsOnValidThread();
        C1660d.m7998a(sensor.getType() == 8);
        if (i == 0) {
            C1639b.m7921c(f11200a, "The values returned by this sensor cannot be trusted");
        }
    }

    public final void onSensorChanged(SensorEvent sensorEvent) {
        this.f11201b.checkIsOnValidThread();
        C1660d.m7998a(sensorEvent.sensor.getType() == 8);
        if (sensorEvent.values[0] < this.f11204e.getMaximumRange()) {
            C1639b.m7916a(f11200a, "Proximity sensor => NEAR state");
            this.f11205f = true;
        } else {
            C1639b.m7916a(f11200a, "Proximity sensor => FAR state");
            this.f11205f = false;
        }
        if (this.f11202c != null) {
            this.f11202c.run();
        }
        C1639b.m7916a(f11200a, "onSensorChanged" + C1660d.m7996a() + ": accuracy=" + sensorEvent.accuracy + ", timestamp=" + sensorEvent.timestamp + ", distance=" + sensorEvent.values[0]);
    }

    private boolean m7881d() {
        if (this.f11204e != null) {
            return true;
        }
        this.f11204e = this.f11203d.getDefaultSensor(8);
        if (this.f11204e == null) {
            return false;
        }
        m7882e();
        return true;
    }

    private void m7882e() {
        if (this.f11204e != null) {
            StringBuilder stringBuilder = new StringBuilder("Proximity sensor: ");
            stringBuilder.append("name=").append(this.f11204e.getName());
            stringBuilder.append(", vendor: ").append(this.f11204e.getVendor());
            stringBuilder.append(", power: ").append(this.f11204e.getPower());
            stringBuilder.append(", resolution: ").append(this.f11204e.getResolution());
            stringBuilder.append(", max range: ").append(this.f11204e.getMaximumRange());
            if (VERSION.SDK_INT >= 9) {
                stringBuilder.append(", min delay: ").append(this.f11204e.getMinDelay());
            }
            if (VERSION.SDK_INT >= 20) {
                stringBuilder.append(", type: ").append(this.f11204e.getStringType());
            }
            if (VERSION.SDK_INT >= 21) {
                stringBuilder.append(", max delay: ").append(this.f11204e.getMaxDelay());
                stringBuilder.append(", reporting mode: ").append(this.f11204e.getReportingMode());
                stringBuilder.append(", isWakeUpSensor: ").append(this.f11204e.isWakeUpSensor());
            }
            C1639b.m7916a(f11200a, stringBuilder.toString());
        }
    }
}
