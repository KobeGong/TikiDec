package com.buddy.tiki.util;

import android.content.Context;
import android.support.v4.view.InputDeviceCompat;
import android.support.v7.recyclerview.BuildConfig;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.swscale;

public class PinYinUtil {
    public static char f2448a = '#';
    private static PinYinUtil f2449b;
    private static String[] f2450c = new String[]{"zuo", "zun", "zui", "zuan", "zu", "zou", "zong", "zi", "zhuo", "zhun", "zhui", "zhuang", "zhuan", "zhuai", "zhua", "zhu", "zhou", "zhong", "zhi", "zheng", "zhen", "zhe", "zhao", "zhang", "zhan", "zhai", "zha", "zeng", "zen", "zei", "ze", "zao", "zang", "zan", "zai", "za", "yun", "yue", "yuan", "yu", "you", "yong", "yo", "ying", "yin", "yi", "ye", "yao", "yang", "yan", "ya", "xun", "xue", "xuan", "xu", "xiu", "xiong", "xing", "xin", "xie", "xiao", "xiang", "xian", "xia", "xi", "wu", "wo", "weng", "wen", "wei", "wang", "wan", "wai", "wa", "tuo", "tun", "tui", "tuan", "tu", "tou", "tong", "ting", "tie", "tiao", "tian", "ti", "teng", "te", "tao", "tang", "tan", "tai", "ta", "suo", "sun", "sui", "suan", "su", "sou", "song", "si", "shuo", "shun", "shui", "shuang", "shuan", "shuai", "shua", "shu", "shou", "shi", "sheng", "shen", "she", "shao", "shang", "shan", "shai", "sha", "seng", "sen", "se", "sao", "sang", "san", "sai", "sa", "ruo", "run", "rui", "ruan", "ru", "rou", "rong", "ri", "reng", "ren", "re", "rao", "rang", "ran", "qun", "que", "quan", "qu", "qiu", "qiong", "qing", "qin", "qie", "qiao", "qiang", "qian", "qia", "qi", "pu", "po", "ping", "pin", "pie", "piao", "pian", "pi", "peng", "pen", "pei", "pao", "pang", "pan", "pai", "pa", "ou", "o", "nuo", "nue", "nuan", "nv", "nu", "nong", "niu", "ning", "nin", "nie", "niao", "niang", "nian", "ni", "neng", "nen", "nei", "ne", "nao", "nang", "nan", "nai", "na", "mu", "mou", "mo", "miu", "ming", "min", "mie", "miao", "mian", "mi", "meng", "men", "mei", "me", "mao", "mang", "man", "mai", "ma", "luo", "lun", "lue", "luan", "lv", "lu", "lou", "long", "liu", "ling", "lin", "lie", "liao", "liang", "lian", "lia", "li", "leng", "lei", "le", "lao", "lang", "lan", "lai", "la", "kuo", "kun", "kui", "kuang", "kuan", "kuai", "kua", "ku", "kou", "kong", "keng", "ken", "ke", "kao", "kang", "kan", "kai", "ka", "jun", "jue", "juan", "ju", "jiu", "jiong", "jing", "jin", "jie", "jiao", "jiang", "jian", "jia", "ji", "huo", "hun", "hui", "huang", "huan", "huai", "hua", "hu", "hou", "hong", "heng", "hen", "hei", "he", "hao", "hang", "han", "hai", "ha", "guo", "gun", "gui", "guang", "guan", "guai", "gua", "gu", "gou", "gong", "geng", "gen", "gei", "ge", "gao", "gang", "gan", "gai", "ga", "fu", "fou", "fo", "feng", "fen", "fei", "fang", "fan", "fa", "er", "en", "e", "duo", "dun", "dui", "duan", "du", "dou", "dong", "diu", "ding", "die", "diao", "dian", "di", "deng", "de", "dao", "dang", "dan", "dai", "da", "cuo", "cun", "cui", "cuan", "cu", "cou", "cong", "ci", "chuo", "chun", "chui", "chuang", "chuan", "chuai", "chu", "chou", "chong", "chi", "cheng", "chen", "che", "chao", "chang", "chan", "chai", "cha", "ceng", "ce", "cao", "cang", "can", "cai", "ca", "bu", "bo", "bing", "bin", "bie", "biao", "bian", "bi", "beng", "ben", "bei", "bao", "bang", "ban", "bai", "ba", "ao", "ang", "an", "ai", "a"};
    private static char[] f2451d = new char[]{'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'O', 'O', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'K', 'K', 'K', 'K', 'K', 'K', 'K', 'K', 'K', 'K', 'K', 'K', 'K', 'K', 'K', 'K', 'K', 'K', 'J', 'J', 'J', 'J', 'J', 'J', 'J', 'J', 'J', 'J', 'J', 'J', 'J', 'J', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'E', 'E', 'E', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'A', 'A', 'A', 'A', 'A'};
    private byte[] f2452e;

    private PinYinUtil() {
    }

    public static PinYinUtil getInstance() {
        if (f2449b == null) {
            synchronized (PinYinUtil.class) {
                if (f2449b == null) {
                    f2449b = new PinYinUtil();
                }
            }
        }
        return f2449b;
    }

    private static boolean m1599a(String regex, int regexIndex, String plain, String pinyin, int index) {
        int k;
        int i = regexIndex;
        int j = index;
        if (index == 0) {
            for (k = 0; k < plain.length(); k++) {
                if (m1599a(regex, regexIndex, plain, pinyin, k + 1)) {
                    return true;
                }
            }
        }
        while (i < regex.length() && j < plain.length()) {
            if (regex.charAt(i) == plain.charAt(j)) {
                j++;
                i++;
            } else if (plain.charAt(j) >= '\u4e00' && plain.charAt(j) <= '\u9fa5') {
                int ix = f2450c.length - (pinyin.charAt(j) + InputDeviceCompat.SOURCE_ANY);
                if (ix < 0 || ix >= f2450c.length) {
                    return false;
                }
                String py = f2450c[ix];
                for (k = 0; k < py.length(); k++) {
                    if (regex.charAt(i + k) != py.charAt(k)) {
                        return false;
                    }
                    if (m1599a(regex, (i + k) + 1, plain, pinyin, j + 1)) {
                        return true;
                    }
                }
                return false;
            } else if (plain.charAt(j) == ' ' || plain.charAt(j) == '\u3000') {
                j++;
            } else if (plain.charAt(j) < 'A' || plain.charAt(j) > 'Z') {
                return false;
            } else {
                if (plain.charAt(j) + 32 != regex.charAt(i)) {
                    return false;
                }
                j++;
                i++;
            }
        }
        if (i != regex.length()) {
            return false;
        }
        return true;
    }

    public static boolean validPinyin(String regex, String plain, String pinyin) {
        if (plain == null || plain.length() == 0 || pinyin == null || plain.length() != pinyin.length()) {
            return false;
        }
        return m1599a(regex, 0, plain, pinyin, 0);
    }

    public static int comparePinyinFrist(String plain1, String py1, String plain2, String py2) {
        int i = 1;
        if (plain1 == null || py1 == null || plain1.length() != py1.length()) {
            return -1;
        }
        if (plain2 == null || py2 == null || plain2.length() != py2.length()) {
            return 1;
        }
        int i2 = 0;
        while (i2 < plain1.length() && i2 < plain2.length()) {
            if (plain1.charAt(i2) != plain2.charAt(i2)) {
                if (plain1.charAt(i2) < '\u4e00' || plain1.charAt(i2) > '\u9fa5') {
                    if (plain2.charAt(i2) >= '\u4e00' && plain2.charAt(i2) <= '\u9fa5') {
                        return 1;
                    }
                    if (py1.charAt(i2) != py2.charAt(i2)) {
                        if (py1.charAt(i2) >= py2.charAt(i2)) {
                            return 1;
                        }
                        return -1;
                    }
                } else if (plain2.charAt(i2) < '\u4e00' || plain2.charAt(i2) > '\u9fa5') {
                    return -1;
                } else {
                    if (py1.charAt(i2) != py2.charAt(i2)) {
                        if (py1.charAt(i2) >= py2.charAt(i2)) {
                            return 1;
                        }
                        return -1;
                    }
                }
            }
            i2++;
        }
        if (plain1.length() == plain2.length()) {
            return 0;
        }
        if (plain1.length() >= plain2.length()) {
            i = -1;
        }
        return i;
    }

    public static int comparePinyin(String plain1, String py1, String plain2, String py2) {
        if (plain1 == null || py1 == null || plain1.length() != py1.length()) {
            return -1;
        }
        if (plain2 == null || py2 == null || plain2.length() != py2.length()) {
            return 1;
        }
        int i = 0;
        while (i < plain1.length() && i < plain2.length()) {
            if (plain1.charAt(i) != plain2.charAt(i)) {
                int ix;
                if (plain1.charAt(i) < '\u4e00' || plain1.charAt(i) > '\u9fa5') {
                    if (plain2.charAt(i) < '\u4e00' || plain2.charAt(i) > '\u9fa5') {
                        if (py1.charAt(i) != py2.charAt(i)) {
                            if (py1.charAt(i) >= py2.charAt(i)) {
                                return -1;
                            }
                            return 1;
                        }
                    } else if (py1.charAt(i) < 'a' || py1.charAt(i) > 'z') {
                        return 1;
                    } else {
                        ix = f2450c.length - (py2.charAt(i) + InputDeviceCompat.SOURCE_ANY);
                        if (ix < 0 || ix >= f2450c.length) {
                            return 1;
                        }
                        if (py1.charAt(i) > f2450c[ix].charAt(0)) {
                            return -1;
                        }
                        return 1;
                    }
                } else if (plain2.charAt(i) < '\u4e00' || plain2.charAt(i) > '\u9fa5') {
                    if (py2.charAt(i) < 'a' || py2.charAt(i) > 'z') {
                        return -1;
                    }
                    ix = f2450c.length - (py1.charAt(i) + InputDeviceCompat.SOURCE_ANY);
                    if (ix < 0 || ix >= f2450c.length) {
                        return -1;
                    }
                    if (f2450c[ix].charAt(0) >= py2.charAt(i)) {
                        return -1;
                    }
                    return 1;
                } else if (py1.charAt(i) != py2.charAt(i)) {
                    if (py1.charAt(i) >= py2.charAt(i)) {
                        return -1;
                    }
                    return 1;
                }
            }
            i++;
        }
        if (plain1.length() == plain2.length()) {
            return 0;
        }
        if (plain1.length() >= plain2.length()) {
            return -1;
        }
        return 1;
    }

    public void release() {
        if (this.f2452e != null) {
            this.f2452e = null;
        }
    }

    public char getFirstLetter(Context context, char gb2312) {
        if (gb2312 < '\u4e00') {
            if (gb2312 < 'A') {
                return f2448a;
            }
            if (gb2312 <= 'Z') {
                return gb2312;
            }
            if (gb2312 < 'a') {
                return f2448a;
            }
            if (gb2312 <= 'z') {
                return (char) (gb2312 - 32);
            }
            return f2448a;
        } else if (gb2312 > '\u9fa5') {
            return f2448a;
        } else {
            int index = f2450c.length - (m1598a(context, gb2312) + InputDeviceCompat.SOURCE_ANY);
            if (index < 0 || index >= f2450c.length) {
                return f2448a;
            }
            return f2451d[index];
        }
    }

    public String getCustomPinyin(Context context, String gb2312) {
        if (gb2312 == null || BuildConfig.VERSION_NAME.equals(gb2312.trim())) {
            return gb2312;
        }
        StringBuffer retuBuf = new StringBuffer();
        getCustomPinyin(context, gb2312, retuBuf);
        StringBuilder sb = new StringBuilder();
        for (char c : retuBuf.toString().toCharArray()) {
            int index = f2450c.length - (c + InputDeviceCompat.SOURCE_ANY);
            if (index < f2450c.length && index >= 0) {
                sb.append(f2450c[index]);
            }
        }
        return sb.toString();
    }

    public String transferCNToPinyin(Context context, String gb2312) {
        if (TextUtils.isEmpty(gb2312)) {
            return gb2312;
        }
        int startIndex = -1;
        int endIndex = -1;
        char[] chars = gb2312.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < chars.length) {
            if (chars[i] < '\u4e00' || chars[i] > '\u9fa5') {
                if (startIndex >= 0) {
                    sb.append(getCustomPinyin(context, gb2312.substring(startIndex, i)));
                    startIndex = -1;
                    endIndex = -1;
                } else {
                    sb.append(chars[i]);
                }
            } else if (startIndex < 0) {
                startIndex = i;
            }
            i++;
        }
        if (startIndex >= 0 && endIndex < 0) {
            sb.append(getCustomPinyin(context, gb2312));
        }
        return sb.toString();
    }

    private char m1598a(Context context, char ch) {
        if (this.f2452e == null) {
            try {
                InputStream bi = context.getAssets().open("u2p.dat");
                this.f2452e = new byte[41804];
                bi.read(this.f2452e);
                bi.close();
            } catch (IOException e) {
                e.printStackTrace();
                return '\u0100';
            }
        }
        return (ch < '\u4e00' || ch > '\u9fa5') ? ch : (char) (((this.f2452e[(ch - 19968) * 2] << 8) | (this.f2452e[((ch - 19968) * 2) + 1] & avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK)) + swscale.SWS_SINC);
    }

    public void getCustomPinyin(Context context, String cnStr, StringBuffer pinyin) {
        if (pinyin != null) {
            pinyin.setLength(0);
        } else {
            pinyin = new StringBuffer();
        }
        char[] chars = cnStr.toCharArray();
        int i = 0;
        while (i < chars.length) {
            if (chars[i] >= '\u4e00' && chars[i] <= '\u9fa5') {
                pinyin.append(m1598a(context, chars[i]));
            } else if (chars[i] < '\u0000' || chars[i] > '\u00ff') {
                pinyin.append(chars[i]);
            } else if (chars[i] < 'A' || chars[i] > 'Z') {
                pinyin.append(chars[i]);
            } else {
                pinyin.append((char) (chars[i] + 32));
            }
            i++;
        }
    }
}
