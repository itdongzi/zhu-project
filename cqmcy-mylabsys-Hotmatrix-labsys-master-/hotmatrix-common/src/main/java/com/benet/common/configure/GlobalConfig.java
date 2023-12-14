package com.benet.common.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 全局配置类
 *
 * @author yoxking
 */
@Component
@ConfigurationProperties(prefix = "hotmatrix")
@Data
public class GlobalConfig
{
    /** 项目名称 */
    private static String name;

    /** 版本 */
    private static String version;

    /** 版权年份 */
    private static String copyrightYear;

    /** 上传路径 */
    private static String profile;

    /** 获取地址开关 */
    private static boolean addressEnabled;

    /** 应用编号 */
    private static String appCode;

    /** 分支编号 */
    private static String branchNo;

    /** 用户认证模式 */
    private static String userDetail;

    public static String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        GlobalConfig.name = name;
    }

    public static String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        GlobalConfig.version = version;
    }

    public static String getCopyrightYear()
    {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear)
    {
        GlobalConfig.copyrightYear = copyrightYear;
    }

    public static String getProfile()
    {
        return profile;
    }

    public void setProfile(String profile)
    {
        GlobalConfig.profile = profile;
    }

    public static boolean isAddressEnabled()
    {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled)
    {
        GlobalConfig.addressEnabled = addressEnabled;
    }

    public static String getAppCode()
    {
        return appCode;
    }

    public void setAppCode(String appCode)
    {
        GlobalConfig.appCode = appCode;
    }

    public static String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        GlobalConfig.branchNo = branchNo;
    }

    public static String getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(String userDetail) {
        GlobalConfig.userDetail = userDetail;
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath()
    {
        return getProfile() + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
       // return getProfile() + "/download/";
        return getProfile() + "/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath()
    {
        return getProfile() + "/profile";
    }
}