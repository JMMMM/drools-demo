package com.study.droolscore.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * com.study.droolscore.utils
 *
 * @author jimmy
 * @date 2019-07-08
 */
public final class DateUtils {
    public static boolean checkWeekDay(Date date, int weekDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == weekDay;
    }
}
