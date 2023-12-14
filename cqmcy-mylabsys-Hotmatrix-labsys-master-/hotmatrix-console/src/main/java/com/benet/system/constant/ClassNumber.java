/*
 * Chsi
 * Created on 2023-02-15
 */
package com.benet.system.constant;

/**
 * @author zhangwd <a href="mailto:mail_zwd@163.com.cn">zhangwd</a>
 * @version $Id$
 */
public enum ClassNumber {
    First("第1-2节"),
    Second("第3-4节"),
    Third("第5-6节"),
    Fourth("第78节"),
    Fifth("第9-10节"),
    Sixth("第11-12节");

    private String description;

    ClassNumber(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static ClassNumber fromDescription(String description) {
        for (ClassNumber weekday : ClassNumber.values()) {
            if (weekday.getDescription().equals(description)) {
                return weekday;
            }
        }
        throw new IllegalArgumentException("Invalid weekday description: " + description);
    }
}