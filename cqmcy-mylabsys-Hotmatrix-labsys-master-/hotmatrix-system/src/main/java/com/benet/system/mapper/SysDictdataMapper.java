package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysDictdata;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 字典数据Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-23
 */
@Mapper
public interface SysDictdataMapper 
{
    /**
     * 查询所有字典数据列表
     *
     * @param appCode 应用编号
     * @return 字典数据集合
     */
    public List<SysDictdata> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询字典数据列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 字典数据集合
     */
    public List<SysDictdata> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询字典数据列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 字典数据集合
     */
    public List<SysDictdata> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询字典数据
     *
     * @param appCode 应用编号
     * @param no 字典数据ID
     * @return 字典数据
     */
    public SysDictdata getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询字典数据名称
     *
     * @param appCode 应用编号
     * @param no 字典数据ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询字典数据计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增字典数据
     *
     * @param info 字典数据
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysDictdata info);

    /**
     * 更新字典数据
     *
     * @param info 字典数据
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysDictdata info);

    /**
     * 硬删除字典数据
     *
     * @param appCode 应用编号
     * @param no 字典数据ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除字典数据
     *
     * @param appCode 应用编号
     * @param nos 字典数据IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除字典数据
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除字典数据
     *
     * @param appCode 应用编号
     * @param no 字典数据ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除字典数据
     *
     * @param appCode 应用编号
     * @param nos 字典数据IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除字典数据
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
