package com.epam.engx.cleancode.functions.task5;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public Date updateCalenderForNextDay(Date date) {
        Calendar calendar = getCalendarForMidnight(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public Date updateCalenderForPriorDay(Date date) {
        Calendar calendar = getCalendarForMidnight(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }
    private Calendar getCalendarForMidnight(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        resetTimeToMidnight(calendar);
        return calendar;
    }

    private void resetTimeToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

}
