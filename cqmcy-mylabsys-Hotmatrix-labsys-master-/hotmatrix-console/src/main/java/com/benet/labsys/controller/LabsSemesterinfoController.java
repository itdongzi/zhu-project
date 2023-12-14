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
import com.benet.labsys.domain.LabsSemesterinfo;
import com.benet.labsys.service.ILabsSemesterinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 学期学年信息Controller
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Api(value = "labsys/semesterinfo", tags = "学期学年信息控制器")
@RestController
@RequestMapping("/labsys/semesterinfo")
public class LabsSemesterinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsSemesterinfoService labsSemesterinfoService;
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
     * 查询学期学年信息列表
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
        int count = labsSemesterinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsSemesterinfo> list = labsSemesterinfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增学期学年信息
     */
    @Oplog(title = "学期学年信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsSemesterinfo labsSemesterinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsSemesterinfo.setSemeNo(UuidUtils.shortUUID());
        labsSemesterinfo.setCreateBy(loginUser.getUserNo());
        labsSemesterinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsSemesterinfoService.AddNewRecord(loginUser.getAppCode(),labsSemesterinfo));
    }

    /**
     * 编辑学期学年信息
     */
    @Oplog(title = "学期学年信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsSemesterinfo labsSemesterinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsSemesterinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsSemesterinfoService.UpdateRecord(loginUser.getAppCode(),labsSemesterinfo));
    }

    /**
     * 保存学期学年信息
     */
    @Oplog(title = "学期学年信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsSemesterinfo labsSemesterinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsSemesterinfoService.getRecordByNo(loginUser.getAppCode(),labsSemesterinfo.getSemeNo()))) {
            labsSemesterinfo.setSemeNo(UuidUtils.shortUUID());
            labsSemesterinfo.setCreateBy(loginUser.getUserNo());
            labsSemesterinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsSemesterinfoService.AddNewRecord(loginUser.getAppCode(),labsSemesterinfo));
        } else {
            labsSemesterinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsSemesterinfoService.UpdateRecord(loginUser.getAppCode(),labsSemesterinfo));
        }
    }

    /**
     * 删除学期学年信息
     */
    @Oplog(title = "学期学年信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsSemesterinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsSemesterinfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取学期学年信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsSemesterinfo info= labsSemesterinfoService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出学期学年信息列表
     */
    @Oplog(title = "学期学年信息", businessType = BusinessType.EXPORT)
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
        int count = labsSemesterinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsSemesterinfo> list = labsSemesterinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsSemesterinfo> util = new ExcelUtils<LabsSemesterinfo>(LabsSemesterinfo.class);
        return util.exportExcel(list, "LabsSemesterinfo");
    }

}
