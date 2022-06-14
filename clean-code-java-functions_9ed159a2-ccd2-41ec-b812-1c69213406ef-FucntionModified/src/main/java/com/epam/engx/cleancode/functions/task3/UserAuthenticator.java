package com.epam.engx.cleancode.functions.task3;

import com.epam.engx.cleancode.functions.task3.thirdpartyjar.SessionManager;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.User;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.UserService;

public abstract class UserAuthenticator implements UserService {
    private SessionManager sessionManager;

    public User login(String userName, String password) {
        User user = getUserByName(userName);
        validateUser(user);
        return loginUser(getUserByName(userName), password);
    }

    private void validateUser(User user) {
        if (user == null)
            throw new UserNotFoundException("User Not exist");
    }

    private User loginUser(User user, String password) {
        if (!this.isPasswordCorrect(user, password)) {
            throw new InvalidCredentialsException();
        }
        sessionManager.setCurrentUser(user);
        return user;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }


}
