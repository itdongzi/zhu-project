package com.benet.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.vmodel.DeptmentVo;
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
import com.benet.system.domain.SysRotaclass;
import com.benet.system.service.ISysRotaclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 值班类型Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "system/rotaclass", tags = "值班类型控制器")
@RestController
@RequestMapping("/system/rotaclass")
public class SysRotaclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysRotaclassService sysRotaclassService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:rotaclass:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询值班类型列表
     */
    @PreAuthorize("@ps.hasPermit('system:rotaclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("menuName")&&StringUtils.isNotEmpty(maps.get("menuName").toString())) {
            condition += "And menu_name like '%" + maps.get("menuName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysRotaclassService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysRotaclass> list = sysRotaclassService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('system:rotaclass:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysRotaclassService.getCountByCondition(loginUser.getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<SysRotaclass> infoList = sysRotaclassService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (SysRotaclass info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getClassNo());
                item.setKey(info.getClassNo());
                item.setTitle(info.getClassName());
                item.setValue(info.getClassNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增值班类型
     */
    @PreAuthorize("@ps.hasPermit('system:rotaclass:addnew')")
    @Oplog(title = "值班类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysRotaclass sysRotaclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysRotaclass.setClassNo(UuidUtils.shortUUID());
        sysRotaclass.setCreateBy(loginUser.getUserNo());
        sysRotaclass.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysRotaclassService.AddNewRecord(loginUser.getAppCode(),sysRotaclass));
    }

    /**
     * 编辑值班类型
     */
    @PreAuthorize("@ps.hasPermit('system:rotaclass:update')")
    @Oplog(title = "值班类型", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysRotaclass sysRotaclass) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysRotaclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysRotaclassService.UpdateRecord(loginUser.getAppCode(),sysRotaclass));
        }

    /**
     * 保存值班类型
     */
    @PreAuthorize("@ps.hasPermit('system:rotaclass:save')")
    @Oplog(title = "值班类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysRotaclass sysRotaclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysRotaclassService.getRecordByNo(loginUser.getAppCode(),sysRotaclass.getClassNo()))) {
            sysRotaclass.setClassNo(UuidUtils.shortUUID());
            sysRotaclass.setCreateBy(loginUser.getUserNo());
            sysRotaclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysRotaclassService.AddNewRecord(loginUser.getAppCode(),sysRotaclass));
        } else {
            sysRotaclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysRotaclassService.UpdateRecord(loginUser.getAppCode(),sysRotaclass));
        }
    }

    /**
     * 删除值班类型
     */
    @PreAuthorize("@ps.hasPermit('system:rotaclass:delete')")
    @Oplog(title = "值班类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysRotaclassService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取值班类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:rotaclass:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysRotaclassService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取值班类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:rotaclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysRotaclass info=sysRotaclassService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出值班类型列表
     */
    @PreAuthorize("@ps.hasPermit('system:rotaclass:export')")
    @Oplog(title = "值班类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("menuName")&&StringUtils.isNotEmpty(maps.get("menuName").toString())) {
            condition += "And menu_name like '%" + maps.get("menuName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysRotaclassService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysRotaclass> list = sysRotaclassService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysRotaclass> util = new ExcelUtils<SysRotaclass>(SysRotaclass.class);
        return util.exportExcel(list, "SysRotaclass");
    }

}
