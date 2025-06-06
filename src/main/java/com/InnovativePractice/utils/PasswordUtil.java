package com.InnovativePractice.utils;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*

Spring Security 提供的加密机制，它更简便且安全。这次我们将使用 BCryptPasswordEncoder 来加密密码。
 使用说明
 调用 encrypt 方法传入原始密码，该方法将返回加密后的密码。
 调用 verify 方法，传入原始密码和加密后的密码以验证二者是否匹配。
 */
public class PasswordUtil {

//    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    // 加密密码
//    public static String encrypt(String password) {
//        return passwordEncoder.encode(password);
//    }
//
//    // 验证密码
//    public static boolean verify(String rawPassword, String encryptedPassword) {
//        return passwordEncoder.matches(rawPassword, encryptedPassword);
//    }
//
//    // 示范主方法
//    public static void main(String[] args) {
//        // 生成的密码示例
//        String originalPassword = "mysecretpassword";
//        String encryptedPassword = encrypt(originalPassword);
//
//        // 验证密码示例
//        boolean isMatch = verify(originalPassword, encryptedPassword);
//
//        System.out.println("Original Password: " + originalPassword);
//        System.out.println("Encrypted Password: " + encryptedPassword);
//        System.out.println("Password Matches: " + isMatch);
//    }
}