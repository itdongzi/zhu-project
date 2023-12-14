package com.benet.labsys.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsMeetorders;

/**
 * 会议室预约Service接口
 * 
 * @author yoxking
 * @date 2023-02-24 09:46:53
 */
public interface ILabsMeetordersService 
{
    /**
     * 查询所有会议室预约列表
     *
     * @param appCode 应用编号
     * @return 会议室预约集合
     */
    public List<LabsMeetorders> getAllRecords(String appCode);

    /**
     * 按分类查询会议室预约列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 会议室预约集合
     */
    public List<LabsMeetorders> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询会议室预约列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 会议室预约集合
     */
    public List<LabsMeetorders> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询会议室预约列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 会议室预约集合
     */
    public List<LabsMeetorders> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询会议室预约
     *
     * @param appCode 应用编号
     * @param no 会议室预约ID
     * @return 会议室预约
     */
    public LabsMeetorders getRecordByNo(String appCode,String no);

    /**
     * 查询会议室预约名称
     *
     * @param appCode 应用编号
     * @param no 会议室预约ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询会议室预约计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增会议室预约
     *
     * @param appCode 应用编号
     * @param info 会议室预约
     * @return 结果
     */
    public int AddNewRecord(String appCode,LabsMeetorders info);

    /**
     * 更新会议室预约
     *
     * @param appCode 应用编号
     * @param info 会议室预约
     * @return 结果
     */
    public int UpdateRecord(String appCode,LabsMeetorders info);

    /**
     * 硬删除会议室预约
     *
     * @param appCode 应用编号
     * @param no 会议室预约ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除会议室预约
     *
     * @param appCode 应用编号
     * @param nos 会议室预约IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除会议室预约
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除会议室预约
     *
     * @param appCode 应用编号
     * @param no 会议室预约ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除会议室预约
     *
     * @param appCode 应用编号
     * @param nos 会议室预约IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除会议室预约
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
