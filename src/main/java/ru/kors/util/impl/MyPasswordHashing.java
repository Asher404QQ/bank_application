package ru.kors.util.impl;

import ru.kors.util.PasswordHashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyPasswordHashing implements PasswordHashing {
    @Override
    public String doHashing(String email, String password) {
        String emailAndPwd = email + password;
        String firstHashPwd = pwdHash(emailAndPwd);
        return pwdHash(firstHashPwd);
    }

    private static String pwdHash(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();

            for (byte b : digest) {
                stringBuilder.append(String.format("%02x", b));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
