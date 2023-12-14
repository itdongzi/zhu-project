package com.benet.system.controller;

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
import com.benet.system.domain.SysRuserrota;
import com.benet.system.service.ISysRuserrotaService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 注册用户排班Controller
 * 
 * @author yoxking
 * @date 2020-10-27
 */
@Api(value = "system/ruserrota", tags = "注册用户排班控制器")
@RestController
@RequestMapping("/system/ruserrota")
public class SysRuserrotaController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysRuserrotaService sysRuserrotaService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:ruserrota:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询注册用户排班列表
     */
    @PreAuthorize("@ps.hasPermit('system:ruserrota:list')")
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
        int count = sysRuserrotaService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysRuserrota> list = sysRuserrotaService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增注册用户排班
     */
    @PreAuthorize("@ps.hasPermit('system:ruserrota:addnew')")
    @Oplog(title = "注册用户排班", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysRuserrota sysRuserrota) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysRuserrota.setRotaNo(UuidUtils.shortUUID());
        sysRuserrota.setCreateBy(loginUser.getUserNo());
        sysRuserrota.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysRuserrotaService.AddNewRecord(loginUser.getAppCode(),sysRuserrota));
    }

    /**
     * 编辑注册用户排班
     */
    @PreAuthorize("@ps.hasPermit('system:ruserrota:update')")
    @Oplog(title = "注册用户排班", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysRuserrota sysRuserrota) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysRuserrota.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysRuserrotaService.UpdateRecord(loginUser.getAppCode(),sysRuserrota));
        }

    /**
     * 保存注册用户排班
     */
    @PreAuthorize("@ps.hasPermit('system:ruserrota:save')")
    @Oplog(title = "注册用户排班", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysRuserrota sysRuserrota) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysRuserrotaService.getRecordByNo(loginUser.getAppCode(),sysRuserrota.getRotaNo()))) {
            sysRuserrota.setRotaNo(UuidUtils.shortUUID());
            sysRuserrota.setCreateBy(loginUser.getUserNo());
            sysRuserrota.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysRuserrotaService.AddNewRecord(loginUser.getAppCode(),sysRuserrota));
        } else {
            sysRuserrota.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysRuserrotaService.UpdateRecord(loginUser.getAppCode(),sysRuserrota));
        }
    }

    /**
     * 删除注册用户排班
     */
    @PreAuthorize("@ps.hasPermit('system:ruserrota:delete')")
    @Oplog(title = "注册用户排班", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysRuserrotaService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取注册用户排班详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:ruserrota:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysRuserrotaService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取注册用户排班详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:ruserrota:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysRuserrota info=sysRuserrotaService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出注册用户排班列表
     */
    @PreAuthorize("@ps.hasPermit('system:ruserrota:export')")
    @Oplog(title = "注册用户排班", businessType = BusinessType.EXPORT)
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
        int count = sysRuserrotaService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysRuserrota> list = sysRuserrotaService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysRuserrota> util = new ExcelUtils<SysRuserrota>(SysRuserrota.class);
        return util.exportExcel(list, "SysRuserrota");
    }

}
