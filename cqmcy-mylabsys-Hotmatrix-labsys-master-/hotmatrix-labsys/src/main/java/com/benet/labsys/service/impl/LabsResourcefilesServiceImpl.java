package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsResourcefilesMapper;
import com.benet.labsys.domain.LabsResourcefiles;
import com.benet.labsys.service.ILabsResourcefilesService;

/**
 * 教学资源文件Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Service
public class LabsResourcefilesServiceImpl implements ILabsResourcefilesService 
{
    @Autowired
    private LabsResourcefilesMapper labsResourcefilesMapper;

    /**
     * 查询所有教学资源文件列表
     *
     * @param appCode 应用编号
     * @return 教学资源文件集合
     */
    @Override
    public List<LabsResourcefiles> getAllRecords(String appCode) {
        return labsResourcefilesMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询教学资源文件列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 教学资源文件集合
     */
    @Override
    public List<LabsResourcefiles> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsResourcefilesMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询教学资源文件列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 教学资源文件集合
     */
    @Override
    public List<LabsResourcefiles> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsResourcefilesMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询教学资源文件列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 教学资源文件集合
     */
    @Override
    public List<LabsResourcefiles> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsResourcefilesMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询教学资源文件
     *
     * @param appCode 应用编号
     * @param no 教学资源文件ID
     * @return 教学资源文件
     */
    @Override
    public LabsResourcefiles getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsResourcefilesMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询教学资源文件名称
     *
     * @param appCode 应用编号
     * @param no 教学资源文件ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsResourcefilesMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询教学资源文件计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsResourcefilesMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增教学资源文件
     *
     * @param appCode 应用编号
     * @param info 教学资源文件
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsResourcefiles info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsResourcefilesMapper.AddNewRecord(info);
    }

    /**
     * 更新教学资源文件
     *
     * @param appCode 应用编号
     * @param info 教学资源文件
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsResourcefiles info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsResourcefilesMapper.UpdateRecord(info);
    }

    /**
     * 硬删除教学资源文件
     *
     * @param appCode 应用编号
     * @param no 教学资源文件ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsResourcefilesMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除教学资源文件
     *
     * @param appCode 应用编号
     * @param nos 教学资源文件IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsResourcefilesMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除教学资源文件
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsResourcefilesMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除教学资源文件
     *
     * @param appCode 应用编号
     * @param no 教学资源文件ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsResourcefilesMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除教学资源文件
     *
     * @param appCode 应用编号
     * @param nos 教学资源文件IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsResourcefilesMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除教学资源文件
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsResourcefilesMapper.SoftDeleteByCondition(appCode,condition);
    }
}
