package com.buddy.tiki.helper;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import com.facebook.android.crypto.keychain.AndroidConceal;
import com.facebook.android.crypto.keychain.SharedPrefsBackedKeyChain;
import com.facebook.crypto.CryptoConfig;
import com.facebook.crypto.Entity;
import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.bytedeco.javacpp.avcodec;

public class SecurityHelper {
    @CheckResult
    public static byte[] encrypt(Context context, @NonNull byte[] plainText, String type) {
        try {
            return AndroidConceal.get().createDefaultCrypto(new SharedPrefsBackedKeyChain(context, CryptoConfig.KEY_256)).encrypt(plainText, Entity.create(type));
        } catch (KeyChainException e) {
            return null;
        } catch (CryptoInitializationException e2) {
            return null;
        } catch (IOException e3) {
            return null;
        }
    }

    @CheckResult
    public static byte[] decrypt(Context context, @NonNull byte[] encryptText, String type) {
        try {
            return AndroidConceal.get().createDefaultCrypto(new SharedPrefsBackedKeyChain(context, CryptoConfig.KEY_256)).decrypt(encryptText, Entity.create(type));
        } catch (KeyChainException e) {
            return null;
        } catch (CryptoInitializationException e2) {
            return null;
        } catch (IOException e3) {
            return null;
        }
    }

    public static String nextPayload() {
        return new BigInteger(avcodec.AV_CODEC_ID_MAD, new SecureRandom()).toString(32);
    }
}
