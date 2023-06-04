package com.mjc.school.common.exceptions;

public class NewsContentInvalidException extends Exception {
    public NewsContentInvalidException(String errorMessage) {
        super("ERROR_CODE: 000012 ERROR_MESSAGE: " + errorMessage);
    }
}
