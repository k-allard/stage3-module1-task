package com.mjc.school.exceptions;

public class IdShouldBeNumberException extends IllegalArgumentException {

    public IdShouldBeNumberException(String errorMessage, Throwable err) {
        super("ERROR_CODE: 000013 ERROR_MESSAGE: " + errorMessage, err);
    }
}
