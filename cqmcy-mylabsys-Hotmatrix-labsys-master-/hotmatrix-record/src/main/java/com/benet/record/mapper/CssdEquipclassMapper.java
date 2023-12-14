package com.benet.record.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.record.domain.CssdEquipclass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 器械类型Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Mapper
public interface CssdEquipclassMapper 
{
    /**
     * 查询所有器械类型列表
     *
     * @param appCode 应用编号
     * @return 器械类型集合
     */
    public List<CssdEquipclass> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询器械类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 器械类型集合
     */
    public List<CssdEquipclass> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询器械类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 器械类型集合
     */
    public List<CssdEquipclass> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询器械类型
     *
     * @param appCode 应用编号
     * @param no 器械类型ID
     * @return 器械类型
     */
    public CssdEquipclass getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询器械类型名称
     *
     * @param appCode 应用编号
     * @param no 器械类型ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询器械类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增器械类型
     *
     * @param info 器械类型
     * @return 结果
     */
    public int AddNewRecord(@Param("info") CssdEquipclass info);

    /**
     * 更新器械类型
     *
     * @param info 器械类型
     * @return 结果
     */
    public int UpdateRecord(@Param("info") CssdEquipclass info);

    /**
     * 硬删除器械类型
     *
     * @param appCode 应用编号
     * @param no 器械类型ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除器械类型
     *
     * @param appCode 应用编号
     * @param nos 器械类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除器械类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除器械类型
     *
     * @param appCode 应用编号
     * @param no 器械类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除器械类型
     *
     * @param appCode 应用编号
     * @param nos 器械类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除器械类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
