package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysDepartment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 部门信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysDepartmentMapper 
{
    /**
     * 查询所有部门信息列表
     *
     * @param appCode 应用编号
     * @return 部门信息集合
     */
    public List<SysDepartment> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询部门信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 部门信息集合
     */
    public List<SysDepartment> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询部门信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 部门信息集合
     */
    public List<SysDepartment> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询部门信息
     *
     * @param appCode 应用编号
     * @param no 部门信息ID
     * @return 部门信息
     */
    public SysDepartment getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询部门信息名称
     *
     * @param appCode 应用编号
     * @param no 部门信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询部门信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增部门信息
     *
     * @param info 部门信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysDepartment info);

    /**
     * 更新部门信息
     *
     * @param info 部门信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysDepartment info);

    /**
     * 硬删除部门信息
     *
     * @param appCode 应用编号
     * @param no 部门信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除部门信息
     *
     * @param appCode 应用编号
     * @param nos 部门信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除部门信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除部门信息
     *
     * @param appCode 应用编号
     * @param no 部门信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除部门信息
     *
     * @param appCode 应用编号
     * @param nos 部门信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除部门信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
