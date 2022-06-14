package com.epam.engx.cleancode.errorhandling.task1;

public class WrongOrderAmountException extends RuntimeException {
    String message;

    public WrongOrderAmountException() {
    }

    public WrongOrderAmountException(String s) {
        super();
        this.message = s;
    }
}
