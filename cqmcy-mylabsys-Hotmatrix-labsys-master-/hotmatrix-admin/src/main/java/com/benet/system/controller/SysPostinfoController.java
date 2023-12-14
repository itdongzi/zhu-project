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
import com.benet.system.domain.SysPostinfo;
import com.benet.system.service.ISysPostinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 岗位信息Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@Api(value = "system/postinfo", tags = "岗位信息控制器")
@RestController
@RequestMapping("/system/postinfo")
public class SysPostinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysPostinfoService sysPostinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:postinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询岗位信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:postinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("postName")&&StringUtils.isNotEmpty(maps.get("postName").toString())) {
            condition += "And post_name like '%" + maps.get("postName")+"%'";
        }
        if(maps.containsKey("postCode")&&StringUtils.isNotEmpty(maps.get("postCode").toString())) {
            condition += "And post_code like '%" + maps.get("postCode")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysPostinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysPostinfo> list = sysPostinfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增岗位信息
     */
    @PreAuthorize("@ps.hasPermit('system:postinfo:addnew')")
    @Oplog(title = "岗位信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysPostinfo sysPostinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysPostinfo.setPostNo(UuidUtils.shortUUID());
        sysPostinfo.setCreateBy(loginUser.getUserNo());
        sysPostinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysPostinfoService.AddNewRecord(loginUser.getAppCode(),sysPostinfo));
    }

    /**
     * 编辑岗位信息
     */
    @PreAuthorize("@ps.hasPermit('system:postinfo:update')")
    @Oplog(title = "岗位信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysPostinfo sysPostinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysPostinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysPostinfoService.UpdateRecord(loginUser.getAppCode(),sysPostinfo));
        }

    /**
     * 保存岗位信息
     */
    @PreAuthorize("@ps.hasPermit('system:postinfo:save')")
    @Oplog(title = "岗位信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysPostinfo sysPostinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysPostinfoService.getRecordByNo(loginUser.getAppCode(),sysPostinfo.getPostNo()))) {
            sysPostinfo.setPostNo(UuidUtils.shortUUID());
            sysPostinfo.setCreateBy(loginUser.getUserNo());
            sysPostinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysPostinfoService.AddNewRecord(loginUser.getAppCode(),sysPostinfo));
        } else {
            sysPostinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysPostinfoService.UpdateRecord(loginUser.getAppCode(),sysPostinfo));
        }
    }

    /**
     * 删除岗位信息
     */
    @PreAuthorize("@ps.hasPermit('system:postinfo:delete')")
    @Oplog(title = "岗位信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysPostinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取岗位信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:postinfo:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysPostinfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取岗位信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:postinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysPostinfo info=sysPostinfoService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出岗位信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:postinfo:export')")
    @Oplog(title = "岗位信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("postName")&&StringUtils.isNotEmpty(maps.get("postName").toString())) {
            condition += "And post_name like '%" + maps.get("postName")+"%'";
        }
        if(maps.containsKey("postCode")&&StringUtils.isNotEmpty(maps.get("postCode").toString())) {
            condition += "And post_code like '%" + maps.get("postCode")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysPostinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysPostinfo> list = sysPostinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysPostinfo> util = new ExcelUtils<SysPostinfo>(SysPostinfo.class);
        return util.exportExcel(list, "SysPostinfo");
    }

}
