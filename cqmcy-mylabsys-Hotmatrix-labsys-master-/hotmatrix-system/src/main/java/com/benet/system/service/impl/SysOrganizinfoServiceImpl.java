package com.benet.system.service.impl;

import java.util.List;

import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysOrganizinfoMapper;
import com.benet.system.domain.SysOrganizinfo;
import com.benet.system.service.ISysOrganizinfoService;

/**
 * 机构信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysOrganizinfoServiceImpl implements ISysOrganizinfoService 
{
    @Autowired
    private SysOrganizinfoMapper sysOrganizinfoMapper;

    /**
     * 查询所有机构信息列表
     *
     * @param appCode 应用编号
     * @return 机构信息集合
     */
    @Override
    public List<SysOrganizinfo> getAllRecords(String appCode) {
        return sysOrganizinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询机构信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 机构信息集合
     */
    @Override
    public List<SysOrganizinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysOrganizinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询机构信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 机构信息集合
     */
    @Override
    public List<SysOrganizinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysOrganizinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询机构信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 机构信息集合
     */
    @Override
    public List<SysOrganizinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysOrganizinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询机构信息
     *
     * @param appCode 应用编号
     * @param no 机构信息ID
     * @return 机构信息
     */
    @Override
    public SysOrganizinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysOrganizinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询机构信息名称
     *
     * @param appCode 应用编号
     * @param no 机构信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysOrganizinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询机构信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysOrganizinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增机构信息
     *
     * @param appCode 应用编号
     * @param info 机构信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysOrganizinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return sysOrganizinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新机构信息
     *
     * @param appCode 应用编号
     * @param info 机构信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysOrganizinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysOrganizinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除机构信息
     *
     * @param appCode 应用编号
     * @param no 机构信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysOrganizinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除机构信息
     *
     * @param appCode 应用编号
     * @param nos 机构信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysOrganizinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除机构信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysOrganizinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除机构信息
     *
     * @param appCode 应用编号
     * @param no 机构信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysOrganizinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除机构信息
     *
     * @param appCode 应用编号
     * @param nos 机构信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysOrganizinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除机构信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysOrganizinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
