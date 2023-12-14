package com.benet.record.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.record.domain.CssdSupplierinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 供应商信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Mapper
public interface CssdSupplierinfoMapper 
{
    /**
     * 查询所有供应商信息列表
     *
     * @param appCode 应用编号
     * @return 供应商信息集合
     */
    public List<CssdSupplierinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询供应商信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 供应商信息集合
     */
    public List<CssdSupplierinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询供应商信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 供应商信息集合
     */
    public List<CssdSupplierinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询供应商信息
     *
     * @param appCode 应用编号
     * @param no 供应商信息ID
     * @return 供应商信息
     */
    public CssdSupplierinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询供应商信息名称
     *
     * @param appCode 应用编号
     * @param no 供应商信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询供应商信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增供应商信息
     *
     * @param info 供应商信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CssdSupplierinfo info);

    /**
     * 更新供应商信息
     *
     * @param info 供应商信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CssdSupplierinfo info);

    /**
     * 硬删除供应商信息
     *
     * @param appCode 应用编号
     * @param no 供应商信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除供应商信息
     *
     * @param appCode 应用编号
     * @param nos 供应商信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除供应商信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除供应商信息
     *
     * @param appCode 应用编号
     * @param no 供应商信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除供应商信息
     *
     * @param appCode 应用编号
     * @param nos 供应商信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除供应商信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
