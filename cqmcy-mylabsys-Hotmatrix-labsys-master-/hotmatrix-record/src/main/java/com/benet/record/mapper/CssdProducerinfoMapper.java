package com.benet.record.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.record.domain.CssdProducerinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 生产商信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Mapper
public interface CssdProducerinfoMapper 
{
    /**
     * 查询所有生产商信息列表
     *
     * @param appCode 应用编号
     * @return 生产商信息集合
     */
    public List<CssdProducerinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询生产商信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 生产商信息集合
     */
    public List<CssdProducerinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询生产商信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 生产商信息集合
     */
    public List<CssdProducerinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询生产商信息
     *
     * @param appCode 应用编号
     * @param no 生产商信息ID
     * @return 生产商信息
     */
    public CssdProducerinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询生产商信息名称
     *
     * @param appCode 应用编号
     * @param no 生产商信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询生产商信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增生产商信息
     *
     * @param info 生产商信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CssdProducerinfo info);

    /**
     * 更新生产商信息
     *
     * @param info 生产商信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CssdProducerinfo info);

    /**
     * 硬删除生产商信息
     *
     * @param appCode 应用编号
     * @param no 生产商信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除生产商信息
     *
     * @param appCode 应用编号
     * @param nos 生产商信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除生产商信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除生产商信息
     *
     * @param appCode 应用编号
     * @param no 生产商信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除生产商信息
     *
     * @param appCode 应用编号
     * @param nos 生产商信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除生产商信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
