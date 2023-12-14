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
import com.benet.labsys.domain.LabsSafetycerte;
import com.benet.labsys.service.ILabsSafetycerteService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 安全证书Controller
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Api(value = "labsys/safetycerte", tags = "安全证书控制器")
@RestController
@RequestMapping("/labsys/safetycerte")
public class LabsSafetycerteController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsSafetycerteService labsSafetycerteService;
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
     * 查询安全证书列表
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
        int count = labsSafetycerteService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsSafetycerte> list = labsSafetycerteService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增安全证书
     */
    @Oplog(title = "安全证书", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsSafetycerte labsSafetycerte) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsSafetycerte.setCerteNo(UuidUtils.shortUUID());
        labsSafetycerte.setCreateBy(loginUser.getUserNo());
        labsSafetycerte.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsSafetycerteService.AddNewRecord(loginUser.getAppCode(),labsSafetycerte));
    }

    /**
     * 编辑安全证书
     */
    @Oplog(title = "安全证书", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsSafetycerte labsSafetycerte) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsSafetycerte.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsSafetycerteService.UpdateRecord(loginUser.getAppCode(),labsSafetycerte));
    }

    /**
     * 保存安全证书
     */
    @Oplog(title = "安全证书", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsSafetycerte labsSafetycerte) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsSafetycerteService.getRecordByNo(loginUser.getAppCode(),labsSafetycerte.getCerteNo()))) {
            labsSafetycerte.setCerteNo(UuidUtils.shortUUID());
            labsSafetycerte.setCreateBy(loginUser.getUserNo());
            labsSafetycerte.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsSafetycerteService.AddNewRecord(loginUser.getAppCode(),labsSafetycerte));
        } else {
            labsSafetycerte.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsSafetycerteService.UpdateRecord(loginUser.getAppCode(),labsSafetycerte));
        }
    }

    /**
     * 删除安全证书
     */
    @Oplog(title = "安全证书", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsSafetycerteService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsSafetycerteService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取安全证书详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsSafetycerte info= labsSafetycerteService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出安全证书列表
     */
    @Oplog(title = "安全证书", businessType = BusinessType.EXPORT)
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
        int count = labsSafetycerteService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsSafetycerte> list = labsSafetycerteService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsSafetycerte> util = new ExcelUtils<LabsSafetycerte>(LabsSafetycerte.class);
        return util.exportExcel(list, "LabsSafetycerte");
    }

}
