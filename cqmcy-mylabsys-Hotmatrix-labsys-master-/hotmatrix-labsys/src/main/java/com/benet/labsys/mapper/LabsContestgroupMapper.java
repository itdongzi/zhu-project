package com.benet.labsys.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsContestgroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 竞赛小组信息Mapper接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
@Mapper
public interface LabsContestgroupMapper 
{
    /**
     * 查询所有竞赛小组信息列表
     *
     * @param appCode 应用编号
     * @return 竞赛小组信息集合
     */
    public List<LabsContestgroup> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询竞赛小组信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 竞赛小组信息集合
     */
    public List<LabsContestgroup> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询竞赛小组信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 竞赛小组信息集合
     */
    public List<LabsContestgroup> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询竞赛小组信息
     *
     * @param appCode 应用编号
     * @param no 竞赛小组信息ID
     * @return 竞赛小组信息
     */
    public LabsContestgroup getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询竞赛小组信息名称
     *
     * @param appCode 应用编号
     * @param no 竞赛小组信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询竞赛小组信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增竞赛小组信息
     *
     * @param info 竞赛小组信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") LabsContestgroup info);

    /**
     * 更新竞赛小组信息
     *
     * @param info 竞赛小组信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") LabsContestgroup info);

    /**
     * 硬删除竞赛小组信息
     *
     * @param appCode 应用编号
     * @param no 竞赛小组信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除竞赛小组信息
     *
     * @param appCode 应用编号
     * @param nos 竞赛小组信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除竞赛小组信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除竞赛小组信息
     *
     * @param appCode 应用编号
     * @param no 竞赛小组信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除竞赛小组信息
     *
     * @param appCode 应用编号
     * @param nos 竞赛小组信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除竞赛小组信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
