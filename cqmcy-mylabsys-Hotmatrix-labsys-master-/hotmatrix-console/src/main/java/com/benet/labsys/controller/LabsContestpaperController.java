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
import com.benet.labsys.domain.LabsContestpaper;
import com.benet.labsys.service.ILabsContestpaperService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 竞赛试题信息Controller
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
@Api(value = "labsys/contestpaper", tags = "竞赛试题信息控制器")
@RestController
@RequestMapping("/labsys/contestpaper")
public class LabsContestpaperController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ILabsContestpaperService labsContestpaperService;
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
     * 查询竞赛试题信息列表
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
        int count = labsContestpaperService.getCountByCondition(loginUser.getAppCode(),condition);
        List<LabsContestpaper> list = labsContestpaperService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增竞赛试题信息
     */
    @PostMapping
    public AjaxResult insert(@RequestBody LabsContestpaper labsContestpaper) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsContestpaper.setPaperNo(UuidUtils.shortUUID());
        labsContestpaper.setCreateBy(loginUser.getUserNo());
        labsContestpaper.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsContestpaperService.AddNewRecord(loginUser.getAppCode(),labsContestpaper));
    }

    /**
     * 编辑竞赛试题信息
     */
    @Oplog(title = "竞赛试题信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody LabsContestpaper labsContestpaper) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        labsContestpaper.setUpdateBy(loginUser.getUserNo());
        return toAjax(labsContestpaperService.UpdateRecord(loginUser.getAppCode(),labsContestpaper));
    }

    /**
     * 保存竞赛试题信息
     */
    @Oplog(title = "竞赛试题信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody LabsContestpaper labsContestpaper) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(labsContestpaperService.getRecordByNo(loginUser.getAppCode(),labsContestpaper.getPaperNo()))) {
            labsContestpaper.setPaperNo(UuidUtils.shortUUID());
            labsContestpaper.setCreateBy(loginUser.getUserNo());
            labsContestpaper.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsContestpaperService.AddNewRecord(loginUser.getAppCode(),labsContestpaper));
        } else {
            labsContestpaper.setUpdateBy(loginUser.getUserNo());
            return toAjax(labsContestpaperService.UpdateRecord(loginUser.getAppCode(),labsContestpaper));
        }
    }

    /**
     * 删除竞赛试题信息
     */
    @Oplog(title = "竞赛试题信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(labsContestpaperService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(labsContestpaperService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取竞赛试题信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        LabsContestpaper info= labsContestpaperService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出竞赛试题信息列表
     */
    @Oplog(title = "竞赛试题信息", businessType = BusinessType.EXPORT)
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
        int count = labsContestpaperService.getCountByCondition(loginUser.getAppCode(),condition);

        List<LabsContestpaper> list = labsContestpaperService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<LabsContestpaper> util = new ExcelUtils<LabsContestpaper>(LabsContestpaper.class);
        return util.exportExcel(list, "LabsContestpaper");
    }

}
