package im.facechat;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import im.facechat.common.p045b.C1639b;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import tv.danmaku.ijk.media.player.BuildConfig;

/* compiled from: PhoneHelper */
class C1727p {
    private static final Class<?> f11500a = C1727p.class;
    private static final FileFilter f11501b = new C17261();

    /* compiled from: PhoneHelper */
    static class C17261 implements FileFilter {
        C17261() {
        }

        public boolean accept(File file) {
            String name = file.getName();
            if (!name.startsWith("cpu")) {
                return false;
            }
            int i = 3;
            while (i < name.length()) {
                if (name.charAt(i) < '0' || name.charAt(i) > '9') {
                    return false;
                }
                i++;
            }
            return true;
        }
    }

    C1727p() {
    }

    static FCBaseParams m8201a(@NonNull Context context, String str) {
        FCBaseParams fCBaseParams = new FCBaseParams();
        fCBaseParams.setAppId(str);
        fCBaseParams.setAid(C1663g.m8005a(context));
        fCBaseParams.setCpu(String.format(Locale.ENGLISH, "verdor:%d:%s", new Object[]{Integer.valueOf(C1727p.m8204b()), C1727p.m8202a()}));
        fCBaseParams.setDb(Build.BRAND);
        fCBaseParams.setDm(Build.DEVICE);
        fCBaseParams.setImei(C1727p.m8203a(context));
        fCBaseParams.setRs(C1727p.m8206c());
        fCBaseParams.setSr(C1727p.m8205b(context));
        return fCBaseParams;
    }

    @Nullable
    private static String m8202a() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        Throwable e;
        Throwable th;
        Object obj;
        String str = null;
        try {
            fileReader = new FileReader("/proc/cpuinfo");
            try {
                bufferedReader = new BufferedReader(fileReader);
                try {
                    str = bufferedReader.readLine().split(":\\s+", 2)[1];
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Throwable e2) {
                            C1639b.m7917a(f11500a, "fileReader close fail", e2);
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable e22) {
                            C1639b.m7917a(f11500a, "bufferReader close fail", e22);
                        }
                    }
                } catch (IOException e3) {
                    e22 = e3;
                    try {
                        C1639b.m7917a(f11500a, "read cpu name fail", e22);
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (Throwable e222) {
                                C1639b.m7917a(f11500a, "fileReader close fail", e222);
                            }
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Throwable e2222) {
                                C1639b.m7917a(f11500a, "bufferReader close fail", e2222);
                            }
                        }
                        return str;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (Throwable e22222) {
                                C1639b.m7917a(f11500a, "fileReader close fail", e22222);
                            }
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Throwable e222222) {
                                C1639b.m7917a(f11500a, "bufferReader close fail", e222222);
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e4) {
                e222222 = e4;
                obj = str;
                C1639b.m7917a(f11500a, "read cpu name fail", e222222);
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return str;
            } catch (Throwable e2222222) {
                obj = str;
                th = e2222222;
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        } catch (IOException e5) {
            e2222222 = e5;
            bufferedReader = str;
            fileReader = str;
            C1639b.m7917a(f11500a, "read cpu name fail", e2222222);
            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return str;
        } catch (Throwable e22222222) {
            bufferedReader = str;
            fileReader = str;
            th = e22222222;
            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        return str;
    }

    private static int m8204b() {
        Throwable e;
        try {
            return new File("/sys/devices/system/cpu/").listFiles(f11501b).length;
        } catch (SecurityException e2) {
            e = e2;
        } catch (NullPointerException e3) {
            e = e3;
        }
        C1639b.m7917a(f11500a, "get cpu cores fail", e);
        return 1;
    }

    @Nullable
    private static String m8203a(@NonNull Context context) {
        if (ContextCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") != 0) {
            return BuildConfig.VERSION_NAME;
        }
        return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
    }

    @Nullable
    private static String m8206c() {
        FileReader fileReader;
        Throwable e;
        Throwable th;
        Object obj;
        String str = null;
        BufferedReader bufferedReader;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader, 8);
                try {
                    str = String.valueOf(Long.valueOf(bufferedReader.readLine().split("\\s+")[1]).longValue());
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Throwable e2) {
                            C1639b.m7917a(f11500a, "fileReader close fail", e2);
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable e22) {
                            C1639b.m7917a(f11500a, "bufferedReader close fail", e22);
                        }
                    }
                } catch (IOException e3) {
                    e22 = e3;
                    try {
                        C1639b.m7917a(f11500a, "read memory fail", e22);
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (Throwable e222) {
                                C1639b.m7917a(f11500a, "fileReader close fail", e222);
                            }
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Throwable e2222) {
                                C1639b.m7917a(f11500a, "bufferedReader close fail", e2222);
                            }
                        }
                        return str;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (Throwable e22222) {
                                C1639b.m7917a(f11500a, "fileReader close fail", e22222);
                            }
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Throwable e222222) {
                                C1639b.m7917a(f11500a, "bufferedReader close fail", e222222);
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e4) {
                e222222 = e4;
                obj = str;
                C1639b.m7917a(f11500a, "read memory fail", e222222);
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return str;
            } catch (Throwable e2222222) {
                obj = str;
                th = e2222222;
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        } catch (IOException e5) {
            e2222222 = e5;
            bufferedReader = str;
            fileReader = str;
            C1639b.m7917a(f11500a, "read memory fail", e2222222);
            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return str;
        } catch (Throwable e22222222) {
            bufferedReader = str;
            fileReader = str;
            th = e22222222;
            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        return str;
    }

    private static String m8205b(@NonNull Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics.widthPixels + "," + displayMetrics.heightPixels;
    }
}
