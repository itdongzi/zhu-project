package com.benet.labsys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.labsys.domain.LabsMeetinfo;
import com.benet.labsys.service.ILabsMeetinfoService;
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
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 会议室信息Controller
 * 
 * @author yoxking
 * @date 2023-02-24 09:46:53
 */
@Api(value = "labsys/meetinfo", tags = "会议室信息控制器")
@RestController
@RequestMapping("/labsys/meetinfo")
public class LabsMeetinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsMeetinfoService labsMeetinfoService;
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
     * 查询实验室类型列表
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
        int count = labsMeetinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsMeetinfo> list = labsMeetinfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增实验室类型
     */
    @Oplog(title = "实验室类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsMeetinfo labsMeetinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsMeetinfo.setMeetNo(UuidUtils.shortUUID());
        labsMeetinfo.setCreateBy(loginUser.getUserNo());
        labsMeetinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsMeetinfoService.AddNewRecord(loginUser.getAppCode(),labsMeetinfo));
    }

    /**
     * 编辑实验室类型
     */
    @Oplog(title = "实验室类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsMeetinfo labsMeetinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsMeetinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsMeetinfoService.UpdateRecord(loginUser.getAppCode(),labsMeetinfo));
    }

    /**
     * 保存实验室类型
     */
    @Oplog(title = "实验室类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsMeetinfo labsMeetinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsMeetinfoService.getRecordByNo(loginUser.getAppCode(),labsMeetinfo.getMeetNo()))) {
            labsMeetinfo.setMeetNo(UuidUtils.shortUUID());
            labsMeetinfo.setCreateBy(loginUser.getUserNo());
            labsMeetinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsMeetinfoService.AddNewRecord(loginUser.getAppCode(),labsMeetinfo));
        } else {
            labsMeetinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsMeetinfoService.UpdateRecord(loginUser.getAppCode(),labsMeetinfo));
        }
    }

    /**
     * 删除实验室类型
     */
    @Oplog(title = "实验室类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsMeetinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsMeetinfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取实验室类型详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsMeetinfo info= labsMeetinfoService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出实验室类型列表
     */
    @Oplog(title = "实验室类型", businessType = BusinessType.EXPORT)
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
        int count = labsMeetinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsMeetinfo> list = labsMeetinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsMeetinfo> util = new ExcelUtils<LabsMeetinfo>(LabsMeetinfo.class);
        return util.exportExcel(list, "LabsMeetinfo");
    }

}
