package com.buddy.tiki.helper;

import android.support.v7.recyclerview.BuildConfig;
import java.security.MessageDigest;
import java.util.Random;
import org.bytedeco.javacpp.avutil;

public class MD5Helper {
    public static String getMD5(String message) {
        return getMD5(message.getBytes());
    }

    public static String getRandomMD5() {
        Random ran = new Random();
        return getMD5((BuildConfig.VERSION_NAME + ran.nextDouble() + System.currentTimeMillis() + ran.nextDouble()).getBytes());
    }

    public static String getMD5(byte[] buffer) {
        try {
            MessageDigest mdAlgorithm = MessageDigest.getInstance("MD5");
            mdAlgorithm.update(buffer);
            byte[] digest = mdAlgorithm.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte aDigest : digest) {
                String plainText = Integer.toHexString(aDigest & avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK);
                if (plainText.length() < 2) {
                    plainText = "0" + plainText;
                }
                hexString.append(plainText);
            }
            return hexString.toString();
        } catch (Exception e) {
            return BuildConfig.VERSION_NAME;
        }
    }
}
