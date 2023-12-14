package com.benet.labsys.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsRoomscheduleMapper;
import com.benet.labsys.domain.LabsRoomschedule;
import com.benet.labsys.service.ILabsRoomscheduleService;

import javax.servlet.http.HttpServletRequest;

/**
 * 实验室排课Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Service
public class LabsRoomscheduleServiceImpl implements ILabsRoomscheduleService 
{
    @Autowired
    private LabsRoomscheduleMapper labsRoomscheduleMapper;

    /**
     * 查询所有实验室排课列表
     *
     * @param appCode 应用编号
     * @return 实验室排课集合
     */
    @Override
    public List<LabsRoomschedule> getAllRecords(String appCode) {
        return labsRoomscheduleMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询实验室排课列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 实验室排课集合
     */
    @Override
    public List<LabsRoomschedule> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsRoomscheduleMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询实验室排课列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 实验室排课集合
     */
    @Override
    public List<LabsRoomschedule> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsRoomscheduleMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询实验室排课列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 实验室排课集合
     */
    @Override
    public List<LabsRoomschedule> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsRoomscheduleMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询实验室排课
     *
     * @param appCode 应用编号
     * @param no 实验室排课ID
     * @return 实验室排课
     */
    @Override
    public LabsRoomschedule getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsRoomscheduleMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询实验室排课名称
     *
     * @param appCode 应用编号
     * @param no 实验室排课ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsRoomscheduleMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询实验室排课计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsRoomscheduleMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增实验室排课
     *
     * @param appCode 应用编号
     * @param info 实验室排课
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsRoomschedule info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsRoomscheduleMapper.AddNewRecord(info);
    }

    /**
     * 更新实验室排课
     *
     * @param appCode 应用编号
     * @param info 实验室排课
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsRoomschedule info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsRoomscheduleMapper.UpdateRecord(info);
    }

    /**
     * 硬删除实验室排课
     *
     * @param appCode 应用编号
     * @param no 实验室排课ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsRoomscheduleMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除实验室排课
     *
     * @param appCode 应用编号
     * @param nos 实验室排课IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsRoomscheduleMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除实验室排课
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsRoomscheduleMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除实验室排课
     *
     * @param appCode 应用编号
     * @param no 实验室排课ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsRoomscheduleMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除实验室排课
     *
     * @param appCode 应用编号
     * @param nos 实验室排课IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsRoomscheduleMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除实验室排课
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsRoomscheduleMapper.SoftDeleteByCondition(appCode,condition);
    }

    @Override
    public List<String> getXnxqbhList(String appCode) {
        return labsRoomscheduleMapper.getXnxqbhList(appCode);
    }

    @Override
    public List<String> getKkzcList(String appCode) {
        return labsRoomscheduleMapper.getKkzcList(appCode);
    }
}
