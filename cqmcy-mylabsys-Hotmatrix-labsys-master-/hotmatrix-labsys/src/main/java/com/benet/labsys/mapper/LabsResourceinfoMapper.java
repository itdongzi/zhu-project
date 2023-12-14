package com.benet.labsys.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsResourceinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 教学资源信息Mapper接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Mapper
public interface LabsResourceinfoMapper 
{
    /**
     * 查询所有教学资源信息列表
     *
     * @param appCode 应用编号
     * @return 教学资源信息集合
     */
    public List<LabsResourceinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询教学资源信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 教学资源信息集合
     */
    public List<LabsResourceinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询教学资源信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 教学资源信息集合
     */
    public List<LabsResourceinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询教学资源信息
     *
     * @param appCode 应用编号
     * @param no 教学资源信息ID
     * @return 教学资源信息
     */
    public LabsResourceinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询教学资源信息名称
     *
     * @param appCode 应用编号
     * @param no 教学资源信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询教学资源信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增教学资源信息
     *
     * @param info 教学资源信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") LabsResourceinfo info);

    /**
     * 更新教学资源信息
     *
     * @param info 教学资源信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") LabsResourceinfo info);

    /**
     * 硬删除教学资源信息
     *
     * @param appCode 应用编号
     * @param no 教学资源信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除教学资源信息
     *
     * @param appCode 应用编号
     * @param nos 教学资源信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除教学资源信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除教学资源信息
     *
     * @param appCode 应用编号
     * @param no 教学资源信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除教学资源信息
     *
     * @param appCode 应用编号
     * @param nos 教学资源信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除教学资源信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
