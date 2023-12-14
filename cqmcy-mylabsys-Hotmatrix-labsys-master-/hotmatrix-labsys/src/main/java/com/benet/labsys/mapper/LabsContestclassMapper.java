package com.benet.labsys.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsContestclass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 竞赛类型Mapper接口
 * 
 * @author yoxking
 * @date 2023-02-23 09:06:40
 */
@Mapper
public interface LabsContestclassMapper 
{
    /**
     * 查询所有竞赛类型列表
     *
     * @param appCode 应用编号
     * @return 竞赛类型集合
     */
    public List<LabsContestclass> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询竞赛类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 竞赛类型集合
     */
    public List<LabsContestclass> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询竞赛类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 竞赛类型集合
     */
    public List<LabsContestclass> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询竞赛类型
     *
     * @param appCode 应用编号
     * @param no 竞赛类型ID
     * @return 竞赛类型
     */
    public LabsContestclass getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询竞赛类型名称
     *
     * @param appCode 应用编号
     * @param no 竞赛类型ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询竞赛类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增竞赛类型
     *
     * @param info 竞赛类型
     * @return 结果
     */
    public int AddNewRecord(@Param("info") LabsContestclass info);

    /**
     * 更新竞赛类型
     *
     * @param info 竞赛类型
     * @return 结果
     */
    public int UpdateRecord(@Param("info") LabsContestclass info);

    /**
     * 硬删除竞赛类型
     *
     * @param appCode 应用编号
     * @param no 竞赛类型ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除竞赛类型
     *
     * @param appCode 应用编号
     * @param nos 竞赛类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除竞赛类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除竞赛类型
     *
     * @param appCode 应用编号
     * @param no 竞赛类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除竞赛类型
     *
     * @param appCode 应用编号
     * @param nos 竞赛类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除竞赛类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}