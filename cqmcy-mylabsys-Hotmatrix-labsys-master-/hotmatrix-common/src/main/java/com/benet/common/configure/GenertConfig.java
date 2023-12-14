package com.benet.common.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取代码生成相关配置
 *
 * @author yoxking
 */
@Component
@ConfigurationProperties(prefix = "genert")
public class GenertConfig {
    /** 作者 */
    public static String author;

    /** 生成包路径 */
    public static String packageName;

    /** 自动去除表前缀，默认是false */
    public static boolean autoRemovePre;

    /** 表前缀(类名不会包含表前缀) */
    public static String tablePrefix;

    public static String getAuthor()
    {
        return author;
    }

    //@Value("${author}")
    public void setAuthor(String author)
    {
        GenertConfig.author = author;
    }

    public static String getPackageName()
    {
        return packageName;
    }

    //@Value("${packageName}")
    public void setPackageName(String packageName)
    {
        GenertConfig.packageName = packageName;
    }

    public static boolean getAutoRemovePre()
    {
        return autoRemovePre;
    }

    //@Value("${autoRemovePre}")
    public void setAutoRemovePre(boolean autoRemovePre)
    {
        GenertConfig.autoRemovePre = autoRemovePre;
    }

    public static String getTablePrefix()
    {
        return tablePrefix;
    }

    //@Value("${tablePrefix}")
    public void setTablePrefix(String tablePrefix)
    {
        GenertConfig.tablePrefix = tablePrefix;
    }
}
