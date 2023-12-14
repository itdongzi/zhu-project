package com.benet.labsys.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsAchieveinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 实训成果Mapper接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
@Mapper
public interface LabsAchieveinfoMapper 
{
    /**
     * 查询所有实训成果列表
     *
     * @param appCode 应用编号
     * @return 实训成果集合
     */
    public List<LabsAchieveinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询实训成果列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 实训成果集合
     */
    public List<LabsAchieveinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询实训成果列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 实训成果集合
     */
    public List<LabsAchieveinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询实训成果
     *
     * @param appCode 应用编号
     * @param no 实训成果ID
     * @return 实训成果
     */
    public LabsAchieveinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询实训成果名称
     *
     * @param appCode 应用编号
     * @param no 实训成果ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询实训成果计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增实训成果
     *
     * @param info 实训成果
     * @return 结果
     */
    public int AddNewRecord(@Param("info") LabsAchieveinfo info);

    /**
     * 更新实训成果
     *
     * @param info 实训成果
     * @return 结果
     */
    public int UpdateRecord(@Param("info") LabsAchieveinfo info);

    /**
     * 硬删除实训成果
     *
     * @param appCode 应用编号
     * @param no 实训成果ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除实训成果
     *
     * @param appCode 应用编号
     * @param nos 实训成果IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除实训成果
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除实训成果
     *
     * @param appCode 应用编号
     * @param no 实训成果ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除实训成果
     *
     * @param appCode 应用编号
     * @param nos 实训成果IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除实训成果
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
