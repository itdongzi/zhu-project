package com.benet.task.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.benet.common.configure.GlobalConfig;
import com.benet.common.constant.PubConstants;
import com.benet.common.constant.QutzConstants;
import com.benet.common.utils.bean.BeanUtils;
import com.benet.common.utils.date.DateTimeUtils;
import com.benet.common.utils.exception.ExceptionUtils;
import com.benet.common.utils.spring.SpringUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.task.domain.SysTaskinfo;
import com.benet.task.domain.SysTaskelog;
import com.benet.task.service.ISysTaskelogService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象quartz调用
 *
 * @author yoxking
 */
public abstract class AbstractJob implements Job
{
    private static final Logger log = LoggerFactory.getLogger(AbstractJob.class);

    /**
     * 线程本地变量
     */
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        SysTaskinfo taskInfo = new SysTaskinfo();
        BeanUtils.copyBeanProp(taskInfo, context.getMergedJobDataMap().get(QutzConstants.TASK_PROPERTIES));
        try
        {
            before(context, taskInfo);
            if (taskInfo != null)
            {
                doExecute(context, taskInfo);
            }
            after(context, taskInfo, null);
        }
        catch (Exception e)
        {
            log.error("任务执行异常  - ：", e);
            after(context, taskInfo, e);
        }
    }

    /**
     * 执行前
     *
     * @param context 工作执行上下文对象
     * @param taskInfo 系统计划任务
     */
    protected void before(JobExecutionContext context, SysTaskinfo taskInfo)
    {
        threadLocal.set(new Date());
    }

    /**
     * 执行后
     *
     * @param context 工作执行上下文对象
     * @param taskInfo 系统计划任务
     */
    protected void after(JobExecutionContext context, SysTaskinfo taskInfo, Exception e)
    {
        LocalDateTime startTime = DateTimeUtils.toLocalDateTime(threadLocal.get());
        threadLocal.remove();

        final SysTaskelog sysTaskeLog = new SysTaskelog();
        sysTaskeLog.setTaskLogno(UuidUtils.shortUUID());
        sysTaskeLog.setTaskName(taskInfo.getTaskName());
        sysTaskeLog.setTaskGroup(taskInfo.getTaskGroup());
        sysTaskeLog.setInvokeTarget(taskInfo.getInvokeTarget());
        sysTaskeLog.setStartTime(startTime);
        sysTaskeLog.setEnditTime(DateTimeUtils.getNowDate());
        long runMs = DateTimeUtils.between(sysTaskeLog.getStartTime(), sysTaskeLog.getEnditTime(), ChronoUnit.MILLIS);
        sysTaskeLog.setTaskMessage(sysTaskeLog.getTaskName() + " 总共耗时：" + runMs + "毫秒");
        if (e != null)
        {
            sysTaskeLog.setResultStatus(PubConstants.FAIL);
            String errorMsg = StringUtils.substring(ExceptionUtils.getExceptionMessage(e), 0, 2000);
            sysTaskeLog.setErrorsMessage(errorMsg);
        }
        else
        {
            sysTaskeLog.setResultStatus((PubConstants.SUCCESS));
        }
        sysTaskeLog.setCheckState("1");

        // 写入数据库当中
        SpringUtils.getBean(ISysTaskelogService.class).AddNewRecord(GlobalConfig.getAppCode(),sysTaskeLog);
    }

    /**
     * 执行方法，由子类重载
     *
     * @param context 工作执行上下文对象
     * @param taskInfo 系统计划任务
     * @throws Exception 执行过程中的异常
     */
    protected abstract void doExecute(JobExecutionContext context, SysTaskinfo taskInfo) throws Exception;
}
