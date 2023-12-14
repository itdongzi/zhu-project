package com.benet.record.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.record.mapper.CssdEquipmentsMapper;
import com.benet.record.domain.CssdEquipments;
import com.benet.record.service.ICssdEquipmentsService;

/**
 * 器械信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Service
public class CssdEquipmentsServiceImpl implements ICssdEquipmentsService 
{
    @Autowired
    private CssdEquipmentsMapper cssdEquipmentsMapper;

    /**
     * 查询所有器械信息列表
     *
     * @param appCode 应用编号
     * @return 器械信息集合
     */
    @Override
    public List<CssdEquipments> getAllRecords(String appCode) {
        return cssdEquipmentsMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询器械信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 器械信息集合
     */
    @Override
    public List<CssdEquipments> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdEquipmentsMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询器械信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 器械信息集合
     */
    @Override
    public List<CssdEquipments> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdEquipmentsMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询器械信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 器械信息集合
     */
    @Override
    public List<CssdEquipments> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdEquipmentsMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询器械信息
     *
     * @param appCode 应用编号
     * @param no 器械信息ID
     * @return 器械信息
     */
    @Override
    public CssdEquipments getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdEquipmentsMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询器械信息名称
     *
     * @param appCode 应用编号
     * @param no 器械信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdEquipmentsMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询器械信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdEquipmentsMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增器械信息
     *
     * @param appCode 应用编号
     * @param info 器械信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdEquipments info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdEquipmentsMapper.AddNewRecord(info);
    }

    /**
     * 更新器械信息
     *
     * @param appCode 应用编号
     * @param info 器械信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdEquipments info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdEquipmentsMapper.UpdateRecord(info);
    }

    /**
     * 硬删除器械信息
     *
     * @param appCode 应用编号
     * @param no 器械信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdEquipmentsMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除器械信息
     *
     * @param appCode 应用编号
     * @param nos 器械信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdEquipmentsMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除器械信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdEquipmentsMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除器械信息
     *
     * @param appCode 应用编号
     * @param no 器械信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdEquipmentsMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除器械信息
     *
     * @param appCode 应用编号
     * @param nos 器械信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdEquipmentsMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除器械信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdEquipmentsMapper.SoftDeleteByCondition(appCode,condition);
    }
}
