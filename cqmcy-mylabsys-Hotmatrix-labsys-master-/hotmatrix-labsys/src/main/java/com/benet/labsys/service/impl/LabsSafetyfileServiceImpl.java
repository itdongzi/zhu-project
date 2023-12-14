package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsSafetyfileMapper;
import com.benet.labsys.domain.LabsSafetyfile;
import com.benet.labsys.service.ILabsSafetyfileService;

/**
 * 安全学习资料Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Service
public class LabsSafetyfileServiceImpl implements ILabsSafetyfileService 
{
    @Autowired
    private LabsSafetyfileMapper labsSafetyfileMapper;

    /**
     * 查询所有安全学习资料列表
     *
     * @param appCode 应用编号
     * @return 安全学习资料集合
     */
    @Override
    public List<LabsSafetyfile> getAllRecords(String appCode) {
        return labsSafetyfileMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询安全学习资料列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 安全学习资料集合
     */
    @Override
    public List<LabsSafetyfile> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsSafetyfileMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询安全学习资料列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 安全学习资料集合
     */
    @Override
    public List<LabsSafetyfile> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsSafetyfileMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询安全学习资料列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 安全学习资料集合
     */
    @Override
    public List<LabsSafetyfile> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsSafetyfileMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询安全学习资料
     *
     * @param appCode 应用编号
     * @param no 安全学习资料ID
     * @return 安全学习资料
     */
    @Override
    public LabsSafetyfile getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSafetyfileMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询安全学习资料名称
     *
     * @param appCode 应用编号
     * @param no 安全学习资料ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSafetyfileMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询安全学习资料计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsSafetyfileMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增安全学习资料
     *
     * @param appCode 应用编号
     * @param info 安全学习资料
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsSafetyfile info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsSafetyfileMapper.AddNewRecord(info);
    }

    /**
     * 更新安全学习资料
     *
     * @param appCode 应用编号
     * @param info 安全学习资料
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsSafetyfile info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsSafetyfileMapper.UpdateRecord(info);
    }

    /**
     * 硬删除安全学习资料
     *
     * @param appCode 应用编号
     * @param no 安全学习资料ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSafetyfileMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除安全学习资料
     *
     * @param appCode 应用编号
     * @param nos 安全学习资料IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsSafetyfileMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除安全学习资料
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsSafetyfileMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除安全学习资料
     *
     * @param appCode 应用编号
     * @param no 安全学习资料ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSafetyfileMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除安全学习资料
     *
     * @param appCode 应用编号
     * @param nos 安全学习资料IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsSafetyfileMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除安全学习资料
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsSafetyfileMapper.SoftDeleteByCondition(appCode,condition);
    }
}
