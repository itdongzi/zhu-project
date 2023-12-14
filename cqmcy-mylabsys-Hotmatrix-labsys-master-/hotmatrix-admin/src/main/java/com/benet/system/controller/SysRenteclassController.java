package com.benet.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.domain.SysConteeclass;
import com.benet.system.vmodel.ItemObjectVo;
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
import com.benet.system.domain.SysRenteclass;
import com.benet.system.service.ISysRenteclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 租户类型Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@Api(value = "system/renteclass", tags = "租户类型控制器")
@RestController
@RequestMapping("/system/renteclass")
public class SysRenteclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysRenteclassService sysRenteclassService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:renteclass:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询租户类型列表
     */
    @PreAuthorize("@ps.hasPermit('system:renteclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("className")&&StringUtils.isNotEmpty(maps.get("className").toString())) {
            condition += "And class_name like '%" + maps.get("className")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysRenteclassService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysRenteclass> list = sysRenteclassService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:renteclass:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysRenteclassService.getCountByCondition(loginUser.getAppCode(), "");
        List<ItemObjectVo> list = buildItemTree(loginUser.getAppCode(), "0");
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode, String parentNo) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<SysRenteclass> infoList = sysRenteclassService.getRecordsByClassNo(appCode,parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (SysRenteclass info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getClassNo());
                item.setKey(info.getClassNo());
                item.setTitle(info.getClassName());
                item.setValue(info.getClassNo());
                item.setChildren(buildItemTree(appCode,info.getClassNo()));

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增租户类型
     */
    @PreAuthorize("@ps.hasPermit('system:renteclass:addnew')")
    @Oplog(title = "租户类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysRenteclass sysAppclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysAppclass.setClassNo(UuidUtils.shortUUID());
        sysAppclass.setCreateBy(loginUser.getUserNo());
        sysAppclass.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysRenteclassService.AddNewRecord(loginUser.getAppCode(),sysAppclass));
    }

    /**
     * 编辑租户类型
     */
    @PreAuthorize("@ps.hasPermit('system:renteclass:update')")
    @Oplog(title = "租户类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysRenteclass sysAppclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysAppclass.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysRenteclassService.UpdateRecord(loginUser.getAppCode(),sysAppclass));
    }

    /**
     * 保存租户类型
     */
    @PreAuthorize("@ps.hasPermit('system:renteclass:save')")
    @Oplog(title = "租户类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysRenteclass sysAppclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysRenteclassService.getRecordByNo(loginUser.getAppCode(),sysAppclass.getClassNo()))) {
            sysAppclass.setClassNo(UuidUtils.shortUUID());
            sysAppclass.setCreateBy(loginUser.getUserNo());
            sysAppclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysRenteclassService.AddNewRecord(loginUser.getAppCode(),sysAppclass));
        } else {
            sysAppclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysRenteclassService.UpdateRecord(loginUser.getAppCode(),sysAppclass));
        }
    }

    /**
     * 删除租户类型
     */
    @PreAuthorize("@ps.hasPermit('system:renteclass:delete')")
    @Oplog(title = "租户类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysRenteclassService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取租户类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:renteclass:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysRenteclassService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取租户类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:renteclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysRenteclass info=sysRenteclassService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出租户类型列表
     */
    @PreAuthorize("@ps.hasPermit('system:renteclass:export')")
    @Oplog(title = "租户类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("className")&&StringUtils.isNotEmpty(maps.get("className").toString())) {
            condition += "And class_name like '%" + maps.get("className")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysRenteclassService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysRenteclass> list = sysRenteclassService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysRenteclass> util = new ExcelUtils<SysRenteclass>(SysRenteclass.class);
        return util.exportExcel(list, "SysRenteclass");
    }

}
