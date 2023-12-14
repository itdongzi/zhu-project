package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysRotaclass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 值班类型Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Mapper
public interface SysRotaclassMapper 
{
    /**
     * 查询所有值班类型列表
     *
     * @param appCode 应用编号
     * @return 值班类型集合
     */
    public List<SysRotaclass> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询值班类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 值班类型集合
     */
    public List<SysRotaclass> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询值班类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 值班类型集合
     */
    public List<SysRotaclass> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询值班类型
     *
     * @param appCode 应用编号
     * @param no 值班类型ID
     * @return 值班类型
     */
    public SysRotaclass getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询值班类型名称
     *
     * @param appCode 应用编号
     * @param no 值班类型ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询值班类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增值班类型
     *
     * @param info 值班类型
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysRotaclass info);

    /**
     * 更新值班类型
     *
     * @param info 值班类型
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysRotaclass info);

    /**
     * 硬删除值班类型
     *
     * @param appCode 应用编号
     * @param no 值班类型ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除值班类型
     *
     * @param appCode 应用编号
     * @param nos 值班类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除值班类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除值班类型
     *
     * @param appCode 应用编号
     * @param no 值班类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除值班类型
     *
     * @param appCode 应用编号
     * @param nos 值班类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除值班类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
