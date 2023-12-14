package com.benet.system.service.impl;

import java.util.List;

import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysDictdataMapper;
import com.benet.system.domain.SysDictdata;
import com.benet.system.service.ISysDictdataService;

/**
 * 字典数据Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-23
 */
@Service
public class SysDictdataServiceImpl implements ISysDictdataService 
{
    @Autowired
    private SysDictdataMapper sysDictdataMapper;

    /**
     * 查询所有字典数据列表
     *
     * @param appCode 应用编号
     * @return 字典数据集合
     */
    @Override
    public List<SysDictdata> getAllRecords(String appCode) {
        return sysDictdataMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询字典数据列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 字典数据集合
     */
    @Override
    public List<SysDictdata> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysDictdataMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询字典数据列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 字典数据集合
     */
    @Override
    public List<SysDictdata> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysDictdataMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询字典数据列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 字典数据集合
     */
    @Override
    public List<SysDictdata> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysDictdataMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询字典数据
     *
     * @param appCode 应用编号
     * @param no 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictdata getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDictdataMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询字典数据名称
     *
     * @param appCode 应用编号
     * @param no 字典数据ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDictdataMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询字典数据计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysDictdataMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增字典数据
     *
     * @param appCode 应用编号
     * @param info 字典数据
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysDictdata info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return sysDictdataMapper.AddNewRecord(info);
    }

    /**
     * 更新字典数据
     *
     * @param appCode 应用编号
     * @param info 字典数据
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysDictdata info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysDictdataMapper.UpdateRecord(info);
    }

    /**
     * 硬删除字典数据
     *
     * @param appCode 应用编号
     * @param no 字典数据ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDictdataMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除字典数据
     *
     * @param appCode 应用编号
     * @param nos 字典数据IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysDictdataMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除字典数据
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysDictdataMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除字典数据
     *
     * @param appCode 应用编号
     * @param no 字典数据ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDictdataMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除字典数据
     *
     * @param appCode 应用编号
     * @param nos 字典数据IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysDictdataMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除字典数据
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysDictdataMapper.SoftDeleteByCondition(appCode,condition);
    }
}
