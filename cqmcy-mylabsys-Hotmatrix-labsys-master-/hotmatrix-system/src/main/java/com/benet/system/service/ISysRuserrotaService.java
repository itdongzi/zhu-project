package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysRuserrota;

/**
 * 注册用户排班Service接口
 * 
 * @author yoxking
 * @date 2020-10-27
 */
public interface ISysRuserrotaService 
{
    /**
     * 查询所有注册用户排班列表
     *
     * @param appCode 应用编号
     * @return 注册用户排班集合
     */
    public List<SysRuserrota> getAllRecords(String appCode);

    /**
     * 按分类查询注册用户排班列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 注册用户排班集合
     */
    public List<SysRuserrota> getRecordsByClassNo(String appCode, String classNo);

    /**
     * 分页查询注册用户排班列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 注册用户排班集合
     */
    public List<SysRuserrota> getRecordsByPaging(String appCode, PagingModel model);

    /**
     * 分页查询注册用户排班列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 注册用户排班集合
     */
    public List<SysRuserrota> getRecordsByPaging(String appCode, int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询注册用户排班
     *
     * @param appCode 应用编号
     * @param no 注册用户排班ID
     * @return 注册用户排班
     */
    public SysRuserrota getRecordByNo(String appCode, String no);

    /**
     * 查询注册用户排班名称
     *
     * @param appCode 应用编号
     * @param no 注册用户排班ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode, String no);

    /**
     * 查询注册用户排班计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode, String condition);

    /**
     * 新增注册用户排班
     *
     * @param appCode 应用编号
     * @param info 注册用户排班
     * @return 结果
     */
    public int AddNewRecord(String appCode, SysRuserrota info);

    /**
     * 更新注册用户排班
     *
     * @param appCode 应用编号
     * @param info 注册用户排班
     * @return 结果
     */
    public int UpdateRecord(String appCode, SysRuserrota info);

    /**
     * 硬删除注册用户排班
     *
     * @param appCode 应用编号
     * @param no 注册用户排班ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode, String no);

    /**
     * 批量硬删除注册用户排班
     *
     * @param appCode 应用编号
     * @param nos 注册用户排班IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode, String[] nos);

    /**
     * 按条件硬删除注册用户排班
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode, String condition);

    /**
     * 软删除注册用户排班
     *
     * @param appCode 应用编号
     * @param no 注册用户排班ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode, String no);

    /**
     * 批量软删除注册用户排班
     *
     * @param appCode 应用编号
     * @param nos 注册用户排班IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode, String[] nos);

    /**
     * 按条件软删除注册用户排班
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode, String condition);
}
