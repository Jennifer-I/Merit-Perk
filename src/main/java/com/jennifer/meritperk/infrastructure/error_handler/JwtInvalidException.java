package com.jennifer.meritperk.infrastructure.error_handler;

public class JwtInvalidException extends RuntimeException {
    public JwtInvalidException(String message) {
        super(message);
    }
}
