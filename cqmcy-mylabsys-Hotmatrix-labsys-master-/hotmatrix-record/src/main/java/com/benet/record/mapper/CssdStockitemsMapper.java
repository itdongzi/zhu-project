package com.benet.record.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.record.domain.CssdStockitems;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 出入库单明细Mapper接口
 * 
 * @author yoxking
 * @date 2021-03-20
 */
@Mapper
public interface CssdStockitemsMapper 
{
    /**
     * 查询所有出入库单明细列表
     *
     * @param appCode 应用编号
     * @return 出入库单明细集合
     */
    public List<CssdStockitems> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询出入库单明细列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 出入库单明细集合
     */
    public List<CssdStockitems> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询出入库单明细列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 出入库单明细集合
     */
    public List<CssdStockitems> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询出入库单明细
     *
     * @param appCode 应用编号
     * @param no 出入库单明细ID
     * @return 出入库单明细
     */
    public CssdStockitems getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询出入库单明细名称
     *
     * @param appCode 应用编号
     * @param no 出入库单明细ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询出入库单明细计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增出入库单明细
     *
     * @param info 出入库单明细
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CssdStockitems info);

    /**
     * 更新出入库单明细
     *
     * @param info 出入库单明细
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CssdStockitems info);

    /**
     * 硬删除出入库单明细
     *
     * @param appCode 应用编号
     * @param no 出入库单明细ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除出入库单明细
     *
     * @param appCode 应用编号
     * @param nos 出入库单明细IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除出入库单明细
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除出入库单明细
     *
     * @param appCode 应用编号
     * @param no 出入库单明细ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除出入库单明细
     *
     * @param appCode 应用编号
     * @param nos 出入库单明细IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除出入库单明细
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
