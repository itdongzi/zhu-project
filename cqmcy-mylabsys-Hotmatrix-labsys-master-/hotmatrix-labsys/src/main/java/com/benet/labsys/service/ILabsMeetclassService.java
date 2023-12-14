package com.benet.labsys.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsMeetclass;

/**
 * 会议室类型Service接口
 * 
 * @author yoxking
 * @date 2023-02-24 09:46:53
 */
public interface ILabsMeetclassService 
{
    /**
     * 查询所有会议室类型列表
     *
     * @param appCode 应用编号
     * @return 会议室类型集合
     */
    public List<LabsMeetclass> getAllRecords(String appCode);

    /**
     * 按分类查询会议室类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 会议室类型集合
     */
    public List<LabsMeetclass> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询会议室类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 会议室类型集合
     */
    public List<LabsMeetclass> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询会议室类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 会议室类型集合
     */
    public List<LabsMeetclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询会议室类型
     *
     * @param appCode 应用编号
     * @param no 会议室类型ID
     * @return 会议室类型
     */
    public LabsMeetclass getRecordByNo(String appCode,String no);

    /**
     * 查询会议室类型名称
     *
     * @param appCode 应用编号
     * @param no 会议室类型ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询会议室类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增会议室类型
     *
     * @param appCode 应用编号
     * @param info 会议室类型
     * @return 结果
     */
    public int AddNewRecord(String appCode,LabsMeetclass info);

    /**
     * 更新会议室类型
     *
     * @param appCode 应用编号
     * @param info 会议室类型
     * @return 结果
     */
    public int UpdateRecord(String appCode,LabsMeetclass info);

    /**
     * 硬删除会议室类型
     *
     * @param appCode 应用编号
     * @param no 会议室类型ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除会议室类型
     *
     * @param appCode 应用编号
     * @param nos 会议室类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除会议室类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除会议室类型
     *
     * @param appCode 应用编号
     * @param no 会议室类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除会议室类型
     *
     * @param appCode 应用编号
     * @param nos 会议室类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除会议室类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
