package com.epam.engx.cleancode.functions.task3;

public class InvalidCredentialsException extends RuntimeException {
    String message;

    public InvalidCredentialsException() {
    }

    public InvalidCredentialsException(String s) {
        super();
        this.message = s;

    }

    @Override
    public String getMessage() {
        return message;
    }

}
