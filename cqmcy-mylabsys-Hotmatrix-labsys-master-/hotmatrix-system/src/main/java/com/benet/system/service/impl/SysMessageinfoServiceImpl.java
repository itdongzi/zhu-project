package com.benet.system.service.impl;

import java.util.List;

import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysMessageinfoMapper;
import com.benet.system.domain.SysMessageinfo;
import com.benet.system.service.ISysMessageinfoService;

/**
 * 消息信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysMessageinfoServiceImpl implements ISysMessageinfoService 
{
    @Autowired
    private SysMessageinfoMapper sysMessageinfoMapper;

    /**
     * 查询所有消息信息列表
     *
     * @param appCode 应用编号
     * @return 消息信息集合
     */
    @Override
    public List<SysMessageinfo> getAllRecords(String appCode) {
        return sysMessageinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询消息信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 消息信息集合
     */
    @Override
    public List<SysMessageinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysMessageinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询消息信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 消息信息集合
     */
    @Override
    public List<SysMessageinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysMessageinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询消息信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 消息信息集合
     */
    @Override
    public List<SysMessageinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysMessageinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询消息信息
     *
     * @param appCode 应用编号
     * @param no 消息信息ID
     * @return 消息信息
     */
    @Override
    public SysMessageinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysMessageinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询消息信息名称
     *
     * @param appCode 应用编号
     * @param no 消息信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysMessageinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询消息信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysMessageinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增消息信息
     *
     * @param appCode 应用编号
     * @param info 消息信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysMessageinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        System.out.println(info.getSendTime());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return sysMessageinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新消息信息
     *
     * @param appCode 应用编号
     * @param info 消息信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysMessageinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysMessageinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除消息信息
     *
     * @param appCode 应用编号
     * @param no 消息信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysMessageinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除消息信息
     *
     * @param appCode 应用编号
     * @param nos 消息信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysMessageinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除消息信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysMessageinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除消息信息
     *
     * @param appCode 应用编号
     * @param no 消息信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysMessageinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除消息信息
     *
     * @param appCode 应用编号
     * @param nos 消息信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysMessageinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除消息信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysMessageinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
