package com.InnovativePractice.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    /**
     * Generates an MD5 hash for the given input string.
     *
     * @param input the string to be hashed
     * @return the MD5 hash as a hexadecimal string
     * @throws NoSuchAlgorithmException if the MD5 algorithm is not available
     */
    public static String getMd5String(String input) {
        if (input == null) {
            return null;
        }

        try {
            // Create a MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Perform the hashing
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception (optional)
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
}