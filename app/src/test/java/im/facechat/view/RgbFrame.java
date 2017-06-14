package im.facechat.view;

public class RgbFrame {
    private int f11556a;
    private float[] f11557b;
    private int f11558c;
    private long f11559d;

    public RgbFrame(int i, float[] fArr, int i2, long j) {
        this.f11556a = i;
        this.f11557b = fArr;
        this.f11558c = i2;
        this.f11559d = j;
    }

    public void setTexMatrix(float[] fArr) {
        this.f11557b = fArr;
    }

    public float[] getTexMatrix() {
        return this.f11557b;
    }

    public void setRgbTextureId(int i) {
        this.f11556a = i;
    }

    public int getRgbTextureId() {
        return this.f11556a;
    }

    public void setRotation(int i) {
        this.f11558c = i;
    }

    public int getRotation() {
        return this.f11558c;
    }

    public void setTimestamp(long j) {
        this.f11559d = j;
    }

    public long getTimestamp() {
        return this.f11559d;
    }
}
