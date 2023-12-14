package com.benet.labsys.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.labsys.service.ILabsDeviceinfoService;
import com.benet.system.service.ISysRuserinfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.benet.labsys.domain.LabsDeviceflows;
import com.benet.labsys.service.ILabsDeviceflowsService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 设备控制过程Controller
 *
 * @author yoxking
 * @date 2022-08-05
 */
@Api(value = "labsys/deviceflows", tags = "设备控制过程控制器")
@RestController
@RequestMapping("/labsys/deviceflows")
@Slf4j
public class LabsDeviceflowsController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsDeviceflowsService labsDeviceflowsService;

    @Autowired
    private ILabsDeviceinfoService labsDeviceinfoService;

    @Autowired
    private ISysRuserinfoService sysRuserinfoService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceflows:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询设备控制过程列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceflows:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("deviceNo")) {
            if (!StringUtils.isBlank((CharSequence) maps.get("deviceNo"))) {
                condition += " device_no=" + "'" + maps.get("deviceNo") + "'";
            }
        }
        log.info("列表查询条件 {}",condition);

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsDeviceflowsService.getCountByCondition(loginUser.getAppCode(), condition);
        List<LabsDeviceflows> list = labsDeviceflowsService.getRecordsByPaging(loginUser.getAppCode(), pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        for (LabsDeviceflows labsDeviceflows : list) {
            String recordNameByNo = labsDeviceinfoService.getRecordNameByNo(loginUser.getAppCode(), labsDeviceflows.getDeviceNo());
            labsDeviceflows.setDeviceNo(recordNameByNo);
        }
        return getDataTable(list, count);
    }

    /**
     * 新增设备控制过程
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceflows:addnew')")
    @Oplog(title = "设备控制过程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsDeviceflows labsDeviceflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsDeviceflows.setDflowNo(UuidUtils.shortUUID());
        labsDeviceflows.setCreateBy(loginUser.getUserNo());
        labsDeviceflows.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsDeviceflowsService.AddNewRecord(loginUser.getAppCode(), labsDeviceflows));
    }

    /**
     * 编辑设备控制过程
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceflows:update')")
    @Oplog(title = "设备控制过程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsDeviceflows labsDeviceflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsDeviceflows.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsDeviceflowsService.UpdateRecord(loginUser.getAppCode(), labsDeviceflows));
    }

    /**
     * 保存设备控制过程
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceflows:save')")
    @Oplog(title = "设备控制过程", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsDeviceflows labsDeviceflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsDeviceflowsService.getRecordByNo(loginUser.getAppCode(), labsDeviceflows.getDflowNo()))) {
            labsDeviceflows.setDflowNo(UuidUtils.shortUUID());
            labsDeviceflows.setCreateBy(loginUser.getUserNo());
            labsDeviceflows.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsDeviceflowsService.AddNewRecord(loginUser.getAppCode(), labsDeviceflows));
        } else {
            labsDeviceflows.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsDeviceflowsService.UpdateRecord(loginUser.getAppCode(), labsDeviceflows));
        }
    }

    /**
     * 删除设备控制过程
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceflows:delete')")
    @Oplog(title = "设备控制过程", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsDeviceflowsService.SoftDeleteByNos(loginUser.getAppCode(), ids));
    }

    /**
     * 获取设备控制过程详细信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceflows:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        LabsDeviceflows labsDeviceflows = labsDeviceflowsService.getRecordByNo(loginUser.getAppCode(), id);
        //声明需要格式化的格式(日期加时间)
        DateTimeFormatter dfDateTime = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        String recordNameByNo = labsDeviceinfoService.getRecordNameByNo(loginUser.getAppCode(), labsDeviceflows.getDeviceNo());
        labsDeviceflows.setDeviceNo(recordNameByNo);
        return AjaxResult.success(labsDeviceflows);
    }

    /**
     * 导出设备控制过程列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceflows:export')")
    @Oplog(title = "设备控制过程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {
        //String condition= pagerRequest.getCondition();
        String condition = "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("name")) {
            condition += " name=" + maps.get("name");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsDeviceflowsService.getCountByCondition(loginUser.getAppCode(), condition);

        List<LabsDeviceflows> list = labsDeviceflowsService.getRecordsByPaging(loginUser.getAppCode(), 1, count, condition, "id", "Asc");
        ExcelUtils<LabsDeviceflows> util = new ExcelUtils<LabsDeviceflows>(LabsDeviceflows.class);
        return util.exportExcel(list, "LabsDeviceflows");
    }

}
