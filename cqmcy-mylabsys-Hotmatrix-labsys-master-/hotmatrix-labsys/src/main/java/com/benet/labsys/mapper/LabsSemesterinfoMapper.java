package com.benet.labsys.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsSemesterinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 学期学年信息Mapper接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Mapper
public interface LabsSemesterinfoMapper 
{
    /**
     * 查询所有学期学年信息列表
     *
     * @param appCode 应用编号
     * @return 学期学年信息集合
     */
    public List<LabsSemesterinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询学期学年信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 学期学年信息集合
     */
    public List<LabsSemesterinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询学期学年信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 学期学年信息集合
     */
    public List<LabsSemesterinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询学期学年信息
     *
     * @param appCode 应用编号
     * @param no 学期学年信息ID
     * @return 学期学年信息
     */
    public LabsSemesterinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询学期学年信息名称
     *
     * @param appCode 应用编号
     * @param no 学期学年信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询学期学年信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增学期学年信息
     *
     * @param info 学期学年信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") LabsSemesterinfo info);

    /**
     * 更新学期学年信息
     *
     * @param info 学期学年信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") LabsSemesterinfo info);

    /**
     * 硬删除学期学年信息
     *
     * @param appCode 应用编号
     * @param no 学期学年信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除学期学年信息
     *
     * @param appCode 应用编号
     * @param nos 学期学年信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除学期学年信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除学期学年信息
     *
     * @param appCode 应用编号
     * @param no 学期学年信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除学期学年信息
     *
     * @param appCode 应用编号
     * @param nos 学期学年信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除学期学年信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    public LabsSemesterinfo getRecordByNameAndType(@Param("appCode") String appCode,@Param("condition") String condition);

}
