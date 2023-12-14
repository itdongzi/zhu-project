package com.benet.labsys.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsSafetyclass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 安全资料分类Mapper接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Mapper
public interface LabsSafetyclassMapper 
{
    /**
     * 查询所有安全资料分类列表
     *
     * @param appCode 应用编号
     * @return 安全资料分类集合
     */
    public List<LabsSafetyclass> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询安全资料分类列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 安全资料分类集合
     */
    public List<LabsSafetyclass> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询安全资料分类列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 安全资料分类集合
     */
    public List<LabsSafetyclass> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询安全资料分类
     *
     * @param appCode 应用编号
     * @param no 安全资料分类ID
     * @return 安全资料分类
     */
    public LabsSafetyclass getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询安全资料分类名称
     *
     * @param appCode 应用编号
     * @param no 安全资料分类ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询安全资料分类计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增安全资料分类
     *
     * @param info 安全资料分类
     * @return 结果
     */
    public int AddNewRecord(@Param("info") LabsSafetyclass info);

    /**
     * 更新安全资料分类
     *
     * @param info 安全资料分类
     * @return 结果
     */
    public int UpdateRecord(@Param("info") LabsSafetyclass info);

    /**
     * 硬删除安全资料分类
     *
     * @param appCode 应用编号
     * @param no 安全资料分类ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除安全资料分类
     *
     * @param appCode 应用编号
     * @param nos 安全资料分类IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除安全资料分类
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除安全资料分类
     *
     * @param appCode 应用编号
     * @param no 安全资料分类ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除安全资料分类
     *
     * @param appCode 应用编号
     * @param nos 安全资料分类IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除安全资料分类
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
