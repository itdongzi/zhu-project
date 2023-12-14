package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsDevicerepairMapper;
import com.benet.labsys.domain.LabsDevicerepair;
import com.benet.labsys.service.ILabsDevicerepairService;

/**
 * 设备报修信息Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Service
public class LabsDevicerepairServiceImpl implements ILabsDevicerepairService 
{
    @Autowired
    private LabsDevicerepairMapper labsDevicerepairMapper;

    /**
     * 查询所有设备报修信息列表
     *
     * @param appCode 应用编号
     * @return 设备报修信息集合
     */
    @Override
    public List<LabsDevicerepair> getAllRecords(String appCode) {
        return labsDevicerepairMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询设备报修信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 设备报修信息集合
     */
    @Override
    public List<LabsDevicerepair> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsDevicerepairMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询设备报修信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 设备报修信息集合
     */
    @Override
    public List<LabsDevicerepair> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsDevicerepairMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询设备报修信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 设备报修信息集合
     */
    @Override
    public List<LabsDevicerepair> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsDevicerepairMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询设备报修信息
     *
     * @param appCode 应用编号
     * @param no 设备报修信息ID
     * @return 设备报修信息
     */
    @Override
    public LabsDevicerepair getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsDevicerepairMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询设备报修信息名称
     *
     * @param appCode 应用编号
     * @param no 设备报修信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsDevicerepairMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询设备报修信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsDevicerepairMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增设备报修信息
     *
     * @param appCode 应用编号
     * @param info 设备报修信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsDevicerepair info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsDevicerepairMapper.AddNewRecord(info);
    }

    /**
     * 更新设备报修信息
     *
     * @param appCode 应用编号
     * @param info 设备报修信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsDevicerepair info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsDevicerepairMapper.UpdateRecord(info);
    }

    /**
     * 硬删除设备报修信息
     *
     * @param appCode 应用编号
     * @param no 设备报修信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsDevicerepairMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除设备报修信息
     *
     * @param appCode 应用编号
     * @param nos 设备报修信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsDevicerepairMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除设备报修信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsDevicerepairMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除设备报修信息
     *
     * @param appCode 应用编号
     * @param no 设备报修信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsDevicerepairMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除设备报修信息
     *
     * @param appCode 应用编号
     * @param nos 设备报修信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsDevicerepairMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除设备报修信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsDevicerepairMapper.SoftDeleteByCondition(appCode,condition);
    }
}
