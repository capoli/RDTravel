package com.realdolmen.rdtravel.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Use this Util to hash the passwords before they are persisted to the database
 */

public class PasswordUtil {
    public static String getPasswordHash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        return Base64.getEncoder().encodeToString(md.digest());
    }
}
