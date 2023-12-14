package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsDeviceflowsMapper;
import com.benet.labsys.domain.LabsDeviceflows;
import com.benet.labsys.service.ILabsDeviceflowsService;

/**
 * 设备控制过程Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Service
public class LabsDeviceflowsServiceImpl implements ILabsDeviceflowsService 
{
    @Autowired
    private LabsDeviceflowsMapper labsDeviceflowsMapper;

    /**
     * 查询所有设备控制过程列表
     *
     * @param appCode 应用编号
     * @return 设备控制过程集合
     */
    @Override
    public List<LabsDeviceflows> getAllRecords(String appCode) {
        return labsDeviceflowsMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询设备控制过程列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 设备控制过程集合
     */
    @Override
    public List<LabsDeviceflows> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsDeviceflowsMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询设备控制过程列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 设备控制过程集合
     */
    @Override
    public List<LabsDeviceflows> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsDeviceflowsMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询设备控制过程列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 设备控制过程集合
     */
    @Override
    public List<LabsDeviceflows> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsDeviceflowsMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询设备控制过程
     *
     * @param appCode 应用编号
     * @param no 设备控制过程ID
     * @return 设备控制过程
     */
    @Override
    public LabsDeviceflows getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsDeviceflowsMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询设备控制过程名称
     *
     * @param appCode 应用编号
     * @param no 设备控制过程ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsDeviceflowsMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询设备控制过程计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsDeviceflowsMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增设备控制过程
     *
     * @param appCode 应用编号
     * @param info 设备控制过程
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsDeviceflows info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsDeviceflowsMapper.AddNewRecord(info);
    }

    /**
     * 更新设备控制过程
     *
     * @param appCode 应用编号
     * @param info 设备控制过程
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsDeviceflows info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsDeviceflowsMapper.UpdateRecord(info);
    }

    /**
     * 硬删除设备控制过程
     *
     * @param appCode 应用编号
     * @param no 设备控制过程ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsDeviceflowsMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除设备控制过程
     *
     * @param appCode 应用编号
     * @param nos 设备控制过程IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsDeviceflowsMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除设备控制过程
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsDeviceflowsMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除设备控制过程
     *
     * @param appCode 应用编号
     * @param no 设备控制过程ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsDeviceflowsMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除设备控制过程
     *
     * @param appCode 应用编号
     * @param nos 设备控制过程IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsDeviceflowsMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除设备控制过程
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsDeviceflowsMapper.SoftDeleteByCondition(appCode,condition);
    }
}
