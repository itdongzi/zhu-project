package com.benet.labsys.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import io.swagger.annotations.Api;
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
import com.benet.labsys.domain.LabsDeviceinfo;
import com.benet.labsys.service.ILabsDeviceinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 实验室设备Controller
 * 
 * @author yoxking
 * @date 2022-08-05
 */
@Api(value = "labsys/deviceinfo", tags = "实验室设备控制器")
@RestController
@RequestMapping("/labsys/deviceinfo")
public class LabsDeviceinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsDeviceinfoService labsDeviceinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询实验室设备列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("name")) {
            condition += " name=" + maps.get("name");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsDeviceinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsDeviceinfo> list = labsDeviceinfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增实验室设备
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceinfo:addnew')")
    @Oplog(title = "实验室设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsDeviceinfo labsDeviceinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsDeviceinfo.setDeviceNo(UuidUtils.shortUUID());
        labsDeviceinfo.setCreateBy(loginUser.getUserNo());
        labsDeviceinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsDeviceinfoService.AddNewRecord(loginUser.getAppCode(),labsDeviceinfo));
    }

    /**
     * 编辑实验室设备
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceinfo:update')")
    @Oplog(title = "实验室设备", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody LabsDeviceinfo labsDeviceinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsDeviceinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsDeviceinfoService.UpdateRecord(loginUser.getAppCode(),labsDeviceinfo));
        }

    /**
     * 保存实验室设备
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceinfo:save')")
    @Oplog(title = "实验室设备", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsDeviceinfo labsDeviceinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsDeviceinfoService.getRecordByNo(loginUser.getAppCode(),labsDeviceinfo.getDeviceNo()))) {
            labsDeviceinfo.setDeviceNo(UuidUtils.shortUUID());
            labsDeviceinfo.setCreateBy(loginUser.getUserNo());
            labsDeviceinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsDeviceinfoService.AddNewRecord(loginUser.getAppCode(),labsDeviceinfo));
        } else {
            labsDeviceinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsDeviceinfoService.UpdateRecord(loginUser.getAppCode(),labsDeviceinfo));
        }
    }

    /**
     * 删除实验室设备
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceinfo:delete')")
    @Oplog(title = "实验室设备", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsDeviceinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取实验室设备详细信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsDeviceinfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 导出实验室设备列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:deviceinfo:export')")
    @Oplog(title = "实验室设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("name")) {
            condition += " name=" + maps.get("name");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsDeviceinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsDeviceinfo> list = labsDeviceinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsDeviceinfo> util = new ExcelUtils<LabsDeviceinfo>(LabsDeviceinfo.class);
        return util.exportExcel(list, "LabsDeviceinfo");
    }

}
