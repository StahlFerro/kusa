package io.stahlferro.kusa.utils;

import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@UtilityClass
public class EncryptionUtils {
    @Autowired
    private PasswordEncoder bcryptEncoder;
    public static String encryptPassword(String plainPassword) {
        return bcryptEncoder.encode(plainPassword);
    }
}
