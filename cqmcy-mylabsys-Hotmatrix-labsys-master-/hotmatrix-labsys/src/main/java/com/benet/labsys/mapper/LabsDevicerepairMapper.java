package com.benet.labsys.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsDevicerepair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 设备报修信息Mapper接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Mapper
public interface LabsDevicerepairMapper 
{
    /**
     * 查询所有设备报修信息列表
     *
     * @param appCode 应用编号
     * @return 设备报修信息集合
     */
    public List<LabsDevicerepair> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询设备报修信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 设备报修信息集合
     */
    public List<LabsDevicerepair> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询设备报修信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 设备报修信息集合
     */
    public List<LabsDevicerepair> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询设备报修信息
     *
     * @param appCode 应用编号
     * @param no 设备报修信息ID
     * @return 设备报修信息
     */
    public LabsDevicerepair getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询设备报修信息名称
     *
     * @param appCode 应用编号
     * @param no 设备报修信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询设备报修信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增设备报修信息
     *
     * @param info 设备报修信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") LabsDevicerepair info);

    /**
     * 更新设备报修信息
     *
     * @param info 设备报修信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") LabsDevicerepair info);

    /**
     * 硬删除设备报修信息
     *
     * @param appCode 应用编号
     * @param no 设备报修信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除设备报修信息
     *
     * @param appCode 应用编号
     * @param nos 设备报修信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除设备报修信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除设备报修信息
     *
     * @param appCode 应用编号
     * @param no 设备报修信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除设备报修信息
     *
     * @param appCode 应用编号
     * @param nos 设备报修信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除设备报修信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
