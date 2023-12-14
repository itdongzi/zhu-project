package com.benet.labsys.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsContestpaper;

/**
 * 竞赛试题信息Service接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
public interface ILabsContestpaperService 
{
    /**
     * 查询所有竞赛试题信息列表
     *
     * @param appCode 应用编号
     * @return 竞赛试题信息集合
     */
    public List<LabsContestpaper> getAllRecords(String appCode);

    /**
     * 按分类查询竞赛试题信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 竞赛试题信息集合
     */
    public List<LabsContestpaper> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询竞赛试题信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 竞赛试题信息集合
     */
    public List<LabsContestpaper> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询竞赛试题信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 竞赛试题信息集合
     */
    public List<LabsContestpaper> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询竞赛试题信息
     *
     * @param appCode 应用编号
     * @param no 竞赛试题信息ID
     * @return 竞赛试题信息
     */
    public LabsContestpaper getRecordByNo(String appCode,String no);

    /**
     * 查询竞赛试题信息名称
     *
     * @param appCode 应用编号
     * @param no 竞赛试题信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询竞赛试题信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增竞赛试题信息
     *
     * @param appCode 应用编号
     * @param info 竞赛试题信息
     * @return 结果
     */
    public int AddNewRecord(String appCode,LabsContestpaper info);

    /**
     * 更新竞赛试题信息
     *
     * @param appCode 应用编号
     * @param info 竞赛试题信息
     * @return 结果
     */
    public int UpdateRecord(String appCode,LabsContestpaper info);

    /**
     * 硬删除竞赛试题信息
     *
     * @param appCode 应用编号
     * @param no 竞赛试题信息ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除竞赛试题信息
     *
     * @param appCode 应用编号
     * @param nos 竞赛试题信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除竞赛试题信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除竞赛试题信息
     *
     * @param appCode 应用编号
     * @param no 竞赛试题信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除竞赛试题信息
     *
     * @param appCode 应用编号
     * @param nos 竞赛试题信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除竞赛试题信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
