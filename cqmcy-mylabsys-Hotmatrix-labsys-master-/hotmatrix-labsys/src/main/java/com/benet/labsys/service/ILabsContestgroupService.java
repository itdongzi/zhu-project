package com.benet.labsys.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsContestgroup;

/**
 * 竞赛小组信息Service接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
public interface ILabsContestgroupService 
{
    /**
     * 查询所有竞赛小组信息列表
     *
     * @param appCode 应用编号
     * @return 竞赛小组信息集合
     */
    public List<LabsContestgroup> getAllRecords(String appCode);

    /**
     * 按分类查询竞赛小组信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 竞赛小组信息集合
     */
    public List<LabsContestgroup> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询竞赛小组信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 竞赛小组信息集合
     */
    public List<LabsContestgroup> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询竞赛小组信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 竞赛小组信息集合
     */
    public List<LabsContestgroup> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询竞赛小组信息
     *
     * @param appCode 应用编号
     * @param no 竞赛小组信息ID
     * @return 竞赛小组信息
     */
    public LabsContestgroup getRecordByNo(String appCode,String no);

    /**
     * 查询竞赛小组信息名称
     *
     * @param appCode 应用编号
     * @param no 竞赛小组信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询竞赛小组信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增竞赛小组信息
     *
     * @param appCode 应用编号
     * @param info 竞赛小组信息
     * @return 结果
     */
    public int AddNewRecord(String appCode,LabsContestgroup info);

    /**
     * 更新竞赛小组信息
     *
     * @param appCode 应用编号
     * @param info 竞赛小组信息
     * @return 结果
     */
    public int UpdateRecord(String appCode,LabsContestgroup info);

    /**
     * 硬删除竞赛小组信息
     *
     * @param appCode 应用编号
     * @param no 竞赛小组信息ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除竞赛小组信息
     *
     * @param appCode 应用编号
     * @param nos 竞赛小组信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除竞赛小组信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除竞赛小组信息
     *
     * @param appCode 应用编号
     * @param no 竞赛小组信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除竞赛小组信息
     *
     * @param appCode 应用编号
     * @param nos 竞赛小组信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除竞赛小组信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
