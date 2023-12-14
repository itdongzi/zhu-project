package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysOperatelog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 操作日志记录Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysOperatelogMapper
{
    /**
     * 查询所有操作日志记录列表
     *
     * @param appCode 应用编号
     * @return 操作日志记录集合
     */
    public List<SysOperatelog> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询操作日志记录列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 操作日志记录集合
     */
    public List<SysOperatelog> getRecordsByClassNo(@Param("appCode") String appCode, @Param("classNo") String classNo);

    /**
     * 分页查询操作日志记录列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 操作日志记录集合
     */
    public List<SysOperatelog> getRecordsByPaging(@Param("appCode") String appCode, @Param("model") PagingModel model);

    /**
     * 查询操作日志记录
     *
     * @param appCode 应用编号
     * @param no 操作日志记录ID
     * @return 操作日志记录
     */
    public SysOperatelog getRecordByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询操作日志记录名称
     *
     * @param appCode 应用编号
     * @param no 操作日志记录ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询操作日志记录计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增操作日志记录
     *
     * @param info 操作日志记录
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysOperatelog info);

    /**
     * 更新操作日志记录
     *
     * @param info 操作日志记录
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysOperatelog info);

    /**
     * 硬删除操作日志记录
     *
     * @param appCode 应用编号
     * @param no 操作日志记录ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除操作日志记录
     *
     * @param appCode 应用编号
     * @param nos 操作日志记录IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除操作日志记录
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除操作日志记录
     *
     * @param appCode 应用编号
     * @param no 操作日志记录ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除操作日志记录
     *
     * @param appCode 应用编号
     * @param nos 操作日志记录IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除操作日志记录
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
