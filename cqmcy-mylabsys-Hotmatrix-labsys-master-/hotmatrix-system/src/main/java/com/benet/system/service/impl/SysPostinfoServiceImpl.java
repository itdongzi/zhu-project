package com.benet.system.service.impl;

import java.util.List;

import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysPostinfoMapper;
import com.benet.system.domain.SysPostinfo;
import com.benet.system.service.ISysPostinfoService;

/**
 * 岗位信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysPostinfoServiceImpl implements ISysPostinfoService 
{
    @Autowired
    private SysPostinfoMapper sysPostinfoMapper;

    /**
     * 查询所有岗位信息列表
     *
     * @param appCode 应用编号
     * @return 岗位信息集合
     */
    @Override
    public List<SysPostinfo> getAllRecords(String appCode) {
        return sysPostinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询岗位信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 岗位信息集合
     */
    @Override
    public List<SysPostinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysPostinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询岗位信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 岗位信息集合
     */
    @Override
    public List<SysPostinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysPostinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询岗位信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 岗位信息集合
     */
    @Override
    public List<SysPostinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysPostinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询岗位信息
     *
     * @param appCode 应用编号
     * @param no 岗位信息ID
     * @return 岗位信息
     */
    @Override
    public SysPostinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysPostinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询岗位信息名称
     *
     * @param appCode 应用编号
     * @param no 岗位信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysPostinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询岗位信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysPostinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增岗位信息
     *
     * @param appCode 应用编号
     * @param info 岗位信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysPostinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return sysPostinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新岗位信息
     *
     * @param appCode 应用编号
     * @param info 岗位信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysPostinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysPostinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除岗位信息
     *
     * @param appCode 应用编号
     * @param no 岗位信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysPostinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除岗位信息
     *
     * @param appCode 应用编号
     * @param nos 岗位信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysPostinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除岗位信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysPostinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除岗位信息
     *
     * @param appCode 应用编号
     * @param no 岗位信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysPostinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除岗位信息
     *
     * @param appCode 应用编号
     * @param nos 岗位信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysPostinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除岗位信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysPostinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
