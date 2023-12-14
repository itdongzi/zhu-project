package com.benet.task.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.task.domain.SysTaskelog;
import com.benet.task.service.ISysTaskelogService;
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
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 定时任务调度日志Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@Api(value = "task/taskelog", tags = "定时任务调度日志控制器")
@RestController
@RequestMapping("/task/taskelog")
public class SysTaskelogController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysTaskelogService sysTaskelogService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:taskelog:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询定时任务调度日志列表
     */
    @PreAuthorize("@ps.hasPermit('system:taskelog:list')")
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
        int count = sysTaskelogService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysTaskelog> list = sysTaskelogService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增定时任务调度日志
     */
    @PreAuthorize("@ps.hasPermit('system:taskelog:addnew')")
    @Oplog(title = "定时任务调度日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysTaskelog sysTasklogs) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysTasklogs.setTaskLogno(UuidUtils.shortUUID());
        sysTasklogs.setCreateBy(loginUser.getUserNo());
        sysTasklogs.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysTaskelogService.AddNewRecord(loginUser.getAppCode(),sysTasklogs));
    }

    /**
     * 编辑定时任务调度日志
     */
    @PreAuthorize("@ps.hasPermit('system:taskelog:update')")
    @Oplog(title = "定时任务调度日志", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysTaskelog sysTasklogs) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysTasklogs.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysTaskelogService.UpdateRecord(loginUser.getAppCode(),sysTasklogs));
        }

    /**
     * 保存定时任务调度日志
     */
    @PreAuthorize("@ps.hasPermit('system:taskelog:save')")
    @Oplog(title = "定时任务调度日志", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysTaskelog sysTasklogs) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysTaskelogService.getRecordByNo(loginUser.getAppCode(),sysTasklogs.getTaskLogno()))) {
            sysTasklogs.setTaskLogno(UuidUtils.shortUUID());
            sysTasklogs.setCreateBy(loginUser.getUserNo());
            sysTasklogs.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysTaskelogService.AddNewRecord(loginUser.getAppCode(),sysTasklogs));
        } else {
            sysTasklogs.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysTaskelogService.UpdateRecord(loginUser.getAppCode(),sysTasklogs));
        }
    }

    /**
     * 删除定时任务调度日志
     */
    @PreAuthorize("@ps.hasPermit('system:taskelog:delete')")
    @Oplog(title = "定时任务调度日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysTaskelogService.HardDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取定时任务调度日志详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:taskelog:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysTaskelogService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 导出定时任务调度日志列表
     */
    @PreAuthorize("@ps.hasPermit('system:taskelog:export')")
    @Oplog(title = "定时任务调度日志", businessType = BusinessType.EXPORT)
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
        int count = sysTaskelogService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysTaskelog> list = sysTaskelogService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysTaskelog> util = new ExcelUtils<SysTaskelog>(SysTaskelog.class);
        return util.exportExcel(list, "SysTasklogs");
    }

}
