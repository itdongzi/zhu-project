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
import lombok.extern.slf4j.Slf4j;
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
import com.benet.labsys.domain.LabsResourcefiles;
import com.benet.labsys.service.ILabsResourcefilesService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 教学资源文件Controller
 * 
 * @author yoxking
 * @date 2022-08-05
 */
@Api(value = "labsys/resourcefiles", tags = "教学资源文件控制器")
@RestController
@RequestMapping("/labsys/resourcefiles")
@Slf4j
public class LabsResourcefilesController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsResourcefilesService labsResourcefilesService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('labsys:resourcefiles:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询教学资源文件列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:resourcefiles:list')")
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
        int count = labsResourcefilesService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsResourcefiles> list = labsResourcefilesService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增教学资源文件
     */
    @PreAuthorize("@ps.hasPermit('labsys:resourcefiles:addnew')")
    @Oplog(title = "教学资源文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsResourcefiles labsResourcefiles) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsResourcefiles.setFileNo(UuidUtils.shortUUID());
        labsResourcefiles.setCreateBy(loginUser.getUserNo());
        labsResourcefiles.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsResourcefilesService.AddNewRecord(loginUser.getAppCode(),labsResourcefiles));
    }

    /**
     * 编辑教学资源文件
     */
    @PreAuthorize("@ps.hasPermit('labsys:resourcefiles:update')")
    @Oplog(title = "教学资源文件", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody LabsResourcefiles labsResourcefiles) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsResourcefiles.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsResourcefilesService.UpdateRecord(loginUser.getAppCode(),labsResourcefiles));
        }

    /**
     * 保存教学资源文件
     */
    @PreAuthorize("@ps.hasPermit('labsys:resourcefiles:save')")
    @Oplog(title = "教学资源文件", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsResourcefiles labsResourcefiles) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsResourcefilesService.getRecordByNo(loginUser.getAppCode(),labsResourcefiles.getFileNo()))) {
            labsResourcefiles.setFileNo(UuidUtils.shortUUID());
            labsResourcefiles.setCreateBy(loginUser.getUserNo());
            labsResourcefiles.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsResourcefilesService.AddNewRecord(loginUser.getAppCode(),labsResourcefiles));
        } else {
            labsResourcefiles.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsResourcefilesService.UpdateRecord(loginUser.getAppCode(),labsResourcefiles));
        }
    }

    /**
     * 删除教学资源文件
     */
    @PreAuthorize("@ps.hasPermit('labsys:resourcefiles:delete')")
    @Oplog(title = "教学资源文件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        log.info("删除教学资源文件");
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsResourcefilesService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取教学资源文件详细信息
     */
    @PreAuthorize("@ps.hasPermit('labsys:resourcefiles:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsResourcefilesService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 导出教学资源文件列表
     */
    @PreAuthorize("@ps.hasPermit('labsys:resourcefiles:export')")
    @Oplog(title = "教学资源文件", businessType = BusinessType.EXPORT)
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
        int count = labsResourcefilesService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsResourcefiles> list = labsResourcefilesService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsResourcefiles> util = new ExcelUtils<LabsResourcefiles>(LabsResourcefiles.class);
        return util.exportExcel(list, "LabsResourcefiles");
    }

}
