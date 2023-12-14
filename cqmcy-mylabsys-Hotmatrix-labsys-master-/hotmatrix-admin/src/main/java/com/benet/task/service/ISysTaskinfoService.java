package com.benet.task.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.task.domain.SysTaskinfo;

/**
 * 定时任务调度Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysTaskinfoService 
{
    /**
     * 查询所有定时任务调度列表
     *
     * @param appCode 应用编号
     * @return 定时任务调度集合
     */
    public List<SysTaskinfo> getAllRecords(String appCode);

    /**
     * 按分类查询定时任务调度列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 定时任务调度集合
     */
    public List<SysTaskinfo> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询定时任务调度列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 定时任务调度集合
     */
    public List<SysTaskinfo> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询定时任务调度列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 定时任务调度集合
     */
    public List<SysTaskinfo> getRecordsByPaging(String appCode,int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询定时任务调度
     *
     * @param appCode 应用编号
     * @param no 定时任务调度ID
     * @return 定时任务调度
     */
    public SysTaskinfo getRecordByNo(String appCode,String no);

    /**
     * 查询定时任务调度名称
     *
     * @param appCode 应用编号
     * @param no 定时任务调度ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询定时任务调度计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增定时任务调度
     *
     * @param appCode 应用编号
     * @param info 定时任务调度
     * @return 结果
     */
    public int AddNewRecord(String appCode,SysTaskinfo info);

    /**
     * 更新定时任务调度
     *
     * @param appCode 应用编号
     * @param info 定时任务调度
     * @return 结果
     */
    public int UpdateRecord(String appCode,SysTaskinfo info);

    /**
     * 硬删除定时任务调度
     *
     * @param appCode 应用编号
     * @param no 定时任务调度ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除定时任务调度
     *
     * @param appCode 应用编号
     * @param nos 定时任务调度IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除定时任务调度
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除定时任务调度
     *
     * @param appCode 应用编号
     * @param no 定时任务调度ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除定时任务调度
     *
     * @param appCode 应用编号
     * @param nos 定时任务调度IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除定时任务调度
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);


    /**
     * 立即运行任务
     *
     * @param taskNo 调度任务编号
     * @return 结果
     */
    public int start(String taskNo);

    /**
     * 暂停任务
     *
     * @param taskNo 调度任务编号
     * @return 结果
     */
    public int pause(String taskNo) ;

    /**
     * 恢复任务
     *
     * @param taskNo 调度任务编号
     * @return 结果
     */
    public int resume(String taskNo);

    /**
     * 删除任务
     *
     * @param taskNo 调度任务编号
     * @return 结果
     */
    public int delete(String taskNo);

    /**
     * 任务调度状态修改
     *
     * @param taskNo 调度信息编号
     * @param status 运行状态
     * @return 结果
     */
    public int changeStatus(String taskNo,String status);


    /**
     * 校验cron表达式是否有效
     *
     * @param expression 表达式
     * @return 结果
     */
    public boolean checkExpression(String expression);
}
