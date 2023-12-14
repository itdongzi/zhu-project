package com.benet.labsys.controller;

import java.util.List;
import java.util.Map;

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
 * @date 2022-11-19 19:36:35
 */
@Api(value = "labsys/deviceflows", tags = "设备控制过程控制器")
@RestController
@RequestMapping("/labsys/deviceflows")
public class LabsDeviceflowsController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsDeviceflowsService labsDeviceflowsService;
    /**
     * 首页
     */
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询设备控制过程列表
     */
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        String condition = "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("roomName")&&StringUtils.isNotEmpty(maps.get("roomName").toString())) {
            condition += "And room_name like '%" + maps.get("roomName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsDeviceflowsService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsDeviceflows> list = labsDeviceflowsService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增设备控制过程
     */
    @Oplog(title = "设备控制过程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsDeviceflows labsDeviceflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsDeviceflows.setDflowNo(UuidUtils.shortUUID());
        labsDeviceflows.setCreateBy(loginUser.getUserNo());
        labsDeviceflows.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsDeviceflowsService.AddNewRecord(loginUser.getAppCode(),labsDeviceflows));
    }

    /**
     * 编辑设备控制过程
     */
    @Oplog(title = "设备控制过程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsDeviceflows labsDeviceflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsDeviceflows.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsDeviceflowsService.UpdateRecord(loginUser.getAppCode(),labsDeviceflows));
    }

    /**
     * 保存设备控制过程
     */
    @Oplog(title = "设备控制过程", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsDeviceflows labsDeviceflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsDeviceflowsService.getRecordByNo(loginUser.getAppCode(),labsDeviceflows.getDflowNo()))) {
            labsDeviceflows.setDflowNo(UuidUtils.shortUUID());
            labsDeviceflows.setCreateBy(loginUser.getUserNo());
            labsDeviceflows.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsDeviceflowsService.AddNewRecord(loginUser.getAppCode(),labsDeviceflows));
        } else {
            labsDeviceflows.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsDeviceflowsService.UpdateRecord(loginUser.getAppCode(),labsDeviceflows));
        }
    }

    /**
     * 删除设备控制过程
     */
    @Oplog(title = "设备控制过程", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsDeviceflowsService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsDeviceflowsService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取设备控制过程详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsDeviceflows info= labsDeviceflowsService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出设备控制过程列表
     */
    @Oplog(title = "设备控制过程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        String condition = "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("roomName")&&StringUtils.isNotEmpty(maps.get("roomName").toString())) {
            condition += "And room_name like '%" + maps.get("roomName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsDeviceflowsService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsDeviceflows> list = labsDeviceflowsService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsDeviceflows> util = new ExcelUtils<LabsDeviceflows>(LabsDeviceflows.class);
        return util.exportExcel(list, "LabsDeviceflows");
    }

}
