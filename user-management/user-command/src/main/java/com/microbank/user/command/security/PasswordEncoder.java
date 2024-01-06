package com.microbank.user.command.security;

public interface PasswordEncoder {
    String encryptPassword(String password);
}
