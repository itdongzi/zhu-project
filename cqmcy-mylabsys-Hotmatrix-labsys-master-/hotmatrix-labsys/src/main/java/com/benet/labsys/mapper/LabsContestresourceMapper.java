package com.benet.labsys.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsContestresource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 竞赛作品信息Mapper接口
 * 
 * @author yoxking
 * @date 2023-02-23 09:06:40
 */
@Mapper
public interface LabsContestresourceMapper 
{
    /**
     * 查询所有竞赛作品信息列表
     *
     * @param appCode 应用编号
     * @return 竞赛作品信息集合
     */
    public List<LabsContestresource> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询竞赛作品信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 竞赛作品信息集合
     */
    public List<LabsContestresource> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询竞赛作品信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 竞赛作品信息集合
     */
    public List<LabsContestresource> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询竞赛作品信息
     *
     * @param appCode 应用编号
     * @param no 竞赛作品信息ID
     * @return 竞赛作品信息
     */
    public LabsContestresource getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询竞赛作品信息名称
     *
     * @param appCode 应用编号
     * @param no 竞赛作品信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询竞赛作品信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增竞赛作品信息
     *
     * @param info 竞赛作品信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") LabsContestresource info);

    /**
     * 更新竞赛作品信息
     *
     * @param info 竞赛作品信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") LabsContestresource info);

    /**
     * 硬删除竞赛作品信息
     *
     * @param appCode 应用编号
     * @param no 竞赛作品信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除竞赛作品信息
     *
     * @param appCode 应用编号
     * @param nos 竞赛作品信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除竞赛作品信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除竞赛作品信息
     *
     * @param appCode 应用编号
     * @param no 竞赛作品信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除竞赛作品信息
     *
     * @param appCode 应用编号
     * @param nos 竞赛作品信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除竞赛作品信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
