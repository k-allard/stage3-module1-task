package com.mjc.school.exceptions;

public class IdShouldBeNumberException extends IllegalArgumentException {

    public IdShouldBeNumberException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
