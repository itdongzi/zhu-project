package com.benet.common.utils.date;

import java.lang.management.ManagementFactory;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateTimeUtils {

    /**
     * 时间格式
     */
    private static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    private static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String YYYY_MM = "yyyy-MM";
    private static final String YYYY = "yyyy";

    /**
     * Date转换为LocalDateTime
     *
     * @param date 日期
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param dateTime 日期时间
     * @return Date
     */
    public static Date toDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * String 类型转成 LocalDateTime ,必须为完整时间,如：2020-01-20 00:00:00
     *
     * @param timeStr 时间字符串
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime parse(String timeStr) {
        return parse(timeStr, YYYY_MM_DD_HH_MM_SS);
    }


    /**
     * String (2020-01-20 00:00:00)类型转成 LocalDateTime
     *
     * @param timeStr timeStr 时间字符串
     * @param pattern pattern 格式
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime parse(String timeStr, String pattern) {
        if (pattern.equals(YYYY)) {
            timeStr += "-01-01 00:00:00";
        } else if (pattern.equals(YYYY_MM)) {
            timeStr += "-01 00:00:00";
        } else if (pattern.equals(YYYY_MM_DD)) {
            timeStr += " 00:00:00";
        } else if (pattern.equals(YYYY_MM_DD_HH)) {
            timeStr += ":00:00";
        } else if (pattern.equals(YYYY_MM_DD_HH_MM)) {
            timeStr += ":00";
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
        return LocalDateTime.parse(timeStr, dtf);
    }

    /**
     * 获取指定格式的指定时间
     *
     * @param dateTime 日期时间
     * @param pattern  指定格式
     * @return 指定格式的日期时间字符串
     */
    public static String formatTime(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取指定格式的当前时间
     *
     * @param pattern 指定格式
     * @return 指定格式的当前时间日期字符串
     */
    public static String formatNow(String pattern) {
        return formatTime(LocalDateTime.now(), pattern);
    }

    /**
     * 判断dateTime1是否早于dateTime2
     *
     * @param dateTime1 日期时间1
     * @param dateTime2 日期时间2
     * @return 判断结果
     */
    public static boolean isBefore(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return dateTime1.isBefore(dateTime2);
    }

    /**
     * 判断dateTime1与dateTime2是否是同一时间
     *
     * @param dateTime1 日期时间1
     * @param dateTime2 日期时间2
     * @return 判断结果
     */
    public static boolean isEqual(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return dateTime1.isEqual(dateTime2);
    }

    /**
     * 判断dateTime1是否晚于dateTime2
     *
     * @param dateTime1 日期时间1
     * @param dateTime2 日期时间2
     * @return 判断结果
     */
    public static boolean isAfter(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return dateTime1.isAfter(dateTime2);
    }

    /**
     * 获取指定日期的毫秒
     *
     * @param dateTime 日期时间
     * @return 毫秒
     */
    public static Long getMilliByTime(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒
     *
     * @param dateTime 日期时间
     * @return 秒
     */
    public static Long getSecondsByTime(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 日期加上一个数，根据field不同加不同值
     *
     * @param dateTime 日期时间
     * @param number   数值
     * @param field    单位
     * @return 已添加数值后的日期时间
     */
    public static LocalDateTime plus(LocalDateTime dateTime, long number, ChronoUnit field) {
        return dateTime.plus(number, field);
    }

    /**
     * 日期减去一个数，根据field不同减不同值
     *
     * @param dateTime 日期时间
     * @param number   数值
     * @param field    单位
     * @return 已减少数值后的日期时间
     */
    public static LocalDateTime minus(LocalDateTime dateTime, long number, ChronoUnit field) {
        return dateTime.minus(number, field);
    }

    /**
     * 获取两个日期的差
     *
     * @param startDateTime 开始时间
     * @param endDateTime   结束时间
     * @param field         单位
     * @return 两个日期之间的差值
     */
    public static long between(LocalDateTime startDateTime, LocalDateTime endDateTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startDateTime), LocalDate.from(endDateTime));
        if (field == ChronoUnit.YEARS) return period.getYears();
        if (field == ChronoUnit.MONTHS) return period.getYears() * 12 + period.getMonths();
        return field.between(startDateTime, endDateTime);
    }

    /**
     * 获取某天的开始时间，例如：yyyy,MM,dd 00:00
     *
     * @param dateTime 某天的日期时间
     * @return 某天的开始时间
     */
    public static LocalDateTime getDayStart(LocalDateTime dateTime) {
        return dateTime.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    /**
     * 获取某天的结束时间，例如：yyy,MM,dd 23:59:59
     *
     * @param dateTime 某天的日期时间
     * @return 某天的结束时间
     */
    public static LocalDateTime getDayEnd(LocalDateTime dateTime) {
        return dateTime.withHour(23)
                .withMinute(59)
                .withSecond(59);
    }

    /**
     * 获取当前LocalDateTime型日期
     *
     * @return LocalDateTime 当前日期
     */
    public static LocalDateTime getNowDate()
    {
        return LocalDateTime.now();
    }

    /**
     * 获取服务器启动时间
     */
    public static LocalDateTime getServerStartDate()
    {
        long milli = ManagementFactory.getRuntimeMXBean().getStartTime();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(milli), ZoneId.systemDefault());
        return localDateTime;
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static String getDatePath()
    {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
