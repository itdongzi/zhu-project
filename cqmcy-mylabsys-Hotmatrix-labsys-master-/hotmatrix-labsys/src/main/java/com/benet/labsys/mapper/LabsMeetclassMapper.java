package com.benet.labsys.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsMeetclass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 会议室类型Mapper接口
 * 
 * @author yoxking
 * @date 2023-02-24 09:46:53
 */
@Mapper
public interface LabsMeetclassMapper 
{
    /**
     * 查询所有会议室类型列表
     *
     * @param appCode 应用编号
     * @return 会议室类型集合
     */
    public List<LabsMeetclass> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询会议室类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 会议室类型集合
     */
    public List<LabsMeetclass> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询会议室类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 会议室类型集合
     */
    public List<LabsMeetclass> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询会议室类型
     *
     * @param appCode 应用编号
     * @param no 会议室类型ID
     * @return 会议室类型
     */
    public LabsMeetclass getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询会议室类型名称
     *
     * @param appCode 应用编号
     * @param no 会议室类型ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询会议室类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增会议室类型
     *
     * @param info 会议室类型
     * @return 结果
     */
    public int AddNewRecord(@Param("info") LabsMeetclass info);

    /**
     * 更新会议室类型
     *
     * @param info 会议室类型
     * @return 结果
     */
    public int UpdateRecord(@Param("info") LabsMeetclass info);

    /**
     * 硬删除会议室类型
     *
     * @param appCode 应用编号
     * @param no 会议室类型ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除会议室类型
     *
     * @param appCode 应用编号
     * @param nos 会议室类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除会议室类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除会议室类型
     *
     * @param appCode 应用编号
     * @param no 会议室类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除会议室类型
     *
     * @param appCode 应用编号
     * @param nos 会议室类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除会议室类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
