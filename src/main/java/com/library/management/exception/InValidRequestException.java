package com.library.management.exception;

public class InValidRequestException extends RuntimeException {
    public InValidRequestException() {
        super("InValid Request");
    }

    public InValidRequestException(String message) {
        super(message);
    }
}
