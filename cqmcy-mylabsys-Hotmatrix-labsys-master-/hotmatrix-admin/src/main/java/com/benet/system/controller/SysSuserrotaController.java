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
import com.benet.system.domain.SysRuserrota;
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
import com.benet.system.domain.SysSuserrota;
import com.benet.system.service.ISysSuserrotaService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 系统用户排班Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "system/suserrota", tags = "系统用户排班控制器")
@RestController
@RequestMapping("/system/suserrota")
public class SysSuserrotaController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysSuserrotaService sysSuserrotaService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:suserrota:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询系统用户排班列表
     */
    @PreAuthorize("@ps.hasPermit('system:suserrota:list')")
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
        int count = sysSuserrotaService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysSuserrota> list = sysSuserrotaService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增系统用户排班
     */
    @PreAuthorize("@ps.hasPermit('system:suserrota:addnew')")
    @Oplog(title = "系统用户排班", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysSuserrota sysSuserrota) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysSuserrota.setRotaNo(UuidUtils.shortUUID());
        sysSuserrota.setCreateBy(loginUser.getUserNo());
        sysSuserrota.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysSuserrotaService.AddNewRecord(loginUser.getAppCode(),sysSuserrota));
    }

    /**
     * 编辑系统用户排班
     */
    @PreAuthorize("@ps.hasPermit('system:suserrota:update')")
    @Oplog(title = "系统用户排班", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysSuserrota sysSuserrota) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysSuserrota.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysSuserrotaService.UpdateRecord(loginUser.getAppCode(),sysSuserrota));
        }

    /**
     * 保存系统用户排班
     */
    @PreAuthorize("@ps.hasPermit('system:suserrota:save')")
    @Oplog(title = "系统用户排班", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysSuserrota sysSuserrota) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysSuserrotaService.getRecordByNo(loginUser.getAppCode(),sysSuserrota.getRotaNo()))) {
            sysSuserrota.setRotaNo(UuidUtils.shortUUID());
            sysSuserrota.setCreateBy(loginUser.getUserNo());
            sysSuserrota.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysSuserrotaService.AddNewRecord(loginUser.getAppCode(),sysSuserrota));
        } else {
            sysSuserrota.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysSuserrotaService.UpdateRecord(loginUser.getAppCode(),sysSuserrota));
        }
    }

    /**
     * 删除系统用户排班
     */
    @PreAuthorize("@ps.hasPermit('system:suserrota:delete')")
    @Oplog(title = "系统用户排班", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysSuserrotaService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取系统用户排班详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:suserrota:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysSuserrotaService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取系统用户排班详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:suserrota:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysSuserrota info=sysSuserrotaService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出系统用户排班列表
     */
    @PreAuthorize("@ps.hasPermit('system:suserrota:export')")
    @Oplog(title = "系统用户排班", businessType = BusinessType.EXPORT)
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
        int count = sysSuserrotaService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysSuserrota> list = sysSuserrotaService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysSuserrota> util = new ExcelUtils<SysSuserrota>(SysSuserrota.class);
        return util.exportExcel(list, "SysSuserrota");
    }

}
