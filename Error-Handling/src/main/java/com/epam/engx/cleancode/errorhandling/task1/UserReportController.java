package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Model;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.UserDao;

public class UserReportController {
    private static final String USER_TOTAL_MESSAGE = "userTotalMessage";
    private UserDao userDao;
    private UserReportBuilder userReportBuilder;


    public String getUserTotalOrderAmountView(String userId, Model model) {
        try {
            String totalMessage = getUserTotalMessage(userId);
            model.addAttribute(USER_TOTAL_MESSAGE, totalMessage);
        }catch (TechnicalErrorException exp){
            return "technicalError";
        }
        return "userTotal";
    }

    private String getUserTotalMessage(String userId) {
        try {
            Double amount = userReportBuilder.getUserTotalOrderAmount(userId);
            return "User Total: " + amount + "$";
        }catch (UserNotExistException exp) {
            return "WARNING: User ID doesn't exist.";
        } catch (WrongOrderAmountException exp) {
            return "ERROR: Wrong order amount.";
        } catch (NoOrderExistException exp) {
            return "WARNING: User have no submitted orders.";
        }
    }


    public UserReportBuilder getUserReportBuilder() {
        return userReportBuilder;
    }

    public void setUserReportBuilder(UserReportBuilder userReportBuilder) {
        this.userReportBuilder = userReportBuilder;
    }
}
