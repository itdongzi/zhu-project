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
import com.benet.labsys.domain.LabsWorklog;
import com.benet.labsys.service.ILabsWorklogService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 实训室工作/检查记录Controller
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Api(value = "labsys/worklog", tags = "实训室工作/检查记录控制器")
@RestController
@RequestMapping("/labsys/worklog")
public class LabsWorklogController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsWorklogService labsWorklogService;
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
     * 查询实训室工作/检查记录列表
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
        int count = labsWorklogService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsWorklog> list = labsWorklogService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增实训室工作/检查记录
     */
    @Oplog(title = "实训室工作/检查记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsWorklog labsWorklog) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsWorklog.setWorkNo(UuidUtils.shortUUID());
        labsWorklog.setCreateBy(loginUser.getUserNo());
        labsWorklog.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsWorklogService.AddNewRecord(loginUser.getAppCode(),labsWorklog));
    }

    /**
     * 编辑实训室工作/检查记录
     */
    @Oplog(title = "实训室工作/检查记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsWorklog labsWorklog) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsWorklog.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsWorklogService.UpdateRecord(loginUser.getAppCode(),labsWorklog));
    }

    /**
     * 保存实训室工作/检查记录
     */
    @Oplog(title = "实训室工作/检查记录", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsWorklog labsWorklog) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsWorklogService.getRecordByNo(loginUser.getAppCode(),labsWorklog.getWorkNo()))) {
            labsWorklog.setWorkNo(UuidUtils.shortUUID());
            labsWorklog.setCreateBy(loginUser.getUserNo());
            labsWorklog.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsWorklogService.AddNewRecord(loginUser.getAppCode(),labsWorklog));
        } else {
            labsWorklog.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsWorklogService.UpdateRecord(loginUser.getAppCode(),labsWorklog));
        }
    }

    /**
     * 删除实训室工作/检查记录
     */
    @Oplog(title = "实训室工作/检查记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsWorklogService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsWorklogService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取实训室工作/检查记录详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsWorklog info= labsWorklogService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出实训室工作/检查记录列表
     */
    @Oplog(title = "实训室工作/检查记录", businessType = BusinessType.EXPORT)
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
        int count = labsWorklogService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsWorklog> list = labsWorklogService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsWorklog> util = new ExcelUtils<LabsWorklog>(LabsWorklog.class);
        return util.exportExcel(list, "LabsWorklog");
    }

}
