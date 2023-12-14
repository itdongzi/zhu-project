package com.benet.labsys.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsWorkitem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 实训室工作/检查项目Mapper接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Mapper
public interface LabsWorkitemMapper 
{
    /**
     * 查询所有实训室工作/检查项目列表
     *
     * @param appCode 应用编号
     * @return 实训室工作/检查项目集合
     */
    public List<LabsWorkitem> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询实训室工作/检查项目列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 实训室工作/检查项目集合
     */
    public List<LabsWorkitem> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询实训室工作/检查项目列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 实训室工作/检查项目集合
     */
    public List<LabsWorkitem> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询实训室工作/检查项目
     *
     * @param appCode 应用编号
     * @param no 实训室工作/检查项目ID
     * @return 实训室工作/检查项目
     */
    public LabsWorkitem getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询实训室工作/检查项目名称
     *
     * @param appCode 应用编号
     * @param no 实训室工作/检查项目ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询实训室工作/检查项目计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增实训室工作/检查项目
     *
     * @param info 实训室工作/检查项目
     * @return 结果
     */
    public int AddNewRecord(@Param("info") LabsWorkitem info);

    /**
     * 更新实训室工作/检查项目
     *
     * @param info 实训室工作/检查项目
     * @return 结果
     */
    public int UpdateRecord(@Param("info") LabsWorkitem info);

    /**
     * 硬删除实训室工作/检查项目
     *
     * @param appCode 应用编号
     * @param no 实训室工作/检查项目ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除实训室工作/检查项目
     *
     * @param appCode 应用编号
     * @param nos 实训室工作/检查项目IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除实训室工作/检查项目
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除实训室工作/检查项目
     *
     * @param appCode 应用编号
     * @param no 实训室工作/检查项目ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除实训室工作/检查项目
     *
     * @param appCode 应用编号
     * @param nos 实训室工作/检查项目IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除实训室工作/检查项目
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
