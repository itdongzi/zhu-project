package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysMenusinfoMapper;
import com.benet.system.domain.SysMenusinfo;
import com.benet.system.service.ISysMenusinfoService;

/**
 * 控制台菜单信息Service业务层处理
 * 
 * @author yoxking
 * @date 2022-09-08 14:13:52
 */
@Service
public class SysMenusinfoServiceImpl implements ISysMenusinfoService 
{
    @Autowired
    private SysMenusinfoMapper sysMenusinfoMapper;

    /**
     * 查询所有控制台菜单信息列表
     *
     * @param appCode 应用编号
     * @return 控制台菜单信息集合
     */
    @Override
    public List<SysMenusinfo> getAllRecords(String appCode) {
        return sysMenusinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询控制台菜单信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 控制台菜单信息集合
     */
    @Override
    public List<SysMenusinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysMenusinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }



    /**
     * 按分类查询控制台菜单信息列表
     *
     * @param appCode 应用编号
     * @param parentNo 父类编号
     * @param userType 用户类型
     * @return 控制台菜单信息集合
     */
    @Override
    public List<SysMenusinfo> getRecordsByUserType(String appCode,String parentNo,String userType) {
        if (StringUtils.isNotEmpty(parentNo)) {
            return sysMenusinfoMapper.getRecordsByUserType(appCode,parentNo,userType);
        }
        return null;
    }

    /**
     * 分页查询控制台菜单信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 控制台菜单信息集合
     */
    @Override
    public List<SysMenusinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysMenusinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询控制台菜单信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 控制台菜单信息集合
     */
    @Override
    public List<SysMenusinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysMenusinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询控制台菜单信息
     *
     * @param appCode 应用编号
     * @param no 控制台菜单信息ID
     * @return 控制台菜单信息
     */
    @Override
    public SysMenusinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysMenusinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询控制台菜单信息
     *
     * @param appCode 应用编号
     * @param menuCode 控制台菜单信息Code
     * @return 控制台菜单信息
     */
    @Override
    public SysMenusinfo getRecordByMenuCode(String appCode,String menuCode) {
        if (StringUtils.isNotEmpty(menuCode)) {
            return sysMenusinfoMapper.getRecordByMenuCode(appCode,menuCode);
        }
        return null;
    }

    /**
     * 查询控制台菜单信息名称
     *
     * @param appCode 应用编号
     * @param no 控制台菜单信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysMenusinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询控制台菜单信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysMenusinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增控制台菜单信息
     *
     * @param appCode 应用编号
     * @param info 控制台菜单信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysMenusinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return sysMenusinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新控制台菜单信息
     *
     * @param appCode 应用编号
     * @param info 控制台菜单信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysMenusinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysMenusinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param no 控制台菜单信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysMenusinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param nos 控制台菜单信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysMenusinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysMenusinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param no 控制台菜单信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysMenusinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param nos 控制台菜单信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysMenusinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysMenusinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
