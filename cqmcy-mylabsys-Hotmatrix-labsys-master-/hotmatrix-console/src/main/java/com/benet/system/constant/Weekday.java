/*
 * Chsi
 * Created on 2023-02-15
 */
package com.benet.system.constant;

/**
 * @author zhangwd <a href="mailto:mail_zwd@163.com.cn">zhangwd</a>
 * @version $Id$
 */
public enum Weekday {
    MONDAY("周一"),
    TUESDAY("周二"),
    WEDNESDAY("周三"),
    THURSDAY("周四"),
    FRIDAY("周五"),
    SATURDAY("周六"),
    SUNDAY("周日");

    private String description;

    Weekday(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Weekday fromDescription(String description) {
        for (Weekday weekday : Weekday.values()) {
            if (weekday.getDescription().equals(description)) {
                return weekday;
            }
        }
        throw new IllegalArgumentException("Invalid weekday description: " + description);
    }
}