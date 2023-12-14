package com.benet.common.enums;

/**
 * 用户状态
 * 
 * @author yoxking
 */
public enum UserStatus
{
    DELETED("0", "删除"),OK("1", "正常"), DISABLE("2", "停用");

    private final String code;
    private final String info;

    UserStatus(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
