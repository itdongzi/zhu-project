package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsDeviceinfoMapper;
import com.benet.labsys.domain.LabsDeviceinfo;
import com.benet.labsys.service.ILabsDeviceinfoService;

/**
 * 实验室设备Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Service
public class LabsDeviceinfoServiceImpl implements ILabsDeviceinfoService 
{
    @Autowired
    private LabsDeviceinfoMapper labsDeviceinfoMapper;

    /**
     * 查询所有实验室设备列表
     *
     * @param appCode 应用编号
     * @return 实验室设备集合
     */
    @Override
    public List<LabsDeviceinfo> getAllRecords(String appCode) {
        return labsDeviceinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询实验室设备列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 实验室设备集合
     */
    @Override
    public List<LabsDeviceinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsDeviceinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询实验室设备列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 实验室设备集合
     */
    @Override
    public List<LabsDeviceinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsDeviceinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询实验室设备列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 实验室设备集合
     */
    @Override
    public List<LabsDeviceinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsDeviceinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询实验室设备
     *
     * @param appCode 应用编号
     * @param no 实验室设备ID
     * @return 实验室设备
     */
    @Override
    public LabsDeviceinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsDeviceinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询实验室设备名称
     *
     * @param appCode 应用编号
     * @param no 实验室设备ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsDeviceinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询实验室设备计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsDeviceinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增实验室设备
     *
     * @param appCode 应用编号
     * @param info 实验室设备
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsDeviceinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsDeviceinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新实验室设备
     *
     * @param appCode 应用编号
     * @param info 实验室设备
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsDeviceinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsDeviceinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除实验室设备
     *
     * @param appCode 应用编号
     * @param no 实验室设备ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsDeviceinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除实验室设备
     *
     * @param appCode 应用编号
     * @param nos 实验室设备IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsDeviceinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除实验室设备
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsDeviceinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除实验室设备
     *
     * @param appCode 应用编号
     * @param no 实验室设备ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsDeviceinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除实验室设备
     *
     * @param appCode 应用编号
     * @param nos 实验室设备IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsDeviceinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除实验室设备
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsDeviceinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
