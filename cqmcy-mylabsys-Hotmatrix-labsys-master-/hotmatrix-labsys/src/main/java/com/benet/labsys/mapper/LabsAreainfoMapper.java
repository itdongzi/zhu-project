package com.benet.labsys.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsAreainfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 实验楼区域Mapper接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
@Mapper
public interface LabsAreainfoMapper 
{
    /**
     * 查询所有实验楼区域列表
     *
     * @param appCode 应用编号
     * @return 实验楼区域集合
     */
    public List<LabsAreainfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询实验楼区域列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 实验楼区域集合
     */
    public List<LabsAreainfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询实验楼区域列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 实验楼区域集合
     */
    public List<LabsAreainfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询实验楼区域
     *
     * @param appCode 应用编号
     * @param no 实验楼区域ID
     * @return 实验楼区域
     */
    public LabsAreainfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询实验楼区域名称
     *
     * @param appCode 应用编号
     * @param no 实验楼区域ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询实验楼区域计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增实验楼区域
     *
     * @param info 实验楼区域
     * @return 结果
     */
    public int AddNewRecord(@Param("info") LabsAreainfo info);

    /**
     * 更新实验楼区域
     *
     * @param info 实验楼区域
     * @return 结果
     */
    public int UpdateRecord(@Param("info") LabsAreainfo info);

    /**
     * 硬删除实验楼区域
     *
     * @param appCode 应用编号
     * @param no 实验楼区域ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除实验楼区域
     *
     * @param appCode 应用编号
     * @param nos 实验楼区域IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除实验楼区域
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除实验楼区域
     *
     * @param appCode 应用编号
     * @param no 实验楼区域ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除实验楼区域
     *
     * @param appCode 应用编号
     * @param nos 实验楼区域IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除实验楼区域
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}