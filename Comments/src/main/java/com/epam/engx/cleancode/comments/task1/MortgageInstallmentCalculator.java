package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;

public class MortgageInstallmentCalculator {

    private static final int MONTHS_IN_YEAR = 12;

    public static double calculateMonthlyPayment(int principalAmount, int mortgageInYears, double rateOfInterest) {

        validateMonthlyPaymentInput(principalAmount, mortgageInYears, rateOfInterest);

        rateOfInterest = convertRateOfInterestIntoDecimals(rateOfInterest);
        if (rateOfInterest == 0)
            return calculateMonthlyPaymentWithoutInterest(principalAmount, mortgageInYears);

        return calculateMonthlyPaymentWithInterest(principalAmount, mortgageInYears, rateOfInterest);
    }

    static double calculateMonthlyPaymentWithoutInterest(int principalAmount, int mortgageInYears) {
        return principalAmount / convertMortgagePeriodInMonths(mortgageInYears);
    }


    private static void validateMonthlyPaymentInput(int principalAmount, int mortgageInYears, double rateOfInterest) {

        if (isAnyPaymentInputInvalid(principalAmount, mortgageInYears, rateOfInterest)) {
            throw new InvalidInputException("Invalid values are not allowed");
        }

    }

    private static boolean isAnyPaymentInputInvalid(int principalAmount, int mortgageInYears, double rateOfInterest) {

        return (principalAmount < 0 || mortgageInYears <= 0 || rateOfInterest < 0);
    }

    private static double calculateRateOfInterestPerMonth(double rateOfInterest) {
        return rateOfInterest / MONTHS_IN_YEAR;
    }

    private static double convertMortgagePeriodInMonths(int mortgageInYears) {
        return mortgageInYears * MONTHS_IN_YEAR;
    }

    private static double convertRateOfInterestIntoDecimals(double rateOfInterest) {

        return rateOfInterest / 100.0;
    }

    private static double calculateMonthlyPaymentWithInterest(int principalAmount, int mortgageInYears, double rateOfInterest) {

        return (principalAmount * calculateRateOfInterestPerMonth(rateOfInterest))
                / (1 - Math.pow(1 + calculateRateOfInterestPerMonth(rateOfInterest),
                -convertMortgagePeriodInMonths(mortgageInYears)));
    }

}
