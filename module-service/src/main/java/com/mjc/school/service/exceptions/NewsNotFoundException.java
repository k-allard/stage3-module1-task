package com.mjc.school.service.exceptions;

public class NewsNotFoundException extends Exception {
    public NewsNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
