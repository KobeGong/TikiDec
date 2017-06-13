package com.buddy.tiki.util;

import android.support.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bytedeco.javacpp.swscale;

public class ByteUtil {
    @Nullable
    public static byte[] readFully(InputStream input) {
        try {
            byte[] buffer = new byte[swscale.SWS_SPLINE];
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            while (true) {
                int bytesRead = input.read(buffer);
                if (bytesRead == -1) {
                    return output.toByteArray();
                }
                output.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            return null;
        }
    }
}
