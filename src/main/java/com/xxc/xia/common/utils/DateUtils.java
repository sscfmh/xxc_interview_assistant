package com.xxc.xia.common.utils;

import java.util.Date;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/3/16 20:27
 */
public class DateUtils {
    /**
     * 加n天
     */
    public static Date addNDays(Date date, int n) {
        if (date == null)
            date = new Date();
        return new Date(date.getTime() + n * 24 * 60 * 60 * 1000L);
    }
}
