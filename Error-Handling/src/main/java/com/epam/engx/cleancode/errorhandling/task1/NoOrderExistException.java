package com.epam.engx.cleancode.errorhandling.task1;

public class NoOrderExistException extends  RuntimeException {
    String message;

    public NoOrderExistException() {

    }

    public NoOrderExistException(String s) {
        super();
        this.message = s;

    }

}
