package com.benet.task.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.task.domain.SysTaskelog;

/**
 * 定时任务调度日志Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysTaskelogService
{
    /**
     * 查询所有定时任务调度日志列表
     *
     * @param appCode 应用编号
     * @return 定时任务调度日志集合
     */
    public List<SysTaskelog> getAllRecords(String appCode);

    /**
     * 按分类查询定时任务调度日志列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 定时任务调度日志集合
     */
    public List<SysTaskelog> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询定时任务调度日志列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 定时任务调度日志集合
     */
    public List<SysTaskelog> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询定时任务调度日志列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 定时任务调度日志集合
     */
    public List<SysTaskelog> getRecordsByPaging(String appCode,int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询定时任务调度日志
     *
     * @param appCode 应用编号
     * @param no 定时任务调度日志ID
     * @return 定时任务调度日志
     */
    public SysTaskelog getRecordByNo(String appCode,String no);

    /**
     * 查询定时任务调度日志名称
     *
     * @param appCode 应用编号
     * @param no 定时任务调度日志ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询定时任务调度日志计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增定时任务调度日志
     *
     * @param appCode 应用编号
     * @param info 定时任务调度日志
     * @return 结果
     */
    public int AddNewRecord(String appCode,SysTaskelog info);

    /**
     * 更新定时任务调度日志
     *
     * @param appCode 应用编号
     * @param info 定时任务调度日志
     * @return 结果
     */
    public int UpdateRecord(String appCode,SysTaskelog info);

    /**
     * 硬删除定时任务调度日志
     *
     * @param appCode 应用编号
     * @param no 定时任务调度日志ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除定时任务调度日志
     *
     * @param appCode 应用编号
     * @param nos 定时任务调度日志IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除定时任务调度日志
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除定时任务调度日志
     *
     * @param appCode 应用编号
     * @param no 定时任务调度日志ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除定时任务调度日志
     *
     * @param appCode 应用编号
     * @param nos 定时任务调度日志IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除定时任务调度日志
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
