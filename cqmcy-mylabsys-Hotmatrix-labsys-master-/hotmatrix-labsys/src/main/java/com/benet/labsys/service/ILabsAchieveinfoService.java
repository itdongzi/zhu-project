package com.benet.labsys.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsAchieveinfo;

/**
 * 实训成果Service接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
public interface ILabsAchieveinfoService 
{
    /**
     * 查询所有实训成果列表
     *
     * @param appCode 应用编号
     * @return 实训成果集合
     */
    public List<LabsAchieveinfo> getAllRecords(String appCode);

    /**
     * 按分类查询实训成果列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 实训成果集合
     */
    public List<LabsAchieveinfo> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询实训成果列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 实训成果集合
     */
    public List<LabsAchieveinfo> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询实训成果列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 实训成果集合
     */
    public List<LabsAchieveinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询实训成果
     *
     * @param appCode 应用编号
     * @param no 实训成果ID
     * @return 实训成果
     */
    public LabsAchieveinfo getRecordByNo(String appCode,String no);

    /**
     * 查询实训成果名称
     *
     * @param appCode 应用编号
     * @param no 实训成果ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询实训成果计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增实训成果
     *
     * @param appCode 应用编号
     * @param info 实训成果
     * @return 结果
     */
    public int AddNewRecord(String appCode,LabsAchieveinfo info);

    /**
     * 更新实训成果
     *
     * @param appCode 应用编号
     * @param info 实训成果
     * @return 结果
     */
    public int UpdateRecord(String appCode,LabsAchieveinfo info);

    /**
     * 硬删除实训成果
     *
     * @param appCode 应用编号
     * @param no 实训成果ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除实训成果
     *
     * @param appCode 应用编号
     * @param nos 实训成果IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除实训成果
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除实训成果
     *
     * @param appCode 应用编号
     * @param no 实训成果ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除实训成果
     *
     * @param appCode 应用编号
     * @param nos 实训成果IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除实训成果
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
