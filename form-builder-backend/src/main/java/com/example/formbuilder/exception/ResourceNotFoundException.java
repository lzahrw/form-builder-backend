// src/main/java/com/example/formbuilder/exception/ResourceNotFoundException.java

package com.example.formbuilder.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
