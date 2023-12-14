package com.benet.system.service.impl;

import java.util.List;

import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysConteeclassMapper;
import com.benet.system.domain.SysConteeclass;
import com.benet.system.service.ISysConteeclassService;

/**
 * 内容类型Service业务层处理
 *
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysConteeclassServiceImpl implements ISysConteeclassService {
    @Autowired
    private SysConteeclassMapper sysConteeclassMapper;

    /**
     * 查询所有内容类型列表
     *
     * @param appCode 应用编号
     * @return 内容类型集合
     */
    @Override
    public List<SysConteeclass> getAllRecords(String appCode) {
        return sysConteeclassMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询内容类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 内容类型集合
     */
    @Override
    public List<SysConteeclass> getRecordsByClassNo(String appCode, String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysConteeclassMapper.getRecordsByClassNo(appCode, classNo);
        }
        return null;
    }

    /**
     * 分页查询内容类型列表
     *
     * @param appCode 应用编号
     * @param model   分页模型
     * @return 内容类型集合
     */
    @Override
    public List<SysConteeclass> getRecordsByPaging(String appCode, PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex() - 1) * model.getPageSize());
            return sysConteeclassMapper.getRecordsByPaging(appCode, model);
        }
        return null;
    }


    /**
     * 分页查询内容类型列表
     *
     * @param appCode    应用编号
     * @param pageIndex  当前页起始索引
     * @param pageSize   页面大小
     * @param condition  条件
     * @param orderField 排序列
     * @param orderType  排序类型
     * @return 内容类型集合
     */
    @Override
    public List<SysConteeclass> getRecordsByPaging(String appCode, int pageIndex, int pageSize, String condition, String orderField, String orderType) {

        PagingModel model = new PagingModel();
        model.setPageIndex((pageIndex - 1) * pageSize);
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
        return sysConteeclassMapper.getRecordsByPaging(appCode, model);
    }

    /**
     * 查询内容类型
     *
     * @param appCode 应用编号
     * @param no      内容类型ID
     * @return 内容类型
     */
    @Override
    public SysConteeclass getRecordByNo(String appCode, String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysConteeclassMapper.getRecordByNo(appCode, no);
        }
        return null;
    }

    /**
     * 查询内容类型名称
     *
     * @param appCode 应用编号
     * @param no      内容类型ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode, String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysConteeclassMapper.getRecordNameByNo(appCode, no);
        }
        return null;
    }

    /**
     * 查询内容类型计数
     *
     * @param appCode   应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode, String condition) {
        return sysConteeclassMapper.getCountByCondition(appCode, condition);
    }

    /**
     * 新增内容类型
     *
     * @param appCode 应用编号
     * @param info    内容类型
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode, SysConteeclass info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return sysConteeclassMapper.AddNewRecord(info);
    }

    /**
     * 更新内容类型
     *
     * @param appCode 应用编号
     * @param info    内容类型
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode, SysConteeclass info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysConteeclassMapper.UpdateRecord(info);
    }

    /**
     * 硬删除内容类型
     *
     * @param appCode 应用编号
     * @param no      内容类型ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode, String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysConteeclassMapper.HardDeleteByNo(appCode, no);
        }
        return 0;
    }

    /**
     * 批量硬删除内容类型
     *
     * @param appCode 应用编号
     * @param nos     内容类型IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode, String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysConteeclassMapper.HardDeleteByNos(appCode, nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除内容类型
     *
     * @param appCode   应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode, String condition) {
        return sysConteeclassMapper.HardDeleteByCondition(appCode, condition);
    }

    /**
     * 软删除内容类型
     *
     * @param appCode 应用编号
     * @param no      内容类型ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode, String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysConteeclassMapper.SoftDeleteByNo(appCode, no);
        }
        return 0;
    }

    /**
     * 批量软删除内容类型
     *
     * @param appCode 应用编号
     * @param nos     内容类型IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode, String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysConteeclassMapper.SoftDeleteByNos(appCode, nos);
        }
        return 0;
    }

    /**
     * 按条件软删除内容类型
     *
     * @param appCode   应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode, String condition) {
        return sysConteeclassMapper.SoftDeleteByCondition(appCode, condition);
    }

    /**
     * 获取className
     *
     * @param appCode
     * @param classNo
     * @return
     */
    @Override
    public String getClassNameByClassNo(String appCode, String classNo) {
        return sysConteeclassMapper.getClassNameByClassNo(appCode, classNo);
    }
}
