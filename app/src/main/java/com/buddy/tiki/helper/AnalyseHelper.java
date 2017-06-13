package com.buddy.tiki.helper;

import android.content.Context;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.buddy.tiki.base.BusinessDomain;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.UMAnalyticsConfig;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.bytedeco.javacpp.swscale;
import org.json.JSONObject;

public class AnalyseHelper {
    public static void init(@NonNull Context context) {
        UMAnalyticsConfig umAnalyticsConfig = new UMAnalyticsConfig(context, "57864194e0f55adbe800142e", BusinessDomain.f405c);
        MobclickAgent.enableEncrypt(false);
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.startWithConfigure(umAnalyticsConfig);
        MobclickAgent.setCatchUncaughtExceptions(true);
    }

    public static boolean checkPermission(Context context, String permission) {
        if (VERSION.SDK_INT >= 23) {
            try {
                if (((Integer) Class.forName("android.content.Context").getMethod("checkSelfPermission", new Class[]{String.class}).invoke(context, new Object[]{permission})).intValue() == 0) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        } else if (context.getPackageManager().checkPermission(permission, context.getPackageName()) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String getDeviceInfo(Context context) {
        FileReader fstream;
        Throwable th;
        try {
            JSONObject json = new JSONObject();
            TelephonyManager tm = (TelephonyManager) context.getSystemService("phone");
            String device_id = null;
            if (checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                device_id = tm.getDeviceId();
            }
            String mac = null;
            try {
                fstream = new FileReader("/sys/class/net/wlan0/address");
            } catch (FileNotFoundException e) {
                fstream = new FileReader("/sys/class/net/eth0/address");
            }
            BufferedReader in = null;
            if (fstream != null) {
                try {
                    BufferedReader in2 = new BufferedReader(fstream, swscale.SWS_SPLINE);
                    try {
                        mac = in2.readLine();
                        if (fstream != null) {
                            try {
                                fstream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (in2 != null) {
                            try {
                                in2.close();
                                in = in2;
                            } catch (IOException e22) {
                                e22.printStackTrace();
                                in = in2;
                            }
                        }
                    } catch (IOException e3) {
                        in = in2;
                        if (fstream != null) {
                            try {
                                fstream.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        json.put("mac", mac);
                        if (TextUtils.isEmpty(device_id)) {
                            device_id = mac;
                        }
                        if (TextUtils.isEmpty(device_id)) {
                            device_id = Secure.getString(context.getContentResolver(), "android_id");
                        }
                        json.put("device_id", device_id);
                        return json.toString();
                    } catch (Throwable th2) {
                        th = th2;
                        in = in2;
                        if (fstream != null) {
                            try {
                                fstream.close();
                            } catch (IOException e22222) {
                                e22222.printStackTrace();
                            }
                        }
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException e222222) {
                                e222222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    if (fstream != null) {
                        fstream.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                    json.put("mac", mac);
                    if (TextUtils.isEmpty(device_id)) {
                        device_id = mac;
                    }
                    if (TextUtils.isEmpty(device_id)) {
                        device_id = Secure.getString(context.getContentResolver(), "android_id");
                    }
                    json.put("device_id", device_id);
                    return json.toString();
                } catch (Throwable th3) {
                    th = th3;
                    if (fstream != null) {
                        fstream.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                    throw th;
                }
            }
            json.put("mac", mac);
            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }
            if (TextUtils.isEmpty(device_id)) {
                device_id = Secure.getString(context.getContentResolver(), "android_id");
            }
            json.put("device_id", device_id);
            return json.toString();
        } catch (Exception e5) {
            e5.printStackTrace();
            return null;
        }
    }
}
