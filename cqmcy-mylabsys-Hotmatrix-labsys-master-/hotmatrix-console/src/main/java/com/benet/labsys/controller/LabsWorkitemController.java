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
import com.benet.labsys.domain.LabsWorkitem;
import com.benet.labsys.service.ILabsWorkitemService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 实训室工作/检查项目Controller
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Api(value = "labsys/workitem", tags = "实训室工作/检查项目控制器")
@RestController
@RequestMapping("/labsys/workitem")
public class LabsWorkitemController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsWorkitemService labsWorkitemService;
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
     * 查询实训室工作/检查项目列表
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
        int count = labsWorkitemService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsWorkitem> list = labsWorkitemService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增实训室工作/检查项目
     */
    @Oplog(title = "实训室工作/检查项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsWorkitem labsWorkitem) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsWorkitem.setItemNo(UuidUtils.shortUUID());
        labsWorkitem.setCreateBy(loginUser.getUserNo());
        labsWorkitem.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsWorkitemService.AddNewRecord(loginUser.getAppCode(),labsWorkitem));
    }

    /**
     * 编辑实训室工作/检查项目
     */
    @Oplog(title = "实训室工作/检查项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsWorkitem labsWorkitem) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsWorkitem.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsWorkitemService.UpdateRecord(loginUser.getAppCode(),labsWorkitem));
    }

    /**
     * 保存实训室工作/检查项目
     */
    @Oplog(title = "实训室工作/检查项目", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsWorkitem labsWorkitem) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsWorkitemService.getRecordByNo(loginUser.getAppCode(),labsWorkitem.getItemNo()))) {
            labsWorkitem.setItemNo(UuidUtils.shortUUID());
            labsWorkitem.setCreateBy(loginUser.getUserNo());
            labsWorkitem.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsWorkitemService.AddNewRecord(loginUser.getAppCode(),labsWorkitem));
        } else {
            labsWorkitem.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsWorkitemService.UpdateRecord(loginUser.getAppCode(),labsWorkitem));
        }
    }

    /**
     * 删除实训室工作/检查项目
     */
    @Oplog(title = "实训室工作/检查项目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsWorkitemService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsWorkitemService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取实训室工作/检查项目详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsWorkitem info= labsWorkitemService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出实训室工作/检查项目列表
     */
    @Oplog(title = "实训室工作/检查项目", businessType = BusinessType.EXPORT)
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
        int count = labsWorkitemService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsWorkitem> list = labsWorkitemService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsWorkitem> util = new ExcelUtils<LabsWorkitem>(LabsWorkitem.class);
        return util.exportExcel(list, "LabsWorkitem");
    }

}
