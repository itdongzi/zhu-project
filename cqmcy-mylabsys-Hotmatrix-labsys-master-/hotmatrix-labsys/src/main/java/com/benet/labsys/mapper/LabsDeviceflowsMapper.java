package com.benet.labsys.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsDeviceflows;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 设备控制过程Mapper接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Mapper
public interface LabsDeviceflowsMapper 
{
    /**
     * 查询所有设备控制过程列表
     *
     * @param appCode 应用编号
     * @return 设备控制过程集合
     */
    public List<LabsDeviceflows> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询设备控制过程列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 设备控制过程集合
     */
    public List<LabsDeviceflows> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询设备控制过程列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 设备控制过程集合
     */
    public List<LabsDeviceflows> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询设备控制过程
     *
     * @param appCode 应用编号
     * @param no 设备控制过程ID
     * @return 设备控制过程
     */
    public LabsDeviceflows getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询设备控制过程名称
     *
     * @param appCode 应用编号
     * @param no 设备控制过程ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询设备控制过程计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增设备控制过程
     *
     * @param info 设备控制过程
     * @return 结果
     */
    public int AddNewRecord(@Param("info") LabsDeviceflows info);

    /**
     * 更新设备控制过程
     *
     * @param info 设备控制过程
     * @return 结果
     */
    public int UpdateRecord(@Param("info") LabsDeviceflows info);

    /**
     * 硬删除设备控制过程
     *
     * @param appCode 应用编号
     * @param no 设备控制过程ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除设备控制过程
     *
     * @param appCode 应用编号
     * @param nos 设备控制过程IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除设备控制过程
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除设备控制过程
     *
     * @param appCode 应用编号
     * @param no 设备控制过程ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除设备控制过程
     *
     * @param appCode 应用编号
     * @param nos 设备控制过程IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除设备控制过程
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
