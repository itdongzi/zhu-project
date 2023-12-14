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
import com.benet.labsys.domain.LabsWorkclass;
import com.benet.labsys.service.ILabsWorkclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 实验室检查类型Controller
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Api(value = "labsys/workclass", tags = "实验室检查类型控制器")
@RestController
@RequestMapping("/labsys/workclass")
public class LabsWorkclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsWorkclassService labsWorkclassService;
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
     * 查询实验室检查类型列表
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
        int count = labsWorkclassService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsWorkclass> list = labsWorkclassService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增实验室检查类型
     */
    @Oplog(title = "实验室检查类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsWorkclass labsWorkclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsWorkclass.setClassNo(UuidUtils.shortUUID());
        labsWorkclass.setCreateBy(loginUser.getUserNo());
        labsWorkclass.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsWorkclassService.AddNewRecord(loginUser.getAppCode(),labsWorkclass));
    }

    /**
     * 编辑实验室检查类型
     */
    @Oplog(title = "实验室检查类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsWorkclass labsWorkclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsWorkclass.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsWorkclassService.UpdateRecord(loginUser.getAppCode(),labsWorkclass));
    }

    /**
     * 保存实验室检查类型
     */
    @Oplog(title = "实验室检查类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsWorkclass labsWorkclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsWorkclassService.getRecordByNo(loginUser.getAppCode(),labsWorkclass.getClassNo()))) {
            labsWorkclass.setClassNo(UuidUtils.shortUUID());
            labsWorkclass.setCreateBy(loginUser.getUserNo());
            labsWorkclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsWorkclassService.AddNewRecord(loginUser.getAppCode(),labsWorkclass));
        } else {
            labsWorkclass.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsWorkclassService.UpdateRecord(loginUser.getAppCode(),labsWorkclass));
        }
    }

    /**
     * 删除实验室检查类型
     */
    @Oplog(title = "实验室检查类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsWorkclassService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsWorkclassService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取实验室检查类型详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsWorkclass info= labsWorkclassService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出实验室检查类型列表
     */
    @Oplog(title = "实验室检查类型", businessType = BusinessType.EXPORT)
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
        int count = labsWorkclassService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsWorkclass> list = labsWorkclassService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsWorkclass> util = new ExcelUtils<LabsWorkclass>(LabsWorkclass.class);
        return util.exportExcel(list, "LabsWorkclass");
    }

}
