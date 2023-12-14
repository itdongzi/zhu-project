package com.benet.labsys.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsDeviceclass;

/**
 * 实验室设备类型Service接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
public interface ILabsDeviceclassService 
{
    /**
     * 查询所有实验室设备类型列表
     *
     * @param appCode 应用编号
     * @return 实验室设备类型集合
     */
    public List<LabsDeviceclass> getAllRecords(String appCode);

    /**
     * 按分类查询实验室设备类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 实验室设备类型集合
     */
    public List<LabsDeviceclass> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询实验室设备类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 实验室设备类型集合
     */
    public List<LabsDeviceclass> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询实验室设备类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 实验室设备类型集合
     */
    public List<LabsDeviceclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询实验室设备类型
     *
     * @param appCode 应用编号
     * @param no 实验室设备类型ID
     * @return 实验室设备类型
     */
    public LabsDeviceclass getRecordByNo(String appCode,String no);

    /**
     * 查询实验室设备类型名称
     *
     * @param appCode 应用编号
     * @param no 实验室设备类型ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询实验室设备类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增实验室设备类型
     *
     * @param appCode 应用编号
     * @param info 实验室设备类型
     * @return 结果
     */
    public int AddNewRecord(String appCode,LabsDeviceclass info);

    /**
     * 更新实验室设备类型
     *
     * @param appCode 应用编号
     * @param info 实验室设备类型
     * @return 结果
     */
    public int UpdateRecord(String appCode,LabsDeviceclass info);

    /**
     * 硬删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param no 实验室设备类型ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param nos 实验室设备类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param no 实验室设备类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param nos 实验室设备类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
