package com.benet.labsys.controller;

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
import com.benet.labsys.domain.LabsRoomorders;
import com.benet.labsys.service.ILabsRoomordersService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 实验室预约Controller
 * 
 * @author yoxking
 * @date 2022-08-05
 */
@Api(value = "labsys/roomorders", tags = "实验室预约控制器")
@RestController
@RequestMapping("/labsys/roomorders")
public class LabsRoomordersController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsRoomordersService labsRoomordersService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomorders:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询实验室预约列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomorders:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("roomNo")) {
            if (!StringUtils.isEmpty((CharSequence) maps.get("roomNo"))){
                condition += " room_no=" + "'" + maps.get("roomNo") + "'";
            }
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = labsRoomordersService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsRoomorders> list = labsRoomordersService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增实验室预约
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomorders:addnew')")
    @Oplog(title = "实验室预约", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsRoomorders labsRoomorders) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsRoomorders.setOrderNo(UuidUtils.shortUUID());
        labsRoomorders.setCreateBy(loginUser.getUserNo());
        labsRoomorders.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsRoomordersService.AddNewRecord(loginUser.getAppCode(),labsRoomorders));
    }

    /**
     * 编辑实验室预约
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomorders:update')")
    @Oplog(title = "实验室预约", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody LabsRoomorders labsRoomorders) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsRoomorders.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsRoomordersService.UpdateRecord(loginUser.getAppCode(),labsRoomorders));
        }

    /**
     * 保存实验室预约
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomorders:save')")
    @Oplog(title = "实验室预约", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsRoomorders labsRoomorders) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsRoomordersService.getRecordByNo(loginUser.getAppCode(),labsRoomorders.getOrderNo()))) {
            labsRoomorders.setOrderNo(UuidUtils.shortUUID());
            labsRoomorders.setCreateBy(loginUser.getUserNo());
            labsRoomorders.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsRoomordersService.AddNewRecord(loginUser.getAppCode(),labsRoomorders));
        } else {
            labsRoomorders.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsRoomordersService.UpdateRecord(loginUser.getAppCode(),labsRoomorders));
        }
    }

    /**
     * 删除实验室预约
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomorders:delete')")
    @Oplog(title = "实验室预约", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsRoomordersService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取实验室预约详细信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomorders:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsRoomordersService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 导出实验室预约列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:roomorders:export')")
    @Oplog(title = "实验室预约", businessType = BusinessType.EXPORT)
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
        int count = labsRoomordersService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsRoomorders> list = labsRoomordersService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsRoomorders> util = new ExcelUtils<LabsRoomorders>(LabsRoomorders.class);
        return util.exportExcel(list, "LabsRoomorders");
    }

}
