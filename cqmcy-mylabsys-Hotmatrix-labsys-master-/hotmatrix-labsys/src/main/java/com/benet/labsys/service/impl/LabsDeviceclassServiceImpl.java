package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsDeviceclassMapper;
import com.benet.labsys.domain.LabsDeviceclass;
import com.benet.labsys.service.ILabsDeviceclassService;

/**
 * 实验室设备类型Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
@Service
public class LabsDeviceclassServiceImpl implements ILabsDeviceclassService 
{
    @Autowired
    private LabsDeviceclassMapper labsDeviceclassMapper;

    /**
     * 查询所有实验室设备类型列表
     *
     * @param appCode 应用编号
     * @return 实验室设备类型集合
     */
    @Override
    public List<LabsDeviceclass> getAllRecords(String appCode) {
        return labsDeviceclassMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询实验室设备类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 实验室设备类型集合
     */
    @Override
    public List<LabsDeviceclass> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsDeviceclassMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询实验室设备类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 实验室设备类型集合
     */
    @Override
    public List<LabsDeviceclass> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsDeviceclassMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询实验室设备类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 实验室设备类型集合
     */
    @Override
    public List<LabsDeviceclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsDeviceclassMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询实验室设备类型
     *
     * @param appCode 应用编号
     * @param no 实验室设备类型ID
     * @return 实验室设备类型
     */
    @Override
    public LabsDeviceclass getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsDeviceclassMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询实验室设备类型名称
     *
     * @param appCode 应用编号
     * @param no 实验室设备类型ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsDeviceclassMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询实验室设备类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsDeviceclassMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增实验室设备类型
     *
     * @param appCode 应用编号
     * @param info 实验室设备类型
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsDeviceclass info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsDeviceclassMapper.AddNewRecord(info);
    }

    /**
     * 更新实验室设备类型
     *
     * @param appCode 应用编号
     * @param info 实验室设备类型
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsDeviceclass info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsDeviceclassMapper.UpdateRecord(info);
    }

    /**
     * 硬删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param no 实验室设备类型ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsDeviceclassMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param nos 实验室设备类型IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsDeviceclassMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsDeviceclassMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param no 实验室设备类型ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsDeviceclassMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param nos 实验室设备类型IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsDeviceclassMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除实验室设备类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsDeviceclassMapper.SoftDeleteByCondition(appCode,condition);
    }
}
