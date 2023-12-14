/*
 * Chsi
 * Created on 2023-02-15
 */
package com.benet.system.constant;

/**
 * @author zhangwd <a href="mailto:mail_zwd@163.com.cn">zhangwd</a>
 * @version $Id$
 */
public enum OrderCheckStatus {
    UNCHECK("0","未审核"),
    PASS("1","审核通过"),
    UNPASS("2","审核不通过");

    private String code;
    private String description;

    OrderCheckStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}