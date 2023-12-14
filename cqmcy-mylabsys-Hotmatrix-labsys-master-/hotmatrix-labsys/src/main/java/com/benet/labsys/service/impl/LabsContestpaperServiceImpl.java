package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsContestpaperMapper;
import com.benet.labsys.domain.LabsContestpaper;
import com.benet.labsys.service.ILabsContestpaperService;

/**
 * 竞赛试题信息Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
@Service
public class LabsContestpaperServiceImpl implements ILabsContestpaperService 
{
    @Autowired
    private LabsContestpaperMapper labsContestpaperMapper;

    /**
     * 查询所有竞赛试题信息列表
     *
     * @param appCode 应用编号
     * @return 竞赛试题信息集合
     */
    @Override
    public List<LabsContestpaper> getAllRecords(String appCode) {
        return labsContestpaperMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询竞赛试题信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 竞赛试题信息集合
     */
    @Override
    public List<LabsContestpaper> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsContestpaperMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询竞赛试题信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 竞赛试题信息集合
     */
    @Override
    public List<LabsContestpaper> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsContestpaperMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询竞赛试题信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 竞赛试题信息集合
     */
    @Override
    public List<LabsContestpaper> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsContestpaperMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询竞赛试题信息
     *
     * @param appCode 应用编号
     * @param no 竞赛试题信息ID
     * @return 竞赛试题信息
     */
    @Override
    public LabsContestpaper getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsContestpaperMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询竞赛试题信息名称
     *
     * @param appCode 应用编号
     * @param no 竞赛试题信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsContestpaperMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询竞赛试题信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsContestpaperMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增竞赛试题信息
     *
     * @param appCode 应用编号
     * @param info 竞赛试题信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsContestpaper info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsContestpaperMapper.AddNewRecord(info);
    }

    /**
     * 更新竞赛试题信息
     *
     * @param appCode 应用编号
     * @param info 竞赛试题信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsContestpaper info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsContestpaperMapper.UpdateRecord(info);
    }

    /**
     * 硬删除竞赛试题信息
     *
     * @param appCode 应用编号
     * @param no 竞赛试题信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsContestpaperMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除竞赛试题信息
     *
     * @param appCode 应用编号
     * @param nos 竞赛试题信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsContestpaperMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除竞赛试题信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsContestpaperMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除竞赛试题信息
     *
     * @param appCode 应用编号
     * @param no 竞赛试题信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsContestpaperMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除竞赛试题信息
     *
     * @param appCode 应用编号
     * @param nos 竞赛试题信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsContestpaperMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除竞赛试题信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsContestpaperMapper.SoftDeleteByCondition(appCode,condition);
    }
}
