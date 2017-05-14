package cn.annpeter.graduation.project.base.common.util;

import cn.annpeter.graduation.project.base.common.exception.CrackErrorException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created on 2016/09/28
 *
 * @author annpeter.it@gmail.com
 */
public class EncryptUtils {
    private static final char[] hexDigits = "0123456789abcdef".toCharArray();

    public static String MD5_16(String data) {
        return MD5_16(data.getBytes());
    }

    public static String MD5_16(byte[] data) {
        return MD5(data).substring(8, 24);
    }

    public static String MD5(String data) {
        return MD5(data.getBytes());
    }

    public static String MD5(byte[] bytes) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new CrackErrorException("encrypt error.", e);
        }
        messageDigest.update(bytes);
        byte[] digestBytes = messageDigest.digest();
        char[] chars = new char[digestBytes.length * 2];
        int k = 0;
        for (byte b : digestBytes) {
            chars[k++] = hexDigits[b >>> 4 & 15];
            chars[k++] = hexDigits[b & 15];
        }
        return new String(chars).toUpperCase();
    }

    public static byte[] aesEncrypt(String data, String key) {
        return aesEncrypt(data.getBytes(), key.getBytes());
    }

    public static byte[] aesEncrypt(byte[] data, byte[] key) {
        try {
            String iv = EncryptUtils.MD5_16(key);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();

            int plaintextLength = data.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(data, 0, plaintext, 0, data.length);

            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return Base64.getEncoder().encode(encrypted);
        } catch (Exception e) {
            throw new CrackErrorException("encrypt error.", e);
        }
    }

    public static byte[] aesDecrypt(String data, String key) {
        return aesEncrypt(data.getBytes(), key.getBytes());
    }

    public static byte[] aesDecrypt(byte[] data, byte[] key) {
        try {
            String iv = EncryptUtils.MD5_16(key);

            byte[] encrypted = Base64.getDecoder().decode(data);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            return cipher.doFinal(encrypted);
        } catch (Exception e) {
            throw new CrackErrorException("decrypt error.", e);
        }
    }

}
