package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsSafetyclassMapper;
import com.benet.labsys.domain.LabsSafetyclass;
import com.benet.labsys.service.ILabsSafetyclassService;

/**
 * 安全资料分类Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Service
public class LabsSafetyclassServiceImpl implements ILabsSafetyclassService 
{
    @Autowired
    private LabsSafetyclassMapper labsSafetyclassMapper;

    /**
     * 查询所有安全资料分类列表
     *
     * @param appCode 应用编号
     * @return 安全资料分类集合
     */
    @Override
    public List<LabsSafetyclass> getAllRecords(String appCode) {
        return labsSafetyclassMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询安全资料分类列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 安全资料分类集合
     */
    @Override
    public List<LabsSafetyclass> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsSafetyclassMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询安全资料分类列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 安全资料分类集合
     */
    @Override
    public List<LabsSafetyclass> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsSafetyclassMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询安全资料分类列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 安全资料分类集合
     */
    @Override
    public List<LabsSafetyclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsSafetyclassMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询安全资料分类
     *
     * @param appCode 应用编号
     * @param no 安全资料分类ID
     * @return 安全资料分类
     */
    @Override
    public LabsSafetyclass getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSafetyclassMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询安全资料分类名称
     *
     * @param appCode 应用编号
     * @param no 安全资料分类ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSafetyclassMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询安全资料分类计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsSafetyclassMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增安全资料分类
     *
     * @param appCode 应用编号
     * @param info 安全资料分类
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsSafetyclass info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsSafetyclassMapper.AddNewRecord(info);
    }

    /**
     * 更新安全资料分类
     *
     * @param appCode 应用编号
     * @param info 安全资料分类
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsSafetyclass info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsSafetyclassMapper.UpdateRecord(info);
    }

    /**
     * 硬删除安全资料分类
     *
     * @param appCode 应用编号
     * @param no 安全资料分类ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSafetyclassMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除安全资料分类
     *
     * @param appCode 应用编号
     * @param nos 安全资料分类IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsSafetyclassMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除安全资料分类
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsSafetyclassMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除安全资料分类
     *
     * @param appCode 应用编号
     * @param no 安全资料分类ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSafetyclassMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除安全资料分类
     *
     * @param appCode 应用编号
     * @param nos 安全资料分类IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsSafetyclassMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除安全资料分类
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsSafetyclassMapper.SoftDeleteByCondition(appCode,condition);
    }
}
