package com.benet.task.utils;

import com.benet.task.domain.SysTaskinfo;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（禁止并发执行）
 * 
 * @author yoxking
 *
 */
@DisallowConcurrentExecution
public class DoncurrentJob extends AbstractJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysTaskinfo taskInfo) throws Exception
    {
        TaskHelper.invokeMethod(taskInfo);
    }
}
