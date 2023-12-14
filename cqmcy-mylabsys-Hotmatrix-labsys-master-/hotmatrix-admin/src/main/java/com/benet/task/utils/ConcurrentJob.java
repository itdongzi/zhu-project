package com.benet.task.utils;

import com.benet.task.domain.SysTaskinfo;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 * 
 * @author yoxking
 *
 */
public class ConcurrentJob extends AbstractJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysTaskinfo taskInfo) throws Exception
    {
        TaskHelper.invokeMethod(taskInfo);
    }
}
