package com.benet.system.service.impl;

import java.util.*;

import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysPermitinfoMapper;
import com.benet.system.domain.SysPermitinfo;
import com.benet.system.service.ISysPermitinfoService;

/**
 * 菜单权限Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysPermitinfoServiceImpl implements ISysPermitinfoService 
{
    @Autowired
    private SysPermitinfoMapper sysPermitinfoMapper;

    /**
     * 查询所有菜单权限列表
     *
     * @param appCode 应用编号
     * @return 菜单权限集合
     */
    @Override
    public List<SysPermitinfo> getAllRecords(String appCode) {
        return sysPermitinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询菜单权限列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 菜单权限集合
     */
    @Override
    public List<SysPermitinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysPermitinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询菜单权限列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 菜单权限集合
     */
    @Override
    public List<SysPermitinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysPermitinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询菜单权限列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 菜单权限集合
     */
    @Override
    public List<SysPermitinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysPermitinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询菜单权限
     *
     * @param appCode 应用编号
     * @param no 菜单权限ID
     * @return 菜单权限
     */
    @Override
    public SysPermitinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysPermitinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询菜单权限名称
     *
     * @param appCode 应用编号
     * @param no 菜单权限ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysPermitinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询菜单权限计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysPermitinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增菜单权限
     *
     * @param appCode 应用编号
     * @param info 菜单权限
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysPermitinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return sysPermitinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新菜单权限
     *
     * @param appCode 应用编号
     * @param info 菜单权限
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysPermitinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysPermitinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除菜单权限
     *
     * @param appCode 应用编号
     * @param no 菜单权限ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysPermitinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除菜单权限
     *
     * @param appCode 应用编号
     * @param nos 菜单权限IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysPermitinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除菜单权限
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysPermitinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除菜单权限
     *
     * @param appCode 应用编号
     * @param no 菜单权限ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysPermitinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除菜单权限
     *
     * @param appCode 应用编号
     * @param nos 菜单权限IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysPermitinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除菜单权限
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysPermitinfoMapper.SoftDeleteByCondition(appCode,condition);
    }

    /**
     * 根据角色ID查询菜单
     *
     * @param appCode 应用编号
     * @param roleNo 角色ID
     * @return 菜单列表
     */
    @Override
    public List<SysPermitinfo> getRecordsByRoleNo(String appCode,String roleNo){
        return sysPermitinfoMapper.getRecordsByRoleNo(appCode,roleNo);
    }

    /**
     * 根据用户ID查询菜单
     *
     * @param appCode 应用编号
     * @param userNo 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysPermitinfo> getRecordsByUserNo(String appCode,String userNo){
        return sysPermitinfoMapper.getRecordsByUserNo(appCode,userNo);
    }

    /**
     * 根据用户ID查询子菜单
     *
     * @param appCode 应用编号
     * @param parentNo 父ID
     * @param userNo 用户ID
     * @return 菜单列表
     */
    public List<SysPermitinfo> getChildrenByUserNo(String appCode,String parentNo,String userNo){
        return sysPermitinfoMapper.getChildrenByUserNo(appCode,parentNo,userNo);
    }


    /**
     * 根据用户ID查询建菜单树
     *
     * @param appCode 应用编号
     * @param parentNo 父ID
     * @param userNo 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysPermitinfo> getPermitTreeByUserNo(String appCode,String parentNo,String userNo) {

        List<SysPermitinfo> permitTree = null;
        SysPermitinfo permit = null;
        List<SysPermitinfo> infoList = getChildrenByUserNo(appCode,parentNo,userNo);

        if (infoList != null && infoList.size() > 0) {
            permitTree = new ArrayList<>();
            for (SysPermitinfo info : infoList) {
                info.setChildren(getPermitTreeByUserNo(appCode,info.getPermitNo(),userNo));
                permitTree.add(info);
            }
        }
        return permitTree;
    }

    /**
     * 根据角色ID查询菜单
     *
     * @param appCode 应用编号
     * @param roleNo 角色ID
     * @return 菜单列表
     */
    @Override
    public Set<String> getPermitCodesByRoleNo(String appCode,String roleNo){

        List<String> permits = sysPermitinfoMapper.getPermitCodesByRoleNo(appCode,roleNo);
        Set<String> permsSet = new HashSet<>();
        for (String permit : permits)
        {
            if (StringUtils.isNotEmpty(permit))
            {
                permsSet.addAll(Arrays.asList(permit.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户ID查询权限
     *
     * @param appCode 应用编号
     * @param userNo 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> getPermitCodesByUserNo(String appCode,String userNo){

        List<String> permits = sysPermitinfoMapper.getPermitCodesByUserNo(appCode,userNo);
        Set<String> permsSet = new HashSet<>();
        for (String permit : permits)
        {
            if (StringUtils.isNotEmpty(permit))
            {
                permsSet.addAll(Arrays.asList(permit.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 校验菜单名称是否唯一
     *
     * @param appCode 应用编号
     * @param permitName 菜单名称
     * @param parentNo 父菜单ID
     * @return 结果
     */
    @Override
    public int checkPermitNameUnique(String appCode,String permitName, String parentNo){
        return sysPermitinfoMapper.checkPermitNameUnique(appCode,permitName,parentNo);
    }
}
