package com.mjc.school.common.exceptions;

public class AuthorNotFoundException extends Exception {
    public AuthorNotFoundException(String errorMessage) {
        super("ERROR_CODE: 000002 ERROR_MESSAGE: " + errorMessage);
    }
}
