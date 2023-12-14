package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysBranchinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 分支信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysBranchinfoMapper 
{
    /**
     * 查询所有分支信息列表
     *
     * @param appCode 应用编号
     * @return 分支信息集合
     */
    public List<SysBranchinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询分支信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 分支信息集合
     */
    public List<SysBranchinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询分支信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 分支信息集合
     */
    public List<SysBranchinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询分支信息
     *
     * @param appCode 应用编号
     * @param no 分支信息ID
     * @return 分支信息
     */
    public SysBranchinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询分支信息名称
     *
     * @param appCode 应用编号
     * @param no 分支信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询分支信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增分支信息
     *
     * @param info 分支信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysBranchinfo info);

    /**
     * 更新分支信息
     *
     * @param info 分支信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysBranchinfo info);

    /**
     * 硬删除分支信息
     *
     * @param appCode 应用编号
     * @param no 分支信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除分支信息
     *
     * @param appCode 应用编号
     * @param nos 分支信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除分支信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除分支信息
     *
     * @param appCode 应用编号
     * @param no 分支信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除分支信息
     *
     * @param appCode 应用编号
     * @param nos 分支信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除分支信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);
}
