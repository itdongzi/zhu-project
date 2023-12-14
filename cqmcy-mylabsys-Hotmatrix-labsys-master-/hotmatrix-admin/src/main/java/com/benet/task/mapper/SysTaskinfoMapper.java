package com.benet.task.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.task.domain.SysTaskinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 定时任务调度Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysTaskinfoMapper 
{
    /**
     * 查询所有定时任务调度列表
     *
     * @param appCode 应用编号
     * @return 定时任务调度集合
     */
    public List<SysTaskinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询定时任务调度列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 定时任务调度集合
     */
    public List<SysTaskinfo> getRecordsByClassNo(@Param("appCode") String appCode, @Param("classNo") String classNo);

    /**
     * 分页查询定时任务调度列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 定时任务调度集合
     */
    public List<SysTaskinfo> getRecordsByPaging(@Param("appCode") String appCode, @Param("model") PagingModel model);

    /**
     * 查询定时任务调度
     *
     * @param appCode 应用编号
     * @param no 定时任务调度ID
     * @return 定时任务调度
     */
    public SysTaskinfo getRecordByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询定时任务调度名称
     *
     * @param appCode 应用编号
     * @param no 定时任务调度ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询定时任务调度计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 新增定时任务调度
     *
     * @param info 定时任务调度
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysTaskinfo info);

    /**
     * 更新定时任务调度
     *
     * @param info 定时任务调度
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysTaskinfo info);

    /**
     * 硬删除定时任务调度
     *
     * @param appCode 应用编号
     * @param no 定时任务调度ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量硬删除定时任务调度
     *
     * @param appCode 应用编号
     * @param nos 定时任务调度IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件硬删除定时任务调度
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 软删除定时任务调度
     *
     * @param appCode 应用编号
     * @param no 定时任务调度ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量软删除定时任务调度
     *
     * @param appCode 应用编号
     * @param nos 定时任务调度IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件软删除定时任务调度
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

}
