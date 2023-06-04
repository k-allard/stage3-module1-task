package com.mjc.school.service.exceptions;

public class NewsTitleInvalidException extends Exception {
    public NewsTitleInvalidException(String errorMessage) {
        super("ERROR_CODE: 000012 ERROR_MESSAGE: " + errorMessage);
    }
}
