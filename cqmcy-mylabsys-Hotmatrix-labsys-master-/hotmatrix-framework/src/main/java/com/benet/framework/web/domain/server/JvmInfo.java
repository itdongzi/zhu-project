package com.benet.framework.web.domain.server;

import com.benet.common.utils.data.ArithmetUtils;
import com.benet.common.utils.date.DateTimeUtils;

import java.lang.management.ManagementFactory;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * JVM相关信息
 * 
 * @author yoxking
 */
public class JvmInfo
{
    /**
     * 当前JVM占用的内存总数(M)
     */
    private double total;

    /**
     * JVM最大可用内存总数(M)
     */
    private double max;

    /**
     * JVM空闲内存(M)
     */
    private double free;

    /**
     * JDK版本
     */
    private String version;

    /**
     * JDK路径
     */
    private String home;

    public double getTotal()
    {
        return ArithmetUtils.div(total, (1024 * 1024), 2);
    }

    public void setTotal(double total)
    {
        this.total = total;
    }

    public double getMax()
    {
        return ArithmetUtils.div(max, (1024 * 1024), 2);
    }

    public void setMax(double max)
    {
        this.max = max;
    }

    public double getFree()
    {
        return ArithmetUtils.div(free, (1024 * 1024), 2);
    }

    public void setFree(double free)
    {
        this.free = free;
    }

    public double getUsed()
    {
        return ArithmetUtils.div(total - free, (1024 * 1024), 2);
    }

    public double getUsage()
    {
        return ArithmetUtils.mul(ArithmetUtils.div(total - free, total, 4), 100);
    }

    /**
     * 获取JDK名称
     */
    public String getName()
    {
        return ManagementFactory.getRuntimeMXBean().getVmName();
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getHome()
    {
        return home;
    }

    public void setHome(String home)
    {
        this.home = home;
    }

    /**
     * JDK启动时间
     */
    public String getStartTime()
    {
        return DateTimeUtils.getServerStartDate().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    /**
     * JDK运行时间(单位：天)
     */
    public String getRunTime()
    {
        return DateTimeUtils.between(DateTimeUtils.getServerStartDate(), DateTimeUtils.getNowDate(), ChronoUnit.DAYS)+"天";
    }
}
