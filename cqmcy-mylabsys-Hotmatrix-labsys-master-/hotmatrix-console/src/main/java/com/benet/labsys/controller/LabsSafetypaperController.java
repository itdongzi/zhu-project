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
import com.benet.labsys.domain.LabsSafetypaper;
import com.benet.labsys.service.ILabsSafetypaperService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 安全试卷Controller
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Api(value = "labsys/safetypaper", tags = "安全试卷控制器")
@RestController
@RequestMapping("/labsys/safetypaper")
public class LabsSafetypaperController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsSafetypaperService labsSafetypaperService;
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
     * 查询安全试卷列表
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
        int count = labsSafetypaperService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsSafetypaper> list = labsSafetypaperService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增安全试卷
     */
    @Oplog(title = "安全试卷", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody LabsSafetypaper labsSafetypaper) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsSafetypaper.setPaperNo(UuidUtils.shortUUID());
        labsSafetypaper.setCreateBy(loginUser.getUserNo());
        labsSafetypaper.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsSafetypaperService.AddNewRecord(loginUser.getAppCode(),labsSafetypaper));
    }

    /**
     * 编辑安全试卷
     */
    @Oplog(title = "安全试卷", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsSafetypaper labsSafetypaper) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsSafetypaper.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsSafetypaperService.UpdateRecord(loginUser.getAppCode(),labsSafetypaper));
    }

    /**
     * 保存安全试卷
     */
    @Oplog(title = "安全试卷", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsSafetypaper labsSafetypaper) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsSafetypaperService.getRecordByNo(loginUser.getAppCode(),labsSafetypaper.getPaperNo()))) {
            labsSafetypaper.setPaperNo(UuidUtils.shortUUID());
            labsSafetypaper.setCreateBy(loginUser.getUserNo());
            labsSafetypaper.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsSafetypaperService.AddNewRecord(loginUser.getAppCode(),labsSafetypaper));
        } else {
            labsSafetypaper.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsSafetypaperService.UpdateRecord(loginUser.getAppCode(),labsSafetypaper));
        }
    }

    /**
     * 删除安全试卷
     */
    @Oplog(title = "安全试卷", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsSafetypaperService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsSafetypaperService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取安全试卷详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsSafetypaper info= labsSafetypaperService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出安全试卷列表
     */
    @Oplog(title = "安全试卷", businessType = BusinessType.EXPORT)
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
        int count = labsSafetypaperService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsSafetypaper> list = labsSafetypaperService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsSafetypaper> util = new ExcelUtils<LabsSafetypaper>(LabsSafetypaper.class);
        return util.exportExcel(list, "LabsSafetypaper");
    }

}
