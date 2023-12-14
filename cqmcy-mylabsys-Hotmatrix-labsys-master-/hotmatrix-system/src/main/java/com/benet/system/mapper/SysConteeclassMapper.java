package com.benet.system.mapper;

import java.util.List;

import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysConteeclass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 内容类型Mapper接口
 *
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysConteeclassMapper {
    /**
     * 查询所有内容类型列表
     *
     * @param appCode 应用编号
     * @return 内容类型集合
     */
    public List<SysConteeclass> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询内容类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 内容类型集合
     */
    public List<SysConteeclass> getRecordsByClassNo(@Param("appCode") String appCode, @Param("classNo") String classNo);

    /**
     * 分页查询内容类型列表
     *
     * @param appCode 应用编号
     * @param model   分页模型
     * @return 内容类型集合
     */
    public List<SysConteeclass> getRecordsByPaging(@Param("appCode") String appCode, @Param("model") PagingModel model);

    /**
     * 查询内容类型
     *
     * @param appCode 应用编号
     * @param no      内容类型ID
     * @return 内容类型
     */
    public SysConteeclass getRecordByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询内容类型名称
     *
     * @param appCode 应用编号
     * @param no      内容类型ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询内容类型计数
     *
     * @param appCode   应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 新增内容类型
     *
     * @param info 内容类型
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysConteeclass info);

    /**
     * 更新内容类型
     *
     * @param info 内容类型
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysConteeclass info);

    /**
     * 硬删除内容类型
     *
     * @param appCode 应用编号
     * @param no      内容类型ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量硬删除内容类型
     *
     * @param appCode 应用编号
     * @param nos     内容类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件硬删除内容类型
     *
     * @param appCode   应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 软删除内容类型
     *
     * @param appCode 应用编号
     * @param no      内容类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量软删除内容类型
     *
     * @param appCode 应用编号
     * @param nos     内容类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件软删除内容类型
     *
     * @param appCode   应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);


    @Select("select class_name from sys_conteeclass where delete_flag = '1' and app_code = #{appCode} and class_no = #{classNo}")
    String getClassNameByClassNo(@Param("appCode") String appCode, @Param("classNo") String classNo);

}
