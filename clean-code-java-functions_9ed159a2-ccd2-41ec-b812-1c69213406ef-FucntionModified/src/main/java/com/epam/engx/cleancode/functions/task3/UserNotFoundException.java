package com.epam.engx.cleancode.functions.task3;

public class UserNotFoundException extends  RuntimeException{
    String message;

    public UserNotFoundException(String s) {
        super();
        this.message = s;

    }

    @Override
    public String getMessage() {
        return message;
    }


}
