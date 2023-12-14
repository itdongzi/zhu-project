package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysRuserinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 注册用户信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysRuserinfoMapper 
{
    /**
     * 查询所有注册用户信息列表
     *
     * @param appCode 应用编号
     * @return 注册用户信息集合
     */
    public List<SysRuserinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询注册用户信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 注册用户信息集合
     */
    public List<SysRuserinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询注册用户信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 注册用户信息集合
     */
    public List<SysRuserinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询注册用户信息
     *
     * @param appCode 应用编号
     * @param no 注册用户信息ID
     * @return 注册用户信息
     */
    public SysRuserinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询注册用户信息名称
     *
     * @param appCode 应用编号
     * @param no 注册用户信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询注册用户信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增注册用户信息
     *
     * @param info 注册用户信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysRuserinfo info);

    /**
     * 更新注册用户信息
     *
     * @param info 注册用户信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysRuserinfo info);

    /**
     * 硬删除注册用户信息
     *
     * @param appCode 应用编号
     * @param no 注册用户信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除注册用户信息
     *
     * @param appCode 应用编号
     * @param nos 注册用户信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除注册用户信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除注册用户信息
     *
     * @param appCode 应用编号
     * @param no 注册用户信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除注册用户信息
     *
     * @param appCode 应用编号
     * @param nos 注册用户信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除注册用户信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);


    /**
     * 通过用户名查询用户
     *
     * @param loginName 用户名
     * @return 用户对象信息
     */
    public SysRuserinfo getRecordByLoginName(@Param("loginName") String loginName);


    /**
     * 通过手机号码查询用户
     *
     * @param telephone 手机号码
     * @return 用户对象信息
     */
    public SysRuserinfo getRecordByTelephone(@Param("telephone") String telephone);


    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象信息
     */
    public SysRuserinfo getRecordByEmail(@Param("email") String email);

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 登录名称
     * @return 结果
     */
    public int checkLoginNameUnique(@Param("loginName") String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param telephone 手机号码
     * @return 结果
     */
    public int checkTelephoneUnique(@Param("telephone") String telephone);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    public int checkEmailUnique(@Param("email") String email);

    /**
     * 修改用户密码信息
     *
     * @param userNo 用户Id
     * @param password 密码
     * @return 结果
     */
    public int resetUserPassword(@Param("userNo") String userNo,@Param("password") String password);
}
