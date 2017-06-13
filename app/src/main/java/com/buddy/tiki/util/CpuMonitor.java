package com.buddy.tiki.util;

import android.content.Context;
import android.os.Environment;
import android.os.SystemClock;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class CpuMonitor {
    private static boolean f2329a = false;
    private static FileOutputStream f2330b;
    private final Context f2331c;
    private LooperExecutor f2332d;
    private long f2333e;
    private int f2334f;
    private double f2335g;
    private double f2336h;
    private double f2337i;
    private double f2338j = -1.0d;
    private double f2339k;
    private double f2340l;
    private double f2341m;
    private double f2342n;
    private long[] f2343o;
    private int f2344p;
    private int f2345q;
    private boolean f2346r = false;
    private String[] f2347s;
    private String[] f2348t;
    private double[] f2349u;
    private ProcStat f2350v;

    class C04771 implements Runnable {
        final /* synthetic */ CpuMonitor f2324a;

        C04771(CpuMonitor this$0) {
            this.f2324a = this$0;
        }

        public void run() {
            this.f2324a.m1516b();
        }
    }

    private class ProcStat {
        final long f2325a;
        final long f2326b;
        final long f2327c;
        final /* synthetic */ CpuMonitor f2328d;

        ProcStat(CpuMonitor cpuMonitor, long userTime, long systemTime, long idleTime) {
            this.f2328d = cpuMonitor;
            this.f2325a = userTime;
            this.f2326b = systemTime;
            this.f2327c = idleTime;
        }
    }

    public CpuMonitor(Context context) {
        this.f2331c = context.getApplicationContext();
        this.f2333e = 0;
        this.f2332d = new LooperExecutor();
        this.f2332d.requestStart();
    }

    public void start() {
        if (this.f2332d != null) {
            m1512a();
        }
    }

    public void release() {
        if (this.f2332d != null) {
            this.f2332d.cancelScheduledTasks();
            this.f2332d.requestStop();
            this.f2332d = null;
        }
        if (f2330b != null) {
            try {
                f2330b.close();
                f2330b = null;
            } catch (IOException e) {
            }
        }
    }

    public void pause() {
        if (this.f2332d != null) {
            this.f2332d.cancelScheduledTasks();
        }
    }

    public void resume() {
        if (this.f2332d != null) {
            m1518d();
            m1512a();
        }
    }

    public synchronized int getCpuUsageCurrent() {
        return m1510a(this.f2337i);
    }

    public synchronized int getCpuUsageAverage() {
        return m1511a(this.f2342n, this.f2334f);
    }

    public synchronized int getCpuFrequencyScaleCurrent() {
        return m1510a(this.f2338j);
    }

    private void m1512a() {
        this.f2332d.scheduleAtFixedRate(new C04771(this), 6000);
    }

    private void m1514a(String statString) {
        if (f2329a) {
            if (f2330b == null) {
                try {
                    f2330b = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "cpu_log.txt", false);
                } catch (FileNotFoundException e) {
                    f2329a = false;
                    return;
                }
            }
            Date date = Calendar.getInstance().getTime();
            try {
                f2330b.write((new SimpleDateFormat("MM-dd HH:mm:ss.SSS").format(date) + " " + "CpuMonitor" + ":" + statString + "\n").getBytes());
            } catch (IOException e2) {
                f2329a = false;
            }
        }
    }

    private void m1516b() {
        boolean logStatistics = false;
        if (SystemClock.elapsedRealtime() - this.f2333e >= 6000) {
            this.f2333e = SystemClock.elapsedRealtime();
            logStatistics = true;
        }
        boolean cpuMonitorAvailable = m1519e();
        if (logStatistics && cpuMonitorAvailable) {
            m1514a(m1520f());
            m1518d();
        }
    }

    private void m1517c() {
        FileReader fin;
        try {
            fin = new FileReader("/sys/devices/system/cpu/present");
            Scanner scanner = new Scanner(new BufferedReader(fin)).useDelimiter("[-\n]");
            scanner.nextInt();
            this.f2344p = scanner.nextInt() + 1;
            scanner.close();
            fin.close();
        } catch (Exception e) {
            fin.close();
        } catch (FileNotFoundException e2) {
        } catch (IOException e3) {
        } catch (Throwable th) {
            fin.close();
        }
        this.f2343o = new long[this.f2344p];
        this.f2347s = new String[this.f2344p];
        this.f2348t = new String[this.f2344p];
        this.f2349u = new double[this.f2344p];
        for (int i = 0; i < this.f2344p; i++) {
            this.f2343o[i] = 0;
            this.f2349u[i] = 0.0d;
            this.f2347s[i] = "/sys/devices/system/cpu/cpu" + i + "/cpufreq/cpuinfo_max_freq";
            this.f2348t[i] = "/sys/devices/system/cpu/cpu" + i + "/cpufreq/scaling_cur_freq";
        }
        this.f2350v = new ProcStat(this, 0, 0, 0);
        m1518d();
        this.f2346r = true;
    }

    private synchronized void m1518d() {
        this.f2339k = 0.0d;
        this.f2340l = 0.0d;
        this.f2341m = 0.0d;
        this.f2342n = 0.0d;
        this.f2334f = 0;
    }

    private synchronized boolean m1519e() {
        boolean z;
        long lastSeenMaxFreq = 0;
        long cpuFreqCurSum = 0;
        long cpuFreqMaxSum = 0;
        if (!this.f2346r) {
            m1517c();
        }
        if (this.f2344p == 0) {
            z = false;
        } else {
            this.f2345q = 0;
            for (int i = 0; i < this.f2344p; i++) {
                this.f2349u[i] = 0.0d;
                if (this.f2343o[i] == 0) {
                    long cpufreqMax = m1515b(this.f2347s[i]);
                    if (cpufreqMax > 0) {
                        lastSeenMaxFreq = cpufreqMax;
                        this.f2343o[i] = cpufreqMax;
                        this.f2347s[i] = null;
                    }
                } else {
                    lastSeenMaxFreq = this.f2343o[i];
                }
                long cpuFreqCur = m1515b(this.f2348t[i]);
                if (cpuFreqCur != 0 || lastSeenMaxFreq != 0) {
                    if (cpuFreqCur > 0) {
                        this.f2345q++;
                    }
                    cpuFreqCurSum += cpuFreqCur;
                    cpuFreqMaxSum += lastSeenMaxFreq;
                    if (lastSeenMaxFreq > 0) {
                        this.f2349u[i] = ((double) cpuFreqCur) / ((double) lastSeenMaxFreq);
                    }
                }
            }
            if (cpuFreqCurSum == 0 || cpuFreqMaxSum == 0) {
                z = false;
            } else {
                double frequencyScale;
                double newFrequencyScale = ((double) cpuFreqCurSum) / ((double) cpuFreqMaxSum);
                if (this.f2338j > 0.0d) {
                    frequencyScale = (this.f2338j + newFrequencyScale) * 0.5d;
                } else {
                    frequencyScale = newFrequencyScale;
                }
                ProcStat procStat = m1521g();
                if (procStat == null) {
                    z = false;
                } else {
                    long diffUserTime = procStat.f2325a - this.f2350v.f2325a;
                    long diffSystemTime = procStat.f2326b - this.f2350v.f2326b;
                    long allTime = (diffUserTime + diffSystemTime) + (procStat.f2327c - this.f2350v.f2327c);
                    if (frequencyScale == 0.0d || allTime == 0) {
                        z = false;
                    } else {
                        this.f2338j = frequencyScale;
                        this.f2341m += frequencyScale;
                        this.f2335g = ((double) diffUserTime) / ((double) allTime);
                        this.f2339k += this.f2335g;
                        this.f2336h = ((double) diffSystemTime) / ((double) allTime);
                        this.f2340l += this.f2336h;
                        this.f2337i = (this.f2335g + this.f2336h) * this.f2338j;
                        this.f2342n += this.f2337i;
                        this.f2334f++;
                        this.f2350v = procStat;
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    private int m1510a(double d) {
        return (int) ((100.0d * d) + 0.5d);
    }

    private int m1511a(double d, int iterations) {
        if (iterations > 0) {
            return (int) (((100.0d * d) / ((double) iterations)) + 0.5d);
        }
        return 0;
    }

    private String m1520f() {
        StringBuilder stat = new StringBuilder();
        stat.append("CPU User: ").append(m1510a(this.f2335g)).append("/").append(m1511a(this.f2339k, this.f2334f)).append(" (").append(m1510a(this.f2335g * this.f2338j)).append(")").append(". System: ").append(m1510a(this.f2336h)).append("/").append(m1511a(this.f2340l, this.f2334f)).append(" (").append(m1510a(this.f2336h * this.f2338j)).append(")").append(". CPU freq %: ").append(m1510a(this.f2338j)).append("/").append(m1511a(this.f2341m, this.f2334f)).append(". Total CPU usage: ").append(m1510a(this.f2337i)).append("/").append(m1511a(this.f2342n, this.f2334f)).append(". Cores: ").append(this.f2345q);
        stat.append("( ");
        for (int i = 0; i < this.f2344p; i++) {
            stat.append(m1510a(this.f2349u[i])).append(" ");
        }
        stat.append("). Battery %: ").append(BatteryUtil.getBatteryPercentLevel());
        return stat.toString();
    }

    private long m1515b(String fileName) {
        long number = 0;
        try {
            FileReader fin = new FileReader(fileName);
            Scanner scannerC = new Scanner(new BufferedReader(fin));
            number = scannerC.nextLong();
            scannerC.close();
            fin.close();
        } catch (Exception e) {
            fin.close();
        } catch (FileNotFoundException e2) {
        } catch (IOException e3) {
        } catch (Throwable th) {
            fin.close();
        }
        return number;
    }

    private ProcStat m1521g() {
        FileReader fin;
        try {
            fin = new FileReader("/proc/stat");
            Scanner scanner = new Scanner(new BufferedReader(fin));
            scanner.next();
            long userTime = scanner.nextLong() + scanner.nextLong();
            long systemTime = scanner.nextLong();
            long idleTime = scanner.nextLong();
            userTime += scanner.nextLong();
            scanner.close();
            fin.close();
            return new ProcStat(this, userTime, systemTime, idleTime);
        } catch (Exception e) {
            fin.close();
            return null;
        } catch (FileNotFoundException e2) {
            return null;
        } catch (IOException e3) {
            return null;
        } catch (Throwable th) {
            fin.close();
        }
    }
}
