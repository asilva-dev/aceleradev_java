package com.challenge.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5625574484783064764L;

    public ResourceNotFoundException(String resourceName) {
        super("Resource: " + resourceName + " not found.");
    }

}