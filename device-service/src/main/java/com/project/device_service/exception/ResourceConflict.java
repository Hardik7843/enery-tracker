package com.project.device_service.exception;

public class ResourceConflict extends RuntimeException {
    public ResourceConflict(String message) {
        super(message);
    }
}
