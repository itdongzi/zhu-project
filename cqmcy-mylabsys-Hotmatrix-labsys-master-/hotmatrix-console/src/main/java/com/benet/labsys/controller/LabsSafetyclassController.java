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
import com.benet.labsys.domain.LabsSafetyclass;
import com.benet.labsys.service.ILabsSafetyclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 安全资料分类Controller
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Api(value = "labsys/safetyclass", tags = "安全资料分类控制器")
@RestController
@RequestMapping("/labsys/safetyclass")
public class LabsSafetyclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsSafetyclassService labsSafetyclassService;
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
     * 查询安全资料分类列表
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
        int count = labsSafetyclassService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsSafetyclass> list = labsSafetyclassService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增安全资料分类
     */
    @Oplog(title = "安全资料分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsSafetyclass labsSafetyclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsSafetyclass.setClassNo(UuidUtils.shortUUID());
        labsSafetyclass.setCreateBy(loginUser.getUserNo());
        labsSafetyclass.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsSafetyclassService.AddNewRecord(loginUser.getAppCode(),labsSafetyclass));
    }

    /**
     * 编辑安全资料分类
     */
    @Oplog(title = "安全资料分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsSafetyclass labsSafetyclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsSafetyclass.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsSafetyclassService.UpdateRecord(loginUser.getAppCode(),labsSafetyclass));
    }

    /**
     * 保存安全资料分类
     */
    @Oplog(title = "安全资料分类", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsSafetyclass labsSafetyclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsSafetyclassService.getRecordByNo(loginUser.getAppCode(),labsSafetyclass.getClassNo()))) {
            labsSafetyclass.setClassNo(UuidUtils.shortUUID());
            labsSafetyclass.setCreateBy(loginUser.getUserNo());
            labsSafetyclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsSafetyclassService.AddNewRecord(loginUser.getAppCode(),labsSafetyclass));
        } else {
            labsSafetyclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsSafetyclassService.UpdateRecord(loginUser.getAppCode(),labsSafetyclass));
        }
    }

    /**
     * 删除安全资料分类
     */
    @Oplog(title = "安全资料分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsSafetyclassService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsSafetyclassService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取安全资料分类详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsSafetyclass info= labsSafetyclassService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出安全资料分类列表
     */
    @Oplog(title = "安全资料分类", businessType = BusinessType.EXPORT)
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
        int count = labsSafetyclassService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsSafetyclass> list = labsSafetyclassService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsSafetyclass> util = new ExcelUtils<LabsSafetyclass>(LabsSafetyclass.class);
        return util.exportExcel(list, "LabsSafetyclass");
    }

}
