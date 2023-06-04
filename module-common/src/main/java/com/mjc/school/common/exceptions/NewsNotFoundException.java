package com.mjc.school.common.exceptions;

public class NewsNotFoundException extends Exception {
    public NewsNotFoundException(String errorMessage) {
        super("ERROR_CODE: 000001 ERROR_MESSAGE: " + errorMessage);
    }
}
