package com.realdolmen.rdtravel.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Use this Util to hash the passwords before they are persisted to the database
 */

public class PasswordUtil {
    public static String getPasswordHash(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available for password hashing", e);
        }
        md.update(password.getBytes());
        return Base64.getEncoder().encodeToString(md.digest());
    }
}
