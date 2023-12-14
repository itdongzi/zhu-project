package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsMeetclassMapper;
import com.benet.labsys.domain.LabsMeetclass;
import com.benet.labsys.service.ILabsMeetclassService;

/**
 * 会议室类型Service业务层处理
 * 
 * @author yoxking
 * @date 2023-02-24 09:46:53
 */
@Service
public class LabsMeetclassServiceImpl implements ILabsMeetclassService 
{
    @Autowired
    private LabsMeetclassMapper labsMeetclassMapper;

    /**
     * 查询所有会议室类型列表
     *
     * @param appCode 应用编号
     * @return 会议室类型集合
     */
    @Override
    public List<LabsMeetclass> getAllRecords(String appCode) {
        return labsMeetclassMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询会议室类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 会议室类型集合
     */
    @Override
    public List<LabsMeetclass> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsMeetclassMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询会议室类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 会议室类型集合
     */
    @Override
    public List<LabsMeetclass> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsMeetclassMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询会议室类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 会议室类型集合
     */
    @Override
    public List<LabsMeetclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsMeetclassMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询会议室类型
     *
     * @param appCode 应用编号
     * @param no 会议室类型ID
     * @return 会议室类型
     */
    @Override
    public LabsMeetclass getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsMeetclassMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询会议室类型名称
     *
     * @param appCode 应用编号
     * @param no 会议室类型ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsMeetclassMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询会议室类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsMeetclassMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增会议室类型
     *
     * @param appCode 应用编号
     * @param info 会议室类型
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsMeetclass info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsMeetclassMapper.AddNewRecord(info);
    }

    /**
     * 更新会议室类型
     *
     * @param appCode 应用编号
     * @param info 会议室类型
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsMeetclass info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsMeetclassMapper.UpdateRecord(info);
    }

    /**
     * 硬删除会议室类型
     *
     * @param appCode 应用编号
     * @param no 会议室类型ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsMeetclassMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除会议室类型
     *
     * @param appCode 应用编号
     * @param nos 会议室类型IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsMeetclassMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除会议室类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsMeetclassMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除会议室类型
     *
     * @param appCode 应用编号
     * @param no 会议室类型ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsMeetclassMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除会议室类型
     *
     * @param appCode 应用编号
     * @param nos 会议室类型IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsMeetclassMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除会议室类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsMeetclassMapper.SoftDeleteByCondition(appCode,condition);
    }
}
