package com.benet.record.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.record.mapper.CssdStdunitinfoMapper;
import com.benet.record.domain.CssdStdunitinfo;
import com.benet.record.service.ICssdStdunitinfoService;

/**
 * 单位信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Service
public class CssdStdunitinfoServiceImpl implements ICssdStdunitinfoService 
{
    @Autowired
    private CssdStdunitinfoMapper cssdStdunitinfoMapper;

    /**
     * 查询所有单位信息列表
     *
     * @param appCode 应用编号
     * @return 单位信息集合
     */
    @Override
    public List<CssdStdunitinfo> getAllRecords(String appCode) {
        return cssdStdunitinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询单位信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 单位信息集合
     */
    @Override
    public List<CssdStdunitinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdStdunitinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询单位信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 单位信息集合
     */
    @Override
    public List<CssdStdunitinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdStdunitinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询单位信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 单位信息集合
     */
    @Override
    public List<CssdStdunitinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdStdunitinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询单位信息
     *
     * @param appCode 应用编号
     * @param no 单位信息ID
     * @return 单位信息
     */
    @Override
    public CssdStdunitinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStdunitinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询单位信息名称
     *
     * @param appCode 应用编号
     * @param no 单位信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStdunitinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询单位信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdStdunitinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增单位信息
     *
     * @param appCode 应用编号
     * @param info 单位信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdStdunitinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdStdunitinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新单位信息
     *
     * @param appCode 应用编号
     * @param info 单位信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdStdunitinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdStdunitinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除单位信息
     *
     * @param appCode 应用编号
     * @param no 单位信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStdunitinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除单位信息
     *
     * @param appCode 应用编号
     * @param nos 单位信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdStdunitinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除单位信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdStdunitinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除单位信息
     *
     * @param appCode 应用编号
     * @param no 单位信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStdunitinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除单位信息
     *
     * @param appCode 应用编号
     * @param nos 单位信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdStdunitinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除单位信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdStdunitinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
