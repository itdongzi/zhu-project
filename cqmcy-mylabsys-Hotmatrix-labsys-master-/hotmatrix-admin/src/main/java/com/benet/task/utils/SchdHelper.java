package com.benet.task.utils;

import com.benet.common.constant.QutzConstants;
import com.benet.common.exception.job.TaskException;
import com.benet.task.domain.SysTaskinfo;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

/**
 * 定时任务工具类
 * 
 * @author yoxking
 *
 */
public class SchdHelper
{
    /**
     * 得到quartz任务类
     *
     * @param taskInfo 执行计划
     * @return 具体执行任务类
     */
    private static Class<? extends Job> getQuartzJobClass(SysTaskinfo taskInfo)
    {
        boolean isConcurrent = "0".equals(taskInfo.getConcurrent());
        return isConcurrent ? ConcurrentJob.class : DoncurrentJob.class;
    }

    /**
     * 构建任务触发对象
     */
    public static TriggerKey getTriggerKey(String jobId, String jobGroup)
    {
        return TriggerKey.triggerKey(QutzConstants.TASK_CLASS_NAME + jobId, jobGroup);
    }

    /**
     * 构建任务键对象
     */
    public static JobKey getJobKey(String jobId, String jobGroup)
    {
        return JobKey.jobKey(QutzConstants.TASK_CLASS_NAME + jobId, jobGroup);
    }

    /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, SysTaskinfo taskInfo) throws SchedulerException, TaskException
    {
        Class<? extends Job> jobClass = getQuartzJobClass(taskInfo);
        // 构建job信息
        String jobId = taskInfo.getTaskNo();
        String jobGroup = taskInfo.getTaskGroup();
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(getJobKey(jobId, jobGroup)).build();

        // 表达式调度构建器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(taskInfo.getTaskExpress());
        cronScheduleBuilder = handleCronScheduleMisfirePolicy(taskInfo, cronScheduleBuilder);

        // 按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(jobId, jobGroup))
                .withSchedule(cronScheduleBuilder).build();

        // 放入参数，运行时的方法可以获取
        jobDetail.getJobDataMap().put(QutzConstants.TASK_PROPERTIES, taskInfo);

        // 判断是否存在
        if (scheduler.checkExists(getJobKey(jobId, jobGroup)))
        {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(getJobKey(jobId, jobGroup));
        }

        scheduler.scheduleJob(jobDetail, trigger);

        // 暂停任务
        if (taskInfo.getTaskStatus().equals(QutzConstants.Status.PAUSE.getValue()))
        {
            scheduler.pauseJob(SchdHelper.getJobKey(jobId, jobGroup));
        }
    }

    /**
     * 设置定时任务策略
     */
    public static CronScheduleBuilder handleCronScheduleMisfirePolicy(SysTaskinfo taskInfo, CronScheduleBuilder cb)
            throws TaskException
    {
        switch (taskInfo.getErrorsPolicy())
        {
            case QutzConstants.MISFIRE_DEFAULT:
                return cb;
            case QutzConstants.MISFIRE_IGNORE_MISFIRES:
                return cb.withMisfireHandlingInstructionIgnoreMisfires();
            case QutzConstants.MISFIRE_FIRE_AND_PROCEED:
                return cb.withMisfireHandlingInstructionFireAndProceed();
            case QutzConstants.MISFIRE_DO_NOTHING:
                return cb.withMisfireHandlingInstructionDoNothing();
            default:
                throw new TaskException("The task misfire policy '" + taskInfo.getErrorsPolicy()
                        + "' cannot be used in cron schedule tasks", TaskException.Code.CONFIG_ERROR);
        }
    }
}