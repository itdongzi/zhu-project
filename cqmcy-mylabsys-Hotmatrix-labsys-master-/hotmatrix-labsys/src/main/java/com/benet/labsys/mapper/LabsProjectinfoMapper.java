package com.benet.labsys.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsProjectinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 申报项目信息Mapper接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Mapper
public interface LabsProjectinfoMapper 
{
    /**
     * 查询所有申报项目信息列表
     *
     * @param appCode 应用编号
     * @return 申报项目信息集合
     */
    public List<LabsProjectinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询申报项目信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 申报项目信息集合
     */
    public List<LabsProjectinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询申报项目信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 申报项目信息集合
     */
    public List<LabsProjectinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询申报项目信息
     *
     * @param appCode 应用编号
     * @param no 申报项目信息ID
     * @return 申报项目信息
     */
    public LabsProjectinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询申报项目信息名称
     *
     * @param appCode 应用编号
     * @param no 申报项目信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询申报项目信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增申报项目信息
     *
     * @param info 申报项目信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") LabsProjectinfo info);

    /**
     * 更新申报项目信息
     *
     * @param info 申报项目信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") LabsProjectinfo info);

    /**
     * 硬删除申报项目信息
     *
     * @param appCode 应用编号
     * @param no 申报项目信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除申报项目信息
     *
     * @param appCode 应用编号
     * @param nos 申报项目信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除申报项目信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除申报项目信息
     *
     * @param appCode 应用编号
     * @param no 申报项目信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除申报项目信息
     *
     * @param appCode 应用编号
     * @param nos 申报项目信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除申报项目信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
