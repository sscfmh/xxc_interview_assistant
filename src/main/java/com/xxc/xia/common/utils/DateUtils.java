package com.xxc.xia.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

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

    public static String ym(Date date) {
        if (date == null)
            date = new Date();
        return DateFormatUtils.format(date, "yyyyMM");
    }

    public static String d(Date date) {
        if (date == null)
            date = new Date();
        return DateFormatUtils.format(date, "dd");
    }
}
