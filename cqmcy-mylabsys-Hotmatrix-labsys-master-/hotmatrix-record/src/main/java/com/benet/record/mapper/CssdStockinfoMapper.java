package com.benet.record.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.record.domain.CssdStockinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 库存信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Mapper
public interface CssdStockinfoMapper 
{
    /**
     * 查询所有库存信息列表
     *
     * @param appCode 应用编号
     * @return 库存信息集合
     */
    public List<CssdStockinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询库存信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 库存信息集合
     */
    public List<CssdStockinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询库存信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 库存信息集合
     */
    public List<CssdStockinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询库存信息
     *
     * @param appCode 应用编号
     * @param no 库存信息ID
     * @return 库存信息
     */
    public CssdStockinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询库存信息名称
     *
     * @param appCode 应用编号
     * @param no 库存信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询库存信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增库存信息
     *
     * @param info 库存信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CssdStockinfo info);

    /**
     * 更新库存信息
     *
     * @param info 库存信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CssdStockinfo info);

    /**
     * 硬删除库存信息
     *
     * @param appCode 应用编号
     * @param no 库存信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除库存信息
     *
     * @param appCode 应用编号
     * @param nos 库存信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除库存信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除库存信息
     *
     * @param appCode 应用编号
     * @param no 库存信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除库存信息
     *
     * @param appCode 应用编号
     * @param nos 库存信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除库存信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
