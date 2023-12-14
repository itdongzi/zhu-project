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
import com.benet.labsys.domain.LabsAchieveinfo;
import com.benet.labsys.service.ILabsAchieveinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 实训成果Controller
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
@Api(value = "labsys/achieveinfo", tags = "实训成果控制器")
@RestController
@RequestMapping("/labsys/achieveinfo")
public class LabsAchieveinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsAchieveinfoService labsAchieveinfoService;
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
     * 查询实训成果列表
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
        int count = labsAchieveinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsAchieveinfo> list = labsAchieveinfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增实训成果
     */
    @Oplog(title = "实训成果", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsAchieveinfo labsAchieveinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsAchieveinfo.setAchieNo(UuidUtils.shortUUID());
        labsAchieveinfo.setCreateBy(loginUser.getUserNo());
        labsAchieveinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsAchieveinfoService.AddNewRecord(loginUser.getAppCode(),labsAchieveinfo));
    }

    /**
     * 编辑实训成果
     */
    @Oplog(title = "实训成果", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsAchieveinfo labsAchieveinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsAchieveinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsAchieveinfoService.UpdateRecord(loginUser.getAppCode(),labsAchieveinfo));
    }

    /**
     * 保存实训成果
     */
    @Oplog(title = "实训成果", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsAchieveinfo labsAchieveinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsAchieveinfoService.getRecordByNo(loginUser.getAppCode(),labsAchieveinfo.getAchieNo()))) {
            labsAchieveinfo.setAchieNo(UuidUtils.shortUUID());
            labsAchieveinfo.setCreateBy(loginUser.getUserNo());
            labsAchieveinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsAchieveinfoService.AddNewRecord(loginUser.getAppCode(),labsAchieveinfo));
        } else {
            labsAchieveinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsAchieveinfoService.UpdateRecord(loginUser.getAppCode(),labsAchieveinfo));
        }
    }

    /**
     * 删除实训成果
     */
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsAchieveinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsAchieveinfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取实训成果详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsAchieveinfo info= labsAchieveinfoService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出实训成果列表
     */
    @Oplog(title = "实训成果", businessType = BusinessType.EXPORT)
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
        int count = labsAchieveinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsAchieveinfo> list = labsAchieveinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsAchieveinfo> util = new ExcelUtils<LabsAchieveinfo>(LabsAchieveinfo.class);
        return util.exportExcel(list, "LabsAchieveinfo");
    }

}
