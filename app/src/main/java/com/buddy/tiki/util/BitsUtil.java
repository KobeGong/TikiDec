package com.buddy.tiki.util;

public class BitsUtil {
    public static boolean isCelebrity(int flagbit) {
        return m1508a(flagbit, 1);
    }

    public static boolean isFriend(int code) {
        return m1508a(code, 4);
    }

    public static boolean isFollowed(int code) {
        return m1508a(code, 2);
    }

    public static boolean isFollowing(int code) {
        return m1508a(code, 1);
    }

    public static boolean isMutualFollow(int code) {
        return isFollowed(code) && isFollowing(code);
    }

    private static boolean m1508a(int bit, int constant) {
        return (bit & constant) == constant;
    }
}
