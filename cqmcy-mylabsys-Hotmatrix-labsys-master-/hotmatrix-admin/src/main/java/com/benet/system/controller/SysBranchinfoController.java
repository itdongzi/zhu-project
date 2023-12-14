package com.benet.system.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.benet.common.core.pager.PageRequest;
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
import com.benet.system.domain.SysBranchinfo;
import com.benet.system.service.ISysBranchinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 分支信息Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@Api(value = "system/branchinfo", tags = "分支信息控制器")
@RestController
@RequestMapping("/system/branchinfo")
public class SysBranchinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysBranchinfoService sysBranchinfoService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:branchinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询分支信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:branchinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("branchName")&&StringUtils.isNotEmpty(maps.get("branchName").toString())) {
            condition += "And branch_name like '%" + maps.get("branchName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysBranchinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysBranchinfo> list = sysBranchinfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增分支信息
     */
    @PreAuthorize("@ps.hasPermit('system:branchinfo:addnew')")
    @Oplog(title = "分支信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysBranchinfo sysBranchinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysBranchinfo.setBranchNo(UuidUtils.shortUUID());
        sysBranchinfo.setCreateBy(loginUser.getUserNo());
        sysBranchinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysBranchinfoService.AddNewRecord(loginUser.getAppCode(),sysBranchinfo));
    }

    /**
     * 编辑分支信息
     */
    @PreAuthorize("@ps.hasPermit('system:branchinfo:update')")
    @Oplog(title = "分支信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysBranchinfo sysBranchinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysBranchinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysBranchinfoService.UpdateRecord(loginUser.getAppCode(),sysBranchinfo));
    }

    /**
     * 保存分支信息
     */
    @PreAuthorize("@ps.hasPermit('system:branchinfo:save')")
    @Oplog(title = "分支信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysBranchinfo sysBranchinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysBranchinfoService.getRecordByNo(loginUser.getAppCode(),sysBranchinfo.getBranchNo()))) {
            sysBranchinfo.setBranchNo(UuidUtils.shortUUID());
            sysBranchinfo.setCreateBy(loginUser.getUserNo());
            sysBranchinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysBranchinfoService.AddNewRecord(loginUser.getAppCode(),sysBranchinfo));
        } else {
            sysBranchinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysBranchinfoService.UpdateRecord(loginUser.getAppCode(),sysBranchinfo));
        }
    }

    /**
     * 删除分支信息
     */
    @PreAuthorize("@ps.hasPermit('system:branchinfo:delete')")
    @Oplog(title = "分支信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysBranchinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取分支信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:branchinfo:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysBranchinfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取分支信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:branchinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysBranchinfo info = sysBranchinfoService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出分支信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:branchinfo:export')")
    @Oplog(title = "分支信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("branchName")&&StringUtils.isNotEmpty(maps.get("branchName").toString())) {
            condition += "And branch_name like '%" + maps.get("branchName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysBranchinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysBranchinfo> list = sysBranchinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysBranchinfo> util = new ExcelUtils<SysBranchinfo>(SysBranchinfo.class);
        return util.exportExcel(list, "SysBranchinfo");
    }

}
