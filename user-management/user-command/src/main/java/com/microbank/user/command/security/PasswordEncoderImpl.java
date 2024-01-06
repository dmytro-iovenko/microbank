package com.microbank.user.command.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderImpl implements PasswordEncoder {

    @Override
    public String encryptPassword(String password) {
        var encoder = new BCryptPasswordEncoder(12);
        String encodedPassword = encoder.encode(password);
        return encodedPassword;
    }
    
}
