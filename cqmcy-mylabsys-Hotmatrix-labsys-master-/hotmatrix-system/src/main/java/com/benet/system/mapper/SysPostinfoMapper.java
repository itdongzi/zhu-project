package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysPostinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 岗位信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysPostinfoMapper 
{
    /**
     * 查询所有岗位信息列表
     *
     * @param appCode 应用编号
     * @return 岗位信息集合
     */
    public List<SysPostinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询岗位信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 岗位信息集合
     */
    public List<SysPostinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询岗位信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 岗位信息集合
     */
    public List<SysPostinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询岗位信息
     *
     * @param appCode 应用编号
     * @param no 岗位信息ID
     * @return 岗位信息
     */
    public SysPostinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询岗位信息名称
     *
     * @param appCode 应用编号
     * @param no 岗位信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询岗位信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增岗位信息
     *
     * @param info 岗位信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysPostinfo info);

    /**
     * 更新岗位信息
     *
     * @param info 岗位信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysPostinfo info);

    /**
     * 硬删除岗位信息
     *
     * @param appCode 应用编号
     * @param no 岗位信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除岗位信息
     *
     * @param appCode 应用编号
     * @param nos 岗位信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除岗位信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除岗位信息
     *
     * @param appCode 应用编号
     * @param no 岗位信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除岗位信息
     *
     * @param appCode 应用编号
     * @param nos 岗位信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除岗位信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
