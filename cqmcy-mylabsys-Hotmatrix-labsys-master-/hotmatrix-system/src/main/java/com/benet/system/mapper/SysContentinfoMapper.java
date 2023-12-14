package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysContentinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 内容信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysContentinfoMapper 
{
    /**
     * 查询所有内容信息列表
     *
     * @param appCode 应用编号
     * @return 内容信息集合
     */
    public List<SysContentinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询内容信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 内容信息集合
     */
    public List<SysContentinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询内容信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 内容信息集合
     */
    public List<SysContentinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询内容信息
     *
     * @param appCode 应用编号
     * @param no 内容信息ID
     * @return 内容信息
     */
    public SysContentinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询内容信息名称
     *
     * @param appCode 应用编号
     * @param no 内容信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询内容信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增内容信息
     *
     * @param info 内容信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysContentinfo info);

    /**
     * 更新内容信息
     *
     * @param info 内容信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysContentinfo info);

    /**
     * 硬删除内容信息
     *
     * @param appCode 应用编号
     * @param no 内容信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除内容信息
     *
     * @param appCode 应用编号
     * @param nos 内容信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除内容信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除内容信息
     *
     * @param appCode 应用编号
     * @param no 内容信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除内容信息
     *
     * @param appCode 应用编号
     * @param nos 内容信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除内容信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
