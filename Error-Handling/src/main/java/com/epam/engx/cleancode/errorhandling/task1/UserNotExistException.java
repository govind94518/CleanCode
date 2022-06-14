package com.epam.engx.cleancode.errorhandling.task1;

public class UserNotExistException extends RuntimeException {
    String message;

    public UserNotExistException() {

    }

    public UserNotExistException(String s) {
        super();
        this.message = s;

    }

    @Override
    public String getMessage() {
        return message;
    }

}
