package com.benet.system.service.impl;

import java.util.List;

import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysRenterinfoMapper;
import com.benet.system.domain.SysRenterinfo;
import com.benet.system.service.ISysRenterinfoService;

/**
 * 租户信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysRenterinfoServiceImpl implements ISysRenterinfoService 
{
    @Autowired
    private SysRenterinfoMapper sysRenterinfoMapper;

    /**
     * 查询所有租户信息列表
     *
     * @param appCode 应用编号
     * @return 租户信息集合
     */
    @Override
    public List<SysRenterinfo> getAllRecords(String appCode) {
        return sysRenterinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询租户信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 租户信息集合
     */
    @Override
    public List<SysRenterinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysRenterinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询租户信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 租户信息集合
     */
    @Override
    public List<SysRenterinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysRenterinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询租户信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 租户信息集合
     */
    @Override
    public List<SysRenterinfo> getRecordsByPaging(String appCode,int pageIndex, int pageSize, String condition, String orderField, String orderType) {

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
        return sysRenterinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询租户信息
     *
     * @param appCode 应用编号
     * @param no 租户信息ID
     * @return 租户信息
     */
    @Override
    public SysRenterinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRenterinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询租户信息名称
     *
     * @param appCode 应用编号
     * @param no 租户信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRenterinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询租户信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysRenterinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增租户信息
     *
     * @param appCode 应用编号
     * @param info 租户信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysRenterinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return sysRenterinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新租户信息
     *
     * @param appCode 应用编号
     * @param info 租户信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysRenterinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysRenterinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除租户信息
     *
     * @param appCode 应用编号
     * @param no 租户信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRenterinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除租户信息
     *
     * @param appCode 应用编号
     * @param nos 租户信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysRenterinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除租户信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysRenterinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除租户信息
     *
     * @param appCode 应用编号
     * @param no 租户信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRenterinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除租户信息
     *
     * @param appCode 应用编号
     * @param nos 租户信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysRenterinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除租户信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysRenterinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
