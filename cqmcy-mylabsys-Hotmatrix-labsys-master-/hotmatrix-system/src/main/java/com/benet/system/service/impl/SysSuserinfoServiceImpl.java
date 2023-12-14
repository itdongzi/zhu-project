package com.benet.system.service.impl;

import java.util.List;

import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysSuserinfoMapper;
import com.benet.system.domain.SysSuserinfo;
import com.benet.system.service.ISysSuserinfoService;

/**
 * 系统用户信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysSuserinfoServiceImpl implements ISysSuserinfoService 
{
    @Autowired
    private SysSuserinfoMapper sysSuserinfoMapper;

    /**
     * 查询所有系统用户信息列表
     *
     * @param appCode 应用编号
     * @return 系统用户信息集合
     */
    @Override
    public List<SysSuserinfo> getAllRecords(String appCode) {
        return sysSuserinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询系统用户信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 系统用户信息集合
     */
    @Override
    public List<SysSuserinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysSuserinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询系统用户信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 系统用户信息集合
     */
    @Override
    public List<SysSuserinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysSuserinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询系统用户信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 系统用户信息集合
     */
    @Override
    public List<SysSuserinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

        PagingModel model = new PagingModel();
        model.setPageIndex((pageIndex-1) * pageSize);
        model.setPageSize(pageSize);
        model.setCondition(condition);
        if (StringUtils.isEmpty(orderField)) {
            model.setOrderField("id");
        } else {
            model.setOrderField(orderField);
        }
        if (StringUtils.isEmpty(orderType)) {
            model.setOrderType("Asc");
        } else {
            model.setOrderType(orderType);
        }
        return sysSuserinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询系统用户信息
     *
     * @param appCode 应用编号
     * @param no 系统用户信息ID
     * @return 系统用户信息
     */
    @Override
    public SysSuserinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysSuserinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询系统用户信息名称
     *
     * @param appCode 应用编号
     * @param no 系统用户信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysSuserinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询系统用户信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysSuserinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增系统用户信息
     *
     * @param appCode 应用编号
     * @param info 系统用户信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysSuserinfo info) {

        if(sysSuserinfoMapper.checkLoginNameUnique(info.getLoginName())<1) {
            info.setCreateTime(DateTimeUtils.getNowDate());
            info.setUpdateTime(DateTimeUtils.getNowDate());
            info.setAppCode(appCode);
            info.setVersion(1L);
            return sysSuserinfoMapper.AddNewRecord(info);
        }
        return 0;
    }

    /**
     * 更新系统用户信息
     *
     * @param appCode 应用编号
     * @param info 系统用户信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysSuserinfo info) {

        info.setLoginName(null);//不能修改账号
        info.setPassword(null);//不能修改密码
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysSuserinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除系统用户信息
     *
     * @param appCode 应用编号
     * @param no 系统用户信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysSuserinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除系统用户信息
     *
     * @param appCode 应用编号
     * @param nos 系统用户信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysSuserinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除系统用户信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysSuserinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除系统用户信息
     *
     * @param appCode 应用编号
     * @param no 系统用户信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysSuserinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除系统用户信息
     *
     * @param appCode 应用编号
     * @param nos 系统用户信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysSuserinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除系统用户信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysSuserinfoMapper.SoftDeleteByCondition(appCode,condition);
    }


    /**
     * 通过用户名查询用户
     *
     * @param loginName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysSuserinfo getRecordByLoginName(String loginName){
        return sysSuserinfoMapper.getRecordByLoginName(loginName);
    }


    /**
     * 通过手机号码查询用户
     *
     * @param telephone 手机号码
     * @return 用户对象信息
     */
    @Override
    public SysSuserinfo getRecordByTelephone(String telephone){
        return sysSuserinfoMapper.getRecordByTelephone(telephone);
    }


    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象信息
     */
    public SysSuserinfo getRecordByEmail(String email){
        return sysSuserinfoMapper.getRecordByEmail(email);
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 登录名称
     * @return 结果
     */
    @Override
    public int checkLoginNameUnique(String loginName){
        return sysSuserinfoMapper.checkLoginNameUnique(loginName);
    }

    /**
     * 校验手机号码是否唯一
     *
     * @param telephone 手机号码
     * @return 结果
     */
    @Override
    public int checkTelephoneUnique(String telephone){
        return sysSuserinfoMapper.checkTelephoneUnique(telephone);
    }

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    @Override
    public int checkEmailUnique(String email){
        return sysSuserinfoMapper.checkEmailUnique(email);
    }

    /**
     * 修改用户密码信息
     *
     * @param userNo 用户Id
     * @param password 密码
     * @return 结果
     */
    @Override
    public int resetUserPassword(String userNo,String password){
        return sysSuserinfoMapper.resetUserPassword(userNo,password);
    }
}
