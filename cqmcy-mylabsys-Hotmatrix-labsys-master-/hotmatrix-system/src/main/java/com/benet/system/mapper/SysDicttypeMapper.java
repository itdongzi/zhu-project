package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysDicttype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 字典类型Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-23
 */
@Mapper
public interface SysDicttypeMapper 
{
    /**
     * 查询所有字典类型列表
     *
     * @param appCode 应用编号
     * @return 字典类型集合
     */
    public List<SysDicttype> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询字典类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 字典类型集合
     */
    public List<SysDicttype> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询字典类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 字典类型集合
     */
    public List<SysDicttype> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询字典类型
     *
     * @param appCode 应用编号
     * @param no 字典类型ID
     * @return 字典类型
     */
    public SysDicttype getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询字典类型名称
     *
     * @param appCode 应用编号
     * @param no 字典类型ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询字典类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增字典类型
     *
     * @param info 字典类型
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysDicttype info);

    /**
     * 更新字典类型
     *
     * @param info 字典类型
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysDicttype info);

    /**
     * 硬删除字典类型
     *
     * @param appCode 应用编号
     * @param no 字典类型ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除字典类型
     *
     * @param appCode 应用编号
     * @param nos 字典类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除字典类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除字典类型
     *
     * @param appCode 应用编号
     * @param no 字典类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除字典类型
     *
     * @param appCode 应用编号
     * @param nos 字典类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除字典类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
