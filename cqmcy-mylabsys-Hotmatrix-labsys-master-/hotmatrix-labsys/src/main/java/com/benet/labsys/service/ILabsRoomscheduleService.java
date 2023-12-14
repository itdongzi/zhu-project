package com.benet.labsys.service;

import java.io.InputStream;
import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsRoomschedule;

import javax.servlet.http.HttpServletRequest;

/**
 * 实验室排课Service接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
public interface ILabsRoomscheduleService 
{
    /**
     * 查询所有实验室排课列表
     *
     * @param appCode 应用编号
     * @return 实验室排课集合
     */
    public List<LabsRoomschedule> getAllRecords(String appCode);

    /**
     * 按分类查询实验室排课列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 实验室排课集合
     */
    public List<LabsRoomschedule> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询实验室排课列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 实验室排课集合
     */
    public List<LabsRoomschedule> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询实验室排课列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 实验室排课集合
     */
    public List<LabsRoomschedule> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询实验室排课
     *
     * @param appCode 应用编号
     * @param no 实验室排课ID
     * @return 实验室排课
     */
    public LabsRoomschedule getRecordByNo(String appCode,String no);

    /**
     * 查询实验室排课名称
     *
     * @param appCode 应用编号
     * @param no 实验室排课ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询实验室排课计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增实验室排课
     *
     * @param appCode 应用编号
     * @param info 实验室排课
     * @return 结果
     */
    public int AddNewRecord(String appCode,LabsRoomschedule info);

    /**
     * 更新实验室排课
     *
     * @param appCode 应用编号
     * @param info 实验室排课
     * @return 结果
     */
    public int UpdateRecord(String appCode,LabsRoomschedule info);

    /**
     * 硬删除实验室排课
     *
     * @param appCode 应用编号
     * @param no 实验室排课ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除实验室排课
     *
     * @param appCode 应用编号
     * @param nos 实验室排课IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除实验室排课
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除实验室排课
     *
     * @param appCode 应用编号
     * @param no 实验室排课ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除实验室排课
     *
     * @param appCode 应用编号
     * @param nos 实验室排课IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除实验室排课
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);

    public List<String> getXnxqbhList(String appCode);

    public List<String> getKkzcList(String appCode);
}
