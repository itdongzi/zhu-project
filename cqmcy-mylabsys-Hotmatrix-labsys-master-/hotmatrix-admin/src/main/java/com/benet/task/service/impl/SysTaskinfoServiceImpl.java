package com.benet.task.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.constant.QutzConstants;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import com.benet.task.domain.SysTaskinfo;
import com.benet.task.mapper.SysTaskinfoMapper;
import com.benet.task.service.ISysTaskinfoService;
import com.benet.task.utils.CronHelper;
import com.benet.task.utils.SchdHelper;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * 定时任务调度Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysTaskinfoServiceImpl implements ISysTaskinfoService
{
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SysTaskinfoMapper sysTaskinfoMapper;

    /**
     * 项目启动时，初始化定时器
     * 主要是防止手动修改数据库导致未同步到定时任务处理（注：不能手动修改数据库ID和任务组名，否则会导致脏数据）
     */
    @PostConstruct
    public void init()
    {
        List<SysTaskinfo> taskList = sysTaskinfoMapper.getAllRecords("6000000551850671");
        for (SysTaskinfo taskInfo : taskList) {
            updateScheduler(taskInfo, taskInfo.getTaskGroup());
        }
    }

    /**
     * 查询所有定时任务调度列表
     *
     * @param appCode 应用编号
     * @return 定时任务调度集合
     */
    @Override
    public List<SysTaskinfo> getAllRecords(String appCode) {
        return sysTaskinfoMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询定时任务调度列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 定时任务调度集合
     */
    @Override
    public List<SysTaskinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysTaskinfoMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询定时任务调度列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 定时任务调度集合
     */
    @Override
    public List<SysTaskinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysTaskinfoMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询定时任务调度列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 定时任务调度集合
     */
    @Override
    public List<SysTaskinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

        PagingModel model = new PagingModel();
        model.setPageIndex((pageIndex-1) * pageSize);
        model.setPageSize(pageSize);
        model.setCondition(condition);
        if (StringUtils.isEmpty(orderField)) {
            model.setOrderField("id");
        } else {
            model.setOrderField(orderField);
        }
        if (StringUtils.isEmpty(orderType)) {
            model.setOrderType("Asc");
        } else {
            model.setOrderType(orderType);
        }
        return sysTaskinfoMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询定时任务调度
     *
     * @param appCode 应用编号
     * @param no 定时任务调度ID
     * @return 定时任务调度
     */
    @Override
    public SysTaskinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTaskinfoMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询定时任务调度名称
     *
     * @param appCode 应用编号
     * @param no 定时任务调度ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTaskinfoMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询定时任务调度计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysTaskinfoMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增定时任务调度
     *
     * @param appCode 应用编号
     * @param info 定时任务调度
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysTaskinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysTaskinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新定时任务调度
     *
     * @param appCode 应用编号
     * @param info 定时任务调度
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysTaskinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysTaskinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除定时任务调度
     *
     * @param appCode 应用编号
     * @param no 定时任务调度ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTaskinfoMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除定时任务调度
     *
     * @param appCode 应用编号
     * @param nos 定时任务调度IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysTaskinfoMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除定时任务调度
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysTaskinfoMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除定时任务调度
     *
     * @param appCode 应用编号
     * @param no 定时任务调度ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTaskinfoMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除定时任务调度
     *
     * @param appCode 应用编号
     * @param nos 定时任务调度IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysTaskinfoMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除定时任务调度
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysTaskinfoMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }


    /**
     * 运行任务
     *
     * @param taskNo 调度任务编号
     * @return 结果
     */
    @Override
    @Transactional
    public int start(String taskNo) {

        SysTaskinfo taskInfo = sysTaskinfoMapper.getRecordByNo(GlobalConfig.getAppCode(), taskNo);
        String taskGroup = taskInfo.getTaskGroup();
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(QutzConstants.TASK_PROPERTIES, taskInfo);
        try {
            scheduler.triggerJob(SchdHelper.getJobKey(taskNo, taskGroup), dataMap);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    /**
     * 暂停任务
     *
     * @param taskNo 调度任务编号
     * @return 结果
     */
    @Override
    @Transactional
    public int pause(String taskNo) {

        try {
            SysTaskinfo taskInfo = sysTaskinfoMapper.getRecordByNo(GlobalConfig.getAppCode(), taskNo);
            String taskGroup = taskInfo.getTaskGroup();
            taskInfo.setTaskStatus(QutzConstants.Status.PAUSE.getValue());
            int rows = sysTaskinfoMapper.UpdateRecord(taskInfo);
            if (rows > 0) {
                scheduler.pauseJob(SchdHelper.getJobKey(taskNo, taskGroup));
            }
            return rows;
        }
        catch (Exception e){
            return 0;
        }
    }

    /**
     * 恢复任务
     *
     * @param taskNo 调度任务编号
     * @return 结果
     */
    @Override
    @Transactional
    public int resume(String taskNo){

        try {
            SysTaskinfo taskInfo = sysTaskinfoMapper.getRecordByNo(GlobalConfig.getAppCode(), taskNo);
            String taskGroup = taskInfo.getTaskGroup();
            taskInfo.setTaskStatus(QutzConstants.Status.NORMAL.getValue());
            int rows = sysTaskinfoMapper.UpdateRecord(taskInfo);
            if (rows > 0) {
                scheduler.resumeJob(SchdHelper.getJobKey(taskNo, taskGroup));
            }
            return rows;
        }
        catch (Exception e){
            return 0;
        }
    }


    /**
     * 删除任务后，所对应的trigger也将被删除
     *
     * @param taskNo 调度任务编号
     */
    @Override
    @Transactional
    public int delete(String taskNo)
    {
        try {
            SysTaskinfo taskInfo = sysTaskinfoMapper.getRecordByNo(GlobalConfig.getAppCode(), taskNo);
            String taskGroup = taskInfo.getTaskGroup();

            scheduler.deleteJob(SchdHelper.getJobKey(taskNo, taskGroup));

            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    /**
     * 任务调度运行状态修改
     *
     * @param taskNo 调度信息编号
     * @param status 状态
     * @return 结果
     */
    @Override
    @Transactional
    public int changeStatus(String taskNo,String status){

        try {
            int rows = 0;
            if (QutzConstants.Status.NORMAL.getValue().equals(status)) {
                rows = resume(taskNo);
            } else if (QutzConstants.Status.PAUSE.getValue().equals(status)) {
                rows = pause(taskNo);
            }
            return rows;
        }
        catch (Exception e){
            return 0;
        }
    }

    /**
     * 更新任务
     *
     * @param taskInfo 任务对象
     * @param taskGroup 任务组名
     */
    public void updateScheduler(SysTaskinfo taskInfo, String taskGroup)
    {
        try {
            String taskNo = taskInfo.getTaskNo();
            // 判断是否存在
            JobKey jobKey = SchdHelper.getJobKey(taskNo, taskGroup);
            if (scheduler.checkExists(jobKey)) {
                // 防止创建时存在数据问题 先移除，然后在执行创建操作
                scheduler.deleteJob(jobKey);
            }
            SchdHelper.createScheduleJob(scheduler, taskInfo);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 校验cron表达式是否有效
     *
     * @param expression 表达式
     * @return 结果
     */
    @Override
    public boolean checkExpression(String expression){
        return CronHelper.isValid(expression);
    }
}
