package com.buddy.tiki.wertc;

import android.util.Log;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CpuMonitor {
    ProcStat f3606a;
    private int[] f3607b = new int[10];
    private int f3608c = 0;
    private int f3609d = 0;
    private long[] f3610e;
    private int f3611f;
    private double f3612g = -1.0d;
    private int f3613h;
    private int f3614i;
    private int f3615j;
    private boolean f3616k = false;
    private String[] f3617l;
    private String[] f3618m;

    private static class ProcStat {
        final long f3604a;
        final long f3605b;

        ProcStat(long aRunTime, long aIdleTime) {
            this.f3604a = aRunTime;
            this.f3605b = aIdleTime;
        }
    }

    private void m2236a() {
        int i;
        try {
            FileReader fin = new FileReader("/sys/devices/system/cpu/present");
            try {
                Scanner scanner = new Scanner(new BufferedReader(fin)).useDelimiter("[-\n]");
                scanner.nextInt();
                this.f3611f = scanner.nextInt() + 1;
                scanner.close();
            } catch (Exception e) {
                Log.e("CpuMonitor", "Cannot do CPU stats due to /sys/devices/system/cpu/present parsing problem");
                this.f3610e = new long[this.f3611f];
                this.f3617l = new String[this.f3611f];
                this.f3618m = new String[this.f3611f];
                for (i = 0; i < this.f3611f; i++) {
                    this.f3610e[i] = 0;
                    this.f3617l[i] = "/sys/devices/system/cpu/cpu" + i + "/cpufreq/cpuinfo_max_freq";
                    this.f3618m[i] = "/sys/devices/system/cpu/cpu" + i + "/cpufreq/scaling_cur_freq";
                }
                this.f3606a = new ProcStat(0, 0);
                this.f3616k = true;
            } finally {
                fin.close();
            }
        } catch (FileNotFoundException e2) {
            Log.e("CpuMonitor", "Cannot do CPU stats since /sys/devices/system/cpu/present is missing");
        } catch (IOException e3) {
            Log.e("CpuMonitor", "Error closing file");
        }
        this.f3610e = new long[this.f3611f];
        this.f3617l = new String[this.f3611f];
        this.f3618m = new String[this.f3611f];
        for (i = 0; i < this.f3611f; i++) {
            this.f3610e[i] = 0;
            this.f3617l[i] = "/sys/devices/system/cpu/cpu" + i + "/cpufreq/cpuinfo_max_freq";
            this.f3618m[i] = "/sys/devices/system/cpu/cpu" + i + "/cpufreq/scaling_cur_freq";
        }
        this.f3606a = new ProcStat(0, 0);
        this.f3616k = true;
    }

    public boolean sampleCpuUtilization() {
        int i;
        long lastSeenMaxFreq = 0;
        long cpufreqCurSum = 0;
        long cpufreqMaxSum = 0;
        if (!this.f3616k) {
            m2236a();
        }
        for (i = 0; i < this.f3611f; i++) {
            if (this.f3610e[i] == 0) {
                long cpufreqMax = m2235a(this.f3617l[i]);
                if (cpufreqMax > 0) {
                    lastSeenMaxFreq = cpufreqMax;
                    this.f3610e[i] = cpufreqMax;
                    this.f3617l[i] = null;
                }
            } else {
                lastSeenMaxFreq = this.f3610e[i];
            }
            cpufreqCurSum += m2235a(this.f3618m[i]);
            cpufreqMaxSum += lastSeenMaxFreq;
        }
        if (cpufreqMaxSum == 0) {
            Log.e("CpuMonitor", "Could not read max frequency for any CPU");
            return false;
        }
        double percentFreq;
        double newPercentFreq = (100.0d * ((double) cpufreqCurSum)) / ((double) cpufreqMaxSum);
        if (this.f3612g > 0.0d) {
            percentFreq = (this.f3612g + newPercentFreq) * 0.5d;
        } else {
            percentFreq = newPercentFreq;
        }
        this.f3612g = newPercentFreq;
        ProcStat procStat = m2237b();
        if (procStat == null) {
            return false;
        }
        int percent;
        long diffRunTime = procStat.f3604a - this.f3606a.f3604a;
        long diffIdleTime = procStat.f3605b - this.f3606a.f3605b;
        this.f3606a = procStat;
        long allTime = diffRunTime + diffIdleTime;
        if (allTime == 0) {
            percent = 0;
        } else {
            percent = (int) Math.round((((double) diffRunTime) * percentFreq) / ((double) allTime));
        }
        percent = Math.max(0, Math.min(percent, 100));
        this.f3608c += percent - this.f3607b[2];
        this.f3609d += percent - this.f3607b[9];
        for (i = 9; i > 0; i--) {
            this.f3607b[i] = this.f3607b[i - 1];
        }
        this.f3607b[0] = percent;
        this.f3613h = percent;
        this.f3614i = this.f3608c / 3;
        this.f3615j = this.f3609d / 10;
        return true;
    }

    public int getCpuCurrent() {
        return this.f3613h;
    }

    public int getCpuAvg3() {
        return this.f3614i;
    }

    public int getCpuAvgAll() {
        return this.f3615j;
    }

    private long m2235a(String fileName) {
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
            Log.e("CpuMonitor", "Error closing file");
        } catch (Throwable th) {
            fin.close();
        }
        return number;
    }

    private ProcStat m2237b() {
        long runTime = 0;
        long idleTime = 0;
        try {
            FileReader fin = new FileReader("/proc/stat");
            ProcStat nextLong;
            try {
                Scanner scanner = new Scanner(new BufferedReader(fin));
                scanner.next();
                nextLong = scanner.nextLong() + scanner.nextLong();
                runTime = nextLong + scanner.nextLong();
                idleTime = scanner.nextLong();
                scanner.close();
                return new ProcStat(runTime, idleTime);
            } catch (Exception e) {
                Log.e("CpuMonitor", "Problems parsing /proc/stat");
                nextLong = null;
                return nextLong;
            } finally {
                fin.close();
            }
        } catch (FileNotFoundException e2) {
            Log.e("CpuMonitor", "Cannot open /proc/stat for reading");
            return null;
        } catch (IOException e3) {
            Log.e("CpuMonitor", "Problems reading /proc/stat");
            return null;
        }
    }
}
