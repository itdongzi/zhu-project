package com.benet.record.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.record.domain.CssdPrintstyle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 打印样式Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Mapper
public interface CssdPrintstyleMapper 
{
    /**
     * 查询所有打印样式列表
     *
     * @param appCode 应用编号
     * @return 打印样式集合
     */
    public List<CssdPrintstyle> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询打印样式列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 打印样式集合
     */
    public List<CssdPrintstyle> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询打印样式列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 打印样式集合
     */
    public List<CssdPrintstyle> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询打印样式
     *
     * @param appCode 应用编号
     * @param no 打印样式ID
     * @return 打印样式
     */
    public CssdPrintstyle getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询打印样式名称
     *
     * @param appCode 应用编号
     * @param no 打印样式ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询打印样式计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增打印样式
     *
     * @param info 打印样式
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CssdPrintstyle info);

    /**
     * 更新打印样式
     *
     * @param info 打印样式
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CssdPrintstyle info);

    /**
     * 硬删除打印样式
     *
     * @param appCode 应用编号
     * @param no 打印样式ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除打印样式
     *
     * @param appCode 应用编号
     * @param nos 打印样式IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除打印样式
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除打印样式
     *
     * @param appCode 应用编号
     * @param no 打印样式ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除打印样式
     *
     * @param appCode 应用编号
     * @param nos 打印样式IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除打印样式
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
