package com.epam.engx.cleancode.functions.task3;

import com.epam.engx.cleancode.functions.task3.thirdpartyjar.Controller;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.User;

public abstract class UserController implements Controller {

    private UserAuthenticator userAuthenticator;

    public void authenticateUser(String userName, String password) {
        try {
            User user = userAuthenticator.login(userName, password);
            generateSuccessLoginResponse(user.toString());
        } catch (InvalidCredentialsException invalidUser) {
            generateFailLoginResponse();
        }
    }


    public void setUserAuthenticator(UserAuthenticator userAuthenticator) {
        this.userAuthenticator = userAuthenticator;
    }
}
