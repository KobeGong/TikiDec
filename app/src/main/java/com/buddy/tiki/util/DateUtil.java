package com.buddy.tiki.util;

import android.support.v7.recyclerview.BuildConfig;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ChatApp;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static long f2351a = 3600000;
    public static ThreadLocal<DateFormat> f2352b = new C04781();
    public static ThreadLocal<DateFormat> f2353c = new C04792();
    public static ThreadLocal<DateFormat> f2354d = new C04803();
    public static ThreadLocal<DateFormat> f2355e = new C04814();
    public static ThreadLocal<DateFormat> f2356f = new C04825();
    public static ThreadLocal<DateFormat> f2357g = new C04836();
    public static ThreadLocal<DateFormat> f2358h = new C04847();

    static class C04781 extends ThreadLocal<DateFormat> {
        C04781() {
        }

        protected /* synthetic */ Object initialValue() {
            return m1522a();
        }

        protected DateFormat m1522a() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        }
    }

    static class C04792 extends ThreadLocal<DateFormat> {
        C04792() {
        }

        protected /* synthetic */ Object initialValue() {
            return m1523a();
        }

        protected DateFormat m1523a() {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        }
    }

    static class C04803 extends ThreadLocal<DateFormat> {
        C04803() {
        }

        protected /* synthetic */ Object initialValue() {
            return m1524a();
        }

        protected DateFormat m1524a() {
            return new SimpleDateFormat("MM\u6708dd\u65e5 HH:mm", Locale.CHINA);
        }
    }

    static class C04814 extends ThreadLocal<DateFormat> {
        C04814() {
        }

        protected /* synthetic */ Object initialValue() {
            return m1525a();
        }

        protected DateFormat m1525a() {
            return new SimpleDateFormat("HH:mm", Locale.CHINA);
        }
    }

    static class C04825 extends ThreadLocal<DateFormat> {
        C04825() {
        }

        protected /* synthetic */ Object initialValue() {
            return m1526a();
        }

        protected DateFormat m1526a() {
            return new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
        }
    }

    static class C04836 extends ThreadLocal<DateFormat> {
        C04836() {
        }

        protected /* synthetic */ Object initialValue() {
            return m1527a();
        }

        protected DateFormat m1527a() {
            return new SimpleDateFormat("yyyy\u5e74MM\u6708dd\u65e5", Locale.CHINA);
        }
    }

    static class C04847 extends ThreadLocal<DateFormat> {
        C04847() {
        }

        protected /* synthetic */ Object initialValue() {
            return m1528a();
        }

        protected DateFormat m1528a() {
            return new SimpleDateFormat("a hh:mm", Locale.CHINA);
        }
    }

    public static String computeTimeDiff(long srcTime, boolean longTime) {
        return computeTimeDiff(srcTime, longTime, false);
    }

    public static String computeTimeDiff(long srcTime, boolean longTime, boolean is24Hour) {
        long now = System.currentTimeMillis();
        if (m1531c(srcTime, now)) {
            if (m1530b(srcTime, now)) {
                if (m1529a(srcTime, now)) {
                    return ChatApp.getInstance().getString(C0376R.string.just_now);
                }
                return ChatApp.getInstance().getString(C0376R.string.video_message_time_len, new Object[]{Long.valueOf(Math.abs(now - srcTime) / 60000)});
            } else if (is24Hour) {
                return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(srcTime));
            } else {
                return new SimpleDateFormat("hh:mm", Locale.getDefault()).format(new Date(srcTime));
            }
        } else if (longTime) {
            return formatMDHM(new Date(srcTime));
        } else {
            return new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault()).format(new Date(srcTime));
        }
    }

    private static boolean m1529a(long srcTime, long now) {
        return Math.abs(srcTime - now) < 60000;
    }

    public static boolean isMessageTimeClose(long srcTime, long dstTime) {
        return Math.abs(srcTime - dstTime) < 180000;
    }

    private static boolean m1530b(long srcTime, long now) {
        return Math.abs(srcTime - now) < 3600000;
    }

    private static boolean m1531c(long srcTime, long now) {
        return Math.abs(srcTime - now) < 86400000;
    }

    public static String format(Date date) {
        return ((DateFormat) f2352b.get()).format(date);
    }

    public static String formatYMD(Date date) {
        return ((DateFormat) f2353c.get()).format(date);
    }

    public static String formatMDHM(Date date) {
        return ((DateFormat) f2354d.get()).format(date);
    }

    public static String formatHM(Date date) {
        return ((DateFormat) f2355e.get()).format(date);
    }

    public static String format(Date date, DateFormat dateFormat) {
        if (dateFormat != null) {
            return dateFormat.format(date);
        }
        return BuildConfig.VERSION_NAME;
    }

    public static boolean isEqualDay(Date date1, Date date2) throws ParseException {
        if (date1 == null || date2 == null || !formatYMD(date1).equals(formatYMD(date2))) {
            return false;
        }
        return true;
    }

    public static String getChatMessageDate(long srcTime) {
        Date currentTime = new Date();
        String currentStr = ((DateFormat) f2357g.get()).format(currentTime);
        Timestamp ts = new Timestamp(srcTime);
        String srcTimeStr = ((DateFormat) f2357g.get()).format(ts);
        if (srcTimeStr.equals(currentStr)) {
            return BuildConfig.VERSION_NAME;
        }
        if (srcTimeStr.equals(((DateFormat) f2357g.get()).format(getYesterday(currentTime)))) {
            return "\u6628\u5929";
        }
        return ((DateFormat) f2357g.get()).format(ts);
    }

    public static String getChatMessageTime(long srcTime) {
        return ((DateFormat) f2358h.get()).format(new Timestamp(srcTime));
    }

    public static String getChatTimespan(long ms) {
        long x = ms / 1000;
        long seconds = x % 60;
        x /= 60;
        long minutes = x % 60;
        x /= 60;
        long hours = x % 24;
        long days = x / 24;
        String prefix = ChatApp.getInstance().getResources().getString(C0376R.string.video_message_video_time_prefix);
        if (days > 0) {
            prefix = prefix + days + ChatApp.getInstance().getResources().getString(C0376R.string.video_message_day);
        }
        if (hours > 0) {
            prefix = prefix + hours + ChatApp.getInstance().getResources().getString(C0376R.string.video_message_hour);
        }
        if (minutes > 0) {
            prefix = prefix + minutes + ChatApp.getInstance().getResources().getString(C0376R.string.video_message_minute);
        }
        if (seconds > 0) {
            return prefix + seconds + ChatApp.getInstance().getResources().getString(C0376R.string.video_message_second);
        }
        return prefix;
    }

    public static Date getYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, -1);
        return calendar.getTime();
    }
}
