package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysRotaclassMapper;
import com.benet.system.domain.SysRotaclass;
import com.benet.system.service.ISysRotaclassService;

/**
 * 值班类型Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Service
public class SysRotaclassServiceImpl implements ISysRotaclassService 
{
    @Autowired
    private SysRotaclassMapper sysRotaclassMapper;

    /**
     * 查询所有值班类型列表
     *
     * @param appCode 应用编号
     * @return 值班类型集合
     */
    @Override
    public List<SysRotaclass> getAllRecords(String appCode) {
        return sysRotaclassMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询值班类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 值班类型集合
     */
    @Override
    public List<SysRotaclass> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysRotaclassMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询值班类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 值班类型集合
     */
    @Override
    public List<SysRotaclass> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysRotaclassMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询值班类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 值班类型集合
     */
    @Override
    public List<SysRotaclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysRotaclassMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询值班类型
     *
     * @param appCode 应用编号
     * @param no 值班类型ID
     * @return 值班类型
     */
    @Override
    public SysRotaclass getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRotaclassMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询值班类型名称
     *
     * @param appCode 应用编号
     * @param no 值班类型ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRotaclassMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询值班类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysRotaclassMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增值班类型
     *
     * @param appCode 应用编号
     * @param info 值班类型
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysRotaclass info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return sysRotaclassMapper.AddNewRecord(info);
    }

    /**
     * 更新值班类型
     *
     * @param appCode 应用编号
     * @param info 值班类型
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysRotaclass info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysRotaclassMapper.UpdateRecord(info);
    }

    /**
     * 硬删除值班类型
     *
     * @param appCode 应用编号
     * @param no 值班类型ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRotaclassMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除值班类型
     *
     * @param appCode 应用编号
     * @param nos 值班类型IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysRotaclassMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除值班类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysRotaclassMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除值班类型
     *
     * @param appCode 应用编号
     * @param no 值班类型ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRotaclassMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除值班类型
     *
     * @param appCode 应用编号
     * @param nos 值班类型IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysRotaclassMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除值班类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysRotaclassMapper.SoftDeleteByCondition(appCode,condition);
    }
}
