package com.epam.engx.cleancode.dry.task1;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InterestCalculator {

    private static final int SENIOR_ELIGIBLE_AGE = 60;
    private static final double INTEREST_PERCENT = 4.5d;
    private static final double SENIOR_INTEREST_PERCENT = 5.5d;
    private static final int BONUS_AGE = 13;
    private static final int LEAP_YEAR_SHIFT = 1;


    public BigDecimal calculateInterest(AccountDetails accountDetails) {
        return isAccountStartedAfterBonusAge(accountDetails) ? getInterestByAge(accountDetails) : BigDecimal.ZERO;
    }

    private boolean isAccountStartedAfterBonusAge(AccountDetails accountDetails) {
        int ageDuringAccountOpening = getDurationBetweenDatesInYears(accountDetails.getDateOfBirth() , accountDetails.getStartDate());
        return ageDuringAccountOpening > BONUS_AGE;
    }

    private int getDurationBetweenDatesInYears(Date from, Date to) {
        Calendar startCalendar = convertDateToCalendar(from);
        Calendar endCalendar = convertDateToCalendar(to);

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        if (isLeapYear(startCalendar, endCalendar))
            return diffYear - 1;
        return diffYear;
    }

    private boolean isLeapYear(Calendar startCalendar, Calendar endCalendar) {
        return endCalendar.get(Calendar.DAY_OF_YEAR) + LEAP_YEAR_SHIFT < startCalendar.get(Calendar.DAY_OF_YEAR);
    }

    private Calendar convertDateToCalendar(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    private BigDecimal  getInterestByAge(AccountDetails accountDetails) {
        int age= getDurationBetweenDatesInYears(accountDetails.getBirth(),new Date());
        return SENIOR_ELIGIBLE_AGE <= age ? BigDecimal.valueOf(getInterest(accountDetails, SENIOR_INTEREST_PERCENT))
                : BigDecimal.valueOf(getInterest(accountDetails, INTEREST_PERCENT));
    }

    private double getInterest(final AccountDetails accountDetails, final double annualInterestRate) {

        return accountDetails.getBalance().doubleValue()
                * getDurationBetweenDatesInYears(accountDetails.getStartDate(), new Date()) * annualInterestRate / 100;
    }
}
