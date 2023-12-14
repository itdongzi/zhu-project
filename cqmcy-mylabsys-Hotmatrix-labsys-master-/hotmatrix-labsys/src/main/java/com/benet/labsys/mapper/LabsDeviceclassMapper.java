package com.benet.labsys.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsDeviceclass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 实验室设备类型Mapper接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
@Mapper
public interface LabsDeviceclassMapper 
{
    /**
     * 查询所有实验室设备类型列表
     *
     * @param appCode 应用编号
     * @return 实验室设备类型集合
     */
    public List<LabsDeviceclass> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询实验室设备类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 实验室设备类型集合
     */
    public List<LabsDeviceclass> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询实验室设备类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 实验室设备类型集合
     */
    public List<LabsDeviceclass> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询实验室设备类型
     *
     * @param appCode 应用编号
     * @param no 实验室设备类型ID
     * @return 实验室设备类型
     */
    public LabsDeviceclass getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询实验室设备类型名称
     *
     * @param appCode 应用编号
     * @param no 实验室设备类型ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询实验室设备类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增实验室设备类型
     *
     * @param info 实验室设备类型
     * @return 结果
     */
    public int AddNewRecord(@Param("info") LabsDeviceclass info);

    /**
     * 更新实验室设备类型
     *
     * @param info 实验室设备类型
     * @return 结果
     */
    public int UpdateRecord(@Param("info") LabsDeviceclass info);

    /**
     * 硬删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param no 实验室设备类型ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param nos 实验室设备类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param no 实验室设备类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param nos 实验室设备类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
