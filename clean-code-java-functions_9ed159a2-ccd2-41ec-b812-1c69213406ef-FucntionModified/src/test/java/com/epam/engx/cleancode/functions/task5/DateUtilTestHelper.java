package com.epam.engx.cleancode.functions.task5;

import java.util.Date;

public class DateUtilTestHelper {

    public static Date getDirectlyIncrementedDate(DateUtil dateUtil, Date date) {
        return dateUtil.updateCalenderForNextDay(date);
    }

    public static Date getInverseIncrementedDate(DateUtil dateUtil, Date date) {
        return dateUtil.updateCalenderForPriorDay(date);
    }
}
