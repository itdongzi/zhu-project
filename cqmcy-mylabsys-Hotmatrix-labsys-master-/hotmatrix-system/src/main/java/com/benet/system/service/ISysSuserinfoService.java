package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysSuserinfo;

/**
 * 系统用户信息Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysSuserinfoService 
{
    /**
     * 查询所有系统用户信息列表
     *
     * @param appCode 应用编号
     * @return 系统用户信息集合
     */
    public List<SysSuserinfo> getAllRecords(String appCode);

    /**
     * 按分类查询系统用户信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 系统用户信息集合
     */
    public List<SysSuserinfo> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询系统用户信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 系统用户信息集合
     */
    public List<SysSuserinfo> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询系统用户信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 系统用户信息集合
     */
    public List<SysSuserinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询系统用户信息
     *
     * @param appCode 应用编号
     * @param no 系统用户信息ID
     * @return 系统用户信息
     */
    public SysSuserinfo getRecordByNo(String appCode,String no);

    /**
     * 查询系统用户信息名称
     *
     * @param appCode 应用编号
     * @param no 系统用户信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询系统用户信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增系统用户信息
     *
     * @param appCode 应用编号
     * @param info 系统用户信息
     * @return 结果
     */
    public int AddNewRecord(String appCode,SysSuserinfo info);

    /**
     * 更新系统用户信息
     *
     * @param appCode 应用编号
     * @param info 系统用户信息
     * @return 结果
     */
    public int UpdateRecord(String appCode,SysSuserinfo info);

    /**
     * 硬删除系统用户信息
     *
     * @param appCode 应用编号
     * @param no 系统用户信息ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除系统用户信息
     *
     * @param appCode 应用编号
     * @param nos 系统用户信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除系统用户信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除系统用户信息
     *
     * @param appCode 应用编号
     * @param no 系统用户信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除系统用户信息
     *
     * @param appCode 应用编号
     * @param nos 系统用户信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除系统用户信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);


    /**
     * 通过用户名查询用户
     *
     * @param loginName 用户名
     * @return 用户对象信息
     */
    public SysSuserinfo getRecordByLoginName(String loginName);


    /**
     * 通过手机号码查询用户
     *
     * @param telephone 手机号码
     * @return 用户对象信息
     */
    public SysSuserinfo getRecordByTelephone(String telephone);


    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象信息
     */
    public SysSuserinfo getRecordByEmail(String email);

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 登录名称
     * @return 结果
     */
    public int checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param telephone 手机号码
     * @return 结果
     */
    public int checkTelephoneUnique(String telephone);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    public int checkEmailUnique(String email);


    /**
     * 修改用户密码信息
     *
     * @param userNo 用户Id
     * @param password 密码
     * @return 结果
     */
    public int resetUserPassword(String userNo,String password);
}
