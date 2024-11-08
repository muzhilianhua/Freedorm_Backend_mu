package com.ruoyi.lock.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    private static final String SALT = "JustSomethingHere"; // 这里可以使用动态盐

    /**
     * 生成密码哈希值
     *
     * @param deviceId
     * @param username
     * @return
     */
    public static String generatePasswordHash(String deviceId, String username) {
        String toHash = deviceId + username + SALT;
        return sha256(toHash);
    }

    /**
     * 生成 SHA-256 哈希
     *
     * @param data
     * @return
     */
    private static String sha256(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            // 转换为十六进制字符串
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
