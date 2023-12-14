package com.benet.labsys.controller;

import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.labsys.domain.LabsAreainfo;
import com.benet.labsys.domain.LabsRoominfo;
import com.benet.labsys.service.ILabsAreainfoService;
import com.benet.labsys.service.ILabsRoominfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 实验室信息Controller
 *
 * @author yoxking
 * @date 2022-08-05
 */
@Api(value = "labsys/roominfo", tags = "实验室信息控制器")
@RestController
@RequestMapping("/labsys/roominfo")
@Slf4j
public class LabsRoominfoController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsRoominfoService labsRoominfoService;

    @Autowired
    private ILabsAreainfoService labsAreainfoService;

    /**
     * 首页
     */
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询实验室信息列表
     */
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("roomName") && StringUtils.isNotEmpty(maps.get("roomName").toString())) {
            condition += "And room_name like '%" + maps.get("roomName") + "%'";
        }

        if (maps.containsKey("areaNo") && StringUtils.isNotEmpty(maps.get("areaNo").toString())) {
            condition += "And area_no =" + maps.get("areaNo");
        }
        if (StringUtils.isNotEmpty(condition)) {
            condition = condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsRoominfoService.getCountByCondition(loginUser.getAppCode(), condition);
        List<LabsRoominfo> list = labsRoominfoService.getRecordsByPaging(loginUser.getAppCode(), pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        for (LabsRoominfo labsRoominfo : list) {
            labsRoominfo.setAreaNo(labsAreainfoService.getRecordNameByNo(loginUser.getAppCode(), labsRoominfo.getAreaNo()));
        }
        return getDataTable(list, count);
    }

    @PostMapping(value = "/query")
    public AjaxResult query(@RequestBody PageRequest pRequest) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        Map maps = pRequest.getDataParams();
        if ( maps == null || maps.isEmpty()) {
            return AjaxResult.success(labsRoominfoService.getAllRecords(loginUser.getAppCode()));
        }
        String condition = "";
        if (maps.containsKey("areaNo") && StringUtils.isNotEmpty(maps.get("areaNo").toString())) {
            condition += "And area_no =" + maps.get("areaNo");
        }
        if (StringUtils.isNotEmpty(condition)) {
            condition = condition.substring(3);
        }

        List<LabsRoominfo> infoList = labsRoominfoService.getRecordsByCondition(loginUser.getAppCode(), condition);

//        if (maps.containsKey("areaNo") && StringUtils.isNotEmpty(maps.get("areaNo").toString())) {
//            String areaNo = (String) maps.get("areaNo");
//            infoList = labsRoominfoService.getRecordsByAreaNo(loginUser.getAppCode(), areaNo);
//        } else {
//            infoList = labsRoominfoService.getAllRecords(loginUser.getAppCode());
//        }
        return AjaxResult.success(infoList);
    }

    /**
     * 新增实验室信息
     */
    @Oplog(title = "实验室信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsRoominfo labsRoominfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsRoominfo.setRoomNo(UuidUtils.shortUUID());
        labsRoominfo.setCreateBy(loginUser.getUserNo());
        labsRoominfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsRoominfoService.AddNewRecord(loginUser.getAppCode(), labsRoominfo));
    }

    /**
     * 编辑实验室信息
     */
    @Oplog(title = "实验室信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsRoominfo labsRoominfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsRoominfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsRoominfoService.UpdateRecord(loginUser.getAppCode(), labsRoominfo));
    }

    /**
     * 保存实验室信息
     */
    @Oplog(title = "实验室信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsRoominfo labsRoominfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsRoominfoService.getRecordByNo(loginUser.getAppCode(), labsRoominfo.getRoomNo()))) {
            labsRoominfo.setRoomNo(UuidUtils.shortUUID());
            labsRoominfo.setCreateBy(loginUser.getUserNo());
            labsRoominfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsRoominfoService.AddNewRecord(loginUser.getAppCode(), labsRoominfo));
        } else {
            labsRoominfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsRoominfoService.UpdateRecord(loginUser.getAppCode(), labsRoominfo));
        }
    }

    /**
     * 删除实验室信息
     */
    @Oplog(title = "实验室信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsRoominfoService.SoftDeleteByNos(loginUser.getAppCode(), ids));
    }

    /**
     * 获取实验室信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsRoominfo labsRoominfo = labsRoominfoService.getRecordByNo(loginUser.getAppCode(), id);
        log.info("楼栋信息 - {}", labsRoominfo);
        return AjaxResult.success(labsRoominfo);
    }

    /**
     * 导出实验室信息列表
     */
    @Oplog(title = "实验室信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("roomName") && StringUtils.isNotEmpty(maps.get("roomName").toString())) {
            condition += "And room_name like '%" + maps.get("roomName") + "%'";
        }
        if (StringUtils.isNotEmpty(condition)) {
            condition = condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsRoominfoService.getCountByCondition(loginUser.getAppCode(), condition);

        List<LabsRoominfo> list = labsRoominfoService.getRecordsByPaging(loginUser.getAppCode(), 1, count, condition, "id", "Asc");
        ExcelUtils<LabsRoominfo> util = new ExcelUtils<LabsRoominfo>(LabsRoominfo.class);
        return util.exportExcel(list, "LabsRoominfo");
    }
}