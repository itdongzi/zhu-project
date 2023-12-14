package com.benet.record.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.record.mapper.CssdEquipclassMapper;
import com.benet.record.domain.CssdEquipclass;
import com.benet.record.service.ICssdEquipclassService;

/**
 * 器械类型Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Service
public class CssdEquipclassServiceImpl implements ICssdEquipclassService 
{
    @Autowired
    private CssdEquipclassMapper cssdEquipclassMapper;

    /**
     * 查询所有器械类型列表
     *
     * @param appCode 应用编号
     * @return 器械类型集合
     */
    @Override
    public List<CssdEquipclass> getAllRecords(String appCode) {
        return cssdEquipclassMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询器械类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 器械类型集合
     */
    @Override
    public List<CssdEquipclass> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdEquipclassMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询器械类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 器械类型集合
     */
    @Override
    public List<CssdEquipclass> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdEquipclassMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询器械类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 器械类型集合
     */
    @Override
    public List<CssdEquipclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdEquipclassMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询器械类型
     *
     * @param appCode 应用编号
     * @param no 器械类型ID
     * @return 器械类型
     */
    @Override
    public CssdEquipclass getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdEquipclassMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询器械类型名称
     *
     * @param appCode 应用编号
     * @param no 器械类型ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdEquipclassMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询器械类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdEquipclassMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增器械类型
     *
     * @param appCode 应用编号
     * @param info 器械类型
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdEquipclass info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdEquipclassMapper.AddNewRecord(info);
    }

    /**
     * 更新器械类型
     *
     * @param appCode 应用编号
     * @param info 器械类型
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdEquipclass info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdEquipclassMapper.UpdateRecord(info);
    }

    /**
     * 硬删除器械类型
     *
     * @param appCode 应用编号
     * @param no 器械类型ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdEquipclassMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除器械类型
     *
     * @param appCode 应用编号
     * @param nos 器械类型IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdEquipclassMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除器械类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdEquipclassMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除器械类型
     *
     * @param appCode 应用编号
     * @param no 器械类型ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdEquipclassMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除器械类型
     *
     * @param appCode 应用编号
     * @param nos 器械类型IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdEquipclassMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除器械类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdEquipclassMapper.SoftDeleteByCondition(appCode,condition);
    }
}
