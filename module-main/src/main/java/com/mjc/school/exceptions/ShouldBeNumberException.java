package com.mjc.school.exceptions;

import java.io.IOException;

public class ShouldBeNumberException extends IOException {

    public ShouldBeNumberException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
