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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.benet.system.domain.SysOperatelog;
import com.benet.system.service.ISysOperatelogService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 操作日志记录Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@Api(value = "system/operatelog", tags = "操作日志记录控制器")
@RestController
@RequestMapping("/system/operatelog")
public class SysOperatelogController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysOperatelogService sysOperatelogService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:operatelog:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询操作日志记录列表
     */
    @PreAuthorize("@ps.hasPermit('system:operatelog:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("oplogTitle")&&StringUtils.isNotEmpty(maps.get("oplogTitle").toString())) {
            condition += "And oplog_title like '%" + maps.get("oplogTitle")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysOperatelogService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysOperatelog> list = sysOperatelogService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增操作日志记录
     */
    @PreAuthorize("@ps.hasPermit('system:operatelog:addnew')")
    @Oplog(title = "操作日志记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysOperatelog sysOperatelogs) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysOperatelogs.setOplogNo(UuidUtils.shortUUID());
        sysOperatelogs.setCreateBy(loginUser.getUserNo());
        sysOperatelogs.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysOperatelogService.AddNewRecord(loginUser.getAppCode(),sysOperatelogs));
    }

    /**
     * 编辑操作日志记录
     */
    @PreAuthorize("@ps.hasPermit('system:operatelog:update')")
    @Oplog(title = "操作日志记录", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysOperatelog sysOperatelogs) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysOperatelogs.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysOperatelogService.UpdateRecord(loginUser.getAppCode(),sysOperatelogs));
        }

    /**
     * 保存操作日志记录
     */
    @PreAuthorize("@ps.hasPermit('system:operatelog:save')")
    @Oplog(title = "操作日志记录", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysOperatelog sysOperatelogs) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysOperatelogService.getRecordByNo(loginUser.getAppCode(),sysOperatelogs.getOplogNo()))) {
            sysOperatelogs.setOplogNo(UuidUtils.shortUUID());
            sysOperatelogs.setCreateBy(loginUser.getUserNo());
            sysOperatelogs.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysOperatelogService.AddNewRecord(loginUser.getAppCode(),sysOperatelogs));
        } else {
            sysOperatelogs.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysOperatelogService.UpdateRecord(loginUser.getAppCode(),sysOperatelogs));
        }
    }

    /**
     * 删除操作日志记录
     */
    @PreAuthorize("@ps.hasPermit('system:operatelog:delete')")
    @Oplog(title = "操作日志记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysOperatelogService.HardDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取操作日志记录详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:operatelog:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysOperatelogService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取操作日志记录详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:operatelog:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysOperatelogService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 导出操作日志记录列表
     */
    @PreAuthorize("@ps.hasPermit('system:operatelog:export')")
    @Oplog(title = "操作日志记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("oplogTitle")&&StringUtils.isNotEmpty(maps.get("oplogTitle").toString())) {
            condition += "And oplog_title like '%" + maps.get("oplogTitle")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysOperatelogService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysOperatelog> list = sysOperatelogService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysOperatelog> util = new ExcelUtils<SysOperatelog>(SysOperatelog.class);
        return util.exportExcel(list, "SysOperatelogs");
    }

}
