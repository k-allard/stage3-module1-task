package com.mjc.school.service.exceptions;

public class AuthorNotFoundException extends Exception {
    public AuthorNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
