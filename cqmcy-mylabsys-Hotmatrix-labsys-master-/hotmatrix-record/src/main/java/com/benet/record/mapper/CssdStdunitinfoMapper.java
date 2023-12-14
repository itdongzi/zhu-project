package com.benet.record.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.record.domain.CssdStdunitinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 单位信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Mapper
public interface CssdStdunitinfoMapper 
{
    /**
     * 查询所有单位信息列表
     *
     * @param appCode 应用编号
     * @return 单位信息集合
     */
    public List<CssdStdunitinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询单位信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 单位信息集合
     */
    public List<CssdStdunitinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询单位信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 单位信息集合
     */
    public List<CssdStdunitinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询单位信息
     *
     * @param appCode 应用编号
     * @param no 单位信息ID
     * @return 单位信息
     */
    public CssdStdunitinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询单位信息名称
     *
     * @param appCode 应用编号
     * @param no 单位信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询单位信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增单位信息
     *
     * @param info 单位信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CssdStdunitinfo info);

    /**
     * 更新单位信息
     *
     * @param info 单位信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CssdStdunitinfo info);

    /**
     * 硬删除单位信息
     *
     * @param appCode 应用编号
     * @param no 单位信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除单位信息
     *
     * @param appCode 应用编号
     * @param nos 单位信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除单位信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除单位信息
     *
     * @param appCode 应用编号
     * @param no 单位信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除单位信息
     *
     * @param appCode 应用编号
     * @param nos 单位信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除单位信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
