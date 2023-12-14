package com.benet.labsys.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsSafetyfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 安全学习资料Mapper接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Mapper
public interface LabsSafetyfileMapper 
{
    /**
     * 查询所有安全学习资料列表
     *
     * @param appCode 应用编号
     * @return 安全学习资料集合
     */
    public List<LabsSafetyfile> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询安全学习资料列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 安全学习资料集合
     */
    public List<LabsSafetyfile> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询安全学习资料列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 安全学习资料集合
     */
    public List<LabsSafetyfile> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询安全学习资料
     *
     * @param appCode 应用编号
     * @param no 安全学习资料ID
     * @return 安全学习资料
     */
    public LabsSafetyfile getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询安全学习资料名称
     *
     * @param appCode 应用编号
     * @param no 安全学习资料ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询安全学习资料计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增安全学习资料
     *
     * @param info 安全学习资料
     * @return 结果
     */
    public int AddNewRecord(@Param("info") LabsSafetyfile info);

    /**
     * 更新安全学习资料
     *
     * @param info 安全学习资料
     * @return 结果
     */
    public int UpdateRecord(@Param("info") LabsSafetyfile info);

    /**
     * 硬删除安全学习资料
     *
     * @param appCode 应用编号
     * @param no 安全学习资料ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除安全学习资料
     *
     * @param appCode 应用编号
     * @param nos 安全学习资料IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除安全学习资料
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除安全学习资料
     *
     * @param appCode 应用编号
     * @param no 安全学习资料ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除安全学习资料
     *
     * @param appCode 应用编号
     * @param nos 安全学习资料IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除安全学习资料
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
