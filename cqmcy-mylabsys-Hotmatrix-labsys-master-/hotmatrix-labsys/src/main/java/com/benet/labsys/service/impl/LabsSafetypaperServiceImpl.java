package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsSafetypaperMapper;
import com.benet.labsys.domain.LabsSafetypaper;
import com.benet.labsys.service.ILabsSafetypaperService;

/**
 * 安全试卷Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Service
public class LabsSafetypaperServiceImpl implements ILabsSafetypaperService 
{
    @Autowired
    private LabsSafetypaperMapper labsSafetypaperMapper;

    /**
     * 查询所有安全试卷列表
     *
     * @param appCode 应用编号
     * @return 安全试卷集合
     */
    @Override
    public List<LabsSafetypaper> getAllRecords(String appCode) {
        return labsSafetypaperMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询安全试卷列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 安全试卷集合
     */
    @Override
    public List<LabsSafetypaper> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsSafetypaperMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询安全试卷列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 安全试卷集合
     */
    @Override
    public List<LabsSafetypaper> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsSafetypaperMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询安全试卷列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 安全试卷集合
     */
    @Override
    public List<LabsSafetypaper> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsSafetypaperMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询安全试卷
     *
     * @param appCode 应用编号
     * @param no 安全试卷ID
     * @return 安全试卷
     */
    @Override
    public LabsSafetypaper getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSafetypaperMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询安全试卷名称
     *
     * @param appCode 应用编号
     * @param no 安全试卷ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSafetypaperMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询安全试卷计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsSafetypaperMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增安全试卷
     *
     * @param appCode 应用编号
     * @param info 安全试卷
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsSafetypaper info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsSafetypaperMapper.AddNewRecord(info);
    }

    /**
     * 更新安全试卷
     *
     * @param appCode 应用编号
     * @param info 安全试卷
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsSafetypaper info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsSafetypaperMapper.UpdateRecord(info);
    }

    /**
     * 硬删除安全试卷
     *
     * @param appCode 应用编号
     * @param no 安全试卷ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSafetypaperMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除安全试卷
     *
     * @param appCode 应用编号
     * @param nos 安全试卷IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsSafetypaperMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除安全试卷
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsSafetypaperMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除安全试卷
     *
     * @param appCode 应用编号
     * @param no 安全试卷ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSafetypaperMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除安全试卷
     *
     * @param appCode 应用编号
     * @param nos 安全试卷IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsSafetypaperMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除安全试卷
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsSafetypaperMapper.SoftDeleteByCondition(appCode,condition);
    }
}
