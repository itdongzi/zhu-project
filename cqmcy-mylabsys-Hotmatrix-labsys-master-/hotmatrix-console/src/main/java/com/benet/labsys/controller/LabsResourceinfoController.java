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
import com.benet.labsys.domain.LabsResourceinfo;
import com.benet.labsys.service.ILabsResourceinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 教学资源信息Controller
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Api(value = "labsys/resourceinfo", tags = "教学资源信息控制器")
@RestController
@RequestMapping("/labsys/resourceinfo")
public class LabsResourceinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsResourceinfoService labsResourceinfoService;
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
     * 查询教学资源信息列表
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
        int count = labsResourceinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsResourceinfo> list = labsResourceinfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增教学资源信息
     */
    @Oplog(title = "教学资源信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsResourceinfo labsResourceinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsResourceinfo.setRescNo(UuidUtils.shortUUID());
        labsResourceinfo.setCreateBy(loginUser.getUserNo());
        labsResourceinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsResourceinfoService.AddNewRecord(loginUser.getAppCode(),labsResourceinfo));
    }

    /**
     * 编辑教学资源信息
     */
    @Oplog(title = "教学资源信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsResourceinfo labsResourceinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsResourceinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsResourceinfoService.UpdateRecord(loginUser.getAppCode(),labsResourceinfo));
    }

    /**
     * 保存教学资源信息
     */
    @Oplog(title = "教学资源信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsResourceinfo labsResourceinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsResourceinfoService.getRecordByNo(loginUser.getAppCode(),labsResourceinfo.getRescNo()))) {
            labsResourceinfo.setRescNo(UuidUtils.shortUUID());
            labsResourceinfo.setCreateBy(loginUser.getUserNo());
            labsResourceinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsResourceinfoService.AddNewRecord(loginUser.getAppCode(),labsResourceinfo));
        } else {
            labsResourceinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsResourceinfoService.UpdateRecord(loginUser.getAppCode(),labsResourceinfo));
        }
    }

    /**
     * 删除教学资源信息
     */
    @Oplog(title = "教学资源信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsResourceinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsResourceinfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取教学资源信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsResourceinfo info= labsResourceinfoService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出教学资源信息列表
     */
    @Oplog(title = "教学资源信息", businessType = BusinessType.EXPORT)
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
        int count = labsResourceinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsResourceinfo> list = labsResourceinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsResourceinfo> util = new ExcelUtils<LabsResourceinfo>(LabsResourceinfo.class);
        return util.exportExcel(list, "LabsResourceinfo");
    }

}
