package com.buddy.tiki.util;

import java.util.Random;

public class StringUtil {
    public static String fuzz(String input) {
        int i;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int inputLength = input != null ? input.length() : 0;
        int j = 0;
        for (i = 0; i < 96 && j < inputLength; i++) {
            if (i % 3 == 0) {
                sb.append(input.charAt(j));
                j++;
            } else {
                sb.append((char) (random.nextInt(26) + 97));
            }
        }
        for (i = sb.length(); i < 96; i++) {
            sb.append((char) (random.nextInt(26) + 97));
        }
        return sb.toString();
    }
}
