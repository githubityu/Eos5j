//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.eblock.eos4j.utils;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Sha {
    public static final String SHA256 = "SHA-256";

    public Sha() {
    }

    public static byte[] SHA256(String text) {
        if (text != null && text.length() != 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(text.getBytes("utf8"));
                byte[] byteBuffer = messageDigest.digest();
                return byteBuffer;
            } catch (NoSuchAlgorithmException var3) {
                var3.printStackTrace();
            } catch (Exception var4) {
                var4.printStackTrace();
            }

            return null;
        } else {
            throw new EException("args_empty", "args is empty");
        }
    }

    public static byte[] SHA256(byte[] b) {
        if (b != null && b.length != 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(b);
                byte[] byteBuffer = messageDigest.digest();
                return byteBuffer;
            } catch (NoSuchAlgorithmException var3) {
                var3.printStackTrace();
            } catch (Exception var4) {
                var4.printStackTrace();
            }

            return null;
        } else {
            throw new EException("args_empty", "args is empty");
        }
    }

    public static byte[] HmacSHA256(byte[] data, byte[] key) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException var4) {
            var4.printStackTrace();
        } catch (InvalidKeyException var5) {
            var5.printStackTrace();
        }

        return null;
    }
}
