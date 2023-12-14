package com.benet.system.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import com.benet.system.domain.SysRolepermit;
import com.benet.system.domain.SysSuserrole;
import com.benet.system.mapper.SysRolepermitMapper;
import com.benet.system.mapper.SysSuserroleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysRoleinfoMapper;
import com.benet.system.domain.SysRoleinfo;
import com.benet.system.service.ISysRoleinfoService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysRoleinfoServiceImpl implements ISysRoleinfoService 
{
    @Autowired
    private SysRoleinfoMapper sysRoleinfoMapper;

    @Autowired
    private SysRolepermitMapper sysRolepermitMapper;

    @Autowired
    private SysSuserroleMapper sysSuserroleMapper;

    /**
     * 查询所有角色信息列表
     *
     * @param appCode 应用编号
     * @return 角色信息集合
     */
    @Override
    public List<SysRoleinfo> getAllRecords(String appCode) {
        return sysRoleinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询角色信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 角色信息集合
     */
    @Override
    public List<SysRoleinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysRoleinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询角色信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 角色信息集合
     */
    @Override
    public List<SysRoleinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysRoleinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询角色信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 角色信息集合
     */
    @Override
    public List<SysRoleinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysRoleinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询角色信息
     *
     * @param appCode 应用编号
     * @param no 角色信息ID
     * @return 角色信息
     */
    @Override
    public SysRoleinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRoleinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询角色信息名称
     *
     * @param appCode 应用编号
     * @param no 角色信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRoleinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询角色信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysRoleinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增角色信息
     *
     * @param appCode 应用编号
     * @param info 角色信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysRoleinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return sysRoleinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新角色信息
     *
     * @param appCode 应用编号
     * @param info 角色信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysRoleinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysRoleinfoMapper.UpdateRecord(info);
    }

    /**
     * 更新角色用户信息
     *
     * @param appCode 应用编号
     * @param roleNo 角色编号
     * @param suerNos 用户列表信息
     * @return 结果
     */
    @Override
    @Transactional
    public int UpdateSusers(String appCode,String roleNo,String[] suerNos){

        int count=0;
        sysSuserroleMapper.HardDeleteByRoleNo(appCode,roleNo);
        SysSuserrole sr=null;
        for (String suerNo:suerNos) {
            sr=new SysSuserrole();
            sr.setUserNo(suerNo);
            sr.setRoleNo(roleNo);
            sr.setBranchNo(appCode);
            sr.setCreateBy("");
            sr.setCreateTime(DateTimeUtils.getNowDate());
            sr.setUpdateBy("");
            sr.setUpdateTime(DateTimeUtils.getNowDate());
            sr.setDeleteFlag("1");
            sr.setComments("");
            sr.setAppCode(appCode);
            sr.setVersion(1L);

            count+=sysSuserroleMapper.AddNewRecord(sr);
        }
        return count;
    }

    /**
     * 更新角色权限信息
     *
     * @param appCode 应用编号
     * @param roleNo 角色编号
     * @param permitNos 权限列表信息
     * @return 结果
     */
    @Override
    @Transactional
    public int UpdatePermits(String appCode,String roleNo,String[] permitNos){

        int count=0;
        sysRolepermitMapper.HardDeleteByRoleNo(appCode,roleNo);
        SysRolepermit rp=null;
        for (String permitNo:permitNos) {
            rp=new SysRolepermit();
            rp.setPermitNo(permitNo);
            rp.setRoleNo(roleNo);
            rp.setBranchNo(appCode);
            rp.setCreateBy("");
            rp.setCreateTime(DateTimeUtils.getNowDate());
            rp.setUpdateBy("");
            rp.setUpdateTime(DateTimeUtils.getNowDate());
            rp.setDeleteFlag("1");
            rp.setComments("");
            rp.setAppCode(appCode);
            rp.setVersion(1L);

            count+=sysRolepermitMapper.AddNewRecord(rp);
        }
        return count;
    }

    /**
     * 硬删除角色信息
     *
     * @param appCode 应用编号
     * @param no 角色信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRoleinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除角色信息
     *
     * @param appCode 应用编号
     * @param nos 角色信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysRoleinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除角色信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysRoleinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除角色信息
     *
     * @param appCode 应用编号
     * @param no 角色信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRoleinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除角色信息
     *
     * @param appCode 应用编号
     * @param nos 角色信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysRoleinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除角色信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysRoleinfoMapper.SoftDeleteByCondition(appCode,condition);
    }

    /**
     * 根据用户ID查询角色
     *
     * @param appCode 应用编号
     * @param userNo 用户ID
     * @return 角色列表
     */
    @Override
    public List<SysRoleinfo> getRecordsByUserNo(String appCode,String userNo){
        return sysRoleinfoMapper.getRecordsByUserNo(appCode,userNo);
    }

    /**
     * 根据用户ID查询角色
     *
     * @param appCode 应用编号
     * @param userNo 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> getRoleCodesByUserNo(String appCode,String userNo){

        List<String> roles = sysRoleinfoMapper.getRoleCodesByUserNo(appCode,userNo);
        Set<String> rolesSet = new HashSet<>();
        for (String role : roles)
        {
            if (StringUtils.isNotEmpty(role))
            {
                rolesSet.addAll(Arrays.asList(role.trim().split(",")));
            }
        }
        return rolesSet;
    }


    /**
     * 根据用户ID查询角色标识
     *
     * @param appCode 应用编号
     * @param roleNo 角色ID
     * @return 用户列表
     */
    @Override
    public List<String> getSuserNosByRoleNo(String appCode,String roleNo){

        return sysRoleinfoMapper.getSuserNosByRoleNo(appCode,roleNo);
    }


    /**
     * 根据用户ID查询角色标识
     *
     * @param appCode 应用编号
     * @param roleNo 角色ID
     * @return 权限列表
     */
    @Override
    public List<String> getPermitNosByRoleNo(String appCode,String roleNo){

        return sysRoleinfoMapper.getPermitNosByRoleNo(appCode,roleNo);
    }

    /**
     * 校验角色名称是否唯一
     *
     * @param appCode 应用编号
     * @param roleName 角色名称
     * @return 角色信息
     */
    @Override
    public int checkRoleNameUnique(String appCode,String roleName){
        return sysRoleinfoMapper.checkRoleNameUnique(appCode,roleName);
    }

    /**
     * 校验角色权限是否唯一
     *
     * @param appCode 应用编号
     * @param roleCode 角色权限
     * @return 角色信息
     */
    @Override
    public int checkRoleCodeUnique(String appCode,String roleCode){
        return sysRoleinfoMapper.checkRoleCodeUnique(appCode,roleCode);
    }
}
