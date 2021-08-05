package com.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    /**
     * 返回当前日期
     * @param pattern
     * @return
     */
    public static String nowFormat(String pattern){
        return new SimpleDateFormat(pattern).format(new Date());
    }

    /**
     * 返回具体时间日期
     * @param date
     * @param pattern
     * @return
     */
    public static String nowFormat(String pattern,Date date){
        return new SimpleDateFormat(pattern).format(date);
    }

}
