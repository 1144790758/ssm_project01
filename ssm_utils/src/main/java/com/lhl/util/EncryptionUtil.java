package com.lhl.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @athor:lhl
 * @create:2020-02-05 20:39
 */
public class EncryptionUtil {

    public static void encryptione(String str){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode(str));
    }

    public static void main(String[] args) {
//        encryptione("123456");

        // Create an encoder with strength 16
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        encoder.encode("123456");
//        encoder.assertTrue(encoder.matches("myPassword", result));
        boolean matches = encoder.matches("123456", "$2a$10$KwZLUrAEXvOub.lNfTm3xu38rzNmku.kRnZ1zCIU7ZHhm/VB0Q4HG");
        System.out.println(matches);
    }

}
