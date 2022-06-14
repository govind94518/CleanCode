package com.epam.engx.cleancode.errorhandling.task1;

public class TechnicalErrorException extends RuntimeException {
    String message;

    public TechnicalErrorException() {

    }

    public TechnicalErrorException(String s) {
        super();
        this.message = s;

    }
}
