package com.jennifer.meritperk.infrastructure.error_handler;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String message) {
        super(message);
    }
}
