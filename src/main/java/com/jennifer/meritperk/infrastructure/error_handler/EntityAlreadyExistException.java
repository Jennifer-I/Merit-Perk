package com.jennifer.meritperk.infrastructure.error_handler;

public class EntityAlreadyExistException extends RuntimeException {
    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
