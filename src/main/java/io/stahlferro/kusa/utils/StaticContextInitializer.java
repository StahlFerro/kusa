//package io.stahlferro.kusa.utils;
//
//import org.apache.catalina.core.ApplicationContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
//@Component
//public class StaticContextInitializer {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private ApplicationContext context;
//
//    @PostConstruct
//    public void init() {
//        EncryptionUtils.setPasswordEncoder(passwordEncoder);
//    }
//}
