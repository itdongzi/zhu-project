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
import com.benet.labsys.domain.LabsDevicerepair;
import com.benet.labsys.service.ILabsDevicerepairService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 设备报修信息Controller
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Api(value = "labsys/devicerepair", tags = "设备报修信息控制器")
@RestController
@RequestMapping("/labsys/devicerepair")
public class LabsDevicerepairController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsDevicerepairService labsDevicerepairService;
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
     * 查询设备报修信息列表
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
        int count = labsDevicerepairService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsDevicerepair> list = labsDevicerepairService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增设备报修信息
     */
    @Oplog(title = "设备报修信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsDevicerepair labsDevicerepair) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsDevicerepair.setRepairNo(UuidUtils.shortUUID());
        labsDevicerepair.setCreateBy(loginUser.getUserNo());
        labsDevicerepair.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsDevicerepairService.AddNewRecord(loginUser.getAppCode(),labsDevicerepair));
    }

    /**
     * 编辑设备报修信息
     */
    @Oplog(title = "设备报修信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsDevicerepair labsDevicerepair) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsDevicerepair.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsDevicerepairService.UpdateRecord(loginUser.getAppCode(),labsDevicerepair));
    }

    /**
     * 保存设备报修信息
     */
    @Oplog(title = "设备报修信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsDevicerepair labsDevicerepair) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsDevicerepairService.getRecordByNo(loginUser.getAppCode(),labsDevicerepair.getRepairNo()))) {
            labsDevicerepair.setRepairNo(UuidUtils.shortUUID());
            labsDevicerepair.setCreateBy(loginUser.getUserNo());
            labsDevicerepair.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsDevicerepairService.AddNewRecord(loginUser.getAppCode(),labsDevicerepair));
        } else {
            labsDevicerepair.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsDevicerepairService.UpdateRecord(loginUser.getAppCode(),labsDevicerepair));
        }
    }

    /**
     * 删除设备报修信息
     */
    @Oplog(title = "设备报修信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsDevicerepairService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsDevicerepairService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取设备报修信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsDevicerepair info= labsDevicerepairService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出设备报修信息列表
     */
    @Oplog(title = "设备报修信息", businessType = BusinessType.EXPORT)
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
        int count = labsDevicerepairService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsDevicerepair> list = labsDevicerepairService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsDevicerepair> util = new ExcelUtils<LabsDevicerepair>(LabsDevicerepair.class);
        return util.exportExcel(list, "LabsDevicerepair");
    }

}
