package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.OK;

public class RegisterAccountAction {
    private static final int PASSWORD_MIN_LENGTH = 8;
    private static final int NAME_MIN_LENGTH = 5;
    private PasswordChecker passwordChecker;
    private AccountManager accountManager;


    public void register(Account account) {
        validateCredentials(account);
        accountManager.createNewAccount(createAccountInformation(account));

    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {

        this.passwordChecker = passwordChecker;
    }

    private void validateCredentials(Account account) {
        validateNameLength(account.getName());
        validatePassword(account.getPassword());
    }

    private void validateNameLength(String name) {
        if (name.length() <= NAME_MIN_LENGTH) {
            throw new WrongAccountNameException();
        }
    }

    private void validatePassword(String password) {
        validatePasswordLength(password);
        validatePasswordChecker(password);
    }

    private void validatePasswordLength(String password) {
        if (password.length() <= PASSWORD_MIN_LENGTH)
            throw new TooShortPasswordException();
    }

    private void validatePasswordChecker(String password) {
        if (passwordChecker.validate(password) != OK) {
            throw new WrongPasswordException();
        }
    }

    private Account createAccountInformation(Account account) {
        account.setCreatedDate(new Date());
        addAddresses(account);
        return account;

    }

    private void addAddresses(Account account) {
        List<Address> addresses = new ArrayList<Address>();
        addresses.add(account.getHomeAddress());
        addresses.add(account.getWorkAddress());
        addresses.add(account.getAdditionalAddress());
        account.setAddresses(addresses);
    }

}
