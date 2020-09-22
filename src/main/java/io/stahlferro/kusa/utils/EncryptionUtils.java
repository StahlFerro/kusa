//package io.stahlferro.kusa.utils;
//
//import lombok.experimental.UtilityClass;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class EncryptionUtils {
//    private static PasswordEncoder passwordEncoder;
//    public static void setPasswordEncoder(PasswordEncoder encoder) {
//        EncryptionUtils.passwordEncoder = encoder;
//    }
//    public static String encryptPassword(String plainPassword) {
//        return passwordEncoder.encode(plainPassword);
//    }
//}
